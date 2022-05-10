package service;

import model.User;

import java.util.List;

public interface IUserService {

    List<User> getUserService();

    List<User> getLockUser();

    void addUser(User user);

    User updateUser(int idUser, User user);

    List<User> getUserLock();

    User lockUser(String userName);

    User unblockUser(String userName);

    boolean checkDuplicateId(int idUser);

    boolean checkDuplicateUserName(String userNme);

    boolean checkDuplicateEmai(String email);

    boolean checkDuplicateName(String name);

    boolean checkDuplicatePhone(String phone);

    User getById(int idUser);

    int getIndexById(int idUser);

    User getUserByUserName(String userName);

    List<User> sortByIdASC();

    List<User> searchByName(String nameUser);

    boolean loginAdmin(String userName, String password);


}
