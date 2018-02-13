package com.app.warantywise.network.response.dashboard;


import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 19/01/18.
 */

public class MerchantResponseData extends BaseResponse {
   private ArrayList<ProductResponse> info;

    public ArrayList<ProductResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ProductResponse> info) {
        this.info = info;
    }
}
