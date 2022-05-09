package view;

import model.Order;
import service.OrderItemService;
import service.OrderService;

public class Test {
    public static void main(String[] args) {
        OrderItemService orderService = new OrderItemService();
        OrderService orderService1 = new OrderService();
//        Order order = orderService.getOrderById(2);
//        order.setTotal(150000);
//        orderService.updateOrder(order);

        //12,CƠM,10000,20,CHAy
        //SnackBar snackBar = new SnackBar("12,CƠM,10000,20,CHAy");
        // snackBar1 = new SnackBar("6,DƯƠNG DÂM,50000,302,HƠI DÂM");


        //2,150000,06/05/2022 17:42
        Order order = new Order();

        int idProduct = 12;
        int idProduct2 = 6;
        Order order1 = new Order("3,200000,07/05/2022 17:42");
orderService1.addOrder(order1);



    }
}
