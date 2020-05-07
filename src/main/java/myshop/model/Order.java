package myshop.model;

import myshop.storage.Storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Order {
    private static Long orderId = 0L;
    private Long id;
    private List<Product> products;
    private User user;
    private BigDecimal amountPayable;

    public Order(User user) {
        this.id = ++orderId;
        this.products = new ArrayList<>(Storage.buckets.stream()
                .filter(b -> b.getUser().getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такой корзины не существует!!!"))
                .getProducts());
        this.user = user;
        this.amountPayable = BigDecimal.valueOf(products.stream()
                .mapToLong(x -> x.getPrice().longValue()).sum());;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
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
