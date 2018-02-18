package com.app.warantywise.ui.dashboard.drawer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityHelpSupportBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.dashboard.DashboardFragment;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class HelpSupportFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private ActivityHelpSupportBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.activity_help_support, container, false);
        return mBinding.getRoot();
    }

    public void initializeData() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.help_support));
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return HelpSupportFragment.class.getSimpleName();
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
