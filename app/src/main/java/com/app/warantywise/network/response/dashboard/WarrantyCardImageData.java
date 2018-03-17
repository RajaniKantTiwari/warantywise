package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 17/03/18.
 */

public class WarrantyCardImageData extends BaseResponse {
    private ArrayList<WarrantyCardImage> info;

    public ArrayList<WarrantyCardImage> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<WarrantyCardImage> info) {
        this.info = info;
    }
}
