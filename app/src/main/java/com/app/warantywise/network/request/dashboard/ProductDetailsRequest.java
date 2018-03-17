package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 14/03/18.
 */

public class ProductDetailsRequest {
    private String product_name;

    public ProductDetailsRequest(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }


}
