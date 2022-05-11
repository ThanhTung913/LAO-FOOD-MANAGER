package view;

import service.*;

import java.util.Scanner;

public class MainView {
    private final IUserService USER_SERVICE;
    private final IProductService SNACK_BAR_SERVICE;
    private final IOrderItemService ORDER_SERVICE_SERVICE;

    MenuAndDisplay menuAndDisplay = new MenuAndDisplay();

    public MainView() {
        USER_SERVICE = new UserService();
        SNACK_BAR_SERVICE = new ProductService();
        ORDER_SERVICE_SERVICE = new OrderItemService();
    }

    Scanner scanner = new Scanner(System.in);
    ProductMangerView productMangerView = new ProductMangerView();
    OrderProductView orderView = new OrderProductView();
    UserView userView = new UserView();
//    public void showMain() {
//        Thread_Login threadLogin = new Thread_Login();
//        Thread thread = new Thread(threadLogin);
//        thread.start();
//        try {
//            thread.join();
//        }catch (Exception e){
//            System.out.println(" ");
//        }
//
//        int option = -1;
//        do {
//            try {
//                menuAndDisplay.mainMenu();
//                System.out.println("Chọn chức năng:");
//                System.out.print("==> ");
//                option = scanner.nextInt();
//                switch (option) {
//                    case 1:
//                        userView.showMenuUser();
//                        break;
//                    case 2:
//                        productMangerView.showManagerProduct();
//                        break;
//                    case 3:
//                        orderView.showOrder();
//                        break;
//                    case 0:
////                        System.out.println("Tạm biệt!!!");
//                        Thread_exist thread_exist = new Thread_exist();
//                        Thread thread1 = new Thread(thread_exist);
//                        thread1.start();
//                        break;
//                    default:
//                        System.out.println("Sai cú pháp!! Xin mời nhập lại");
//                        break;
//                }
//            } catch (Exception e) {
//                System.err.println("Nhập không đúng, xin mời nhập lại");
//            }
//        } while (option != -1);
//    }

    public void showMain() {
        Thread_Login threadLogin = new Thread_Login();
        Thread thread = new Thread(threadLogin);
        thread.start();
        try {
            thread.join();
        }catch (Exception e){
            System.out.println(" ");
        }
        int option = -1;
        do {
            try {
                System.out.println("|-------------------------------------------------------------------------------------------------------|");
                System.out.println("|                                           ẨM THỰC LÀO - TUNGTP                                        |");
                System.out.println("|-------------------------------------------------------------------------------------------------------|");
                menuAndDisplay.mainMenu();
                System.out.println("Chọn chức năng:");
                System.out.print("==> ");
                option = Integer.parseInt(scanner.nextLine());
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
                        System.out.println("Chào tạm biệt và hẹn gặp lại!!!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập sai lựa chọn, vui lòng nhập lại!!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
        } while (option != 0);
    }
}
