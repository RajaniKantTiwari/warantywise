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
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityOrderDetailsBinding;
import com.app.warantywise.network.request.Feedback;
import com.app.warantywise.network.request.dashboard.MerchantRequest;
import com.app.warantywise.network.request.dashboard.OrderDetailsRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.Order;
import com.app.warantywise.network.response.OrderDetailData;
import com.app.warantywise.network.response.dashboard.MerchantResponse;
import com.app.warantywise.network.response.dashboard.MerchantResponseData;
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.adapter.OrderDetailsAdapter;
import com.app.warantywise.ui.adapter.OrderListAdapter;
import com.app.warantywise.ui.authentication.CommonActivity;
import com.app.warantywise.ui.dialogfrag.ContactDialogFragment;
import com.app.warantywise.ui.dialogfrag.OrderFeedbackDialogFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.app.warantywise.utility.AppConstants.REQUEST_CALL;


/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsActivity extends CommonActivity
        implements OrderListAdapter.OrderListener,
        OrderFeedbackDialogFragment.OrderDialogListener,ContactDialogFragment.ContactDialogListener {

    private ActivityOrderDetailsBinding mBinding;
    private OrderDetailsAdapter mOrderAdapter;
    private Order order;
    private Intent callIntent;
    @Inject
    CommonPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        initializeData();
        setListener();
    }


    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.order_details));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtility.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mBinding.rvCartItem.setLayoutManager(manager);
        mOrderAdapter = new OrderDetailsAdapter(this);
        mBinding.rvCartItem.setAdapter(mOrderAdapter);
        Intent intent = getIntent();
        if (CommonUtility.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtility.isNotNull(bundle)) {
                order = bundle.getParcelable(AppConstants.ORDER_DETAILS);
                mBinding.tvOrderDate.setText(order.getInvoiceDate());
                mBinding.tvInvoice.setText(order.getInvoiceNumber());
                mBinding.tvOrderStatus.setText(order.getOrder_status());
                if (CommonUtility.isNotNull(order.getRating()) && (!order.getRating().equalsIgnoreCase("0"))) {
                    mBinding.tvFeedBack.setVisibility(View.GONE);
                    mBinding.ratingBar.setVisibility(View.VISIBLE);
                    mBinding.ratingBar.setRating(CommonUtility.setRating(order.getRating()));
                } else {
                    mBinding.tvFeedBack.setVisibility(View.VISIBLE);
                }
                OrderDetailsRequest request = new OrderDetailsRequest();
                request.setOrderid(order.getInvoiceNumber());
                presenter.orderDetails(this, request);
                MerchantRequest merchantRequest = new MerchantRequest(order.getMerchant_id());
                presenter.getMerchantDetails(this, merchantRequest);
            }
        }

    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.tvFeedBack.setOnClickListener(this);
        mBinding.tvContact.setOnClickListener(this);
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 1) {
                orderDetails(response);
            } else if (requestCode == 2) {
                merchantDetails(response);
            }
        }
    }

    private void merchantDetails(BaseResponse response) {
        MerchantResponseData data = (MerchantResponseData) response;
        ArrayList<ProductResponse> merchantResponse = data.getInfo();
        if (CommonUtility.isNotNull(merchantResponse) && merchantResponse.size() > 0) {
            ProductResponse merchant = merchantResponse.get(0);
            GlideUtils.loadImage(this, merchant.getImage(), mBinding.storeImage, null, R.drawable.icon_placeholder);
            mBinding.tvStoreName.setText(merchant.getName());
            mBinding.tvAddress.setText(merchant.getAddress());
        }
    }

    private void orderDetails(BaseResponse response) {
        OrderDetailData orderDetailData = (OrderDetailData) response;
        if (CommonUtility.isNotNull(orderDetailData.getOrderdetails()) && orderDetailData.getOrderdetails().size() > 0) {
            mOrderAdapter.setCartList(orderDetailData.getOrderdetails());

        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            finish();
        } else if (view == mBinding.tvFeedBack) {
            feedBackClicked();
        }else if(view==mBinding.tvContact){
            contact();
        }
    }

    private void contact() {
        Bundle bundle = new Bundle();
        MerchantResponse merchantResponse=new MerchantResponse();
        merchantResponse.setId(String.valueOf(order.getMerchant_id()));
        //merchantResponse.setLogo();
        //merchantResponse.setAddress();
        //merchantResponse.setMobileNumber();
        //merchantResponse.setName();
        bundle.putParcelable(AppConstants.PRODUCT_INFO,merchantResponse);
        CommonUtility.showContactDialog(this, bundle, this);
    }


    private void feedBackClicked() {
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.ID, order.getId());
        bundle.putString(AppConstants.STORE_NAME, order.getStore_name());
        CommonUtility.showOrderDialog(this, bundle, this);
    }

    @Override
    public void submit(int id, float rating, String feedbackStr) {
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setRating(String.valueOf(rating));
        mBinding.ratingBar.setRating(CommonUtility.setRating(String.valueOf(rating)));
        mBinding.tvFeedBack.setVisibility(View.GONE);
        mBinding.ratingBar.setVisibility(View.VISIBLE);
        feedback.setComments(feedbackStr);
        presenter.submitFeedBack(this, feedback);
    }

    @Override
    public void onOrderClick(int position) {

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

    @Override
    public void messageChat(String name, String mobileNumber) {
        Bundle bundle = new Bundle();
       /* bundle.putString(AppConstants.CHAT_WITH, mobileNumber);
        bundle.putString(AppConstants.CHAT_USER_NAME, name);
        ExplicitIntent.getsInstance().navigateTo(this, ChatActivity.class, bundle);*/
    }
}
