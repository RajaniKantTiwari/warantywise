package com.app.warantywise.ui.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.warantywise.WarantyApplication;
import com.app.warantywise.injector.component.CommonComponent;
import com.app.warantywise.injector.component.DaggerCommonComponent;
import com.app.warantywise.injector.module.CommonModule;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.base.MvpView;


/**
 * Created by arvind on 01/11/17.
 */
/*
Parent Activity of Authorization to give functionality to all Activity
*/
public abstract class CommonActivity extends BaseActivity implements MvpView {
    private CommonComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent= DaggerCommonComponent.builder().
                commonModule(new CommonModule(this)).
                applicationComponent(((WarantyApplication) getApplication()).
                        getApplicationComponent()).build();
        attachView();
    }

    public CommonComponent getActivityComponent() {
        return mActivityComponent;
    }
}