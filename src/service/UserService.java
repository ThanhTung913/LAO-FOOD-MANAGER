package service;

import Utils.CSVUtils;
import Utils.SortUserByIdASC;
import model.Role;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    static final String PATH_USER = "src/data/User.csv";
    static final String PATH_LOCK_USER = "src/data/LockUser.csv";


    @Override
    public List<User> getUserService() {
        List<String> records = CSVUtils.readFile(PATH_USER);
        List<User> userList = new ArrayList<>();
        for (String record : records) {
            userList.add(new User(record));
        }
        return userList;
    }

    @Override
    public List<User> getLockUser() {
        List<String> records = CSVUtils.readFile(PATH_LOCK_USER);
        List<User> lockUserList = new ArrayList<>();
        for (String record : records) {
            lockUserList.add(new User(record));
        }
        return lockUserList;
    }

    @Override
    public void addUser(User user) {
        List<User> userList = getUserService();
        userList.add(user);
        try {
            CSVUtils.writeFile(PATH_USER, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User updateUser(int id, User user) {
        List<User> userList = getUserService();
        int index = getIndexById(id);
        User udateUser = userList.remove(index);
        userList.add(index, user);
        try {
            CSVUtils.writeFile(PATH_USER, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return udateUser;
    }

    @Override
    public User lockUser(String userName) {
        List<User> userList = getUserService();
        List<User> lockUserList = getUserLock();
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                User lockUser = user;
                userList.remove(user);
                lockUserList.add(lockUser);
                try {
                    CSVUtils.writeFile(PATH_LOCK_USER, lockUserList);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    CSVUtils.writeFile(PATH_USER, userList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return lockUser;
            }
        }
        return null;
    }

    @Override
    public User unblockUser(String userName) {
        List<User> userList = getUserService();
        List<User> userLockList = getUserLock();
        for (User user : userLockList) {
            if (user.getUserName().equals(userName)) {
                userList.add(user);
                userLockList.remove(user);
                try {
                    CSVUtils.writeFile(PATH_USER, userList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    CSVUtils.writeFile(PATH_LOCK_USER, userLockList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        List<User> userList = getUserService();
        List<User> lockUserList = getUserLock();
        for (User user : userList) {
            if (user.getIdUser() == id) {
                return true;
            }
        }
        for (User user : lockUserList) {
            if (user.getIdUser() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getUserLock() {
        List<String> records = CSVUtils.readFile(PATH_LOCK_USER);
        List<User> lockUserList = new ArrayList<>();
        for (String record : records) {
            lockUserList.add(new User(record));
        }
        return lockUserList;
    }

    @Override
    public boolean checkDuplicateUserName(String userName) {
        List<User> userList = getUserService();
        List<User> lockUserList = getUserLock();
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        for (User user : lockUserList) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateEmai(String email) {
        List<User> userList = getUserService();
        List<User> lockUserList = getUserLock();
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        for (User user : lockUserList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<User> userList = getUserService();
        List<User> lockUserList = getUserLock();
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        for (User user : lockUserList) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicatePhone(String phone) {
        List<User> userList = getUserService();
        List<User> lockUserList = getUserLock();
        for (User user : userList) {
            if (user.getPhone() == phone) {
                return true;
            }
        }
        for (User user : lockUserList) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getById(int id) {
        List<User> userList = getUserService();
        for (User user : userList) {
            if (user.getIdUser() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public int getIndexById(int id) {
        List<User> userList = getUserService();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getIdUser() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public User getUserByUserName(String userName) {
        List<User> userList = getUserService();
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> sortByIdASC() {
        List<User> userList = getUserService();
        userList.sort(new SortUserByIdASC());
        return userList;
    }

    @Override
    public List<User> searchByName(String name) {
        List<User> userList = getUserService();
        List<User> searchUser = new ArrayList<>();
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                searchUser.add(user);
                return searchUser;
            }
        }
        return searchUser;
    }

    @Override
    public boolean loginAdmin(String userName, String password) {
        List<User> userList = getUserService();
        for (User user : userList) {
//            if (user.getUserName().equals(userName) && user.getPassword().equals(password) && user.getRole().equals(Role.ADMIN)) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
