package com.project.datavisualization.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.datavisualization.service.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password required");
        }

        if (customerService.authenticate(username, password)) {
            session.setAttribute("username", username); // Set user session attribute
            
            // Redirect to the account endpoint for the authenticated user
            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/customers/" + username + "/account").build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    
    
    @GetMapping
    public String loginForm() {
        // Return the path to your login form HTML file
        return "login.html";
    }
    
    
//    @GetMapping("/account")
//    public ResponseEntity<Map<String, Object>> getAccountDetails(HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        if (username != null) {
//            Map<String, Object> accountDetails = new HashMap<>();
//            accountDetails.put("username", username);
//            accountDetails.put("balance", "1000"); // Get balance from database or somewhere
//            // Get coupons for the user
//            Set<String> coupons = customerService.getCustomerCoupons(username);
//            accountDetails.put("coupons", coupons);
//            return ResponseEntity.ok(accountDetails);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Clear user session
        return ResponseEntity.ok("Logout successful");
    }
}


