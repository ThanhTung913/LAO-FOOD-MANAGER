package view;

import model.OrderItem;
import model.Product;
import service.OrderItemService;
import service.ProductService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderProductView {
    static Scanner scanner = new Scanner(System.in);
    private static final OrderItemService ORDER_ITEM_SERVICE = new OrderItemService();
    private static final ProductService PRODUCT_SERVICE = new ProductService();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " VND");
    MenuAndDisplay menuAndDisplay = new MenuAndDisplay();


    public static void main(String[] args) throws ParseException {


        OrderProductView orderView = new OrderProductView();
        orderView.showOrder();
        // record: 06/05/2022 15:42
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        Date date = new Date();
//        System.out.println(formatter.format(date));
//
//        String sDate = "06/05/2022 15:42";
//        Date date1 = formatter.parse(sDate);
//        System.out.println(date1);
//        System.out.println(formatter.format(date1));
//
    }

    public void showOrder() {
        int option = -1;
        do {
            try {
                MenuAndDisplay.menuOrder();
                System.out.print("Chọn chức năng: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        orderProduct();
                        break;
                    case 2:
                        removeOrder();
                        break;
                    case 3:
                        displayPayList();
                        break;
                    case 4:
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Không có chức năng, vui lòng nhập lại");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Nhập phím không đúng kiểu dữ liệu, vui lòng nhập lại");
            }

        } while (option != 0);

    }

    private void removeOrder() {
        boolean check = false;
        int id = 0;
        do {
            do {
                check = false;
                OrderItem order = (OrderItem) ORDER_ITEM_SERVICE.getOrderService();
                displayOrerList(order);
                System.out.println("Nhập ID sản phẩm đã order bạn muốn sửa");
                id = getIdproduct();
                if (!ORDER_ITEM_SERVICE.checkDuplicateId(id)) {
                    System.out.println("Sản phẩm này chưa được order");
                    check = true;
                }
            } while (check);

            Product snackBar = PRODUCT_SERVICE.getById(id);
            OrderItem order = ORDER_ITEM_SERVICE.getById(id);
            String name = order.getName();
            int price = order.getPrice();
            int quantity = 0;
            do {
                try {
                    check = false;
                    System.out.println("Nhập số lượng bạn muốn xóa");
                    System.out.print("==> ");
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity > snackBar.getQuantity()) {
                        System.out.println("Vượt quá số lượng, vui lòng nhập lại");
                        check = true;
                    }
                    if (quantity <= 0) {
                        System.out.println("Số lượng không được nhỏ hơn 1");
                        check = true;
                    }
                } catch (Exception e) {
                    System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
                }
            } while (check);
            do {
                try {
                    check = false;
                    if (quantity > order.getQuantity()) {
                        System.out.println("Số lượng lớn hơn số lượng bạn đã order, vui lòng nhập lại!!!");
                        check = true;
                    }
                } catch (Exception e) {
                    System.out.println("Nhập số lượng không hợp lệ, vui lòng nhập lại");
                }

            } while (check);
            int total = price * quantity;

            OrderItem newDrinkWater = new OrderItem(id, name, price, order.getQuantity() - quantity, order.getTotal() - total);
            if (quantity == order.getQuantity()) {
                snackBar.setQuantity(snackBar.getQuantity() + quantity);
                PRODUCT_SERVICE.remove(id);
                PRODUCT_SERVICE.add(snackBar);
                ORDER_ITEM_SERVICE.remove(id);
                System.out.println("Đã hủy sản phẩm: " + name);
            }

            if (quantity < order.getQuantity()) {
                snackBar.setQuantity(snackBar.getQuantity() + quantity);
                PRODUCT_SERVICE.remove(id);
                PRODUCT_SERVICE.add(snackBar);
                ORDER_ITEM_SERVICE.remove(id);
                ORDER_ITEM_SERVICE.add(newDrinkWater);
            }
        } while (check);
    }

    public void orderProduct() {
        displayProduct();
        int idOrder = (int) System.currentTimeMillis();
        boolean check = false;
        int idProduct = 0;
        do {
            try {
                check = false;
                System.out.println("Nhập ID sản phẩm bạn muốn order");
                System.out.print("==> ");
                idProduct = getIdproduct();
                if (!PRODUCT_SERVICE.checkDuplicateId(idProduct)) {
                    System.out.println("ID không tồn tại, vui lòng nhập lại!!");
                    check = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ID không hợp lệ, vui lòng nhập lại!!");
            }
        } while (check);
        Product snackBar = PRODUCT_SERVICE.getById(idProduct);
        String name = snackBar.getName();
        int price = snackBar.getPrice();
        int quantity = 0;
        do {
            try {
                check = false;
                System.out.println("Nhập số lượng bạn muốn order");
                System.out.print("==> ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > snackBar.getQuantity()) {
                    System.out.println("Vượt quá số lượng, vui lòng nhập lại");
                    check = true;
                    System.out.println("Nhập 1 phím để quay lại");
                    scanner.nextLine();
                    break;
                }
                if (quantity < 0) {
                    System.out.println("Số lượng không được nhỏ hơn 1");
                    check = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
            }
        } while (check);
        int total = quantity * price;
        OrderItem newOrder = new OrderItem(idOrder, idProduct, name, price, quantity, total);
        if (ORDER_ITEM_SERVICE.checkDuplicateId(idProduct)) {
                    OrderItem order = ORDER_ITEM_SERVICE.getById(idProduct);
                    int newQuantity = order.getQuantity() + quantity;
                    int newTotal = newQuantity * order.getPrice();
                    order.setQuantity(newQuantity);
                    order.setTotal(newTotal);
                    snackBar.setQuantity(snackBar.getQuantity() - quantity);
                    ORDER_ITEM_SERVICE.remove(order.getIdProduct());
                    ORDER_ITEM_SERVICE.add(order);
                    PRODUCT_SERVICE.update(idProduct, snackBar);
                    System.out.println("Đã order sản phẩm thành công");
                    displayOrerList(newOrder);
                    backOrder();
        } else if (snackBar.getQuantity() <= quantity) {
            System.out.println("Số lượng không đủ, vui lòng nhập lại!!!");
        } else {
            ORDER_ITEM_SERVICE.add(newOrder);
            snackBar.setQuantity(snackBar.getQuantity() - quantity);
            PRODUCT_SERVICE.remove(idProduct);
            PRODUCT_SERVICE.add(snackBar);
            System.out.println("Đã order sản phẩm");
            displayOrerList(newOrder);
            backOrder();
        }
    }

    public void displayOrerList(OrderItem order) {
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.println("|                                       HÓA ĐƠN                                       |");
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Thành tiền");
        System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", order.getIdProduct(), order.getName(),
                decimalFormat.format(order.getPrice()), order.getQuantity(), decimalFormat.format(order.getTotal()));
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public void backOrder() {
        String orderProductCtn = null;
        do {
            try {
                System.out.println("Nhấn 'c' để tiếp tục order");
                System.out.println("     'b' để quay lại ");
//                System.out.println("     'p' để thanh toán");
                orderProductCtn = scanner.nextLine();
                switch (orderProductCtn) {
                    case "c":
                        orderProduct();
                        break;
                    case "b":
                        showOrder();
                        break;
//                    case "p":
//                        displayPayList();
//                        showOrder();
//                        break;
                    default:
                        System.out.println("Không có chức năng, vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Nhấn không hợp lệ, vui lòng nhập lại!!");
            }

        } while (orderProductCtn != null);
    }

    public int getIdproduct() {
        int id = 0;
        do {
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id < 1) {
                    System.out.println("Id phải lớn hơn 1");
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dữ liệu, vui lòng nhập lại!");
            }
        } while (id < 1);
        return id;
    }

    public void displayProduct() {
        List<Product> snackBarList = PRODUCT_SERVICE.sortProduct();
        System.out.println("|-------------------------------------------------------------------------------------------|");
        System.out.println("|                                      DANH SÁCH SẢN PHẨM                                   |");
        System.out.println("|-------------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-25s\n", "ID", "Name", "Price", "Quantity", "Mô tả sản phẩm");
        for (Product snackBar : snackBarList) {
            System.out.printf("%-10d%-25s%-20s%-15s%-25s\n", snackBar.getId(), snackBar.getName(),
                    decimalFormat.format(snackBar.getPrice()), snackBar.getQuantity(), snackBar.getDetail());
        }
        System.out.println("|--------------------------------------------------------------------------------------------|");
    }




    private void displayPayList() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
//        System.out.println(formatter.format(date));
        List<OrderItem> payment = ORDER_ITEM_SERVICE.getOrderService();
//        if (payment.size() == 0) {
//            System.out.println("Không có đơn hàng nào để thanh toán!!!");
//            return;
//        }
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.println("|                                   SẢN PHẨM ĐÃ ORDER                                 |");
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Thành tiền");
        for (OrderItem order : payment) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", order.getIdProduct(), order.getName(),
                    decimalFormat.format(order.getPrice()), order.getQuantity(), decimalFormat.format(order.getTotal()));
        }
        System.out.println("|-------------------------------------------------------------------------------------|T");
        int total = 0;
        for (OrderItem order : payment) {
            total += order.getTotal();
        }

        System.out.println("Tổng tiền: " + decimalFormat.format(total) +" Ngày mua hàng: " + formatter.format(date) );
    }

    private void quayXeOrder() {
        String choice = null;
        do {
            System.out.println("|          'c' ĐỂ QUAY LẠI ORDER              |");
            System.out.println("|          'd' ĐỂ QUAY LẠI ĐĂNG NHẬP          |");
            System.out.println("|          'q' ĐỂ THOÁT CHƯƠNG TRÌNH          |");
            System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
            try {
                choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        orderProduct();
                        break;
                    case "c":
                        showOrder();
                        break;
                    case "d":
                        LoginView loginView = new LoginView();
                        loginView.menuLogin();
                        break;
                    case "q":
                        Thread_exist thread_exist = new Thread_exist();
                        Thread thread1 = new Thread(thread_exist);
                        thread1.start();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Nhập không đúng cú phảp vui lòng nhập lai");
                }
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
            }
        } while (choice != null);
    }
}

