package com.app.warantywise.ui.dashboard.drawer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityTermConditionBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.utility.CommonUtility;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class HelpSupportActivity extends BaseActivity {

    @Inject
    CommonPresenter presenter;
    private ActivityTermConditionBinding mBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_help_support);
        initializeData();
    }

    private void initializeData() {
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtility.getColor(this,R.color.background));
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.term_and_condition));
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {

    }
}
