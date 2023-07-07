package model;

import java.util.ArrayList;
import java.util.List;

public class CartBean {

    public CartBean() {
        productCount = 0;
    };
    public CartBean(int id, int userId) {
        this.id = id;
        this.userId = userId;
        productCount = 0;
        productList = new ArrayList<>();
    };

    public CartBean(int userId) {
        this.userId = userId;
        productCount = 0;
        productList = new ArrayList<>();
    };

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItemBean> getProductList() {
        return productList;
    }

    public void setProductList(List<CartItemBean> productList) {
        this.productList = productList;

        for (CartItemBean tmpItem : productList)
            this.productCount += tmpItem.getQuantity();
    }

    public void addProduct(String productCode, int quantity){

        boolean isIn = false;

        for (CartItemBean tmpItem : productList){
            if (tmpItem.getProductCode().equals(productCode)) {
                isIn = true; // The product is already in the cart
                tmpItem.setQuantity(tmpItem.getQuantity() + quantity);
            }
        }

        if (!isIn){
            CartItemBean newCartItem = new CartItemBean(productCode, quantity, this.id);
            productList.add(newCartItem);
        }

    }

    public void removeProduct(String productCode){
        for(int i = 0; i < productList.size(); i++){
            if (productList.get(i).getProductCode().equals(productCode)) {
                productList.remove(i);
                break;
            }
        }
    }


    private int id, userId, productCount;
    private List<CartItemBean> productList;
}
