package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 18/03/18.
 */

public class ManufactorServiceCentorResponseData extends BaseResponse{
    private ArrayList<ServiceCentorResponse> info;

    public ArrayList<ServiceCentorResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ServiceCentorResponse> info) {
        this.info = info;
    }
}
