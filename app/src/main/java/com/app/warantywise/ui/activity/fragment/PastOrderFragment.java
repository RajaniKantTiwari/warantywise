package com.app.warantywise.ui.activity.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentPastOrderBinding;
import com.app.warantywise.network.request.Feedback;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.Order;
import com.app.warantywise.network.response.dashboard.MerchantResponse;
import com.app.warantywise.ui.activity.MyOrderActivity;
import com.app.warantywise.ui.activity.OrderDetailsActivity;
import com.app.warantywise.ui.adapter.LiveOrderAdapter;
import com.app.warantywise.ui.base.BaseFragment;
import com.app.warantywise.ui.dialogfrag.ContactDialogFragment;
import com.app.warantywise.ui.dialogfrag.OrderFeedbackDialogFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;

import java.util.ArrayList;

import static com.app.warantywise.utility.AppConstants.REQUEST_CALL;


/**
 * Created by rajnikant on 31/12/17.
 */

public class PastOrderFragment extends BaseFragment implements
        LiveOrderAdapter.OrderListener,
        OrderFeedbackDialogFragment.OrderDialogListener
        ,ContactDialogFragment.ContactDialogListener{
    private FragmentPastOrderBinding mBinding;
    private LiveOrderAdapter mAdapter;
    private Intent callIntent;
    private ArrayList<Order> pastOrderList;
    private MyOrderActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_past_order, container, false);
        mActivity = (MyOrderActivity) getActivity();
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        pastOrderList = new ArrayList<>();
        mAdapter = new LiveOrderAdapter(getBaseActivity(),this, pastOrderList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return PastOrderFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        mActivity.getPresenter().attachView(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    public void setPastOrder(ArrayList<Order> pastOrderList) {
        this.pastOrderList.clear();
        if(CommonUtility.isNotNull(pastOrderList)&&pastOrderList.size()>0){
            this.pastOrderList.addAll(pastOrderList);
            mBinding.rvOrder.setVisibility(View.VISIBLE);
            mBinding.layoutNoData.layoutNoData.setVisibility(View.GONE);
        }else{
            mBinding.rvOrder.setVisibility(View.GONE);
            mBinding.layoutNoData.layoutNoData.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void setVisibility(int visible) {
        mBinding.layoutOrder.setVisibility(visible);
    }

    @Override
    public void viewDetailsClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.ORDER_DETAILS, pastOrderList.get(position));
        ExplicitIntent.getsInstance().navigateTo(getActivity(),OrderDetailsActivity.class,bundle);
    }

    @Override
    public void helpClick(int position) {
        openDialog(position);
    }

    @Override
    public void feedBackClicked(int position) {
        Bundle bundle = new Bundle();
        if (CommonUtility.isNotNull(pastOrderList) && pastOrderList.size() > position) {
            Order order = pastOrderList.get(position);
            bundle.putInt(AppConstants.ID, order.getId());
            bundle.putString(AppConstants.STORE_NAME, order.getStore_name());
        }
        CommonUtility.showOrderDialog(mActivity, bundle, this);
    }

    @Override
    public void submit(int id, float rating, String feedbackStr) {
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setRating(String.valueOf(rating));
        feedback.setComments(feedbackStr);
        mActivity.getPresenter().submitFeedBack(mActivity, feedback);
    }
    private void openDialog(int position) {
        Bundle bundle = new Bundle();
        MerchantResponse merchantResponse=new MerchantResponse();
        merchantResponse.setId(String.valueOf(pastOrderList.get(position).getMerchant_id()));
        //merchantResponse.setLogo();
        //merchantResponse.setAddress();
        //merchantResponse.setMobileNumber();
        //merchantResponse.setName();
        bundle.putParcelable(AppConstants.PRODUCT_INFO,merchantResponse);
        CommonUtility.showContactDialog(getBaseActivity(), bundle, this);
    }

    @Override
    public void contact(String phoneNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getBaseActivity(),new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            return;
        } else
            startActivity(callIntent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    getBaseActivity().showToast(getResources().getString(R.string.permition_denied));
                }
            }
        }
    }

    @Override
    public void messageChat(String name, String mobileNumber) {
        Bundle bundle = new Bundle();
        /*bundle.putString(AppConstants.CHAT_WITH, mobileNumber);
        bundle.putString(AppConstants.CHAT_USER_NAME, name);
        ExplicitIntent.getsInstance().navigateTo(mActivity, ChatActivity.class, bundle);*/
    }
}
