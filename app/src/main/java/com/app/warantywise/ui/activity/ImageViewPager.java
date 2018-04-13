package com.app.warantywise.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.warantywise.R;
import com.app.warantywise.databinding.ImageFullscreenPreviewBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.network.response.dashboard.StoreImages;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;

import java.util.ArrayList;

/**
 * Created by rajnikant on 28/01/18.
 */

//	adapter
public class ImageViewPager extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private ImageFullscreenPreviewBinding mBinding;
    private ArrayList<Product> storeImageList;
    private Activity activity;
    public ImageViewPager(BaseActivity activity, ArrayList<Product> storeImageList) {
        layoutInflater=LayoutInflater.from(activity);
        this.activity=activity;
        this.storeImageList=storeImageList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mBinding=DataBindingUtil.inflate(layoutInflater, R.layout.image_fullscreen_preview, container, false);
       initializeData(container,position);
        return mBinding.getRoot();
    }

    private void initializeData(ViewGroup container, int position) {
        Product image = storeImageList.get(position);
           /* Glide.with(getActivity()).load(image.getLarge())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewPreview);*/
        GlideUtils.loadImageWithZoom(activity,image.getImageUrl(),mBinding.ivPreview);
        container.addView(mBinding.getRoot());
    }

    @Override
    public int getCount() {
        return CommonUtility.isNotNull(storeImageList)?storeImageList.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view ==obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
