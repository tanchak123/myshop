package myshop.service.impl;

import myshop.dao.OrderDao;
import myshop.lib.Inject;
import myshop.lib.Service;
import myshop.model.Order;
import myshop.service.BucketService;
import myshop.service.OrderService;
import myshop.storage.Storage;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private BucketService shoppingCartService;

    @Override
    public void create(Order order) {
        orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public void deleteByOrder(Order order) {
        orderDao.delete(order.getId());
    }
}
