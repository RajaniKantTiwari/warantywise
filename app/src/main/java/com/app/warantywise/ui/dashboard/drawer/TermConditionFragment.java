package com.app.warantywise.ui.dashboard.drawer;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityTermConditionBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class TermConditionFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private ActivityTermConditionBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.activity_term_condition, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.term_and_condition));

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return null;
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
