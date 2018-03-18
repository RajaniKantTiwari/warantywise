package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 17/03/18.
 */

public class ExtendedWarrantyCardData extends BaseResponse{
    private ArrayList<ExtendedWarrantyCard> info;

    public ArrayList<ExtendedWarrantyCard> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ExtendedWarrantyCard> info) {
        this.info = info;
    }
}
