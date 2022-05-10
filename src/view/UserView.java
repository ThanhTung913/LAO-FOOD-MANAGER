package view;

import Utils.ValidateUtils;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserView {
    Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    MenuAndDisplay menuAndDisplay = new MenuAndDisplay();

    public static void main(String[] args) {
        UserView viewManageUser = new UserView();
        viewManageUser.showMenuUser();
    }

    public void showMenuUser() {
        int option = -1;
        do {
            try {
                MenuAndDisplay.menuManagerUser();
                System.out.print("Chọn chức năng bạn muốn làm: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        updateUser();
                        break;
                    case 3:
                        lockUser();
                        break;
                    case 4:
                        unlockUser();
                        break;
                    case 5:
                        menuAndDisplay.displayListUser();
                        break;
                    case 6:
                        menuAndDisplay.displayLockUserList();
                        break;
                    case 7:
                        menuAndDisplay.searchUserByName();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Chọn sai chức năng, vui lòng chọn đúng chức năng!!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Phím không hợp lệ, vui lòng chọn đúng!!!!");
            }

        } while (option != 0);
    }

    public void addUser() {
        int id = 0;
        int option = -1;
        do {
            do {
                id = getIdUser();
                if (userService.checkDuplicateId(id)) {
                    System.out.println("Id đã tồn tại, vui lòng nhập lại!!!");
                }
            } while (userService.checkDuplicateId(id));
            String userName;
            do {
                userName = getUseName(1);
                if (userService.checkDuplicateUserName(userName)) {
                    System.out.println("Tên đăng nhập đã tồn tại, vui lòng nhập lại");
                }
            } while (userService.checkDuplicateUserName(userName));
            String passWord = getPassWord(1);
            String name;
            do {
                name = getNameUser(1);
                if (userService.checkDuplicateName(name)) {
                    System.out.println("Tên đã tồn tại, vui lòng nhập lại");
                }
            } while (userService.checkDuplicateName(name));
            String phone;
            do {
                phone = getPhoneUser(1);
                if (userService.checkDuplicatePhone(phone)) {
                    System.out.println("Số điện thoại đã tồn tại, vui lòng nhập lại");
                }
            } while (userService.checkDuplicatePhone(phone));

            String email;
            do {
                email = getEmailUser(1);
                if (userService.checkDuplicateEmai(email)) {
                    System.out.println("Địa chỉ email đã tồn tại, vui lòng nhập lại");
                }
            } while (userService.checkDuplicateEmai(email));
            System.out.print("Nhập địa chỉ người dùng: ");
            String address = scanner.nextLine();
            User newUser = new User();
            newUser.setName(name);
            newUser.setIdUser(id);
            newUser.setUserName(userName);
            newUser.setAddress(address);
            newUser.setEmail(email);
            newUser.setPhone(phone);
            newUser.setPassword(passWord);
            menuAndDisplay.SetRole(newUser);
            userService.addUser(newUser);
            System.out.println("Đã thêm vào thành công!!");
            String addUserCtn = null;
            do {
                try {
                    System.out.println("Nhấn 'c' để tiếp tục thêm");
                    System.out.println("     'b' để quay lại ");
                    System.out.println("     'q' để thoát chương trình");
                    addUserCtn = scanner.nextLine();
                    switch (addUserCtn) {
                        case "c":
                            addUser();
                            break;
                        case "b":
                            showMenuUser();
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
            } while (addUserCtn != null);
        } while (option != 2);
    }


    public void updateUser() {
        int id = 0;
        int option = -1;
        do {
            try {
                menuAndDisplay.displayListUser();
                System.out.print("Nhập id sản phẩm bạn muốn sửa: ");
                id = Integer.parseInt(scanner.nextLine());
                if (!userService.checkDuplicateId(id)) {
                    System.out.println("Id chưa tồn tại, vui lòng nhập lại!!!");
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dự liệu, vui lòng nhập lại!!!");
            }

        } while (id < 0 || !userService.checkDuplicateId(id));

        String useName;
        do {
            useName = getUseName(2);
            if (userService.checkDuplicateUserName(useName)) {
                System.out.println("Tên đăng nhập đã tồn tại, vui lòng nhập lại");
            }
        } while (userService.checkDuplicateUserName(useName));
        String passWord = getPassWord(2);
        String name;
        do {
            name = getNameUser(2);
            if (userService.checkDuplicateName(name)) {
                System.out.println("Tên đã tồn tại, vui lòng nhập lại");
            }
        } while (userService.checkDuplicateName(name));
        String phone;
        do {
            phone = getPhoneUser(2);
            if (userService.checkDuplicatePhone(phone)) {
                System.out.println("Số điện thoại đã tồn tại, vui lòng nhập lại");
            }
        } while (userService.checkDuplicatePhone(phone));

        String email;
        do {
            email = getEmailUser(2);
            if (userService.checkDuplicateEmai(email)) {
                System.out.println("Địa chỉ email đã tồn tại, vui lòng nhập lại");
            }
        } while (userService.checkDuplicateEmai(email));
        System.out.print("Nhập lại địa chỉ: ");
        String address = scanner.nextLine();
        User editUser = userService.getById(id);
        editUser.setName(name);
        editUser.setUserName(useName);
        editUser.setAddress(address);
        editUser.setEmail(email);
        editUser.setPhone(phone);
        editUser.setPassword(passWord);
        User u1 = userService.updateUser(id, editUser);
        String updateUserCtn = null;
        do {
            try {
                System.out.println("Nhấn 'c' để tiếp tục sửa");
                System.out.println("     'b' để quay lại ");
                System.out.println("     'q' để thoát chương trình");
                updateUserCtn = scanner.nextLine();
                switch (updateUserCtn) {
                    case "c":
                        updateUser();
                        break;
                    case "b":
                        showMenuUser();
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

        } while (updateUserCtn != null);

    }

    private String getEmailUser(int flag) {
        String email;
        do {
            if (flag == 1) {
                System.out.print("Nhập địa chỉ email bạn muốn thêm: ");
            }
            if (flag == 2) {
                System.out.print("Nhập địa chỉ email mới bạn muốn sửa: ");
            }
            email = scanner.nextLine();
            if (!ValidateUtils.isEmailValid(email)) {
                System.out.println("Email không đúng định dạng, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isEmailValid(email));

        return email;
    }

    private String getPhoneUser(int flag) {
        String phone = null;
        do {
//            String temp = scanner.nextLine();
//            if (temp.equals("hai")) {
//                String choice = null;
//                do {
//                    System.out.println("|          'c' ĐỂ QUAY LẠI QUẢN LÝ SẢN PHẨM   |");
//                    System.out.println("|          'd' ĐỂ QUAY LẠI ĐĂNG NHẬP          |");
//                    System.out.println("|          'q' ĐỂ THOÁT CHƯƠNG TRÌNH          |");
//                    System.out.println("|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|");
//                    try {
//                        choice = scanner.nextLine();
//                        switch (choice) {
//                            case "b":
//                                addUser();
//                                break;
//                            case "c":
//                                showMenuUser();
//                                break;
//                            case "d":
//                                LoginView loginView = new LoginView();
//                                loginView.menuLogin();
//                                break;
//                            case "q":
//                                Thread_exist thread_exist = new Thread_exist();
//                                Thread thread1 = new Thread(thread_exist);
//                                thread1.start();
//                                try {
//                                    Thread.sleep(3000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                            default:
//                                System.out.println("Nhập không đúng cú phảp vui lòng nhập lai");
//                        }
//                    } catch (Exception e) {
//                        System.out.println("Nhập không hợp lệ, vui lòng nhập lại");
//                    }
//                } while (choice != null);
//            }else {
            if (flag == 1) {
                System.out.print("Nhập số điện thoại: ");
            }
            if (flag == 2) {
                System.out.print("Nhập số điện thoại mới bạn muốn sửa: ");
            }

            phone = scanner.nextLine();
            if (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số điện thoại không đúng định dạng, vui lòng nhập lại!!!");
            }

        } while (!ValidateUtils.isPhoneValid(phone));

        return phone;
    }

    private String getNameUser(int flag) {
        String name;
        do {
            if (flag == 1) {
                System.out.print("Nhập tên người dùng bạn muốn thêm: ");
            }
            if (flag == 2) {
                System.out.print("Nhập tên người dùng mới bạn muốn sửa: ");
            }
            name = scanner.nextLine();
            if (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên không đúng định dạng, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isNameValid(name));

        return name;
    }

    private String getPassWord(int flag) {
        String passWord;
        do {
            if (flag == 1) {
                System.out.print("Nhập mật khẩu của bạn: ");
            }
            if (flag == 2) {
                System.out.print("Nhập mật khẩu mới bạn muốn sửa: ");
            }
            passWord = scanner.nextLine();
            if (!ValidateUtils.isPasswordValid(passWord)) {
                System.out.println("Mật khẩu như Tùng, quá yếu, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isPasswordValid(passWord));
        return passWord;
    }

    private String getUseName(int flag) {
        String useName;
        do {
            if (flag == 1) {
                System.out.print("Nhập tên đăng nhập bạn muốn thêm: ");
            }
            if (flag == 2) {
                System.out.print("Nhập tên đăng nhập mới bạn muốn sửa: ");
            }
            useName = scanner.nextLine();
            if (!ValidateUtils.isNameValid(useName)) {
                System.out.println("Tên không đúng định dạng, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isNameValid(useName));
        return useName;
    }

    private int getIdUser() {
        int id = 0;
        do {
            try {
                System.out.print("Nhập Id người dùng: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id < 0) {
                    System.out.println("Id phải lớn hơn 0!!!");
                }
            } catch (Exception e) {
                System.out.println("Nhập không đứng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
        } while (id < 1);
        return id;
    }

    //Lock User
    private void lockUser() {
        int option = -1;
        String choice;
        String userName;
        do {
            menuAndDisplay.displayListUser();
            System.out.print("Nhập tên đăng nhập người dùng bạn muốn khóa: ");
            userName = scanner.nextLine();
            if (!userService.checkDuplicateUserName(userName)) {
                System.out.println("Tên đăng nhập người dùng không tồn tại, vui lòng nhập lại!!!");
            }
        } while (!userService.checkDuplicateUserName(userName));
        System.out.println("Nhấn 'y' nếu bạn chắc chắn muốn khóa | nhấn 'b' để quay lại");
        do {
            choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    User userLock = userService.lockUser(userName);
                    System.out.println("Khóa người dùng thành công!!");
                    menuAndDisplay.displayLockUserList();
                    String lockUserCtn = null;
                    do {
                        try {
                            System.out.println("Nhấn 'c' để tiếp tục khóa người dùng");
                            System.out.println("     'b' để quay lại ");
                            System.out.println("     'q' để thoát chương trình");
                            lockUserCtn = scanner.nextLine();
                            switch (lockUserCtn) {
                                case "c":
                                    lockUser();
                                    break;
                                case "b":
                                    showMenuUser();
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

                    } while (lockUserCtn != null);
                    break;
                case "b":
                    break;
                default:
                    System.out.println("Nhập không chính xác, vui lòng nhập lại!!");
                    break;
            }
        } while (option != -1);
    }

    private void unlockUser() {
        String userName;
        List<User> userListLock = userService.getLockUser();
        if (userListLock.size() == 0) {
            System.out.println("Hiện không có tài khoản nào bị khóa");
            System.out.println("Nhấn phím bất kì để quay lại!!!");
            return;
        }
        do {
            System.out.print("Nhập tên đăng nhập người dùng bạn muốn mở khóa: ");
            userName = scanner.nextLine();
            if (!userService.checkDuplicateUserName(userName)) {
                System.out.println("Tên đăng nhập này chưa tồn tại, vui lòng nhập lại tên khác!!!");
            }
            if (!ValidateUtils.isNameValid(userName)) {
                System.out.println("Nhập không đúng định dạng tên đăng nhập, vui lòng nhập lại");
            }
        } while (!ValidateUtils.isNameValid(userName) || !userService.checkDuplicateUserName(userName));
        User userUnlock = userService.unblockUser(userName);
        menuAndDisplay.displayListUser();

    }


}

