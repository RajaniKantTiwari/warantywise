package com.app.warantywise.network.response.dashboard;

import com.app.warantywise.network.response.BaseResponse;

/**
 * Created by rajnikant on 17/03/18.
 */

public class AllProduct {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
