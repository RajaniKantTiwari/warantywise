package com.app.warantywise.network.request.dashboard;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

/**
 * Created by rajnikant on 24/01/18.
 */

public class InsurancePlan implements Observable{
    private PropertyChangeRegistry changeRegistry=new PropertyChangeRegistry();
    private String insurancePlanRate;
    private String imageUrl;
    private String insuranceCompanyName;
    private boolean isSelected;

    private String insuranceServiceName;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInsuranceServiceName() {
        return insuranceServiceName;
    }

    public void setInsuranceServiceName(String insuranceServiceName) {
        this.insuranceServiceName = insuranceServiceName;
    }

    @Bindable
    public String getInsurancePlanRate() {
        return insurancePlanRate;
    }

    public void setInsurancePlanRate(String insurancePlanRate) {
        this.insurancePlanRate = insurancePlanRate;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        changeRegistry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        changeRegistry.remove(onPropertyChangedCallback);
    }
}
