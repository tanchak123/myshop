package myshop.service;

import java.math.BigDecimal;
import java.util.List;
import myshop.model.Bucket;
import myshop.model.Product;

public interface BucketService extends GenericService<Bucket, Long> {

    public Bucket getByLogin(String login);

    public Bucket getByUserId(Long id);

    void deleteByBucket(Bucket bucket);

    public void addItemByName(String name, String username);

    public List<Product> getProducts(Long id);

    public void add(Long productId, Long bucketId);

    public void clearBucket(Long productId, Long userId);

    public BigDecimal getSum(Long userId);

}

