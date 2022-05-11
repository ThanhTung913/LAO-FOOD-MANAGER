package view;

import model.Product;
import service.ProductService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ProductMangerView {
    Scanner scanner = new Scanner(System.in);
    ProductService snackBarService = new ProductService();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " VND");
    MenuAndDisplay menuAndDisplay = new MenuAndDisplay();


    public static void main(String[] args) {
        ProductMangerView productMangerView = new ProductMangerView();
        productMangerView.showManagerProduct();
    }

    public void showManagerProduct() {
        int number = -1;
        do {
            try {
                menuAndDisplay.menuManagerProduct();
                System.out.print("Chọn lựa chọn bạn muốn làm: ");
                number = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
            switch (number) {
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
                    break;
                default:
                    System.out.println("Nhập sai, mời nhập lại!!!");
                    break;
            }
        } while (number != 0);
    }

    //    ADDDDDDDDD VÀOOOOOOOOOOOOOOOOO
    public void add() {
        Product snackBar = new Product();
        boolean check = false;
        int id = 0;
        String newNameProduct = null;
        int price = 0;
        int quantity = 0;

        do {          // check id add
            try {
                check = false;
                System.out.println("Nhập ID bạn muốn thêm");
                System.out.print("==>");
//                String temp = scanner.nextLine();
//                if (temp.equals("hai")) {
//                    System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
//                    System.out.println("|    NHẤN  'b' ĐỂ THÊM SẢN PHẨM               |");
//                    quayXeProduct();
//                } else {
                    id = getIdAdd();
                    if (snackBarService.checkDuplicateId(id)) {
                        System.out.println("ID sản phẩm đã tồn tại, vui lòng nhập lại");
                        check = true;
                    }
//                }
            } catch (Exception e) {
                System.out.println("ID không hợp lệ, vui lòng nhập lại");
                check = true;
            }
        } while (check);

        do {      // check name add
            try {
                check = false;
                System.out.println("Nhập tên sản phẩm bạn muốn thêm");
                System.out.print("==>");
//                newNameProduct = scanner.nextLine();
                String temp = scanner.nextLine();
                if (temp.equals("hai"))
                    return;
                newNameProduct = temp;
                if (snackBarService.checkDuplicateName(newNameProduct)) {
                    System.out.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                    check = true;
                } else if (newNameProduct == null) {
                    System.out.println("Tên không được để trống, vui lòng nhập lại");
                }
                ;
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
                    quayXeProduct();
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
                    quayXeProduct();
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
                quayXeProduct();
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
                                Product snackBar = snackBarService.getById(id);
                                switch (option) {
//                                    case 1:
////                                        String temp = scanner.nextLine();
//                                        name = getNameUpdate();
//                                        snackBar.setName(name);
//                                        snackBarService.update(id, snackBar);
//                                        System.out.println("Sửa tên sản phẩm thành công!!!");
//                                        String updateNameProduct = null;
//                                        do {
//                                            try {
//                                                System.out.println("Nhấn 'c' để tiếp tục sửa");
//                                                System.out.println("     'b' để quay lại ");
//                                                System.out.println("     'q' để thoát chương trình");
//                                                updateNameProduct = scanner.nextLine();
//                                                switch (updateNameProduct) {
//                                                    case "c":
//                                                        updateProduct();
//                                                        break;
//                                                    case "b":
//                                                        showManagerProduct();
//                                                        break;
//                                                    case "p":
//                                                        Thread_exist thread_exist = new Thread_exist();
//                                                        Thread thread1 = new Thread(thread_exist);
//                                                        thread1.start();
//                                                        try {
//                                                            Thread.sleep(3000);
//                                                        } catch (InterruptedException e) {
//                                                            e.printStackTrace();
//                                                        }
//                                                        break;
//                                                    default:
//                                                        System.out.println("Không có chức năng, vui lòng nhập lại");
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Nhấn không hợp lệ, vui lòng nhập lại!!");
//                                            }
//
//                                        } while (updateNameProduct != null);
//                                        break;
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
                                        break;
                                }

                            } catch (Exception e) {
                                System.out.println("Nhập không hợp lệ, vui lòng nhập lại!!");
                            }
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
        snackBarService.lockProduct(id);
        System.out.println("Khóa sản phẩm thành công!!!");
        menuAndDisplay.disPlayProductLock();
    }

//            Mở Khóa sản phẩm
    public void unlockProduct() {
        int id;
        List<Product> userListLock = snackBarService.getLockProduct();
        if (userListLock.size() == 0) {
            System.out.println("Hiện không có sản phẩm nào bị khóa");
            System.out.println("Nhấn phím bất kì để quay lại!!!");
            return;
        }
        do {
            System.out.print("Nhập ID sản phẩm bạn muốn mở khóa: ");
            id = Integer.parseInt(scanner.nextLine());
            if (!snackBarService.checkDuplicateId(id)) {
                System.out.println("ID này chưa tồn tại, vui lòng nhập lại tên khác!!!");
            }
        } while (!snackBarService.checkDuplicateId(id));

        int option = 0;
        do {
            try {
                System.out.println("Nhập '1' nếu bạn chắc chắn muốn mở khóa, nhập '2' để quay lại");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        Product unlockProduct = snackBarService.unlockProduct(id);
                      menuAndDisplay.disPlayProductLock();
                        System.out.println("Bạn đã mở khóa thành công!!!");
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Chọn không đúng chức năng, chọn lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai dữ liệu, mời nhập lại!!!!");
            }
        } while (option != 2 && option != 1);
    }


    public void quayXeProduct() {
        String choice = null;
        do {
            System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
            System.out.println("|    NHẤN  'b' ĐỂ THÊM SẢN PHẨM               |");
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
