package com.app.warantywise.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentZoomImageSliderBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.StoreImages;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;


public class ZoomAnimationImageActivity extends BaseActivity {
    private ArrayList<Product> storeImageList;
    private ImageViewPager mImageAdapter;
    private int selectedPosition = 0;
    private Animation animation;
    private FragmentZoomImageSliderBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this, R.layout.fragment_zoom_image_slider);
        initializeData();
        setListener();
    }

    private void setCurrentItem(int position) {
        mBinding.viewPager.setCurrentItem(position, false);
        displayMetaInfo(selectedPosition);
    }

    //	page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void displayMetaInfo(int position) {
        if(CommonUtility.isNotNull(storeImageList)&&storeImageList.size()>position){
            mBinding.tvCount.setText((position + 1) + " of " + storeImageList.size());
            Product storeImage = storeImageList.get(position);
            //lblTitle.setText(storeImage.getCreated_at());
        }
    }

    public void initializeData() {
        Intent intent = getIntent();
        if(CommonUtility.isNotNull(intent)){
            Bundle bundle=intent.getExtras();
            if(CommonUtility.isNotNull(bundle)){
                storeImageList = bundle.getParcelableArrayList(BundleConstants.IMAGE_LIST);
                selectedPosition = bundle.getInt(BundleConstants.POSITION);
            }
            mImageAdapter = new ImageViewPager(this,storeImageList);
            mBinding.viewPager.setAdapter(mImageAdapter);
            mBinding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
            setCurrentItem(selectedPosition);
        }

    }

    public void setListener() {
        mBinding.imageLayout.setOnClickListener(this);
    }


    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        //mBinding.imageLayout.setVisibility(View.GONE);
        mBinding.getRoot().startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, BundleConstants.ANIMATION_TIME);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onBackPressed() {

    }
}
