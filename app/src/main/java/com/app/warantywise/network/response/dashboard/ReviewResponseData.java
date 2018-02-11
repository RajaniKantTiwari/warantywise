package com.app.warantywise.network.response.dashboard;


import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 21/01/18.
 */

public class ReviewResponseData extends BaseResponse {
   private ArrayList<ReviewResponse> info;

    public ArrayList<ReviewResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ReviewResponse> info) {
        this.info = info;
    }
}
