package service;

import model.OrderProduct;

import java.util.List;

public interface IRevenueService {

    public List<OrderProduct> getAllOrder();

    List<OrderProduct> getOrderFollowDay();

    List<OrderProduct> getOrderFollowMonth();

    int revenueFollowDay();

    int revenueFollowMonth();
}
