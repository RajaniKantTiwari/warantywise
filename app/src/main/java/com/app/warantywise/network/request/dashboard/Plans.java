package com.app.warantywise.network.request.dashboard;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

/**
 * Created by rajnikant on 24/01/18.
 */

public class Plans implements Observable{
    private PropertyChangeRegistry changeRegistry=new PropertyChangeRegistry();
    private String planRate;
    private String warantyPeriod;
    private String replaceMentService;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getWarantyPeriod() {
        return warantyPeriod;
    }

    public void setWarantyPeriod(String warantyPeriod) {
        this.warantyPeriod = warantyPeriod;
    }

    public String getReplaceMentService() {
        return replaceMentService;
    }

    public void setReplaceMentService(String replaceMentService) {
        this.replaceMentService = replaceMentService;
    }

    @Bindable
    public String getPlanRate() {
        return planRate;
    }

    public void setPlanRate(String planRate) {
        this.planRate = planRate;
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
