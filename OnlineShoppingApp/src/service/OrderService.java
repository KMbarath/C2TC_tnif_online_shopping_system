package service;

import java.util.ArrayList;
import java.util.List;
import entity.Order;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) { orders.add(order); }
    public List<Order> getAllOrders() { return orders; }
    public Order getOrderById(int id) {
        return orders.stream().filter(o -> o.getOrderId() == id).findFirst().orElse(null);
    }
}
