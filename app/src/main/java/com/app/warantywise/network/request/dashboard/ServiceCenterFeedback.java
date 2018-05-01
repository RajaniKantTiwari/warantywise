package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 01/05/18.
 */

public class ServiceCenterFeedback {
    private String service_center_id;
    private String ratings;
    private String comments;

    public String getService_center_id() {
        return service_center_id;
    }

    public void setService_center_id(String service_center_id) {
        this.service_center_id = service_center_id;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
