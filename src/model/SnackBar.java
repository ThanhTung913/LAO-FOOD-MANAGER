package model;

import java.time.LocalDate;

public class SnackBar {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String detail;
//    private LocalDate create_at;

    public SnackBar() {
    }

    public SnackBar(int id, String name, int price, int quantity, String state) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.detail = state;
//        this.create_at = LocalDate.now();
    }

    public SnackBar(String record) {
        String[] filder = record.split(",");
        this.id = Integer.parseInt(filder[0]);
        this.name = filder[1];
        this.price = Integer.parseInt(filder[2]);
        this.quantity = Integer.parseInt(filder[3]);
        this.detail = filder[4];
//        this.create_at = LocalDate.parse(filder[5]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

//    public LocalDate getCreate_at() {
//        return create_at;
//    }

    @Override
    public String toString() {
//        return id + "," + name + "," + price + "," + quantity + "," + detail + "," + create_at;
        return id + "," + name + "," + price + "," + quantity + "," + detail;
    }
}
