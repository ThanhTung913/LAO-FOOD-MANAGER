package service;

import Utils.CSVUtils;
import Utils.SortOrderByIdASC;
import model.OrderProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService {
    private static final String PATH_FILE_ODER_PRODUCT = "src/data/OrderProduct.csv";
//    private static final String PATH_PAYMENT = "src/data/payment.csv";


//    OrderProduct
    @Override
    public List<OrderProduct> getOrderService() {
        List<OrderProduct> orderList = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH_FILE_ODER_PRODUCT);
        for (String record : records) {
            orderList.add(new OrderProduct(record));
        }
        return orderList;
    }

    @Override
    public void add(OrderProduct orderProduct) {
        List<OrderProduct> orderList = getOrderService();
        orderList.add(orderProduct);
        try {
            CSVUtils.writeFile(PATH_FILE_ODER_PRODUCT, orderList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderProduct remove(String name) {
        List<OrderProduct> orderList = getOrderService();
        OrderProduct remove = null;
        for (OrderProduct order : orderList) {
            if (order.getName().equals(name)) {
                remove = order;
                orderList.remove(order);
                break;
            }
            try {
                CSVUtils.writeFile(PATH_FILE_ODER_PRODUCT, orderList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return remove;

    }

    @Override
    public boolean checkDuplicateId(int id) {
        List<OrderProduct> orderList = getOrderService();
        for (OrderProduct order : orderList) {
            if (order.getIdProduct() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<OrderProduct> orderList = getOrderService();
        for (OrderProduct order : orderList) {
            if (order.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public OrderProduct getById(int id) {
        List<OrderProduct> orderList = getOrderService();
        for (OrderProduct order : orderList) {
            if (order.getIdProduct() == id) {
                return order;
            }
        }
        return null;
    }


    @Override
    public int getIndexById(int id) {
        List<OrderProduct> orderList = getOrderService();
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getIdProduct() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public OrderProduct getByName(String name) {
        List<OrderProduct> orderList = getOrderService();
        for (OrderProduct order : orderList) {
            if (order.getName().equals(name)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<OrderProduct> sortByIdASC() {
        List<OrderProduct> orderList = getOrderService();
        orderList.sort(new SortOrderByIdASC());
        return orderList;
    }

    @Override
    public List<OrderProduct> payment() {
        List<OrderProduct> orderList = getOrderService();
//        List<OrderProduct> payment = getOrderService();
//        try {
//            CSVUtils.writeFile(PATH_PAYMENT, payment);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            CSVUtils.writeFile(PATH_FILE_ODER_PRODUCT, orderList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderList;
    }



//Order


}
