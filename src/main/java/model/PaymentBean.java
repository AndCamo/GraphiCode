package model;

import java.time.LocalDate;

public class PaymentBean {

    public PaymentBean() {};

    public PaymentBean(String cardNumber, String cardHorlder, String cvv, String expirationDate){
        this.cardNumber = cardNumber;
        this.cardHorlder = cardHorlder;
        this.cvv = cvv;
        this.setExpirationDate(expirationDate);
    };


    public String getCardHorlder() {
        return cardHorlder;
    }

    public void setCardHorlder(String cardHorlder) {
        this.cardHorlder = cardHorlder;
    }

    public String getExpirationDate() {
        return expirationDate.toString();
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = LocalDate.parse(expirationDate);
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private String cardNumber, cardHorlder, cvv;
    private LocalDate expirationDate;
}
