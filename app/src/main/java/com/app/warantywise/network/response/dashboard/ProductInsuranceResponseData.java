package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 17/03/18.
 */

public class ProductInsuranceResponseData extends BaseResponse {
private ArrayList<ProductOfferResponse> info;

    public ArrayList<ProductOfferResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ProductOfferResponse> info) {
        this.info = info;
    }
}
