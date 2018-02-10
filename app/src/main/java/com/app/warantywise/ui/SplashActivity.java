package com.app.warantywise.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.warantywise.R;
import com.app.warantywise.WarantyApplication;
import com.app.warantywise.ui.authentication.LoginActivity;
import com.app.warantywise.ui.authentication.VerifyAccountActivity;
import com.app.warantywise.ui.dashboard.DashBoardActivity;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.PreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(WarantyApplication.isDebug){
                    ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, DashBoardActivity.class);
                }else{
                    if (PreferenceUtils.isLogin()) {
                        ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, DashBoardActivity.class);
                    } else {
                        ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, LoginActivity.class);

                    }
                }
                finish();
            }
        }, AppConstants.SPLASH_TIME);
    }
}
