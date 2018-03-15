package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 14/03/18.
 */

public class ProductDetailsRequest {
    private int product_id;

    public ProductDetailsRequest(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }


}
