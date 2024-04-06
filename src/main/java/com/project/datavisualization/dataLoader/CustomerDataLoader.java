package com.project.datavisualization.dataLoader;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.datavisualization.model.Coupon;
import com.project.datavisualization.model.Customer;
import com.project.datavisualization.repository.CouponRepository;
import com.project.datavisualization.repository.CustomerRepository;

import jakarta.annotation.PostConstruct;

@Component
public class CustomerDataLoader {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CouponRepository couponRepository;

    @PostConstruct
    public void init() {
        Customer customer1 = new Customer();
        customer1.setUsername("user1"); // Setting username for customer1
        customer1.setPassword("password1"); // Setting password for customer1 directly in plain text
        Set<Coupon> coupons1 = new HashSet<>();
        Optional<Coupon> coupon1Optional = couponRepository.findByCode("OFF5"); // Assuming there's a method to find coupons by code
        Optional<Coupon> coupon2Optional = couponRepository.findByCode("OFF10"); // Assuming there's a method to find coupons by code

        // Extracting Coupon object from Optional
        Coupon coupon1 = coupon1Optional.orElse(null);
        Coupon coupon2 = coupon2Optional.orElse(null);

        if (coupon1 != null) {
            coupons1.add(coupon1);
        }
        if (coupon2 != null) {
            coupons1.add(coupon2);
        }

        customer1.setCoupons(coupons1);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setUsername("user2"); // Setting username for customer2
        customer2.setPassword("password2"); // Setting password for customer2 directly in plain text
        Set<Coupon> coupons2 = new HashSet<>();
        Optional<Coupon> coupon3Optional = couponRepository.findByCode("OFF10"); // Assuming there's a method to find coupons by code

        // Extracting Coupon object from Optional
        Coupon coupon3 = coupon3Optional.orElse(null);

        if (coupon3 != null) {
            coupons2.add(coupon3);
        }

        customer2.setCoupons(coupons2);
        customerRepository.save(customer2);
    }
}