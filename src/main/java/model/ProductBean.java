package model;

import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductBean {

    public ProductBean() {
        this.reviews = new ArrayList<>();
    };


    public ProductBean(String name, String category,
                       String image, String description, double price){
        this.code = this.generateCode(name);
        this.name = name;
        this.category = category;
        this.image = image;
        this.description = description;
        this.price = price;
        this.sale = 0;
        this.reviews = new ArrayList<>();
    }
    public ProductBean(String code, String name, String category,
                       String image, String description, double price){
        this.code = code;
        this.name = name;
        this.category = category;
        this.image = image;
        this.description = description;
        this.price = price;
        this.sale = 0;
        this.reviews = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String generateCode(String name){
        int numbCode =  (int) Math.floor(Math.random() *(901) + 100);
        String subCode = name.substring(0, 2).toUpperCase();

        return subCode.concat(Integer.toString(numbCode));
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public double getDiscountedPrice(){
        double newPrice, discountValue;
        discountValue = (this.getPrice() * this.getSale()) / 100;
        newPrice = this.getPrice() - discountValue;
        DecimalFormat df = new DecimalFormat("####0.00");
        newPrice = Math.round(newPrice*100.0)/100.0;

        return newPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ReviewBean> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewBean> reviews) {
        this.reviews = reviews;
    }

    public void addReview(ReviewBean newReview){
        reviews.add(newReview);
    }

    public boolean checkProductValues() {
        final Pattern code_regex = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
        final Pattern productName_regex = Pattern.compile("^[a-zA-Z0-9]+(([',. -][a-zA-Z0-9 ])?[a-zA-Z0-9]*)*$");
        final Pattern price_regex = Pattern.compile("^[0-9]+([.][0-9]+)?$");
        final Pattern sale_regex = Pattern.compile("^[0-9]{1,3}$");

        Matcher matcher = null;
        boolean matchFlag = true;

        String code = this.getCode();
        matcher = code_regex.matcher(code);
        if (!matcher.find() || code.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATO CODICE");
        }

        String name = this.getName();
        if (name == null || name.isEmpty() || name.length() > 40){
            matchFlag = false;
            System.out.println("SBAGLIATO CODICE");
        }

        double price = this.getPrice();
        matcher = price_regex.matcher(price+"");
        if (!matcher.find() || price == 0.0){
            matchFlag = false;
            System.out.println("SBAGLIATO PREZZO");
        }

        double sale = this.getSale();
        if (sale < 0 || sale > 100){
            matchFlag = false;
            System.out.println("SBAGLIATO SCONTO");
        }

        String category = this.getCategory();
        matcher = productName_regex.matcher(category);
        if (!matcher.find() || category.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATA CATEGORIA");
        }

        String description = this.getDescription();
        if (description.isEmpty() || description.length() > 1024){
            matchFlag = false;
            System.out.println("SBAGLIATA DESCRIZIONE");
        }


        return matchFlag;
    }


    private List<ReviewBean> reviews;
    private String code, name, category, image, description;
    private int sale;
    private double price;
}
