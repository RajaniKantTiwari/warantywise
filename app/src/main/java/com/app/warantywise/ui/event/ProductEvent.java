package com.app.warantywise.ui.event;


import com.app.warantywise.network.response.dashboard.ResponseData;
import com.app.warantywise.network.response.dashboard.ServiceCentorResponse;
import com.app.warantywise.network.response.dashboard.YourProduct;

import java.util.ArrayList;

/**
 * Created by Amul on 27/12/17.
 */

public class ProductEvent {
    //use 1 for list 2 for map
    private int listMap;

    private ArrayList<YourProduct> productList;
    private ArrayList<ServiceCentorResponse> productMapList;
    private ArrayList<ResponseData> offerList;

    public ArrayList<ServiceCentorResponse> getProductMapList() {
        return productMapList;
    }

    public void setProductMapList(ArrayList<ServiceCentorResponse> productMapList) {
        this.productMapList = productMapList;
    }

    public ArrayList<YourProduct> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<YourProduct> productList) {
        this.productList = productList;
    }

    public int getListMap() {
        return listMap;
    }

    public void setListMap(int listMap) {
        this.listMap = listMap;
    }

    public void setOfferList(ArrayList<ResponseData> offerList) {
        this.offerList=offerList;
    }

    public ArrayList<ResponseData> getOfferList() {
        return offerList;
    }
}
