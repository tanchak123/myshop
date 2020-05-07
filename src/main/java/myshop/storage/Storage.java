package myshop.storage;

import java.util.ArrayList;
import java.util.List;
import myshop.model.Bucket;
import myshop.model.Order;
import myshop.model.Product;
import myshop.model.User;

public class Storage {
    public static final List<User> users = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Product> products = new ArrayList<>();
}
