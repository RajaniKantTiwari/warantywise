package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 17/03/18.
 */

public class AllProductData extends BaseResponse {
    private ArrayList<AllProduct> info;

    public ArrayList<AllProduct> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<AllProduct> info) {
        this.info = info;
    }
}
