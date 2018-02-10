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
        mBinding.tvGetOtp.setOnClickListener(this);
        mBinding.tvSignupForAccount.setOnClickListener(this);
    }

    public void initializeData() {
        try {
            /*InstaceID instanceID = InstanceID.getInstance(this);

            String token = instanceID.getAuthToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);*/

            //Log.i(TAG, "GCM Registration Token: " + token);

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if(isNotNull(response)){
            if(response instanceof LoginResponse){
                LoginResponse loginResponse=(LoginResponse)response;
                if(isNotNull(loginResponse)){
                    String type=loginResponse.getType();
                    if(type.equals(AppConstants.SUCCESS)){
                        PreferenceUtils.setUserName(userName);
                        PreferenceUtils.setUserMono(mobileNumber);
                        Bundle bundle=new Bundle();
                        bundle.putString(BundleConstants.USER_NAME,userName);
                        bundle.putString(BundleConstants.MOBILE_NUMBER,mobileNumber);
                        ExplicitIntent.getsInstance().navigateTo(this,VerifyAccountActivity.class,bundle);
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvGetOtp){
            CommonUtility.clicked(mBinding.tvGetOtp);
           if(isValid()){
               if(isNetworkConnected()){
                   presenter.getLoginDetail(this,new LoginRequest(userName,mobileNumber,
                           PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
               }
           }
        }else if(view==mBinding.tvSignupForAccount){
            CommonUtility.clicked(mBinding.tvSignupForAccount);
        }
    }

    private boolean isValid() {
        mobileNumber=mBinding.edMobileNumber.getText().toString();
        userName=mBinding.edName.getText().toString();
        if((isNotNull(mobileNumber)&&mobileNumber.trim().length()>0)&&(isNotNull(userName)&&userName.trim().length()>0)){
            return true;
        }else if(isNull(userName)||userName.trim().length()==0){
            showToast(getResources().getString(R.string.please_enter_name));
            return false;
        }else{
            showToast(getResources().getString(R.string.please_enter_mobile_number));
            return false;
        }

    }


}
