package com.project.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerResource {
    private CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value= "/id", produces= MediaType.APPLICATION_JSON_VALUE)
    public Customer showCustomer()
    {
        return new Customer("abc", "123") ;
    }
    @PostMapping(value= "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody Customer customer)
    {
        return customer;
    }

    public static class Customer {
        private String customers;
        private String id;

        public String getCustomers() {
            return customers;
        }

        public void setCustomers(String customers) {
            this.customers = customers;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Customer(String customers, String id) {
            this.customers = customers;
            this.id = id;
        }

        public Customer()
        {

        }
    }
}
