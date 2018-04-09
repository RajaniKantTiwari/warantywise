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
import com.app.warantywise.event.OfferEvent;
import com.app.warantywise.event.ProductAdded;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.WarrantyCardImageData;
import com.app.warantywise.network.response.dashboard.YourProduct;
import com.app.warantywise.network.response.dashboard.YourProductData;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.home.adapter.ProductListAdapter;
import com.app.warantywise.ui.dialogfrag.OfferDialogFragment;
import com.app.warantywise.ui.event.ProductEvent;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class YourProductListFragment extends DashboardFragment implements
        ProductListAdapter.ProductListListener, OfferDialogFragment.OfferDialogListener {

    private FragmentProductListBinding mBinding;
    private ProductListAdapter mProductAdapter;
    private ArrayList<YourProduct> productList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
        CommonUtility.register(this);
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().attachView(this);
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
        productList = new ArrayList<>();
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.your_product));
        //For Plan
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvProductList.setLayoutManager(layoutManager);
        mProductAdapter = new ProductListAdapter(getDashboardActivity(), productList, this);
        mBinding.rvProductList.setAdapter(mProductAdapter);
        getPresenter().yourProduct(getDashboardActivity());
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if(requestCode==1){
                YourProductData productData = (YourProductData) response;
                setProductData(productData);
            }else if(requestCode==4){
                WarrantyCardImageData data = (WarrantyCardImageData) response;
                setWarrantyCardData(data);
            }
        }

    }
    private void setProductData(YourProductData productData) {
        productList.clear();
        if (CommonUtility.isNotNull(productData.getInfo())&&productData.getInfo().size()>0) {
            productList.clear();
            productList.addAll(productData.getInfo());
            mProductAdapter.notifyDataSetChanged();
        }
    }
    private void setWarrantyCardData(WarrantyCardImageData data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleConstants.WARRANTY_IMAGE, data.getInfo().size() > 0 ? data.getInfo().get(0) : null);
        CommonUtility.showWarrantyImageDialog(getDashboardActivity(), bundle);
    }
    @Override
    public void onOfferClicked(int position) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(BundleConstants.PRODUCT,productList.get(position));
        //It would be modifiy
        getDashboardActivity().addFragmentInContainer(new OfferFragment(), bundle, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    @Override
    public void onBuyInsuranceClicked(int position) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(BundleConstants.PRODUCT,productList.get(position));
        getDashboardActivity().addFragmentInContainer(new InsuranceChoiceFragment(), bundle, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    @Override
    public void onExtendClicked(int position) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(BundleConstants.PRODUCT,productList.get(position));
        getDashboardActivity().addFragmentInContainer(new YourProductFragment(), bundle, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    @Override
    public void onWarrantyClicked(int position) {
        getPresenter().getWarrantyCardImage(this,getDashboardActivity(), new WarrantyCardImageRequest(productList.get(position).getWw_productid()));
    }

    @Override
    public void onLocationClicked(int position) {
        getDashboardActivity().addFragmentInContainer(new ProductMapFragment(), null, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    @Override
    public void onDetailsClick(int position) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(BundleConstants.PRODUCT,productList.get(position));
        getDashboardActivity().addFragmentInContainer(new DetailsFragment(), bundle, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    @Subscribe
    public void onEvent(ProductAdded event) {
        getPresenter().yourProduct(getDashboardActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }
}
