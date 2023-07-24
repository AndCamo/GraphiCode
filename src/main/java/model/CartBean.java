package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartBean {

    public CartBean() {
        productCount = 0;
        productList = new ArrayList<>();
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
        if (productList != null) {
            this.productList = productList;
            for (CartItemBean tmpItem : productList)
                this.productCount += tmpItem.getQuantity();
            productCount = productList.size();
        } else {
            productCount = 0;
        }
    }

    public void addProduct(CartItemBean newCartItem){
        productList.add(newCartItem);
        productCount = productList.size();
    }

    public void removeProduct(CartItemBean itemToRemove){
        for(int i = 0; i < productList.size(); i++){
            if (productList.get(i).getId() == itemToRemove.getId()) {
                productList.remove(i);
                break;
            }
        }
        productCount = productList.size();
    }

    public void loadCart() throws SQLException {
        CartItemDAO cartItemService = new CartItemDAO();
        List<CartItemBean> itemList = cartItemService.doRetrieveByCartId(this.id);
        this.setProductList(itemList);
    }


    private int id, userId, productCount;
    private List<CartItemBean> productList;
}
