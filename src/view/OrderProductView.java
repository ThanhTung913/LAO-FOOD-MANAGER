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
                        showOrder();
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
                System.out.println("Nhập không đúng, vui lòng nhập lại!!");
            }
        } while (option != -1);
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
                ORDER_SERVICE.remove(name);
                System.out.println("Đã hủy sản phẩm: " + name);
            }

            if (quantity < order.getQuantity()) {
                snackBar.setQuantity(snackBar.getQuantity() + quantity);
                SNACK_BAR_SERVICE.remove(id);
                SNACK_BAR_SERVICE.add(snackBar);
                ORDER_SERVICE.remove(name);
                ORDER_SERVICE.add(newDrinkWater);
            }
            displayOrerList();
        } while (check);
    }

    public void orderProduct() {
        displayProduct();
//        Calendar calendar = Calendar.getInstance();
//        Date date = calendar.getTime();

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
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
            }
        } while (check);
        int total = quantity * price;
        OrderProduct newOrder = new OrderProduct(idOrder, idProduct, name, price, quantity, total);
        if (ORDER_SERVICE.checkDuplicateId(idProduct)) {
            OrderProduct order = ORDER_SERVICE.getById(idProduct);
            order.setQuantity(order.getQuantity() + quantity);
            snackBar.setQuantity(snackBar.getQuantity() - quantity);
            SNACK_BAR_SERVICE.remove(idProduct);
            SNACK_BAR_SERVICE.add(snackBar);
            SNACK_BAR_SERVICE.remove(order.getIdProduct());
            SNACK_BAR_SERVICE.add(snackBar);
            System.out.println("Đã order sản phẩm thành công");
            displayOrerList();
        } else if (snackBar.getQuantity() <= quantity) {
            System.out.println("Số lượng không đủ, vui lòng nhập lại!!!");
        } else {
            ORDER_SERVICE.add(newOrder);
            snackBar.setQuantity(snackBar.getQuantity() - quantity);
            SNACK_BAR_SERVICE.remove(idProduct);
            SNACK_BAR_SERVICE.add(snackBar);
            System.out.println("Đã order sản phẩm");
            displayOrerList();
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
        System.out.printf("%-10s%-25s%-20s%-15s%-25s\n", "ID", "Name", "Price", "Quantity","Mô tả sản phẩm");
        for (SnackBar snackBar : snackBarList) {
            System.out.printf("%-10d%-25s%-20s%-15s%-25s\n", snackBar.getId(), snackBar.getName(),
                    decimalFormat.format(snackBar.getPrice()), snackBar.getQuantity(), snackBar.getDetail());
        }
        System.out.println("|--------------------------------------------------------------------------------------------|");
    }

    public void displayOrerList() {
        List<OrderProduct> orderList = ORDER_SERVICE.sortByIdASC();
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.println("|                              XEM ORDER                                              |");
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
        List<OrderProduct> payment = ORDER_SERVICE.payment();
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
}
