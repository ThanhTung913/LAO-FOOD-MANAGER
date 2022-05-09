package service;

import Utils.CSVUtils;
import model.Order;
import model.OrderProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    OrderItemService orderService = new OrderItemService();
    private static final String PATH_ORDER = "src/data/Order.csv";

//    public List<OrderProduct> getAllOrder() {
//        return orderService.getOrderService();
//    }
//
//    @Override
//    public List<OrderProduct> getOrderFollowDay() {
//
//        return null;
//    }
//
//    @Override
//    public List<OrderProduct> getOrderFollowMonth() {
//        return null;
//    }
//
//    @Override
//    public int revenueFollowDay() {
//
//        return 0;
//    }
//
//    @Override
//    public int revenueFollowMonth() {
//        return 0;
//    }

    public List<Order> getOrders() {
        List<Order> orderList = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH_ORDER);
        for (String record : records) {
            orderList.add(new Order(record));
        }
        return orderList;
    }

    public void updateOrder(Order order) {
        List<Order> orderList = getOrders();
        for (Order order1 : orderList) {
            if (order1.getIdOrder() == order.getIdOrder()) {
                order1.setTotal(order.getTotal());
                order1.setCreate_at(order.getCreate_at());
            }
        }
        try {
            CSVUtils.writeFile(PATH_ORDER, orderList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(Order order) {
        List<Order> orderList = getOrders();
        orderList.add(order);
        try {
            CSVUtils.writeFile(PATH_ORDER, orderList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(int id) {
        List<Order> orderList = getOrders();
        for (Order order : orderList) {
            if (order.getIdOrder() == id) {
                return order;
            }
        }
        return null;
    }
}
