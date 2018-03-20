package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 20/03/18.
 */

public class ServiceCenterImageData extends BaseResponse{
    private ArrayList<ServiceCenterImage> info;

    public ArrayList<ServiceCenterImage> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ServiceCenterImage> info) {
        this.info = info;
    }
}
