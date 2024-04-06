package com.project.datavisualization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.datavisualization.model.AccountDetails;
import com.project.datavisualization.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{username}/account")
    public ResponseEntity<?> getAccountDetails(@PathVariable String username) {
        AccountDetails accountDetails = customerService.getAccountDetails(username);
        if (accountDetails != null) {
            return ResponseEntity.ok(accountDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


