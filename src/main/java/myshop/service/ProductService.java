package myshop.service;

import java.util.List;
import myshop.model.Product;

public interface ProductService extends GenericService<Product, Long> {

    Product getByName(String name);

    void updatePrice(String name, String price);

    void deleteByProduct(String name);

    List<String> getNames();

    boolean checkProducts(String name);

}
