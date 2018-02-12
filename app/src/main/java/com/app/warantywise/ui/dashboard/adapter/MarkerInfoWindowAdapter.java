package com.app.warantywise.ui.dashboard.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ProductDialogBinding;
import com.app.warantywise.network.response.dashboard.MerchantResponse;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final LayoutInflater mInflator;
    private Activity mActivity;
    private HashMap<Marker, MerchantResponse> mMarkersHashMap;

    public MarkerInfoWindowAdapter(Activity activity, HashMap<Marker, MerchantResponse> mMarkersHashMap) {
        mActivity = activity;
        this.mMarkersHashMap = mMarkersHashMap;
        mInflator = LayoutInflater.from(mActivity);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ProductDialogBinding mBinding = DataBindingUtil.inflate(mInflator, R.layout.product_dialog, null, false);
        MerchantResponse response = mMarkersHashMap.get(marker);
        if (CommonUtility.isNotNull(response)) {
            GlideUtils.loadImage(mActivity, response.getImage(), mBinding.ivProduct, null, R.drawable.icon_placeholder);
            mBinding.setMerchantResponse(response);
            mBinding.tvTitle.setText(response.getName());
            mBinding.tvAddress.setText(response.getAddress());
            mBinding.tvType.setText(response.getCategory());
        }
        return mBinding.getRoot();
    }
}
