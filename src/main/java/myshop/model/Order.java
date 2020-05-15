package myshop.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Order {
    private Long id;
    private HashMap<Product, Integer> products;
    private User user;
    private BigDecimal amountPayable;

    public Order(User user, List<Product> products) {
        HashMap<Product, Integer> hashMap = new HashMap<>();
        HashSet<Product> hashSet = new HashSet<>(products);
        for (Product product :hashSet) {
            int count = 0;
            for (Product product1 : products) {
                if (product.equals(product1)) {
                    count++;
                }
            }
            hashMap.put(product, count);
        }
        this.products = hashMap;
        this.user = user;
        this.amountPayable = BigDecimal.valueOf(products.stream()
                .mapToLong(x -> x.getPrice().longValue()).sum());;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getAmountPayable() {
        return amountPayable;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", items=" + products
                + ", user=" + user
                + ", amountPayable=" + amountPayable
                + '}';
    }
}
