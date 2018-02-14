package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentConfirmOrderBinding;
import com.app.warantywise.databinding.FragmentInsurancePaymentBinding;
import com.app.warantywise.network.request.Cover;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.home.adapter.CoverAdapter;
import com.app.warantywise.ui.dashboard.home.adapter.PlansAdapter;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class InsurancePaymentFragment extends DashboardFragment implements CoverAdapter.CoverListener {

    private FragmentInsurancePaymentBinding mBinding;
    private ArrayList<Cover> coverList;
    private CoverAdapter coverAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_insurance_payment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {
        mBinding.tvHome.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return InsurancePaymentFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
        //For Plan
        LinearLayoutManager plansManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvCover.setLayoutManager(plansManager);
        coverList = new ArrayList<>();
        setList();
        CommonUtility.setRecyclerViewHeight(mBinding.rvCover, coverList, AppConstants.COVER_HEIGHT);
        coverAdapter = new CoverAdapter(getDashboardActivity(), coverList, this);
        mBinding.rvCover.setAdapter(coverAdapter);
    }

    private void setList() {
        Cover cover = new Cover();
        cover.setCover("Insurance");
        coverList.add(cover);
        Cover cover1 = new Cover();
        cover1.setCover("Insurance Helth");
        coverList.add(cover1);
        Cover cover2 = new Cover();
        cover2.setCover("Body Checkup");
        coverList.add(cover2);
        Cover cover3 = new Cover();
        cover3.setCover("Nokia Insurance");
        coverList.add(cover3);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvHome) {
            CommonUtility.clicked(mBinding.tvHome);
            // mFragmentNavigation.popFragment();
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onItemClick(int position) {

    }
}
