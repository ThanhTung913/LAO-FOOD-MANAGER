package view;

import java.util.Scanner;

public class TurnTheCar {
    Scanner scanner = new Scanner(System.in);
    MainView mainView = new MainView();
    ProductMangerView productMangerView = new ProductMangerView();
    UserView userView = new UserView();
    OrderProductView orderView = new OrderProductView();


    public void turnTheCar() {
        int choice = -1;
        do {
            try {
                System.out.println("|=============================================|");
                System.out.println("|      NHẤN CÁC PHÍM SAU ĐỂ CHỌN CHỨC NĂNG:   |");
                System.out.println("|        T1. QUAY LẠI MENU CHÍNH              |");
                System.out.println("|        T2. QUAY LẠI MENU PHỤ                |");
                System.out.println("|        T3. THOÁT CHƯƠNG TRÌNH               |");
                System.out.println("|=============================================");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        mainView.showMain();
                        break;
                    case 2:
                        productMangerView.showManagerProduct();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhaoaj lại");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } while (choice != -1);


    }
}
