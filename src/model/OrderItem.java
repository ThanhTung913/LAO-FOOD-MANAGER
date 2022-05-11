package model;

public class OrderItem {
    private int idProduct;
    private int idOrder;
    private String name;
    private int price;
    private int quantity;
    private int total;
//    private LocalDate create_at;
//    private long create_at;
//    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public OrderItem() {
    }

    public OrderItem(int idOrder, int idProduct, String name, int price, int quantity, int total) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        Date date = new Date();
//        System.out.println(formatter.format(date));
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
//        this.create_at = LocalDate.now();
    }

    public OrderItem(int idProduct, String name, int price, int quantity, int total) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public OrderItem(String record) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        Date date = new Date();
//        System.out.println(formatter.format(date));
        String[] fields = record.split(",");
        this.idOrder = Integer.parseInt(fields[0]);
        this.idProduct = Integer.parseInt(fields[1]);
        this.name = fields[2];
        this.price = Integer.parseInt(fields[3]);
        this.quantity = Integer.parseInt(fields[4]);
        this.total = Integer.parseInt(fields[5]);
//        this.create_at = LocalDate.parse(fields[6]);
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

//    public LocalDate getCreate_at() {
//        return create_at;
//    }

    @Override
    public String toString() {
//        return idOrder + "," + idProduct + "," + name + "," + price + "," + quantity + "," + total + "," + create_at;
        return idOrder + "," + idProduct + "," + name + "," + price + "," + quantity + "," + total;
    }
}
