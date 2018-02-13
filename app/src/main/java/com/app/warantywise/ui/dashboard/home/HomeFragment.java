package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentHomeBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.DashboardPresenter;
import com.app.warantywise.ui.event.MerchantEvent;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by Amul on 13/11/17.
 */
public class HomeFragment extends DashboardFragment {

    @Inject
    DashboardPresenter presenter;
    private MerchantEvent event;
    FragmentHomeBinding mBinding;
    private String search;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        addFragment();
        return mBinding.getRoot();
    }

    private void addFragment() {
        mBinding.listButton.setTag(R.drawable.location);
        mBinding.listButton.setVisibility(View.VISIBLE);
        event = new MerchantEvent();
        event.setListMap(AppConstants.LIST_PRODUCT);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.MERCHANT_MAP_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.MERCHANT_LIST_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
    }


    @Override
    public void initializeData() {
        Bundle bundle=getArguments();

       // getPresenter().getMerchantListBySearch(getBaseActivity(),search);
    }

    @Override
    public void setListener() {
        mBinding.listButton.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        if (view == mBinding.listButton) {
            if ((int) mBinding.listButton.getTag() == R.drawable.location) {
                mBinding.listButton.setImageResource(R.drawable.checked);
                mBinding.listButton.setTag(R.drawable.checked);
            } else {
                mBinding.listButton.setImageResource(R.drawable.location);
                mBinding.listButton.setTag(R.drawable.location);
            }
            CommonUtility.clicked(mBinding.listButton);
            listMapConversion();
        }
    }


    private void listMapConversion() {
        if (event.getListMap() == AppConstants.LIST_PRODUCT) {
            event.setListMap(AppConstants.MAP_PRODUCT);
        } else {
            event.setListMap(AppConstants.LIST_PRODUCT);
        }
        EventBus.getDefault().post(event);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {

        }


    }

}
