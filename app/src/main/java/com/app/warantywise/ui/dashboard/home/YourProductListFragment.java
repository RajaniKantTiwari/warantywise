package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentProductListBinding;
import com.app.warantywise.network.request.dashboard.OfferRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.OfferResponse;
import com.app.warantywise.network.response.dashboard.OfferResponseData;
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.home.adapter.ProductListAdapter;
import com.app.warantywise.ui.dialogfrag.OfferDialogFragment;
import com.app.warantywise.ui.event.MerchantEvent;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class YourProductListFragment extends DashboardFragment implements
        ProductListAdapter.ProductListListener,OfferDialogFragment.OfferDialogListener{

    private FragmentProductListBinding mBinding;
    private ProductListAdapter mProductAdapter;
    private ArrayList<ProductResponse> productList;
    private ArrayList<OfferResponse> offerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
        CommonUtility.register(this);
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return YourProductListFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.your_product));
        //For Plan
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvProductList.setLayoutManager(layoutManager);
        mProductAdapter = new ProductListAdapter(getDashboardActivity(), this);
        mBinding.rvProductList.setAdapter(mProductAdapter);
        offerList=new ArrayList<>();
        getPresenter().getProductOffers(getDashboardActivity(),new OfferRequest("1"));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    @Subscribe
    public void onMessageEvent(MerchantEvent event) {
        if (event.getListMap() == AppConstants.LIST_PRODUCT) {
            mBinding.layoutList.setVisibility(View.VISIBLE);
            productList = event.getProductList();
            mProductAdapter.setLocationList(event.getProductList());
        } else if (event.getListMap() == AppConstants.MAP_PRODUCT) {
            mBinding.layoutList.setVisibility(View.GONE);
        }
    }

    @Override
    public void onOfferClicked(int position) {
        Bundle bundle=new Bundle();
       CommonUtility.showOfferDialog(getDashboardActivity(),bundle,this);
    }

    @Override
    public void onBuyInsuranceClicked(int position) {
        getDashboardActivity().addFragmentInContainer(new InsuranceChoiceFragment(),null,true
                ,true, BaseActivity.AnimationType.NONE,false);
    }

    @Override
    public void onExtendClicked(int position) {
        getDashboardActivity().addFragmentInContainer(new YourProductFragment(),null,true
                ,true, BaseActivity.AnimationType.NONE,false);
    }

    @Override
    public void onWarrantyClicked(int position) {
        getDashboardActivity().addFragmentInContainer(new DetailsFragment(),null,true
                ,true, BaseActivity.AnimationType.NONE,false);
    }
}
