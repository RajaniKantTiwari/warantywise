package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 17/03/18.
 */

public class ExtendeWarrantyRequest {
    private String masterproductid;

    public ExtendeWarrantyRequest(String masterproductid) {
        this.masterproductid = masterproductid;
    }

    public String getMasterproductid() {
        return masterproductid;
    }


}
