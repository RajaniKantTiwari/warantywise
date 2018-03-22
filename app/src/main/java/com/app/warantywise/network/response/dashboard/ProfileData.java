package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 22/03/18.
 */

public class ProfileData extends BaseResponse {
    private ArrayList<ProfileDetail> prifiledata;

    public ArrayList<ProfileDetail> getPrifiledata() {
        return prifiledata;
    }

    public void setPrifiledata(ArrayList<ProfileDetail> prifiledata) {
        this.prifiledata = prifiledata;
    }
}
