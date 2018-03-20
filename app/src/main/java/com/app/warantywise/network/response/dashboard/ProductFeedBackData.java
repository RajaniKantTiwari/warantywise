package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 20/03/18.
 */

public class ProductFeedBackData extends BaseResponse {
    private ArrayList<ProductFeedback> info;

    public ArrayList<ProductFeedback> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ProductFeedback> info) {
        this.info = info;
    }
}
