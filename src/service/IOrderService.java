package service;

import model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();
    void updateOrder(Order order);
    void addOrder(Order order);
    Order getOrderById(int id);
}
