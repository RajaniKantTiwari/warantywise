package com.app.warantywise.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.warantywise.utility.AppConstants.FRAGMENTS.MERCHANT_LIST_FRAGMENT;
import static com.app.warantywise.utility.AppConstants.FRAGMENTS.MERCHANT_MAP_FRAGMENT;
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
    int SETTING_HEIGHT = 44;
    int REQUEST_CALL = 1;

    long SPLASH_TIME = 3000;
    int CORNER_RADIUS = 10;
    int LIST_PRODUCT = 1;
    int MAP_PRODUCT = 2;
    String RESPONSE = "response";
    int PERMISSIONS_REQUEST_LOCATION = 99;
    int MAX_LENGTH = 50;
    float MAX_ZOOM = 17;
    float TILT = 90;
    float BEARING = 45;
    int PLANHEIGHT = 48;
    int INSURANCE_PLANHEIGHT = 52;
    int COVER_HEIGHT = 38;
    String MOBILE_NUMBER = "mobile_number";
    int DATE_OF_BIRTH=1;
    int ANIVERSARY=2;
    int PADDING = 17;


    @IntDef({NOTIFICATION_FRAGMENT,USER_FRAGMENT,MERCHANT_MAP_FRAGMENT,
            MERCHANT_LIST_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int NOTIFICATION_FRAGMENT=0;
        int USER_FRAGMENT=1;
        int MERCHANT_MAP_FRAGMENT = 3;
        int MERCHANT_LIST_FRAGMENT = 4;
    }
}
