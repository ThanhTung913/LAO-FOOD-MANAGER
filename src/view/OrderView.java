package view;

import model.Order;
import model.OrderProduct;

public class OrderView {
    OrderProduct orderProduct = new OrderProduct();
    OrderProductView orderProductView  = new OrderProductView();
    public static void main(String[] args) {
    }

    public void saveOrrder(){
        Order order = new Order();
        order.setIdOrder(orderProduct.getIdOrder());
        order.setTotal(orderProduct.getTotal());

    }
}
