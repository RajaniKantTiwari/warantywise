package com.app.warantywise.network.request.dashboard;

/**
 * Created by rajnikant on 01/05/18.
 */

public class GenerateMyOrder {
    private String customer_product_id;
    private String choosen_plan_id;
    private String plan_type;

    public String getCustomer_product_id() {
        return customer_product_id;
    }

    public void setCustomer_product_id(String customer_product_id) {
        this.customer_product_id = customer_product_id;
    }

    public String getChoosen_plan_id() {
        return choosen_plan_id;
    }

    public void setChoosen_plan_id(String choosen_plan_id) {
        this.choosen_plan_id = choosen_plan_id;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }
}
