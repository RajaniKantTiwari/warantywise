package com.app.warantywise.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.warantywise.utility.AppConstants.FRAGMENTS.NOTIFICATION_FRAGMENT;
import static com.app.warantywise.utility.AppConstants.FRAGMENTS.USER_FRAGMENT;

/**
 * Created by arvind on 01/11/17.
 */

public interface AppConstants {
    String AUTHORIZATION = "Authorization";
    String SUCCESS = "success";
    String FORBIDDEN = "403";
    String TIME_ZONE="GMT";
    String PROFILE_UPDATE_PARAMETER = "profileUrl";
    int LOGOUT = 101;
    String DEVICETYPE="1";
    int NO_OF_TAB = 4;
    int DEVICE_TOKEN_RESPONSE=1;
    int HOME =0;
    int MYWARANTY = 1;
    int INSURANCE = 2;
    int EDIT_PROFILE = 3;
    int HELP = 4;
    int SENIOR_CITIZEN = 5;
    int TERM_CONDITION = 6;
    int PAYMENT_HEIGHT = 48;
    long SPLASH_TIME = 800;

    @IntDef({NOTIFICATION_FRAGMENT,USER_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int NOTIFICATION_FRAGMENT=0;
        int USER_FRAGMENT=1;
    }
}
