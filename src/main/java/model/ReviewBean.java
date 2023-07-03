package model;

public class ReviewBean {


    public ReviewBean() {}

    public ReviewBean(int id, int userId, int starNumber, String productCode, String reviewContent){
        this.id = id;
        this.userId = userId;
        this.starNumber = starNumber;
        this.productCode = productCode;
        this.reviewContent = reviewContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(int starNumber) {
        if (starNumber < 0)
            starNumber = 0;
        if (starNumber > 5)
            starNumber = 5;

        this.starNumber = starNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    private int id, userId, starNumber;
    private String productCode, reviewContent;
}
