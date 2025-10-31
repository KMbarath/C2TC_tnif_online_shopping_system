package com.tnsif.onlineshoppingApp.entity;

import java.util.List;

public class Order {
    private static int nextId = 1;
    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status;

    public Order(Customer customer, List<ProductQuantityPair> products) {
        this.orderId = nextId++;
        this.customer = customer;
        this.products = products;
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<ProductQuantityPair> getProducts() { return products; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order ID: " + orderId + ", Customer: " + customer.getUsername() + ", Status: " + status + "\n");
        for (ProductQuantityPair pq : products) {
            sb.append("  ").append(pq).append("\n");
        }
        return sb.toString();
    }
}
