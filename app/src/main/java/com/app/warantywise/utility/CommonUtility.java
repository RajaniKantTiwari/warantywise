package com.app.warantywise.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.app.warantywise.BuildConfig;
import com.app.warantywise.R;
import com.app.warantywise.network.request.dashboard.InsurancePlan;
import com.app.warantywise.network.request.dashboard.Plans;
import com.app.warantywise.ui.authentication.AddProductActivity;
import com.app.warantywise.ui.authentication.LoginActivity;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dialogfrag.CustomDialogFragment;
import com.app.warantywise.ui.dialogfrag.FeedbackDialogFragment;
import com.app.warantywise.ui.dialogfrag.OfferDialogFragment;
import com.app.warantywise.ui.dialogfrag.ProfileDialogFragment;
import com.app.warantywise.widget.CustomEditText;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by arvind on 01/11/17.
 */

public class CommonUtility {
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private DateFormat newDateFormat;
    private static final String TAG=CommonUtility.class.getSimpleName();

    public static int convertDpToPx(int dp, Context context) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }

    /**
     * Show toast message for long time
     *
     * @param context
     * @param msg
     */
    public static void showToastLong(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

    //Check object is null
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static void showDialog(AppCompatActivity activity, Bundle bundle, CustomDialogFragment.CustomDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        CustomDialogFragment alertdFragment = new CustomDialogFragment();
        alertdFragment.DialogListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }


    public static void showOrderDialog(AppCompatActivity activity, Bundle bundle, FeedbackDialogFragment.FeedbackDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FeedbackDialogFragment alertdFragment = new FeedbackDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }
    public static void showUpdateDialog(AppCompatActivity activity, Bundle bundle, ProfileDialogFragment.ProfileDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        ProfileDialogFragment alertdFragment = new ProfileDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }

    public static void showOfferDialog(AppCompatActivity activity, Bundle bundle, OfferDialogFragment.OfferDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        OfferDialogFragment alertdFragment = new OfferDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }


    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean checkService(BaseActivity activity) {
        if (!BuildConfig.DEBUG && CommonUtility.isSimulator()) {
            activity.showToast(activity.getResources().getString(R.string.google_service_not_present));
            return false;
        }
        if (!BuildConfig.DEBUG && !CommonUtility.checkGooglePlaySevices(activity)) {
            activity.showToast(activity.getResources().getString(R.string.google_service_not_present));
            return false;
        }
        return true;
    }

    public static boolean isSimulator() {
        boolean isSimulator = "google_sdk".equals(Build.PRODUCT)
                || "vbox86p".equals(Build.PRODUCT)
                || "sdk".equals(Build.PRODUCT);
        LogUtils.LOGD(TAG, "Build.PRODUCT= " + Build.PRODUCT + "  isSimulator= "
                + isSimulator);

        return isSimulator;
    }

    public static boolean checkGooglePlaySevices(final Activity activity) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                Dialog dialog = googleAPI.getErrorDialog(activity, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
                dialog.show();
            }
            return false;
        }

        return true;
    }

    /**
     * Convert Time from milli to DD/MM/YYYY format
     *
     * @param millis
     * @return
     */
    public static String convertMilliToEEEMMMDYYYY(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d, yyyy hh:mm aaa");
        return formatter.format(new Date(millis));
    }

    public static String convertMilliToTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aaa");

        return formatter.format(new Date(time));
    }

    public static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);
    }

    public static int getColor(String color) {
        return Color.parseColor(color);
    }

    public static void clicked(View view) {
        view.setEnabled(false);
        final Animation animation = new AlphaAnimation(1.0f, 0.3f);
        animation.setDuration(100);
        animation.setFillAfter(true);
        view.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(100);
                animation.setFillAfter(true);
                view.startAnimation(animation);
            }
        }, 110);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 1000);
    }

    public static void setDialog(Dialog dialog) {
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
    }

    public static void setPadding(Dialog dialog, Activity activity) {
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, convertDpToPx(10, activity));
        dialog.getWindow().setBackgroundDrawable(inset);
    }

    public static void register(Fragment fragment) {
        if (!EventBus.getDefault().isRegistered(fragment)) {
            EventBus.getDefault().register(fragment);
        }
    }

    public static void register(Activity activity) {
        if (!EventBus.getDefault().isRegistered(activity)) {
            EventBus.getDefault().register(activity);
        }
    }

    public static void unregister(Fragment fragment) {
        if (EventBus.getDefault().isRegistered(fragment)) {
            EventBus.getDefault().unregister(fragment);
        }
    }

    public static void unregister(Activity activity) {
        if (EventBus.getDefault().isRegistered(activity)) {
            EventBus.getDefault().unregister(activity);
        }
    }


    public static String oneDecimalPlaceString(String str) {
        if (isNotNull(str) && str.length() > 0) {
            double value = Double.parseDouble(str);
            return String.format("%.1f", value);
        }
        return null;
    }

    public static String twoDecimalPlaceString(String str) {
        if (isNotNull(str) && str.length() > 0) {
            double value = Double.parseDouble(str);
            return String.format("%.2f", value);
        }
        return null;
    }

    public static String oneDecimalPlace(double number) {
        return String.format("%.1f", number);
    }

    public static String twoDecimalPlace(double number) {
        return String.format("%.2f", number);
    }

    public static String getStartEndDate(String startDate, String endDate) {
        StringBuilder date = new StringBuilder();
        date.append(changeStringIntoDate(startDate));
        date.append(" to ");
        date.append(changeStringIntoDate(endDate));
        return date.toString();
    }

    public static String addStartEndDate(String startDate, String endDate) {
        StringBuilder date = new StringBuilder();
        date.append(startDate);
        date.append(" to ");
        date.append(endDate);
        return date.toString();
    }

    public static String getDay(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        DateFormat outputFormat = new SimpleDateFormat("E");// the day of the week

        //DateFormat outputFormat = new SimpleDateFormat("EEEE");// the day of the week spelled out completely
        outputFormat.setTimeZone(timeZone);
        try {
            String formattedDate = outputFormat.format(date);
            return formattedDate;
        } catch (Exception ex) {
            ex.toString();
        }
        return null;
    }

    public static String changeStringIntoDate(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        DateFormat outputFormat = new SimpleDateFormat("hh:mm a");
        outputFormat.setTimeZone(timeZone);
        try {
            String formattedDate = outputFormat.format(date);
            return formattedDate;
        } catch (Exception ex) {
            ex.toString();
        }
        return null;
    }

    public static String addStrings(String first, String second) {
        String str = first +" "+second;
        return str;
    }

    public static float setRating(String rating) {
        String str = String.format("%.2f", Double.parseDouble(rating));
        return Float.parseFloat(str);
    }

    public static void setDays(ArrayList<Days> daysArrayList) {
        Days day1 = new Days();
        day1.setNameOfDays("Sunday");
        daysArrayList.add(day1);

        Days day2 = new Days();
        day2.setNameOfDays("Monday");
        daysArrayList.add(day2);

        Days day3 = new Days();
        day3.setNameOfDays("Tuesday");
        daysArrayList.add(day3);

        Days day4 = new Days();
        day4.setNameOfDays("WednusDay");
        daysArrayList.add(day4);

        Days day5 = new Days();
        day5.setNameOfDays("Thursday");
        daysArrayList.add(day5);

        Days day6 = new Days();
        day6.setNameOfDays("Friday");
        daysArrayList.add(day6);

        Days day7 = new Days();
        day7.setNameOfDays("Saturday");
        daysArrayList.add(day7);
    }

    public static void setPlan(ArrayList<Plans> daysArrayList) {
        Plans plan1 = new Plans();
        plan1.setPlanRate("Rs 765");
        plan1.setWarantyPeriod("for 1 Year");
        plan1.setReplaceMentService("Replacement & on spot service");
        daysArrayList.add(plan1);


        Plans plan2 = new Plans();
        plan2.setPlanRate("Rs 865");
        plan2.setWarantyPeriod("for 2 Year");
        plan2.setReplaceMentService("Replacement & on spot service");
        daysArrayList.add(plan2);

        Plans plan3 = new Plans();
        plan3.setPlanRate("Rs 877");
        plan3.setWarantyPeriod("for 3 Year");
        plan3.setReplaceMentService("Replacement & on spot service");
        daysArrayList.add(plan3);


    }


    public static void setInsurancePlan(ArrayList<InsurancePlan> insurancePlansList) {
        InsurancePlan plan1 = new InsurancePlan();
        plan1.setImageUrl("");
        plan1.setInsuranceCompanyName("Thomas cook");
        plan1.setInsuranceServiceName("Thomas cook product save insurance");
        plan1.setInsurancePlanRate("765");

        insurancePlansList.add(plan1);


        InsurancePlan plan2 = new InsurancePlan();
        plan2.setImageUrl("");
        plan2.setInsuranceCompanyName("Tata AIG");
        plan2.setInsuranceServiceName("Tata AUI insurance");
        plan2.setInsurancePlanRate("665");

        insurancePlansList.add(plan2);

        InsurancePlan plan3 = new InsurancePlan();
        plan3.setImageUrl("");
        plan3.setInsuranceCompanyName("Reliance Insurance");
        plan3.setInsurancePlanRate("865");
        plan3.setInsuranceServiceName("Reliance general product");
        insurancePlansList.add(plan3);


    }

    public static String getDeviceUniqueId(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getCountryName(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException ignored) {
            Address result;
            if (addresses != null && !addresses.isEmpty()) {
                return addresses.get(0).getCountryName();
            }
            return null;
        }
        return addresses.get(0).getCountryCode();
    }

    public static void setRecyclerViewHeight(RecyclerView recyclerView, List list, int height) {
        if (CommonUtility.isNotNull(list)) {
            ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
            params.height = CommonUtility.convertDpToPx(height, recyclerView.getContext()) * list.size();
            recyclerView.setLayoutParams(params);
        }
    }

    public static void logout(Activity activity) {
        PreferenceUtils.setDeviceToken(null);
        PreferenceUtils.setUserId(-1);
        PreferenceUtils.setAuthToken(null);
        PreferenceUtils.setLogin(false);
        ExplicitIntent.getsInstance().navigateTo(activity, LoginActivity.class);
    }

    public static void setVisibility(View layoutMain, View layoutNoData, boolean shouldMainVisible) {
        if (shouldMainVisible) {
            layoutMain.setVisibility(View.VISIBLE);
            layoutNoData.setVisibility(View.GONE);
        } else {
            layoutMain.setVisibility(View.GONE);
            layoutNoData.setVisibility(View.VISIBLE);
        }
    }

    /**
     * create multipart from file
     *
     * @param filePath
     * @return
     */
    public static MultipartBody.Part createMultipart(String filePath, String parameter) {
        if (TextUtils.isEmpty(filePath)) return null;
        File file = new File(filePath.replace("file:////", "/"));

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(parameter, file.getName(), requestFile);
    }

    public static void showCursorEnd(CustomEditText editText) {
        editText.setSelection(editText.getText().length());
    }

    public static boolean isShopOpen(String openTime, String closeTime) {
        long open = 0;
        long close = 0;
        final Date current = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
        String date = dateFormat.format(current);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm aa");
        try {
            Date startTime = simpleDateFormat.parse(date + " " + openTime);
            Date endTime = simpleDateFormat.parse(date + " " + closeTime);
            open = current.getTime() - startTime.getTime();
            close = endTime.getTime() - current.getTime();
            //Log.e("OPEN_TIME", "Current " + current.getTime() + " Open   " + startTime.getTime() + " Close  " + endTime.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return open > 0 && close > 0;
    }

    public static String getCreatedDate(String date) {
        String createdDate=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
            Date cDate=dateFormat.parse(date);
            DateFormat newDateFormat = new SimpleDateFormat("hh:mm aa dd MMM yyyy");
            newDateFormat.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
            return newDateFormat.format(cDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createdDate;
    }

    public static void openDatePicker(AddProductActivity activity) {
        DatePickerDialog dpd=null;

        Calendar now = Calendar.getInstance();
        if (dpd == null) {
            dpd = DatePickerDialog.newInstance(
                    activity,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        }
        dpd.vibrate(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_1);
            dpd.setAccentColor(Color.parseColor("#9C27B0"));

        dpd.show(activity.getFragmentManager(), "Datepickerdialog");
    }

    public static void openDatePickerProfile(ProfileDialogFragment fragment,android.app.FragmentManager fragmentManager) {
        DatePickerDialog dpd=null;

        Calendar now = Calendar.getInstance();
        if (dpd == null) {
            dpd = DatePickerDialog.newInstance(
                    fragment,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        }
        dpd.vibrate(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_1);
        dpd.setAccentColor(Color.parseColor("#9C27B0"));

        dpd.show(fragmentManager, "Datepickerdialog");
    }
    public static String getIntMonth(int monthOfYear) {
        if(monthOfYear<10){
          return "0"+monthOfYear;
        }
        return ""+monthOfYear;
    }
    public static String getMonth(int monthOfYear) {
        switch (monthOfYear){
            case 0:
                return "jan";
            case 1:
                return "feb";

            case 2:
                return "mar";
            case 3:
                return "apr";
            case 4:
                return "may";

            case 5:
                return "jun";

            case 6:
                return "jul";
            case 7:
                return "aug";

            case 8:
                return "sept";
            case 9:
                return "oct";
            case 10:
                return "nov";

            case 11:
                return "dec";

        }
        return null;
    }
    public static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }
}
