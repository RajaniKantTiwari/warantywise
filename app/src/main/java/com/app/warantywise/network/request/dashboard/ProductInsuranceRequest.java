package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 17/03/18.
 */

public class ProductInsuranceRequest {
    private String masterproductid;

    public ProductInsuranceRequest(String masterproductid) {
        this.masterproductid = masterproductid;
    }

    public String getMasterproductid() {
        return masterproductid;
    }


}
