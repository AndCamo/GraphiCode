package model;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private List<ReviewBean> reviews;
    private String code, name, category, image, description;
    private int sale;
    private double price;
}
