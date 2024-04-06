package com.project.datavisualization.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.datavisualization.exception.RegistrationException;
import com.project.datavisualization.model.AccountDetails;
import com.project.datavisualization.model.Customer;
import com.project.datavisualization.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;

    public void registerCustomer(String username, String password) throws RegistrationException {
        // Check if the username is already taken
        if (customerRepository.findByUsername(username) != null) {
            throw new RegistrationException("Username already exists");
        }

        // Create a new Customer object
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password); // In a real system, you'd hash the password for security

        // Save the new customer to the database
        customerRepository.save(customer);
    }

    private final Map<String, String> hardcodedUsers = new HashMap<>(); // Hardcoded user credentials
    private final Map<String, Set<String>> hardcodedUserCoupons = new HashMap<>(); // Hardcoded user coupons

    public CustomerService() {
        // Initialize hardcoded users and their passwords (plain text)
        hardcodedUsers.put("user1", "password1");
        hardcodedUsers.put("user2", "password2");

        // Initialize hardcoded user coupons
        Set<String> user1Coupons = new HashSet<>();
        user1Coupons.add("Coupon 1");
        user1Coupons.add("Coupon 2");

        Set<String> user2Coupons = new HashSet<>();
        user2Coupons.add("Coupon 3");

        hardcodedUserCoupons.put("user1", user1Coupons);
        hardcodedUserCoupons.put("user2", user2Coupons);
    }
    

    public AccountDetails getAccountDetails(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null) {
            AccountDetails accountDetails = new AccountDetails();
            accountDetails.setUsername(customer.getUsername());
            // Set other account details such as email, etc.
            return accountDetails;
        } else {
            return null;
        }
    }
    
    

    public boolean authenticate(String username, String password) {
        // Authenticate user based on hardcoded credentials (plain text)
        return hardcodedUsers.containsKey(username) && hardcodedUsers.get(username).equals(password);
    }

    public Set<String> getCustomerCoupons(String username) {
        // Retrieve coupons for the authenticated user
        return hardcodedUserCoupons.getOrDefault(username, new HashSet<>());
    }
}
