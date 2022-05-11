package view;

import model.Role;
import model.Product;
import model.User;
import service.ProductService;
import service.UserService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class MenuAndDisplay {
    ProductService snackBarService = new ProductService();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " VND");
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menuManagerUser();
    }

    public static void mainMenu() {
        System.out.println("");
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_|");
        System.out.println("|                        MAIN MENU                         |");
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_|");
        System.out.println("|                 1. QUẢN LÍ NGƯỜI DÙNG                    |");
        System.out.println("|                 2. QUẢN LÍ SẢN PHẦM                      |");
        System.out.println("|                 3. QUẢN LÍ ORDER                         |");
        System.out.println("|                 4. DOANH THU                             |");
        System.out.println("|                                0. THOÁT CHƯƠNG TRÌNH     |");
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_|");
    }

    public static void menuOrder() {
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
        System.out.println("|                                                                         |");
        System.out.println("|                              ORDER SẢN PHẨM                             |");
        System.out.println("|                                                                         |");
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
        System.out.println("|                    1. CHỌN SẢN PHẨM                                     |");
        System.out.println("|                    2. SỬA SẢN PHẨM ĐÃ CHỌN                              |");
        System.out.println("|                    3. HIỂN THỊ DANH SÁCH SẢN PHẨM ĐÃ CHỌN               |");
        System.out.println("|                    4. QUAY LẠI                                          |");
        System.out.println("|                    6. THANH TOÁN                                         |");
        System.out.println("|                                               5. THOÁT CHƯƠNG TRÌNH     |");
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
    }

    public static void menuManagerProduct() {
        System.out.println("|---------------------------------------------------------------|");
        System.out.println("|                                                               |");
        System.out.println("|_______________________________________________________________|");
        System.out.println("|                    1. THÊM SẢN PHẨM                           |");
        System.out.println("|                    2. SỬA SẢN PHẨM                            |");
        System.out.println("|                    3. ẨN SẢN PHẨM                             |");
        System.out.println("|                    4. MỞ KHÓA SẢN PHẨM                        |");
        System.out.println("|                    5. HIỂN THỊ DANH SÁCH SẢN PHẨM             |");
        System.out.println("|                    6. TÌM KIẾM SẢN PHẨM                       |");
        System.out.println("|                    7. QUAY LẠI                                |");
        System.out.println("|                                    0. THOÁT CHƯƠNG TRÌNH      |");
        System.out.println("|_______________________________________________________________|");
    }

    public static void menuManagerUser() {
        System.out.println("|===========================================================|");
        System.out.println("|                          USER MANAGER                     |");
        System.out.println("|___________________________________________________________|");
        System.out.println("|            1. THÊM NGƯỜI DÙNG                             |");
        System.out.println("|            2. SỬA THÔNG TIN NGƯỜI DÙNG                    |");
        System.out.println("|            3. KHÓA NGƯỜI DÙNG                             |");
        System.out.println("|            4. MỞ KHÓA NGƯỜI DÙNG                          |");
        System.out.println("|            5. HIỂN THỊ DANH SÁCH NGƯỜI DÙNG               |");
        System.out.println("|            6. HIỂN THỊ DANH SÁCH NGƯỜI DÙNG BỊ KHÓA       |");
        System.out.println("|            7. TÌM KIẾM NGƯỜI DÙNG                         |");
        System.out.println("|            8. QUAY LẠI                                    |");
        System.out.println("|                                    0. THOÁT CHƯƠNG TRÌNH  |");
        System.out.println("|___________________________________________________________|");
    }

    public void displayProduct() {
        List<Product> snackBarList = snackBarService.sortProduct();
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.println("|                              DANH SÁCH SẢN PHẨM                                     |");
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Mô tả sản phẩm");
        for (Product snackBar : snackBarList) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", snackBar.getId(), snackBar.getName(),
                    decimalFormat.format(snackBar.getPrice()), snackBar.getQuantity(), snackBar.getDetail());
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }


    // MENU AND DISPLAY MANGER PRODUCT
    public void disPlayProductLock() {
        List<Product> snackBarList = snackBarService.getLockProduct();
        System.out.println("|-----------------------------------------------------------------------------------------|");
        System.out.println("|                            DANH SÁCH SẢN PHẨM BỊ KHÓA                                   |");
        System.out.println("|-----------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Mô tả sản phẩm");
        for (Product snackBar : snackBarList) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", snackBar.getId(), snackBar.getName(),
                    decimalFormat.format(snackBar.getPrice()), snackBar.getQuantity(), snackBar.getDetail());
        }
        System.out.println("|-----------------------------------------------------------------------------------------|");
    }

    public void searchByName() {
        System.out.println("Nhập tên sản phẩm bạn muốn tìm");
        System.out.print("==> ");
        String name = scanner.nextLine();
        List<Product> searchByName = snackBarService.searchSnackBar(name);
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.println("|                              DANH SÁCH SẢN PHẨM                                     |");
        System.out.println("|-------------------------------------------------------------------------------------|");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Mô tả sản phẩm");
        for (Product snackBar : searchByName) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", snackBar.getId(), snackBar.getName(),
                    decimalFormat.format(snackBar.getPrice()), snackBar.getQuantity(), snackBar.getDetail());
        }
        System.out.println("|--------------------------------------------------------------------------------------|");
    }

    public void menuUpdateProduct() {
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|                           UPDATE                               |");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|             1. SỬA TÊN SẢN PHẨM                                |");
        System.out.println("|             2. SỬA GIÁ SẢN PHẨM                                |");
        System.out.println("|             3. SỬA SỐ LƯỢNG SẢN PHẨM                           |");
        System.out.println("|             4. SỬA MÔ TẢ SẢN PHẨM                              |");
        System.out.println("|             5. QUAY LẠI                                        |");
        System.out.println("|                                     0. THOÁT CHƯƠNG TRÌNH      |");
        System.out.println("|----------------------------------------------------------------|");
    }


    //    MENU AND DISPLAY MANAGER USER
    public void SetRole(User newUser) {
        int option = -1;
        do {
            try {
                System.out.println("|________________________|");
                System.out.println("|        SET ROLE        |");
                System.out.println("|________________________|");
                System.out.println("|       1. ADMIN         |");
                System.out.println("|       2. USER          |");
                System.out.println("|________________________|");
                System.out.println("Chọn role: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        newUser.setRole(Role.ADMIN);
                        break;
                    case 2:
                        newUser.setRole(Role.USER);
                        break;
                    default:
                        System.out.println("Nhập không đúng, vui lòng nhập lại");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Nhập không đúng, vui lòng nhập lại");
            }
        } while (option != 1 && option != 2);
    }

    public void searchUserByName() {
        System.out.print("Nhập tên người dùng bạn muốn tìm kiếm: ");
        String searchName = scanner.nextLine();
        List<User> userList = userService.searchByName(searchName);
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                               KẾT QUẢ TÌM KIẾM                                                       |");
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
        for (User user : userList) {
            System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", user.getIdUser(),
                    user.getUserName(), user.getPassword(), user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    public void displayListUser() {
        List<User> userList = userService.getUserService();
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                               THÔNG TIN NGƯỜI DÙNG                                                   |");
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
        for (User user : userList) {
            System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", user.getIdUser(),
                    user.getUserName(), user.getPassword(), user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
    }

    public void displayLockUserList() {
        List<User> userList = userService.getLockUser();
        if (userList.size() == 0) {
            System.out.println("Hiện không có người dùng nào bị khóa!!!");
            return;
        }
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                           THÔNG TIN NGƯỜI DÙNG BỊ KHÓA                                               |");
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
        for (User user : userList) {
            System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", user.getIdUser(),
                    user.getUserName(), user.getPassword(), user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
    }


//    MENU AND DISPLAY MANAGER ORDER

}




