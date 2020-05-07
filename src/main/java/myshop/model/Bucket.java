package myshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private static Long bucketId = 0L;
    private Long id;
    private List<Product> products;
    private User user;

    public Bucket(User user) {
        this.user = user;
        this.products = new ArrayList<>();
        this.id = ++bucketId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void removeItem(Long id) {
        products.removeIf(item -> item.getId().equals(id));
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

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id
                + ", items=" + products
                + ", user=" + user
                + '}';
    }
}
