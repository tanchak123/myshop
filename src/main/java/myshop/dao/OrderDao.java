package myshop.dao;

import myshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    @Override
    void create(Order order);

}
