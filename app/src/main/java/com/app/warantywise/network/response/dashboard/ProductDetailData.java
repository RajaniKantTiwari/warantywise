package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 14/03/18.
 */

public class ProductDetailData extends BaseResponse {
    private ArrayList<ProductDetail> info;

    public ArrayList<ProductDetail> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ProductDetail> info) {
        this.info = info;
    }
}
