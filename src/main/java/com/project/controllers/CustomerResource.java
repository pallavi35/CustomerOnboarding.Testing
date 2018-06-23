package com.project.controllers;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerResource {
    private CustomerResource customerService;

    @GetMapping(value= "/id", produces= MediaType.APPLICATION_JSON_VALUE)
    public Customer showCustomer()
    {
        return new Customer("abc", "123") ;
    }
    @PostMapping(value= "/api/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCustomer(Model model)
    {
        return "customer";
    }

    private class Customer {
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
    }
}
