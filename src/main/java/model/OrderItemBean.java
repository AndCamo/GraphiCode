package model;

public class OrderItemBean {

    public OrderItemBean() {}

    public OrderItemBean(int id, int orderNumber, int quantity, String productCode, double price){
        this.id = id;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.productCode = productCode;
        this.price = price;
    }

    public OrderItemBean(int orderNumber, int quantity, String productCode, double price){
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.productCode = productCode;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    private int id, orderNumber, quantity;
    String productCode;
    double price;
}
