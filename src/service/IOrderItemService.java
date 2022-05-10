package service;

import model.OrderProduct;

import java.util.List;

public interface IOrderItemService {
    List <OrderProduct> getOrderService();
    void add(OrderProduct order);

    OrderProduct remove(int id);

    boolean checkDuplicateId(int id);

    boolean checkDuplicateName(String name);

    OrderProduct getById(int id);

    int getIndexById(int id);

    OrderProduct getByName(String name);

    List<OrderProduct> sortByIdASC();

    List<OrderProduct> payment();
}
