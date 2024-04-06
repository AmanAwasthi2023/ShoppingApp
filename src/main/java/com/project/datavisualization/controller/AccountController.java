package com.project.datavisualization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.datavisualization.model.AccountDetails;
import com.project.datavisualization.service.CustomerService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<AccountDetails> getAccountDetails(@RequestParam String username) {
        // Assuming you have a method in CustomerService to retrieve account details
        AccountDetails accountDetails = customerService.getAccountDetails(username);
        if (accountDetails != null) {
            return ResponseEntity.ok(accountDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

