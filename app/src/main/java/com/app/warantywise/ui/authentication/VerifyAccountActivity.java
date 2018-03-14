package com.app.warantywise.ui.authentication;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;


import com.app.warantywise.R;
import com.app.warantywise.WarantyApplication;
import com.app.warantywise.databinding.ActivityVerifyAccountBinding;
import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.dashboard.DashBoardActivity;
import com.app.warantywise.ui.dialogfrag.CustomDialogFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.PreferenceUtils;

import javax.inject.Inject;

/**
 * Created by on 23/12/17.
 */

public class VerifyAccountActivity extends CommonActivity implements TextWatcher, View.OnKeyListener, CustomDialogFragment.CustomDialogListener {
    private ActivityVerifyAccountBinding mBinding;
    StringBuilder otpNumber = new StringBuilder();
    @Inject
    CommonPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify_account);
        showKeyboard();
        initializeData();
        setListener();
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public void setListener() {
        mBinding.edFirst.addTextChangedListener(this);
        mBinding.edSecond.addTextChangedListener(this);
        mBinding.edThird.addTextChangedListener(this);
        mBinding.edFourth.addTextChangedListener(this);
        mBinding.edFirst.setOnKeyListener(this);
        mBinding.edSecond.setOnKeyListener(this);
        mBinding.edThird.setOnKeyListener(this);
        mBinding.edFourth.setOnKeyListener(this);


        mBinding.butLayout.tvOne.setOnClickListener(this);
        mBinding.butLayout.tvTwo.setOnClickListener(this);
        mBinding.butLayout.tvthree.setOnClickListener(this);
        mBinding.butLayout.tvFour.setOnClickListener(this);
        mBinding.butLayout.tvFive.setOnClickListener(this);
        mBinding.butLayout.tvSix.setOnClickListener(this);
        mBinding.butLayout.tvSeven.setOnClickListener(this);
        mBinding.butLayout.tvEight.setOnClickListener(this);
        mBinding.butLayout.tvNine.setOnClickListener(this);
        mBinding.butLayout.tvZero.setOnClickListener(this);
        mBinding.butLayout.layoutBack.setOnClickListener(this);
        mBinding.layoutHeader.ivDrawer.setOnClickListener(this);
    }

    public void initializeData() {
        mBinding.layoutHeader.ivDrawer.setImageResource(R.drawable.ic_back);
        mBinding.layoutHeader.ivDrawer.setPadding(CommonUtility.convertDpToPx(AppConstants.PADDING, this), CommonUtility.convertDpToPx(AppConstants.PADDING, this),
                CommonUtility.convertDpToPx(AppConstants.PADDING, this), CommonUtility.convertDpToPx(AppConstants.PADDING, this));
        mBinding.edFirst.setInputType(InputType.TYPE_NULL);
        mBinding.edSecond.setInputType(InputType.TYPE_NULL);
        mBinding.edThird.setInputType(InputType.TYPE_NULL);
        mBinding.edFourth.setInputType(InputType.TYPE_NULL);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.butLayout.tvOne) {
            setText("1");
        } else if (view == mBinding.butLayout.tvTwo) {
            setText("2");
        } else if (view == mBinding.butLayout.tvthree) {
            setText("3");
        } else if (view == mBinding.butLayout.tvFour) {
            setText("4");
        } else if (view == mBinding.butLayout.tvFive) {
            setText("5");
        } else if (view == mBinding.butLayout.tvSix) {
            setText("6");
        } else if (view == mBinding.butLayout.tvSeven) {
            setText("7");
        } else if (view == mBinding.butLayout.tvEight) {
            setText("8");
        } else if (view == mBinding.butLayout.tvNine) {
            setText("9");
        } else if (view == mBinding.butLayout.tvZero) {
            setText("0");
        } else if (view == mBinding.butLayout.layoutBack) {
           back();
        }else if(view==mBinding.layoutHeader.ivDrawer){
            finish();
        }
    }

    private void back() {
        if (otpNumber.length() == 1) {
            mBinding.edFirst.setText("");
            mBinding.edFirst.requestFocus();
            otpNumber.deleteCharAt(0);
        } else if (otpNumber.length() == 2) {
            mBinding.edSecond.clearFocus();
            mBinding.edSecond.setText("");
            mBinding.edFirst.requestFocus();
            otpNumber.deleteCharAt(1);
        } else if (otpNumber.length() == 3) {
            mBinding.edThird.clearFocus();
            mBinding.edThird.setText("");

            mBinding.edSecond.requestFocus();
            otpNumber.deleteCharAt(2);
        } else if (otpNumber.length() == 4) {
            mBinding.edFourth.clearFocus();
            mBinding.edFourth.setText("");
            mBinding.edThird.requestFocus();
            otpNumber.deleteCharAt(3);
        }
    }
    private void setText(String text) {
        if (mBinding.edFirst.hasFocus()) {
            mBinding.edFirst.setText(text);
        } else if (mBinding.edSecond.hasFocus()) {
            mBinding.edSecond.setText(text);
        } else if (mBinding.edThird.hasFocus()) {
            mBinding.edThird.setText(text);
        } else if (mBinding.edFourth.hasFocus()) {
            mBinding.edFourth.setText(text);
        }

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == 1) {
            hideSoftKeyboard(mBinding.getRoot());
            gotoNext(response);
        } else if (requestCode == 2) {
            getOtp(response);
        }
        if (WarantyApplication.isDebug) {
            ExplicitIntent.getsInstance().navigateTo(this, DashBoardActivity.class);
        }
    }

    private void getOtp(BaseResponse response) {
        if (isNotNull(response)) {
            if (response instanceof LoginResponse) {
                LoginResponse loginResponse = (LoginResponse) response;
                if (isNotNull(loginResponse)) {
                    String type = loginResponse.getType();
                    if (type.equals(AppConstants.SUCCESS)) {
                        showToast(getResources().getString(R.string.otp_has_been_send));
                    }
                }
            }
        }
    }

    private void gotoNext(BaseResponse response) {
        try {
            if (isNotNull(response)) {
                if (response instanceof VerifyMobileResponse) {
                    VerifyMobileResponse verifyMobileResponse = (VerifyMobileResponse) response;
                    if (isNotNull(verifyMobileResponse)) {
                        String status = verifyMobileResponse.getStatus();
                        if (status.equals(AppConstants.SUCCESS)) {
                            hideKeyboard();
                            PreferenceUtils.setUserId(verifyMobileResponse.getId());
                            PreferenceUtils.setAuthToken(verifyMobileResponse.getAuthkey());
                            PreferenceUtils.setLogin(true);

                            //setToken();
                            ExplicitIntent.getsInstance().clearPreviousNavigateTo(this, DashBoardActivity.class);
                            finish();
                        } else {
                            hideKeyboard();
                            showToast(getResources().getString(R.string.server_error));
                        }
                    }
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /*private void setToken() {
        DeviceTokenRequest request=new DeviceTokenRequest();
        request.setUserid(PreferenceUtils.getUserId());
        DeviceToken token=new DeviceToken();
        token.setDeveiceUniqId(CommonUtils.getDeviceUniqueId(this));
        token.setDeviceTokenId(PreferenceUtils.getDeviceToken());
        token.setDeviceType(GeneralConstant.DEVICETYPE);
        request.setInfo(token);
        presenter.setDeviceToken(this,request);
    }*/

    @Override
    public void onError(String message, int requestCode) {
        showToast("Error");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (otpNumber.length() == 0 && mBinding.edFirst.length() == 1) {
            otpNumber.append(s);
            mBinding.edFirst.clearFocus();
            mBinding.edSecond.requestFocus();
            mBinding.edSecond.setCursorVisible(true);
        } else if (otpNumber.length() == 1 && mBinding.edSecond.length() == 1) {
            otpNumber.append(s);
            mBinding.edSecond.clearFocus();
            mBinding.edThird.requestFocus();
            mBinding.edThird.setCursorVisible(true);
        } else if (otpNumber.length() == 2 && mBinding.edThird.length() == 1) {
            otpNumber.append(s);
            mBinding.edThird.clearFocus();
            mBinding.edFourth.requestFocus();
            mBinding.edFourth.setCursorVisible(true);
        } else if (otpNumber.length() == 3 && mBinding.edFourth.length() == 1) {
            otpNumber.append(s);
            mBinding.edThird.clearFocus();
            mBinding.edFourth.requestFocus();
            mBinding.edFourth.setCursorVisible(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (otpNumber.length() == 4) {
            PreferenceUtils.setLogin(true);
            presenter.verifyMobileNumber(this, new VerifyMobileRequest(PreferenceUtils.getUserMono(), Integer.parseInt(otpNumber.toString())));
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (otpNumber.length() == 1) {
                mBinding.edFirst.requestFocus();
                otpNumber.deleteCharAt(0);
            } else if (otpNumber.length() == 2) {
                mBinding.edSecond.clearFocus();
                mBinding.edFirst.requestFocus();
                otpNumber.deleteCharAt(1);
            } else if (otpNumber.length() == 3) {
                mBinding.edThird.clearFocus();
                mBinding.edSecond.requestFocus();
                otpNumber.deleteCharAt(2);
            } else if (otpNumber.length() == 4) {
                mBinding.edFourth.clearFocus();
                mBinding.edThird.requestFocus();
                otpNumber.deleteCharAt(3);
            }
        }
        return false;
    }

    @Override
    public void ok(String str) {
        presenter.getLoginDetail(this, new LoginRequest(PreferenceUtils.getUserName(), PreferenceUtils.getUserMono(),
                PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
        hideKeyboard();
    }

    @Override
    public void cancel() {

    }
}
