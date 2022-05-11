package service;

import Utils.CSVUtils;
import Utils.SortOrderByIdASC;
import model.OrderItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService {
    private static final String PATH_FILE_ODER_PRODUCT = "src/data/OrderProduct.csv";
    private static final String PATH_PAYMENT = "src/data/payment.csv";


    //    OrderProduct
    public List<OrderItem> getPayment() {
        List<OrderItem> listPayment = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH_PAYMENT);
        for (String record : records) {
            listPayment.add(new OrderItem(record));
        }
        return listPayment;
    }

    @Override
    public List<OrderItem> getOrderService() {
        List<OrderItem> orderList = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH_FILE_ODER_PRODUCT);
        for (String record : records) {
            orderList.add(new OrderItem(record));
        }
        return orderList;
    }

    @Override
    public void add(OrderItem orderItem) {
        List<OrderItem> orderList = getOrderService();
        orderList.add(orderItem);
        try {
            CSVUtils.writeFile(PATH_FILE_ODER_PRODUCT, orderList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem remove(int id) {
        List<OrderItem> orderList = getOrderService();
        OrderItemService orderItemService = new OrderItemService();
        OrderItem orderProduct = orderItemService.getById(id);
        OrderItem remove = null;
        for (OrderItem order : orderList) {
            if (order.getIdProduct() == id) {
                remove = order;
                orderList.remove(orderProduct);
            }
            try {
                CSVUtils.writeFile(PATH_FILE_ODER_PRODUCT, orderList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return remove;
        }
        return null;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        List<OrderItem> orderList = getOrderService();
        for (OrderItem order : orderList) {
            if (order.getIdProduct() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<OrderItem> orderList = getOrderService();
        for (OrderItem order : orderList) {
            if (order.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public OrderItem getById(int id) {
        List<OrderItem> orderList = getOrderService();
        for (OrderItem order : orderList) {
            if (order.getIdProduct() == id) {
                return order;
            }
        }
        return null;
    }


    @Override
    public int getIndexById(int id) {
        List<OrderItem> orderList = getOrderService();
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getIdProduct() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public OrderItem getByName(String name) {
        List<OrderItem> orderList = getOrderService();
        for (OrderItem order : orderList) {
            if (order.getName().equals(name)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<OrderItem> sortByIdASC() {
        List<OrderItem> orderList = getOrderService();
        orderList.sort(new SortOrderByIdASC());
        return orderList;
    }

    @Override
    public List<OrderItem> payment() {
        List<OrderItem> orderList = getOrderService();
        List<OrderItem> payment = getOrderService();
        try {
            CSVUtils.writeFile(PATH_PAYMENT, payment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            CSVUtils.writeFile(PATH_FILE_ODER_PRODUCT, orderList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderList;
    }


//Order


}
