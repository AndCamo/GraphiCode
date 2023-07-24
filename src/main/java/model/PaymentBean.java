package model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentBean {

    public PaymentBean() {};

    public PaymentBean(String cardNumber, String cardHorlder, String cvv, String expirationDate, int userId){
        this.cardNumber = cardNumber;
        this.cardHorlder = cardHorlder;
        this.cvv = cvv;
        this.userId = userId;
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
        if (expirationDate!=null) {
            expirationDate = expirationDate.replaceAll("\\s", "");
            this.expirationDate = expirationDate;
        }
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean checkPaymentValue(){
        final Pattern number_regex = Pattern.compile("^[0-9]{4}(?:[ ]{0,1}[0-9]{4}){3}$");
        final Pattern expire_regex = Pattern.compile("^([ ]*0[1-9]|[ ]*1[0-2])[ ]*\\/([ ]*[0-9]{2})$");
        final Pattern cvc_regex = Pattern.compile("^([0-9]{3})$");
        final Pattern holder_regex = Pattern.compile("^([a-zA-Z\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27]\\s?){2,255}$");

        Matcher matcher = null;
        boolean matchFlag = true;

        String cardNumber = this.getCardNumber();
        matcher = number_regex.matcher(cardNumber);
        if(cardNumber == null || cardNumber.isEmpty() || !matcher.find()){
            matchFlag = false;
            System.out.println("NUMERO CARTA SBAGLIATO");
        }

        String cardHolder = this.getCardHorlder();
        matcher = holder_regex.matcher(cardHolder);
        if(cardHolder == null || cardHolder.isEmpty() || !matcher.find()){
            matchFlag = false;
            System.out.println("CARD HOLDER SBAGLIATO");
        }

        String expireDate = this.getExpirationDate();
        matcher = expire_regex.matcher(expireDate);
        if(expireDate == null || expireDate.isEmpty() || !matcher.find()){
            matchFlag = false;
            System.out.println("SCADENZA SBAGLIATA");
        }

        String cardCode = this.getCvv();
        matcher = cvc_regex.matcher(cardCode);
        if(cardCode == null || cardCode.isEmpty() || !matcher.find()){
            matchFlag = false;
            System.out.println("CVV SBAGLIATO");
        }

        return matchFlag;
    }


    private int id, userId;
    private String cardNumber, cardHorlder, cvv;
    private String expirationDate;
}
