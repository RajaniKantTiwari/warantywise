package com.app.warantywise.ui.dashboard.notification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentNotificationBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.dashboard.DashboardFragment;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class NotificationFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private FragmentNotificationBinding mBinding;
    private NotificationAdapter mNotificationAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.message));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        LinearLayoutManager notificationManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvNotification.setLayoutManager(notificationManager);
        mNotificationAdapter = new NotificationAdapter(getDashboardActivity());
        mBinding.rvNotification.setAdapter(mNotificationAdapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return NotificationFragment.class.getSimpleName();
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
