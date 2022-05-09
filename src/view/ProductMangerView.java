package view;

import model.SnackBar;
import service.SnackBarService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ProductMangerView {
    Scanner scanner = new Scanner(System.in);
    SnackBarService snackBarService = new SnackBarService();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " VND");
    MenuAndDisplay menuAndDisplay = new MenuAndDisplay();

    public static void main(String[] args) {
        ProductMangerView productMangerView = new ProductMangerView();
        productMangerView.showManagerProduct();
    }

    public void showManagerProduct() {
        int choice = -1;
        do {
            try {
                menuAndDisplay.menuManagerProduct();
                System.out.println("Chọn chức năng: ");
                System.out.print("==> ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai, vui lòng nhập lại.");
            }
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    lockProduct();
                    break;
                case 4:
                    unlockProduct();
                    break;
                case 5:
                    menuAndDisplay.displayProduct();
                    break;
                case 6:
                    menuAndDisplay.searchByName();
                    break;
                case 0:
                    System.out.println("back");
                    menuAndDisplay.mainMenu();
                    break;
                default:
                    System.out.println("Nhập sai, mời nhập lại!!!");
                    break;
            }
        } while (choice != -1);
    }

    //    ADDDDDDDDD VÀOOOOOOOOOOOOOOOOO
    public void add() {
        SnackBar snackBar = new SnackBar();
        boolean check = false;
        int id = 0;
        String newNameProduct = null;
        int price = 0;
        int quantity = 0;

        do {          // check id add
            try {
                check = false;
                System.out.println("Nhập ID bạn muốn thêm hoặc nhập 'hai' để quay xe");
                System.out.print("==>");
                String temp = scanner.nextLine();
                if (temp.equals("hai")) {
                    System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
                    System.out.println("|    NHẤN  'b' ĐỂ THÊM SẢN PHẨM               |");
                    quayXe();
                } else {

                    id = getIdAdd();
                    if (snackBarService.checkDuplicateId(id)) {
                        System.out.println("ID sản phẩm đã tồn tại, vui lòng nhập lại");
                        check = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("ID không hợp lệ, vui lòng nhập lại");
                check = true;
            }
        } while (check);

        do {      // check name add
            try {
                check = false;
                System.out.println("Nhập tên sản phẩm bạn muốn thêm hoặc nhập 'hai' để quay xe ");
                System.out.print("==>");
//                newNameProduct = scanner.nextLine();
                String temp = scanner.nextLine();
                if (temp.equals("hai"))
                    return;
                newNameProduct = temp;
                if (snackBarService.checkDuplicateName(newNameProduct)) {
                    System.out.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Tên sản phẩm không hợp lệ, vui lòng nhập lại");
            }

        } while (check);

        do {    // Check price add
            try {
                check = false;
                System.out.println("Nhập giá sản phẩm hoặc nhập 'hai' để quay xe");
                System.out.print("==> ");
//                price = Integer.parseInt(scanner.nextLine());
                String temp = scanner.nextLine();
                if (temp.equals("hai")) {
                    System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
                    System.out.println("|    NHẤN  'b' ĐỂ THÊM SẢN PHẨM               |");
                    quayXe();
                }
                price = Integer.parseInt(temp);
                if (price < 10000) {
                    System.out.println("Giá sản phẩm phải lớn hơn 0 và nhỏ hơn 100K");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Giá không hợp lệ, vui lòng nhập lại");
                check = true;
            }
        } while (price < 10000);

        do {     // check quantity add
            try {
                check = false;
                System.out.println("Nhập số lượng sản phẩm hoặc nhập 'hai' để quay xe");
                System.out.print("==> ");
                String temp = scanner.nextLine();
                if (temp.equals("hai")) {
                    System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
                    System.out.println("|    NHẤN  'b' ĐỂ THÊM SẢN PHẨM               |");
                    quayXe();
                }
                quantity = Integer.parseInt(temp);
                if (quantity < 0) {
                    System.out.println("Số lượng không được nhỏ hơn 1");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Số lượng không hợp lệ, vui lòng nhập lại");
                check = true;
            }
        } while (check);

        String detail = "";
        do {

            check = false;
            System.out.println("Thêm mô tả sản phẩm hoặc nhập 'hai' để quay xe ");
            String temp = scanner.nextLine();
            if (temp.equals("hai")) {
                System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
                System.out.println("|    NHẤN  'b' ĐỂ THÊM SẢN PHẨM               |");
                quayXe();
                check = false;
            }
            detail = temp;
        } while (check);
        snackBar.setId(id);
        snackBar.setName(newNameProduct);
        snackBar.setPrice(price);
        snackBar.setQuantity(quantity);
        snackBar.setDetail(detail);
        snackBarService.add(snackBar);
        System.out.println("Thêm sản phẩm thành công");
    }

    public int getIdAdd() {
        int id = 0;
        do {
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id < 0) {
                    System.out.println("ID không đúng, vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Nhập sai vui lòng nhập lại.");
            }
        } while (id < 1);
        return id;
    }

    //    UPDATEEEEEEEEEEEEE Ở ĐÂYYYYYYYYYY
    public void updateProduct() {
        int option = -1;
        menuAndDisplay.displayProduct();
        boolean check = false;
        int id = 0;
        String name = null;
        int price = 0;
        int quantity = 0;
        String detail = null;
        do {
            do {
                try {
                    System.out.println("Nhập ID bạn muốn sửa");
                    System.out.print("==> ");
                    id = Integer.parseInt(scanner.nextLine());
                    if (!snackBarService.checkDuplicateIdUnlockProduct(id)) {
                        System.out.println("ID sản phẩm không tồn tại, vui lòng nhập lại!!!");
                    } else {
                        do {
                            try {
                                menuAndDisplay.menuUpdateProduct();
                                System.out.println("Chọn chức năng ");
                                System.out.print("==> ");
                                option = Integer.parseInt(scanner.nextLine());
                                SnackBar snackBar = snackBarService.getById(id);
                                switch (option) {
                                    case 1:
                                        String temp = scanner.nextLine();
                                        name = getNameUpdate();
                                        snackBar.setName(name);
                                        snackBarService.update(id, snackBar);
                                        System.out.println("Sửa tên sản phẩm thành công!!!");
                                        String updateNameProduct = null;
                                        do {
                                            try {
                                                System.out.println("Nhấn 'c' để tiếp tục sửa");
                                                System.out.println("     'b' để quay lại ");
                                                System.out.println("     'q' để thoát chương trình");
                                                updateNameProduct = scanner.nextLine();
                                                switch (updateNameProduct) {
                                                    case "c":
                                                        updateProduct();
                                                        break;
                                                    case "b":
                                                        showManagerProduct();
                                                        break;
                                                    case "p":
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
                                                        System.out.println("Không có chức năng, vui lòng nhập lại");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Nhấn không hợp lệ, vui lòng nhập lại!!");
                                            }

                                        } while (updateNameProduct != null);
                                        break;
                                    case 2:
                                        price = getPriceUpdate();
                                        snackBar.setPrice(price);
                                        snackBarService.update(id, snackBar);
                                        System.out.println("Sửa giá sản phẩm thành công!!");
                                        String updatePriceProduct = null;
                                        do {
                                            try {
                                                System.out.println("Nhấn 'c' để tiếp tục sửa");
                                                System.out.println("     'b' để quay lại ");
                                                System.out.println("     'q' để thoát chương trình");
                                                updatePriceProduct = scanner.nextLine();
                                                switch (updatePriceProduct) {
                                                    case "c":
                                                        updateProduct();
                                                        break;
                                                    case "b":
                                                        showManagerProduct();
                                                        break;
                                                    case "p":
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
                                                        System.out.println("Không có chức năng, vui lòng nhập lại");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Nhấn không hợp lệ, vui lòng nhập lại!!");
                                            }

                                        } while (updatePriceProduct != null);
                                        break;
                                    case 3:
                                        quantity = getQuantity();
                                        snackBar.setQuantity(quantity);
                                        snackBarService.update(id, snackBar);
                                        System.out.println("Sửa số lượng sản phẩm thành công!!!");
                                        String updateQuantityProduct = null;
                                        do {
                                            try {
                                                System.out.println("Nhấn 'c' để tiếp tục sửa");
                                                System.out.println("     'b' để quay lại ");
                                                System.out.println("     'q' để thoát chương trình");
                                                updateQuantityProduct = scanner.nextLine();
                                                switch (updateQuantityProduct) {
                                                    case "c":
                                                        updateProduct();
                                                        break;
                                                    case "b":
                                                        showManagerProduct();
                                                        break;
                                                    case "p":
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
                                                        System.out.println("Không có chức năng, vui lòng nhập lại");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Nhấn không hợp lệ, vui lòng nhập lại!!");
                                            }

                                        } while (updateQuantityProduct != null);
                                        break;
                                    case 4:
                                        System.out.println("Mô tả sản phẩm");
                                        detail = scanner.nextLine();
                                        snackBar.setDetail(detail);
                                        snackBarService.update(id, snackBar);
                                        System.out.println("Sửa mô tả sản phẩm thành công!!");
                                        String updateDetailProduct = null;
                                        do {
                                            try {
                                                System.out.println("Nhấn 'c' để tiếp tục sửa");
                                                System.out.println("     'b' để quay lại ");
                                                System.out.println("     'q' để thoát chương trình");
                                                updateDetailProduct = scanner.nextLine();
                                                switch (updateDetailProduct) {
                                                    case "c":
                                                        updateProduct();
                                                        break;
                                                    case "b":
                                                        showManagerProduct();
                                                        break;
                                                    case "p":
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
                                                        System.out.println("Không có chức năng, vui lòng nhập lại");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Nhấn không hợp lệ, vui lòng nhập lại!!");
                                            }

                                        } while (updateDetailProduct != null);
                                        break;
                                    default:
                                        System.out.println("Không có chức năng, vui lòng nhập lại!!");
                                }

                            } catch (Exception e) {
                                System.out.println("Nhập không hợp lệ, vui lòng nhập lại!!");
                            }
                            String backUpdate = null;
                            do {
                                try {
                                    System.out.println("Nhấn 'c' để tiếp tục sửa");
                                    System.out.println("     'b' để quay lại ");
                                    System.out.println("     'q' để thoát chương trình");
                                    backUpdate = scanner.nextLine();
                                    switch (backUpdate) {
                                        case "c":
                                            updateProduct();
                                            break;
                                        case "b":
                                            showManagerProduct();
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
                                            System.out.println("Không có chức năng, vui lòng nhập lại");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Nhấn không hợp lệ, vui lòng nhập lại!!");
                                }

                            } while (backUpdate != null);


                        } while (option != -1);
                    }

                } catch (Exception e) {
                    System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
                }
            } while (!snackBarService.checkDuplicateId(id));
        } while (option != -1);
    }

    private int getQuantity() {
        boolean check = false;
        int quantity = 0;
        do {     // check quantity update
            try {
                check = false;
                System.out.println("Nhập số lượng sản phẩm: ");
                System.out.print("==>");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.println("Số lượng không được nhỏ hơn F1");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Số lượng không hợp lệ, vui lòng nhập lại");
                check = true;
            }
        } while (check);
        return quantity;
    }

    private String getNameUpdate() {
        boolean check = false;
        String name = null;
        do {
            try {
                check = false;
                System.out.println("Nhập tên mới cho sản phẩm bạn muốn sửa");
                System.out.print("==> ");
                name = scanner.nextLine();
                if (snackBarService.checkDuplicateName(name)) {
                    System.out.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Tên không hợp lệ, vui lòng nhập lại");
                check = true;
            }
        } while (check);
        return name;
    }

    private int getPriceUpdate() {
        boolean check = false;
        int price = 0;
        do {    // Check price updat
            try {
                check = false;
                System.out.println("Nhập giá sản phẩm: ");
                System.out.print("==>");
                price = Integer.parseInt(scanner.nextLine());
                if (price < 10000) {
                    System.out.println("Giá sản phẩm phải lớn hơn 0 và nhỏ hơn 100K");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Giá không hợp lệ, vui lòng nhập lại");
                check = true;
            }
        } while (price < 10000);
        return price;
    }

    public void lockProduct() {
        int id = 0;
        boolean check = false;
        do {
            try {
                menuAndDisplay.displayProduct();
                System.out.println("Nhập ID sản phẩm bạn muốn khóa");
                System.out.println("==> ");
                id = Integer.parseInt(scanner.nextLine());
                if (!snackBarService.checkDuplicateId(id)) {
                    System.out.println("ID sản phẩm không tồn tại, vui lòng nhập lại");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("ID không hợp lệ, vui lòng nhập lại");
            }
        } while (check = false);
        snackBarService.lockSnackBar(id);
        System.out.println("Khóa sản phẩm thành công!!!");
        menuAndDisplay.disPlayProductLock();
    }

    //        Mở Khóa sản phẩm
    public void unlockProduct() {
        if (snackBarService.getLockProduct().size() == 0) {
            System.out.println("KHÔNG CÓ SẢN PHẨM NÀO BỊ KHÓA, VUI LÒNG NHẬP LẠI");
            return;
        }
        int id = 0;
        boolean check = false;
        do {
            try {
                check = false;
                menuAndDisplay.disPlayProductLock();
                System.out.println("Nhập ID bạn muốn mở khóa");
                System.out.print("==> ");
                id = Integer.parseInt(scanner.nextLine());
                if (!snackBarService.checkDuplicateId(id)) {
                    System.out.println("ID không tồn tại, vui lòng nhập lại!!");
                    check = true;
                }
                if (snackBarService.checkDuplicateIdUnlockProduct(id)) {
                    System.out.println("Sản phẩm chưa bị khóa, vui lòng chọn lại!!!");
                    check = true;
                }
                List<SnackBar> lockProductList = snackBarService.getLockProduct();
                SnackBar lockSnackBar = null;
                for (SnackBar snackBar : lockProductList) {
                    if (snackBar.getId() == id) {
                        lockSnackBar = snackBar;
                        break;
                    }
                }
                lockProductList.remove(id);
                snackBarService.update(id, lockSnackBar);

            } catch (Exception e) {
                System.out.println("Nhập không đúng, vui lòng nhập lại");
                check = true;
            }
        } while (check = false);
    }

    public void quayXe() {
        String choice = null;
        do {
            System.out.println("|          'c' ĐỂ QUAY LẠI QUẢN LÝ SẢN PHẨM   |");
            System.out.println("|          'd' ĐỂ QUAY LẠI ĐĂNG NHẬP          |");
            System.out.println("|          'q' ĐỂ THOÁT CHƯƠNG TRÌNH          |");
            System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
            try {
                choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        add();
                        break;
                    case "c":
                        showManagerProduct();
                        break;
                    case "d":
                        LoginView loginView = new LoginView();
                        loginView.menuLogin();
                        break;
                    case "q":

                    default:
                        System.out.println("Nhập không đúng cú phảp vui lòng nhập lai");
                }
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
            }
        } while (choice != null);
    }
}
