package myshop.dao.impl;

import myshop.dao.OrderDao;
import myshop.lib.Dao;
import myshop.model.Order;
import myshop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public void create(Order order) {
        Storage.orders.add(order);
    }

    @Override
    public void update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(i -> Storage.orders.get(i).getId().equals(order.getId()))
                .forEach(i -> Storage.orders.set(i, order));
    }

    @Override
    public Order get(Long id) {
        return Storage.orders.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Неверный айди"));
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public void delete(Long id) {
        Storage.orders.removeIf(order -> order.getId().equals(id));
    }

}
