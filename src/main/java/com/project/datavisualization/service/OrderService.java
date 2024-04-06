package com.project.datavisualization.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.datavisualization.exception.InvalidCouponException;
import com.project.datavisualization.exception.InvalidOrderIdException;
import com.project.datavisualization.exception.InvalidQuantityException;
import com.project.datavisualization.exception.NoResponseFromPaymentServerException;
import com.project.datavisualization.exception.OrderAlreadyPaidException;
import com.project.datavisualization.exception.OrderNotFoundException;
import com.project.datavisualization.exception.PaymentFailedException;
import com.project.datavisualization.model.Coupon;
import com.project.datavisualization.model.PurchaseOrder;
import com.project.datavisualization.model.Product;
import com.project.datavisualization.model.Transaction;
import com.project.datavisualization.model.Customer;
import com.project.datavisualization.repository.CouponRepository;
import com.project.datavisualization.repository.PurchaseOrderRepository;
import com.project.datavisualization.repository.ProductRepository;
import com.project.datavisualization.repository.TransactionRepository;
import com.project.datavisualization.repository.CustomerRepository;

@Service
public class OrderService {

    @Autowired
    private CustomerRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private PurchaseOrderRepository orderRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public PurchaseOrder placeOrder(Long userId, int qty, String couponCode) throws InvalidQuantityException, InvalidCouponException {
        Optional<Customer> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new InvalidOrderIdException("Invalid user ID: " + userId);
        }

        Optional<Product> productOptional = productRepository.findById(1L); // Assuming there's only one product
        if (productOptional.isEmpty()) {
            throw new InvalidOrderIdException("Product not found");
        }
        Product product = productOptional.get();

        if (qty < 1 || qty > product.getAvailableQuantity()) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        double totalPrice = qty * product.getPrice();
        int discountPercentage = 0;

        if (couponCode != null && !couponCode.isEmpty()) {
            Optional<Coupon> couponOptional = couponRepository.findByCode(couponCode);
            if (couponOptional.isPresent()) {
                Coupon coupon = couponOptional.get();
                if (!userOptional.get().getCoupons().contains(coupon)) {
                    discountPercentage = coupon.getDiscountPercentage();
                    userOptional.get().getCoupons().add(coupon); // Corrected line
                    userRepository.save(userOptional.get());
                } else {
                    throw new InvalidCouponException("Coupon already used");
                }
            } else {
                throw new InvalidCouponException("Invalid coupon code");
            }
        }

        double discountedPrice = totalPrice - (totalPrice * discountPercentage / 100);

        product.setOrdered(product.getOrdered() + qty);
        product.setAvailableQuantity(product.getAvailableQuantity() - qty);
        productRepository.save(product);

        PurchaseOrder order = new PurchaseOrder();
        order.setUserId(userId);
        order.setQuantity(qty);
        order.setAmount(discountedPrice);
        order.setCoupon(couponCode);
        order.setStatus("Pending"); // Assuming status starts as pending
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        return order;
    }

    public Transaction makePayment(Long userId, Long orderId, double amount) throws PaymentFailedException, InvalidOrderIdException, NoResponseFromPaymentServerException, OrderAlreadyPaidException {
        Optional<PurchaseOrder> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new InvalidOrderIdException("Invalid order ID: " + orderId);
        }
        PurchaseOrder order = orderOptional.get();

        if (!order.getStatus().equals("Pending")) {
            throw new OrderAlreadyPaidException("Order is already paid for");
        }

        // Mock payment status
        int randomStatusCode = ThreadLocalRandom.current().nextInt(1, 6);
        if (randomStatusCode == 1) {
            throw new PaymentFailedException("Payment Failed as amount is invalid");
        } else if (randomStatusCode == 2) {
            throw new PaymentFailedException("Payment Failed from bank");
        } else if (randomStatusCode == 3) {
            throw new NoResponseFromPaymentServerException("No response from payment server");
        }

        order.setStatus("Paid");
        orderRepository.save(order);

        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setOrderId(orderId);
        transaction.setTransactionId("tran" + UUID.randomUUID().toString().replaceAll("-", ""));
        transaction.setStatus("successful");
        transaction.setDescription("Payment successful");
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        return transaction;
    }

    public List<PurchaseOrder> getAllOrders(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Transaction> getOrderTransactions(Long userId, Long orderId) throws OrderNotFoundException {
        Optional<PurchaseOrder> orderOptional = orderRepository.findByIdAndUserId(orderId, userId);
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order not found for user ID: " + userId + " and order ID: " + orderId);
        }
        return transactionRepository.findAllByUserIdAndOrderId(userId, orderId);
    }
}
