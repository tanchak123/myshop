package myshop.service;

import myshop.model.Order;

public interface OrderService extends GenericService<Order, Long> {

    void deleteByOrder(Order order);

}
