package com.tnsif.onlineshoppingApp.service;

import java.util.ArrayList;
import java.util.List;

import com.tnsif.onlineshoppingApp.entity.Customer;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) { customers.add(customer); }
    public List<Customer> getAllCustomers() { return customers; }
    public Customer getCustomerById(int id) {
        return customers.stream().filter(c -> c.getUserId() == id).findFirst().orElse(null);
    }
}
