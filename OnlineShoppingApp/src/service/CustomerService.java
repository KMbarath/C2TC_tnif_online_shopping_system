package service;

import java.util.ArrayList;
import java.util.List;
import entity.Customer;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) { customers.add(customer); }
    public List<Customer> getAllCustomers() { return customers; }
    public Customer getCustomerById(int id) {
        return customers.stream().filter(c -> c.getUserId() == id).findFirst().orElse(null);
    }
}
