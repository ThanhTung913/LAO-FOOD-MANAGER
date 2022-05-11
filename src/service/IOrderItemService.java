package service;

import model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> getOrderService();

    void add(OrderItem orderItem);

    OrderItem remove(int id);

    boolean checkDuplicateId(int id);

    boolean checkDuplicateName(String name);

    OrderItem getById(int id);

    int getIndexById(int id);

    OrderItem getByName(String name);

    List<OrderItem> sortByIdASC();

    List<OrderItem> payment();
}
