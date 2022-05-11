package view;

import model.Order;
import model.OrderItem;

public class OrderView {
    OrderItem orderProduct = new OrderItem();
    OrderProductView orderProductView  = new OrderProductView();
    public static void main(String[] args) {
    }

    public void saveOrrder(){
        Order order = new Order();
        order.setIdOrder(orderProduct.getIdOrder());
        order.setTotal(orderProduct.getTotal());

    }
}
