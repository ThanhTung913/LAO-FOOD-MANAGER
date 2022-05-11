package service;

import model.OrderItem;

import java.util.List;

public interface IRevenueService {

    public List<OrderItem> getAllOrder();

    List<OrderItem> getOrderFollowDay();

    List<OrderItem> getOrderFollowMonth();

    int revenueFollowDay();

    int revenueFollowMonth();
}
