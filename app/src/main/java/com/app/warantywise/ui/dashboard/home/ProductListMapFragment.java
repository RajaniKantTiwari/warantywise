package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentHomeBinding;
import com.app.warantywise.event.OfferEvent;
import com.app.warantywise.event.WarrantyEvent;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.request.dashboard.OfferRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.ManufactorServiceCentorResponseData;
import com.app.warantywise.network.response.dashboard.OfferResponseData;
import com.app.warantywise.network.response.dashboard.ResponseData;
import com.app.warantywise.network.response.dashboard.ServiceCentorResponse;
import com.app.warantywise.network.response.dashboard.WarrantyCardImage;
import com.app.warantywise.network.response.dashboard.WarrantyCardImageData;
import com.app.warantywise.network.response.dashboard.YourProduct;
import com.app.warantywise.network.response.dashboard.YourProductData;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.DashboardPresenter;
import com.app.warantywise.ui.event.ProductEvent;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Amul on 13/11/17.
 */
public class ProductListMapFragment extends DashboardFragment {

    @Inject
    DashboardPresenter presenter;
    private ProductEvent event;
    FragmentHomeBinding mBinding;
    private ArrayList<YourProduct> productList;
    private ArrayList<ServiceCentorResponse> productMapList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        CommonUtility.register(this);
        return mBinding.getRoot();
    }

    private void addFragment() {
        mBinding.listButton.setTag(R.drawable.location);
        mBinding.listButton.setVisibility(View.VISIBLE);
        event = new ProductEvent();
        event.setListMap(AppConstants.LIST_PRODUCT);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.PRODUCT_MAP_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE, false);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.PRODUCT_LIST_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE, false);
    }


    @Override
    public void initializeData() {
        addFragment();
        productList = new ArrayList<>();
        productMapList = new ArrayList<>();
        getPresenter().yourProduct(getDashboardActivity());
        getPresenter().getManufactorServiceCenter(getDashboardActivity());

    }

    @Override
    public void setListener() {
        mBinding.listButton.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return ProductListMapFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onDestroy() {
        CommonUtility.unregister(this);
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        if (view == mBinding.listButton) {
            if ((int) mBinding.listButton.getTag() == R.drawable.location) {
                mBinding.listButton.setImageResource(R.drawable.checked);
                mBinding.listButton.setTag(R.drawable.checked);
            } else {
                mBinding.listButton.setImageResource(R.drawable.location);
                mBinding.listButton.setTag(R.drawable.location);
            }
            CommonUtility.clicked(mBinding.listButton);
            listMapConversion();
        }
    }


    private void listMapConversion() {
        if (event.getListMap() == AppConstants.LIST_PRODUCT) {
            event.setListMap(AppConstants.MAP_PRODUCT);
        } else {
            event.setListMap(AppConstants.LIST_PRODUCT);
        }
        EventBus.getDefault().post(event);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 1) {
                YourProductData productData = (YourProductData) response;
                setProductData(productData);
            } else if (requestCode == 2) {
                ManufactorServiceCentorResponseData serviceCentorResponseData = (ManufactorServiceCentorResponseData) response;
                setMapData(serviceCentorResponseData);
            }else if (requestCode == 3) {
                OfferResponseData data = (OfferResponseData) response;
                setOfferData(data);
            }else if (requestCode == 4) {
                WarrantyCardImageData data = (WarrantyCardImageData) response;
                setWarrantyCardData(data);
            }
        }
    }

    private void setWarrantyCardData(WarrantyCardImageData data) {
        event.setOfferWarrantyImage(2);
        event.setWarrantyImageList(data.getInfo());
        EventBus.getDefault().post(event);
    }

    private void setOfferData(OfferResponseData data) {
        event.setOfferWarrantyImage(1);
        event.setOfferList(data.getInfo());
        EventBus.getDefault().post(event);
    }
    private void setMapData(ManufactorServiceCentorResponseData serviceCentorResponseData) {
        productMapList.clear();
        if (CommonUtility.isNotNull(serviceCentorResponseData.getInfo())) {
            productMapList.addAll(serviceCentorResponseData.getInfo());
            event.setProductMapList(productMapList);
            EventBus.getDefault().post(event);
        }
    }

    private void setProductData(YourProductData productData) {
        productList.clear();
        if (CommonUtility.isNotNull(productData.getInfo())) {
            productList.addAll(productData.getInfo());
            event.setProductList(productList);
            EventBus.getDefault().post(event);
        }
    }

    @Subscribe
    public void onOfferEvent(OfferEvent event) {
        getPresenter().getProductOffers(getDashboardActivity(), new OfferRequest("1"));
    }

    @Subscribe
    public void onWarrantyEvent(WarrantyEvent event) {
        getPresenter().getWarrantyCardImage(getDashboardActivity(), new WarrantyCardImageRequest(productList.get(event.getPosition()).getProduct_id()));

    }

}
