package model;

public class User {
    private int idUser;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Role role;

    public User() {
    }


    public User(int idUser, String userName, String password, String name, String phone, String email, String address, Role role) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public User(String record) {
        String[] filed = record.split(",");
        this.idUser = Integer.parseInt(filed[0]);
        this.userName = filed[1];
        this.password = filed[2];
        this.name = filed[3];
        this.phone = filed[4];
        this.email = filed[5];
        this.address = filed[6];
        this.role = Role.parseRole(filed[7]);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return idUser + "," + userName + "," + password + "," + name + "," + phone + "," + email + "," + address + "," + role;
    }
}
