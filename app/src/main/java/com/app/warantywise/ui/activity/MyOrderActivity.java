package com.app.warantywise.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityMyOrderBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.MyOrder;
import com.app.warantywise.network.response.MyOrderData;
import com.app.warantywise.network.response.Order;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.activity.fragment.LiveOrderFragment;
import com.app.warantywise.ui.activity.fragment.PastOrderFragment;
import com.app.warantywise.ui.authentication.CommonActivity;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by rajnikant on 31/12/17.
 */

public class MyOrderActivity extends CommonActivity {
    private ActivityMyOrderBinding mBinding;
    //@Inject
    //CommonPresenter presenter;
    private ArrayList<Order> recentOrderList;
    private ArrayList<Order> pastOrderList;
    private LiveOrderFragment liveOrderFragment;
    private PastOrderFragment pastOrderFragment;
    private int frNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);
        initializeData();
        setListener();
    }

    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.my_order));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtility.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        liveOrderFragment = new LiveOrderFragment();
        pastOrderFragment = new PastOrderFragment();
        pushFragment(pastOrderFragment, null, R.id.container, true, true, BaseActivity.AnimationType.NONE,true);
        pushFragment(liveOrderFragment, null, R.id.container, true, true, BaseActivity.AnimationType.NONE,true);
        //presenter.getMyOrder(this);
    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.layoutLiveOrder.setOnClickListener(this);
        mBinding.layoutPastOrder.setOnClickListener(this);
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        //presenter.attachView(this);
    }

    /*public CommonPresenter getPresenter() {
        return presenter;
    }*/

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutLiveOrder) {
            liveOrder();
        } else if (view == mBinding.layoutPastOrder) {
            pastOrder();
        } else if (mBinding.layoutHeader.ivBack == view) {
            finish();
        }
    }

    private void pastOrder() {
        mBinding.tvLiveOrder.setTextColor(CommonUtility.getColor(this, R.color.dark_black));
        mBinding.tvPastOrder.setTextColor(CommonUtility.getColor(this, R.color.color_dark_grey));
        mBinding.ivPastOrder.setBackgroundColor(CommonUtility.getColor(this, R.color.dark_black_color));
        mBinding.ivLiveOrder.setBackgroundColor(CommonUtility.getColor(this, R.color.ver_bg_color));
        pastOrderFragment.setPastOrder(pastOrderList);
        liveOrderFragment.setVisibility(View.GONE);
        pastOrderFragment.setVisibility(View.VISIBLE);

    }

    private void liveOrder() {
        mBinding.tvLiveOrder.setTextColor(CommonUtility.getColor(this, R.color.color_dark_grey));
        mBinding.tvPastOrder.setTextColor(CommonUtility.getColor(this, R.color.dark_black));
        mBinding.ivLiveOrder.setBackgroundColor(CommonUtility.getColor(this, R.color.dark_black_color));
        mBinding.ivPastOrder.setBackgroundColor(CommonUtility.getColor(this, R.color.ver_bg_color));
        liveOrderFragment.setLiveOrder(recentOrderList);
        liveOrderFragment.setVisibility(View.VISIBLE);
        pastOrderFragment.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(frNumber==1){
            super.onBackPressed();
            frNumber=0;
        }else{
            finish();
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response) && response instanceof MyOrderData) {
            MyOrderData data = (MyOrderData) response;
            MyOrder myOrder = data.getMyorder();
            if (CommonUtility.isNotNull(myOrder)) {
                recentOrderList = myOrder.getRecent();
                pastOrderList = myOrder.getPast();
                pastOrderFragment.setPastOrder(pastOrderList);
                liveOrderFragment.setLiveOrder(recentOrderList);

            }
        }
    }

    public void addFragmentInContainer(Fragment fragment, Bundle bundle, boolean addToBackStack, boolean shouldAdd, @AnimationType int animationType) {
        frNumber=1;
        //pushFragment(fragment, bundle, R.id.container, addToBackStack, shouldAdd, animationType);
        pushFragment(fragment, bundle, android.R.id.content, addToBackStack, shouldAdd, animationType,true);
    }

}
