package com.app.warantywise.network.response;

import com.app.warantywise.network.response.dashboard.OrderData;

import java.util.ArrayList;

/**
 * Created by rajnikant on 03/04/18.
 */

public class OrderDetailData extends BaseResponse {
    private ArrayList<OrderData> orderdetails;

    public ArrayList<OrderData> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(ArrayList<OrderData> orderdetails) {
        this.orderdetails = orderdetails;
    }
}
