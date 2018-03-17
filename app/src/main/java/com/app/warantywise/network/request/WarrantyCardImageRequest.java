package com.app.warantywise.network.request;

/**
 * Created by rajnikant on 17/03/18.
 */

public class WarrantyCardImageRequest {
    private String ww_productid;

    public WarrantyCardImageRequest(String ww_productid) {
        this.ww_productid=ww_productid;
    }

    public String getWw_productid() {
        return ww_productid;
    }

}
