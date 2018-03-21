package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 14/03/18.
 */

public class CompanyDetailsRequest {
    private String manufact_name;

    public String getManufact_name() {
        return manufact_name;
    }

    public CompanyDetailsRequest(String manufact_name) {
        this.manufact_name = manufact_name;
    }
}
