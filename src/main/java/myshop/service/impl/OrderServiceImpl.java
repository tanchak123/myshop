package myshop.service.impl;

import java.util.List;
import myshop.dao.OrderDao;
import myshop.lib.Inject;
import myshop.lib.Service;
import myshop.model.Order;
import myshop.service.BucketService;
import myshop.service.OrderService;
import myshop.storage.Storage;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private BucketService bucketService;

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
        return orderDao.getAll();
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
