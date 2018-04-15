package com.app.warantywise.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityMyOrderBinding;
import com.app.warantywise.network.request.Feedback;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.MyOrder;
import com.app.warantywise.network.response.MyOrderData;
import com.app.warantywise.network.response.Order;
import com.app.warantywise.network.response.dashboard.MerchantResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.activity.fragment.LiveOrderFragment;
import com.app.warantywise.ui.activity.fragment.PastOrderFragment;
import com.app.warantywise.ui.adapter.LiveOrderAdapter;
import com.app.warantywise.ui.authentication.CommonActivity;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dialogfrag.ContactDialogFragment;
import com.app.warantywise.ui.dialogfrag.OrderFeedbackDialogFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.app.warantywise.utility.AppConstants.REQUEST_CALL;


/**
 * Created by rajnikant on 31/12/17.
 */

public class MyOrderActivity extends CommonActivity implements LiveOrderAdapter.OrderListener,
        OrderFeedbackDialogFragment.OrderDialogListener,ContactDialogFragment.ContactDialogListener{
    private ActivityMyOrderBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private ArrayList<Order> recentOrderList;
    private LiveOrderAdapter mAdapter;
    private String rating;
    private Intent callIntent;


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

        mAdapter = new LiveOrderAdapter(this, this, recentOrderList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (mBinding.layoutHeader.ivBack == view) {
            finish();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response) && response instanceof MyOrderData) {
            MyOrderData data = (MyOrderData) response;
            MyOrder myOrder = data.getMyorder();
            if (CommonUtility.isNotNull(myOrder)) {
                recentOrderList = myOrder.getRecent();

            }
        }
    }

    @Override
    public void viewDetailsClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.ORDER_DETAILS, recentOrderList.get(position));
        ExplicitIntent.getsInstance().navigateTo(this,OrderDetailsActivity.class,bundle);
    }

    @Override
    public void helpClick(int position) {
        openDialog(position);
    }

    @Override
    public void feedBackClicked(int position) {

    }
    private void openDialog(int position) {
        Bundle bundle = new Bundle();
        MerchantResponse merchantResponse=new MerchantResponse();
        merchantResponse.setId(String.valueOf(recentOrderList.get(position).getMerchant_id()));
        //merchantResponse.setLogo();
        //merchantResponse.setAddress();
        //merchantResponse.setMobileNumber();
        //merchantResponse.setName();
        bundle.putParcelable(AppConstants.PRODUCT_INFO,merchantResponse);
        CommonUtility.showContactDialog(this, bundle, this);
    }

    @Override
    public void submit(int id, float rating, String feedbackStr) {
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setRating(String.valueOf(rating));
        this.rating=String.valueOf(rating);
        feedback.setComments(feedbackStr);
        presenter.submitFeedBack(this, feedback);
    }

    @Override
    public void contact(String phoneNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            return;
        } else
            startActivity(callIntent);
    }

    @Override
    public void messageChat(String name, String mobileNumber) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    showToast(getResources().getString(R.string.permition_denied));
                }
            }
        }
    }
}
