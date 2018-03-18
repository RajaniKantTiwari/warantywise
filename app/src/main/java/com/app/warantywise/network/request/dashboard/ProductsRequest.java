package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 18/03/18.
 */

public class ProductsRequest {
    private String ww_productid;

    public ProductsRequest(String ww_productid) {
        this.ww_productid=ww_productid;
    }

    public String getWw_productid() {
        return ww_productid;
    }
}
