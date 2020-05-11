package myshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import myshop.dao.ProductDao;
import myshop.lib.Dao;
import myshop.model.Product;
import myshop.storage.Storage;

@Dao
public class ProductDaoImpl implements ProductDao {

    @Override
    public void create(Product product) {
        Storage.products.add(product);
    }

    @Override
    public Product get(Long id) {
        return Storage.products
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Не могу найти товар с айди --> "
                        + id));
    }

    @Override
    public void update(Product product) {
        IntStream.range(0, Storage.products.size())
                .filter(ind -> Storage.products.get(ind).getId().equals(product.getId()))
                .forEach(ind -> Storage.products.set(ind, product));
    }

    @Override
    public void delete(Long id) {
        Storage.products.removeIf(item -> item.getId().equals(id));
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }
}
