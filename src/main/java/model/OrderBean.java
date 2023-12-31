package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBean {

    public OrderBean(){
    }

    public OrderBean(int orderNumber, int userId, int paymentID, String orderDate, double totalAmount){
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.paymentID = paymentID;
        this.setOrderDate(orderDate);
        this.totalAmount = totalAmount;
    }

    public OrderBean(int userId, int paymentID, String orderDate, double totalAmount){
        this.userId = userId;
        this.paymentID = paymentID;
        this.setOrderDate(orderDate);
        this.totalAmount = totalAmount;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
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


    private int orderNumber, userId;
    private int paymentID;
    private LocalDate orderDate;
    private double totalAmount;

}
