package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 21/03/18.
 */

public class CompanyDetailData extends BaseResponse {
    private ArrayList<CompanyDetail> info;

    public ArrayList<CompanyDetail> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<CompanyDetail> info) {
        this.info = info;
    }
}
