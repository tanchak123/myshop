package myshop.service;

import myshop.model.Product;

import java.util.List;

public interface ProductService extends GenericService<Product, Long>{

    Product getByName(String name);

    void updatePrice(String name, String price);

    void deleteByProduct(String name);

    List<String> getNames();

    boolean checkProducts(String name);

}
