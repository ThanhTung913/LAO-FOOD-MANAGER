package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int idOrder;
    private Date create_at;
    private int total;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Order() {
    }


    public Order(String record) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        System.out.println(formatter.format(date));
        //idOrder,total,datetime;
        //1,250000,06/05/2022 15:42
        String[] records = record.split(",");
        this.idOrder = Integer.parseInt(records[0]);
        this.total = Integer.parseInt(records[1]);
        try {
            this.create_at = formatter.parse(records[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public Order(int idOrder, Date create_at, int total) {
        this.idOrder = idOrder;
        this.create_at = create_at;
        this.total = total;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        String str = null;

        try {
            str = idOrder + "," + total + "," + formatter.format(create_at);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
