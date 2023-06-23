package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBean {

    public OrderBean(){
        orderItemList = new ArrayList<>();
    }

    public OrderBean(int orderNumber, int userId, String cardNumber, String orderDate, double totalAmount){
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.setOrderDate(orderDate);
        this.totalAmount = totalAmount;
    }

    public OrderBean(int userId, String cardNumber, String orderDate, double totalAmount){
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.setOrderDate(orderDate);
        this.totalAmount = totalAmount;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate.toString();
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = LocalDate.parse(orderDate);
    }

    public List<OrderItemBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemBean> orderItemList) {
        this.orderItemList = orderItemList;
    }

    private int orderNumber, userId;
    private String cardNumber;
    private LocalDate orderDate;
    private double totalAmount;
    private List<OrderItemBean> orderItemList;

}
