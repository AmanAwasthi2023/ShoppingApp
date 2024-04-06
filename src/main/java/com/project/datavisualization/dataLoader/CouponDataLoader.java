package com.project.datavisualization.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.datavisualization.model.Coupon;
import com.project.datavisualization.repository.CouponRepository;

import jakarta.annotation.PostConstruct;

@Component
public class CouponDataLoader {

    @Autowired
    private CouponRepository couponRepository;

    @PostConstruct
    public void init() {
        Coupon coupon1 = new Coupon();
        coupon1.setCode("OFF5");
        coupon1.setDiscountPercentage(5);
        couponRepository.save(coupon1);

        Coupon coupon2 = new Coupon();
        coupon2.setCode("OFF10");
        coupon2.setDiscountPercentage(10);
        couponRepository.save(coupon2);
    }
}
