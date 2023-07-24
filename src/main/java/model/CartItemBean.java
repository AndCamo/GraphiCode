package model;

public class CartItemBean {
    private int id, cartId, quantity, briefingId;
    private String productCode;

    public CartItemBean() {};

    public CartItemBean(String productCode, int quantity, int cartId) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.cartId = cartId;
    };

    public CartItemBean(int id, String productCode, int quantity, int cartId) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.cartId = cartId;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getBriefingId() {
        return briefingId;
    }

    public void setBriefingId(int briefingId) {
        this.briefingId = briefingId;
    }
}
