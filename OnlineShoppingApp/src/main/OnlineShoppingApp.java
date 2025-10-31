package main;

import java.util.*;
import entity.*;
import service.*;

public class OnlineShoppingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();
        AdminService adminService = new AdminService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        while (true) {
            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> adminMenu(sc, adminService, productService, orderService);
                case 2 -> customerMenu(sc, customerService, productService, orderService);
                case 3 -> { System.out.println("Exiting..."); sc.close(); return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    static void adminMenu(Scanner sc, AdminService adminService, ProductService productService, OrderService orderService) {
        while (true) {
            System.out.println("\nAdmin Menu:\n1. Add Product\n2. Remove Product\n3. View Products\n4. Create Admin\n5. View Admins\n6. Update Order Status\n7. View Orders\n8. Return");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Product ID: "); int pid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Product Name: "); String name = sc.nextLine();
                    System.out.print("Enter Product Price: "); double price = sc.nextDouble();
                    System.out.print("Enter Stock Quantity: "); int qty = sc.nextInt();
                    productService.addProduct(new Product(pid, name, price, qty));
                    System.out.println("Product added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter Product ID to remove: ");
                    int pid = sc.nextInt();
                    productService.removeProduct(pid);
                    System.out.println("Product removed successfully!");
                }
                case 3 -> {
                    System.out.println("Products:");
                    productService.getAllProducts().forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Enter Admin ID: "); int aid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Username: "); String uname = sc.nextLine();
                    System.out.print("Enter Email: "); String email = sc.nextLine();
                    adminService.addAdmin(new Admin(aid, uname, email));
                    System.out.println("Admin created successfully!");
                }
                case 5 -> {
                    System.out.println("Admins:");
                    adminService.getAllAdmins().forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Enter Order ID: "); int oid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter new status (Completed/Delivered/Cancelled): "); String status = sc.nextLine();
                    Order order = orderService.getOrderById(oid);
                    if (order != null) {
                        order.setStatus(status);
                        System.out.println("Order status updated!");
                    } else System.out.println("Order not found!");
                }
                case 7 -> {
                    System.out.println("Orders:");
                    orderService.getAllOrders().forEach(System.out::println);
                }
                case 8 -> { System.out.println("Exiting Admin..."); return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    static void customerMenu(Scanner sc, CustomerService customerService, ProductService productService, OrderService orderService) {
        while (true) {
            System.out.println("\nCustomer Menu:\n1. Create Customer\n2. View Customers\n3. Place Order\n4. View Orders\n5. View Products\n6. Return");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User ID: "); int uid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Username: "); String uname = sc.nextLine();
                    System.out.print("Enter Email: "); String email = sc.nextLine();
                    System.out.print("Enter Address: "); String addr = sc.nextLine();
                    customerService.addCustomer(new Customer(uid, uname, email, addr));
                    System.out.println("Customer created successfully!");
                }
                case 2 -> {
                    System.out.println("Customers:");
                    customerService.getAllCustomers().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter Customer ID: ");
                    int cid = sc.nextInt(); sc.nextLine();
                    Customer customer = customerService.getCustomerById(cid);
                    if (customer == null) { System.out.println("Customer not found!"); break; }

                    List<ProductQuantityPair> orderItems = new ArrayList<>();
                    while (true) {
                        System.out.print("Enter Product ID to add to order (or -1 to complete): ");
                        int pid = sc.nextInt();
                        if (pid == -1) break;
                        Product product = productService.getProductById(pid);
                        if (product == null) { System.out.println("Invalid Product ID!"); continue; }
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        if (qty > product.getStockQuantity()) {
                            System.out.println("Not enough stock available!");
                        } else {
                            orderItems.add(new ProductQuantityPair(product, qty));
                            product.setStockQuantity(product.getStockQuantity() - qty);
                        }
                    }

                    if (!orderItems.isEmpty()) {
                        Order order = new Order(customer, orderItems);
                        orderService.addOrder(order);
                        customer.getOrders().add(order);
                        System.out.println("Order placed successfully!");
                    } else {
                        System.out.println("No items in order!");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Customer ID: "); int cid = sc.nextInt();
                    Customer cust = customerService.getCustomerById(cid);
                    if (cust != null)
                        cust.getOrders().forEach(System.out::println);
                    else
                        System.out.println("Customer not found!");
                }
                case 5 -> {
                    System.out.println("Products:");
                    productService.getAllProducts().forEach(System.out::println);
                }
                case 6 -> { System.out.println("Exiting Customer Menu..."); return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
