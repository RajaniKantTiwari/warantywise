package com.app.warantywise.network.response;

/**
 * Created by rajnikant on 20/02/18.
 */

public class MyOrderData extends BaseResponse {
    private MyOrder  myorder;

    public MyOrder getMyorder() {
        return myorder;
    }

    public void setMyorder(MyOrder myorder) {
        this.myorder = myorder;
    }
}
