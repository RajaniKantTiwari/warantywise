package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 20/03/18.
 */

public class ProductDetailsData extends BaseResponse {
   private ArrayList<ProductDetails> info;

    public ArrayList<ProductDetails> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ProductDetails> info) {
        this.info = info;
    }
}
