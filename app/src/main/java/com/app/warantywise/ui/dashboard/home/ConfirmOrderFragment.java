package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentConfirmOrderBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.drawer.HelpSupportActivity;
import com.app.warantywise.ui.dashboard.drawer.TermConditionActivity;
import com.app.warantywise.ui.dashboard.notification.NotificationFragment;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class ConfirmOrderFragment extends DashboardFragment {

    private FragmentConfirmOrderBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_order, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {
        mBinding.tvRaiseAnIssue.setOnClickListener(this);
        mBinding.tvHome.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return ConfirmOrderFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvRaiseAnIssue) {
            CommonUtility.clicked(mBinding.tvRaiseAnIssue);
            ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), HelpSupportActivity.class);
        } else if (view == mBinding.tvHome) {
            CommonUtility.clicked(mBinding.tvHome);
            getDashboardActivity().clearAllTopFragment(NotificationFragment.class.getSimpleName());
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

}
