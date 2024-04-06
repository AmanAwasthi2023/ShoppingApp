package com.project.datavisualization.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.datavisualization.exception.CustomerNotLoggedInException;
import com.project.datavisualization.service.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    
    
    @Autowired
    private CouponService couponService;

    @GetMapping("/fetch")
    public ResponseEntity<Map<String, Integer>> fetchCoupons(@RequestParam String username, @RequestParam String password) {
        try {
            Map<String, Integer> coupons = couponService.fetchCouponsByUsername(username);
            return ResponseEntity.ok(coupons);
        } catch (CustomerNotLoggedInException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

