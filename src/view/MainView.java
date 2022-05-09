package view;

import service.*;

import java.util.Scanner;

public class MainView {
    private final IUserService USER_SERVICE;
    private final ISnackBarService SNACK_BAR_SERVICE;
    private final IOrderItemService ORDER_SERVICE_SERVICE;
    Scanner scanner = new Scanner(System.in);
    MenuAndDisplay menuAndDisplay = new MenuAndDisplay();

    public MainView() {
        USER_SERVICE = new UserService();
        SNACK_BAR_SERVICE = new SnackBarService();
        ORDER_SERVICE_SERVICE = new OrderItemService();
    }

    public void showMain() {
        Thread_Login threadLogin = new Thread_Login();
        Thread thread = new Thread(threadLogin);
        thread.start();
        try {
            thread.join();
        }catch (Exception e){
            System.out.println(" ");
        }

        Scanner scanner = new Scanner(System.in);
        ProductMangerView productMangerView = new ProductMangerView();
        OrderProductView orderView = new OrderProductView();
        UserView userView = new UserView();

        int option = -1;
        do {
            try {
                menuAndDisplay.mainMenu();
                System.out.println("Chọn chức năng:");
                System.out.print("==> ");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        userView.showMenuUser();
                        break;
                    case 2:
                        productMangerView.showManagerProduct();
                        break;
                    case 3:
                        orderView.showOrder();
                        break;
                    case 0:
//                        System.out.println("Tạm biệt!!!");
                        Thread_exist thread_exist = new Thread_exist();
                        Thread thread1 = new Thread(thread_exist);
                        thread1.start();
                        break;
                    default:
                        System.out.println("Sai cú pháp!! Xin mời nhập lại");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Nhập không đúng, xin mời nhập lại");
            }
        } while (option != -1);
    }


}
