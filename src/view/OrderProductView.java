package view;

import model.OrderProduct;
import model.SnackBar;
import service.OrderItemService;
import service.SnackBarService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderProductView {
    static Scanner scanner = new Scanner(System.in);
    private static final OrderItemService ORDER_SERVICE = new OrderItemService();
    private static final SnackBarService SNACK_BAR_SERVICE = new SnackBarService();
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
                        displayOrerList();
                        break;
                    case 4:
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    case 6:
                        displayPayList();
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
                displayOrerList();
                System.out.println("Nhập ID sản phẩm đã order bạn muốn sửa");
                id = getIdproduct();
                if (!ORDER_SERVICE.checkDuplicateId(id)) {
                    System.out.println("Sản phẩm này chưa được order");
                    check = true;
                }
            } while (check);

            SnackBar snackBar = SNACK_BAR_SERVICE.getById(id);
            OrderProduct order = ORDER_SERVICE.getById(id);
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

            OrderProduct newDrinkWater = new OrderProduct(id, name, price, order.getQuantity() - quantity, order.getTotal() - total);
            if (quantity == order.getQuantity()) {
                snackBar.setQuantity(snackBar.getQuantity() + quantity);
                SNACK_BAR_SERVICE.remove(id);
                SNACK_BAR_SERVICE.add(snackBar);
                ORDER_SERVICE.remove(id);
                System.out.println("Đã hủy sản phẩm: " + name);
            }

            if (quantity < order.getQuantity()) {
                snackBar.setQuantity(snackBar.getQuantity() + quantity);
                SNACK_BAR_SERVICE.remove(id);
                SNACK_BAR_SERVICE.add(snackBar);
                ORDER_SERVICE.remove(id);
                ORDER_SERVICE.add(newDrinkWater);
            }
            displayOrerList();
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
                if (!SNACK_BAR_SERVICE.checkDuplicateId(idProduct)) {
                    System.out.println("ID không tồn tại, vui lòng nhập lại!!");
                    check = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ID không hợp lệ, vui lòng nhập lại!!");
            }
        } while (check);
        SnackBar snackBar = SNACK_BAR_SERVICE.getById(idProduct);
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
                }
                if (quantity <= 0) {
                    System.out.println("Số lượng không được nhỏ hơn 1");
                    check = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
            }
        } while (check);
        int total = quantity * price;
        OrderProduct newOrder = new OrderProduct(idOrder, idProduct, name, price, quantity, total);
        if (ORDER_SERVICE.checkDuplicateId(idProduct)) {
            OrderProduct order = ORDER_SERVICE.getById(idProduct);
            int newQuantity = order.getQuantity() + quantity;
            int newTotal = newQuantity * order.getPrice();
            order.setQuantity(newQuantity);
            order.setTotal(newTotal);
            snackBar.setQuantity(snackBar.getQuantity() - quantity);
            ORDER_SERVICE.remove(order.getIdProduct());
            ORDER_SERVICE.add(order);
            SNACK_BAR_SERVICE.update(idProduct,snackBar);
            System.out.println("Đã order sản phẩm thành công");
            displayOrerList();
            backOrder();
        } else if (snackBar.getQuantity() <= quantity) {
            System.out.println("Số lượng không đủ, vui lòng nhập lại!!!");
        } else {
            ORDER_SERVICE.add(newOrder);
            snackBar.setQuantity(snackBar.getQuantity() - quantity);
            SNACK_BAR_SERVICE.remove(idProduct);
            SNACK_BAR_SERVICE.add(snackBar);
            System.out.println("Đã order sản phẩm");
            displayOrerList();
            backOrder();
        }
    }

    public void backOrder() {
        String orderProductCtn = null;
        do {
            try {
                System.out.println("Nhấn 'c' để tiếp tục order");
                System.out.println("     'b' để quay lại ");
                System.out.println("     'p' để thanh toán");
                orderProductCtn = scanner.nextLine();
                switch (orderProductCtn) {
                    case "c":
                        orderProduct();
                        break;
                    case "b":
                        showOrder();
                        break;
                    case "p":
                        displayPayList();
                        showOrder();
                        break;
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
        List<SnackBar> snackBarList = SNACK_BAR_SERVICE.sortSnackbar();
        System.out.println("|-------------------------------------------------------------------------------------------|");
        System.out.println("|                                      DANH SÁCH SẢN PHẨM                                   |");
        System.out.println("|-------------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-25s\n", "ID", "Name", "Price", "Quantity", "Mô tả sản phẩm");
        for (SnackBar snackBar : snackBarList) {
            System.out.printf("%-10d%-25s%-20s%-15s%-25s\n", snackBar.getId(), snackBar.getName(),
                    decimalFormat.format(snackBar.getPrice()), snackBar.getQuantity(), snackBar.getDetail());
        }
        System.out.println("|--------------------------------------------------------------------------------------------|");
    }

    public void displayOrerList() {
        List<OrderProduct> orderList = ORDER_SERVICE.sortByIdASC();
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.println("|                                       HÓA ĐƠN                                       |");
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Thành tiền");
        for (OrderProduct order : orderList) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", order.getIdProduct(), order.getName(),
                    decimalFormat.format(order.getPrice()), order.getQuantity(), decimalFormat.format(order.getTotal()));
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }


    private void displayPayList() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        System.out.println(formatter.format(date));
        List<OrderProduct> payment = ORDER_SERVICE.getOrderService();
        if (payment.size() == 0) {
            System.out.println("Không có đơn hàng nào để thanh toán!!!");
            return;
        }
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.println("|                                   SẢN PHẨM ĐÃ ORDER                                 |");
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Thành tiền");
        for (OrderProduct order : payment) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", order.getIdProduct(), order.getName(),
                    decimalFormat.format(order.getPrice()), order.getQuantity(), decimalFormat.format(order.getTotal()));
        }
        System.out.println("|-------------------------------------------------------------------------------------|T");
        int total = 0;
        for (OrderProduct order : payment) {
            total += order.getTotal();
        }

        System.out.println("Tổng thanh toán: " + decimalFormat.format(total));
        System.out.println("Ngày thanh toán: " + formatter.format(date));
        System.out.println("Bạn đã thanh toán thành công");
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

