package com.project.datavisualization.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.datavisualization.exception.CustomerNotLoggedInException;
import com.project.datavisualization.model.Coupon;
import com.project.datavisualization.model.Customer;
import com.project.datavisualization.repository.CouponRepository;
import com.project.datavisualization.repository.CustomerRepository;

@Service
public class CouponService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CouponRepository couponRepository;

    public Map<String, Integer> fetchCouponsByUsername(String username) throws CustomerNotLoggedInException {
        Customer user = customerRepository.findByUsername(username);
        if (user == null) {
            throw new CustomerNotLoggedInException("Please log in first.");
        }

        Set<Coupon> coupons = user.getCoupons();
        Map<String, Integer> couponMap = new HashMap<>();
        for (Coupon coupon : coupons) {
            couponMap.put(coupon.getCode(), coupon.getDiscountPercentage());
        }
        return couponMap;
    }
}

