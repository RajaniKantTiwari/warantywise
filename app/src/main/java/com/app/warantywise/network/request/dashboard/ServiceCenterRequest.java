package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 18/03/18.
 */

public class ServiceCenterRequest {
    private String service_center_id;

    public ServiceCenterRequest(String service_center_id) {
        this.service_center_id=service_center_id;
    }

    public String getService_center_id() {
        return service_center_id;
    }


}
