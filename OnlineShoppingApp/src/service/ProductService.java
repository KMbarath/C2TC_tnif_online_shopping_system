package service;

import java.util.ArrayList;
import java.util.List;
import entity.Product;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) { products.add(product); }
    public void removeProduct(int id) { products.removeIf(p -> p.getProductId() == id); }
    public List<Product> getAllProducts() { return products; }
    public Product getProductById(int id) {
        return products.stream().filter(p -> p.getProductId() == id).findFirst().orElse(null);
    }
}
