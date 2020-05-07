package myshop.service.impl;

import myshop.dao.BucketDao;
import myshop.dao.ProductDao;
import myshop.dao.UserDao;
import myshop.lib.Inject;
import myshop.lib.Service;
import myshop.model.Bucket;
import myshop.model.Product;
import myshop.service.BucketService;
import myshop.storage.Storage;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private BucketDao bucketDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private UserDao userDao;

    @Override
    public void create(Bucket bucket) {
        bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(bucketDao.getAll().stream()
                .filter(bucket -> bucket.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такого айди не существует :("))
                .getId());
    }

    @Override
    public Bucket getByLogin(String login) {
        return bucketDao.get(bucketDao.getAll().stream()
                .filter(bucket -> bucket.getUser().getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такого пользователя"
                        + " не существует :("))
                .getId());
    }

    @Override
    public Bucket getByUserId(Long id) {
        return bucketDao.get(bucketDao.getAll().stream()
                .filter(bucket -> bucket.getUser().getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такого пользователя"
                        + " не существует :("))
                .getId());
    }

    @Override
    public void update(Bucket bucket) {
        bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {
        bucketDao.delete(id);
    }

    @Override
    public void deleteByBucket(Bucket bucket) {
        bucketDao.delete(bucket.getId());
    }

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }

    @Override
    public void addItemByName(String productName, String login) {
        getByLogin(login).getProducts().add(productDao.getAll().stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Продукт не найден :(")));
    }

    @Override
    public List<Product> getProducts(Long id) {
        return bucketDao.getAll().stream()
                .filter(bucket -> bucket.getId().equals(id))
                .findFirst()
                .map(bucket -> bucket.getProducts())
                .orElseThrow(() -> new NoSuchElementException("Такого айди не существует :("));

    }

    @Override
    public void add(Long productId, Long bucketId) {
        get(bucketId).getProducts().add(Storage.products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такого айди не существует :(")));
    }

    @Override
    public void clearBucket(Long userId, Long productId) {
        get(userId).getProducts().remove(
                IntStream.range(0, getByUserId(userId).getProducts().size())
                .filter(ind -> getByUserId(userId).getProducts().get(ind).getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такого предмета нет в корзине :(")));
    }

    public BigDecimal getSum(Long user_id) {
        return new BigDecimal(getByUserId(user_id).getProducts().
                stream().mapToLong(product -> product.getPrice().longValue()).sum());
    }
}
