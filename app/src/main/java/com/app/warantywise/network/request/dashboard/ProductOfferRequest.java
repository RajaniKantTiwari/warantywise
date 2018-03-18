package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 17/03/18.
 */

public class ProductOfferRequest {
    private String masterproductid;

    public ProductOfferRequest(String masterproductid) {
        this.masterproductid = masterproductid;
    }

    public String getMasterproductid() {
        return masterproductid;
    }


}
