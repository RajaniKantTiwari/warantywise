package com.app.warantywise.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.warantywise.utility.AppConstants.FRAGMENTS.PRODUCT_LIST_FRAGMENT;
import static com.app.warantywise.utility.AppConstants.FRAGMENTS.PRODUCT_MAP_FRAGMENT;
import static com.app.warantywise.utility.AppConstants.FRAGMENTS.NOTIFICATION_FRAGMENT;
import static com.app.warantywise.utility.AppConstants.FRAGMENTS.USER_FRAGMENT;

/**
 * Created by arvind on 01/11/17.
 */

public interface AppConstants {
    String AUTHORIZATION = "Authorization";
    String SUCCESS = "success";
    String FORBIDDEN = "403";
    String UN_AUTHORIZED = "401";
    String ORDER_DETAILS = "order_details";
    String TIME_ZONE="GMT";
    String PROFILE_UPDATE_PARAMETER = "profileUrl";
    int LOGOUT = 101;
    String DEVICETYPE="1";
    int NO_OF_TAB = 4;
    int DEVICE_TOKEN_RESPONSE=1;
    int HOME =0;
    int MYWARANTY = 1;
    String STORE_NAME = "store_name";
    String ID = "id";
    int HELP = 2;
    String PRODUCT_INFO = "product_info";
    int SENIOR_CITIZEN = 5;
    int TERM_CONDITION = 3;
    int REQUEST_CALL = 1;

    long SPLASH_TIME = 3000;
    int CORNER_RADIUS = 6;
    int LIST_PRODUCT = 1;
    int MAP_PRODUCT = 2;
    String RESPONSE = "response";
    int PERMISSIONS_REQUEST_LOCATION = 99;
    int MAX_LENGTH = 50;
    float MAX_ZOOM = 17;
    float TILT = 90;
    float BEARING = 45;
    int PLANHEIGHT = 48;
    int INSURANCE_PLANHEIGHT = 54;
    int COVER_HEIGHT = 38;
    int DATE_OF_BIRTH=1;
    int ANIVERSARY=2;
    int PADDING = 17;
    int PRODUCT_IMAGE = 0;
    int BILL_IMAGE=1;
    int BARCODE_IMAGE = 2;
    int WARRANTY_CARD_IMAGE = 3;
    long API_SERVICE = 300;
    int API_TIMEOUT_IN_SECOND=200;


    @IntDef({NOTIFICATION_FRAGMENT,USER_FRAGMENT, PRODUCT_MAP_FRAGMENT,
            PRODUCT_LIST_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int NOTIFICATION_FRAGMENT=0;
        int USER_FRAGMENT=1;
        int PRODUCT_MAP_FRAGMENT = 3;
        int PRODUCT_LIST_FRAGMENT = 4;
    }
}
