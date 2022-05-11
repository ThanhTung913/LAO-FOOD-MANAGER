package view;

import model.Role;
import model.User;
import service.ProductService;
import service.UserService;

import java.util.Scanner;

public class LoginView {
    private final UserService USER_SERVICE = new UserService();
    private final MainView MAIN_VIEW = new MainView();
    private final ProductService SNACKBAR_SERVICE = new ProductService();
    private final OrderProductView ORDER_VIEW = new OrderProductView();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.menuLogin();
    }


    public void menuLogin() {
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
        System.out.println("|                                ĐĂNG NHẬP                                |");
        System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
        String userName = null;
        String password = null;
        do {
            try {
                System.out.println("UserName: ");
                System.out.print("=> ");
                userName = scanner.nextLine();
                System.out.println("Password: ");
                System.out.print("=> ");
                password = scanner.nextLine();
                if (USER_SERVICE.loginAdmin(userName, password)) {
                    System.out.println("Đăng nhập thành công!!!");
                    System.out.println("----------------------------------------------------------");
                    MAIN_VIEW.showMain();
                } else {
                    System.out.println("Nhập sai, vui lòng nhập lại");
                }
                User user = USER_SERVICE.getUserByUserName(userName);
                if (user.getRole().equals(Role.ADMIN)) {
                    MAIN_VIEW.showMain();
                } else if (user.getRole().equals(Role.USER)) {
                    ORDER_VIEW.showOrder();
                }
            } catch (Exception e) {
                System.out.println();
            }

        } while (!USER_SERVICE.loginAdmin(userName, password));


    }

}

