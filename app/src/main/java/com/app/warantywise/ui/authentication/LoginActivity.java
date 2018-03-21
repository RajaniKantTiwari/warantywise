package com.app.warantywise.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityLoginBinding;
import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.base.MvpView;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.PreferenceUtils;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import javax.inject.Inject;

public class LoginActivity extends CommonActivity implements MvpView, View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private String mobileNumber;


    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initializeData();
        setListener();
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    public void initializeData() {
        PreferenceUtils.setLatitude(28.544869);
        PreferenceUtils.setLongitude(77.128134);

    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (isNotNull(response)) {
            if (response instanceof LoginResponse) {
                LoginResponse loginResponse = (LoginResponse) response;
                if (isNotNull(loginResponse)) {
                    String type = loginResponse.getType();
                    if (type.equals(AppConstants.SUCCESS)) {
                        PreferenceUtils.setUserName(userName);
                        PreferenceUtils.setUserMono(mobileNumber);
                        ExplicitIntent.getsInstance().navigateTo(this, VerifyAccountActivity.class);
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            CommonUtility.clicked(mBinding.tvSubmit);
            if (isValid()) {
                Bundle bundle = new Bundle();
                bundle.putString(BundleConstants.USER_NAME, userName);
                bundle.putString(BundleConstants.MOBILE_NUMBER, mobileNumber);
                if (isNetworkConnected()) {
                    presenter.getLoginDetail(this, new LoginRequest(userName, mobileNumber,
                            PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
                }
            }
        }
    }

    private boolean isValid() {
        mobileNumber = mBinding.edMobileNumber.getText().toString();
        userName = mBinding.edName.getText().toString();
        if (isNull(userName) || userName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_name));
            return false;
        } else if (isNull(mobileNumber) || mobileNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_mobile_number));
            return false;
        } else if (mobileNumber.trim().length() < 10) {
            showToast(getResources().getString(R.string.please_enter_ten_digit_valid_mobile_nubmer));
            return false;
        }
        return true;
    }


}
