package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 17/03/18.
 */

public class OfferResponseData extends BaseResponse{
private ArrayList<ResponseData> info;

    public ArrayList<ResponseData> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ResponseData> info) {
        this.info = info;
    }
}
