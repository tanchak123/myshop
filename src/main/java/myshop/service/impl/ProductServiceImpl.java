package myshop.service.impl;

import myshop.dao.ProductDao;
import myshop.lib.Inject;
import myshop.lib.Service;
import myshop.model.Product;
import myshop.service.ProductService;
import myshop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDao productDao;

    @Override
    public void create(Product product) {
        productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id);
    }

    @Override
    public Product getByName(String name) {
        return productDao.get(Storage.products.stream()
                .filter(product -> product
                        .getName()
                        .equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Не могу найти товар -->"
                        + name))
                .getId());
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void updatePrice(String name, String price) {
        getByName(name).setPrice(price);

    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public void deleteByProduct(String name) {
        productDao.delete(getByName(name)
                .getId());
    }

    @Override
    public List<String> getNames() {
        return productDao.getAll().stream()
                .map(product -> product.getName())
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkProducts(String name) {
        for (String test : getNames()) {
            if (name.equals(test)) {
                return true;
            }
        }
        return false;
    }
}
