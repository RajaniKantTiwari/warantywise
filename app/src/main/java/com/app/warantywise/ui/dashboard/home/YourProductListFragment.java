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
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.home.adapter.ProductListAdapter;
import com.app.warantywise.utility.CommonUtility;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class YourProductListFragment extends DashboardFragment implements ProductListAdapter.ProductListListener {

    private FragmentProductListBinding mBinding;
    private ProductListAdapter mProductAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
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
        //For Plan
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvProductList.setLayoutManager(layoutManager);
        mProductAdapter = new ProductListAdapter(getDashboardActivity(), this);
        mBinding.rvProductList.setAdapter(mProductAdapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onItemClick(int position) {

    }
}
