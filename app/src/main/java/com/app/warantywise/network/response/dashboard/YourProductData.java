package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 14/03/18.
 */

public class YourProductData extends BaseResponse {
  private ArrayList<YourProduct> info;

    public ArrayList<YourProduct> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<YourProduct> info) {
        this.info = info;
    }
}
