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
    private static Long usersId = 0L;
    private static Long orderId = 0L;
    private static Long bucketId = 0L;
    private static Long productId = 0L;

    public static Long getUsersId() {
        return ++usersId;
    }

    public static Long getOrderId() {
        return ++orderId;
    }

    public static Long getBucketId() {
        return ++bucketId;
    }

    public static Long getProductId() {
        return ++productId;
    }
}
