package com.app.warantywise.ui.dashboard.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ProductDialogBinding;
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final LayoutInflater mInflator;
    private Activity mActivity;
    private HashMap<Marker, ProductResponse> mMarkersHashMap;
    private MarkerInfoListener listener;

    public interface MarkerInfoListener {
        void call(String mobileNumber);
        void view(String view);
    }

    public MarkerInfoWindowAdapter(Activity activity, HashMap<Marker, ProductResponse> mMarkersHashMap, MarkerInfoListener listener) {
        mActivity = activity;
        this.mMarkersHashMap = mMarkersHashMap;
        mInflator = LayoutInflater.from(mActivity);
        this.listener = listener;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ProductDialogBinding mBinding = DataBindingUtil.inflate(mInflator, R.layout.product_dialog, null, false);
        ProductResponse response = mMarkersHashMap.get(marker);
        if (CommonUtility.isNotNull(response)) {
            GlideUtils.loadImage(mActivity, response.getImage(), mBinding.ivProduct, null, R.drawable.icon_placeholder);
            mBinding.setMerchantResponse(response);
            mBinding.tvService.setText(response.getName());
            mBinding.tvAddress.setText(response.getAddress());
            GlideUtils.loadImageTwoRoundedCorner(mActivity,response.getImage(),mBinding.ivProduct,
                    null,R.drawable.shubh,6,6,0,0);

            mBinding.tvCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 listener.call("9821945433");
                }
            });

            mBinding.tvView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.view("9821945433");
                }
            });
        }
        return mBinding.getRoot();
    }
}
