package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentInsuranceBinding;
import com.app.warantywise.network.request.dashboard.InsurancePlan;
import com.app.warantywise.network.request.dashboard.Plans;
import com.app.warantywise.network.request.dashboard.ProductInsuranceRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.home.adapter.InsurancePlansAdapter;
import com.app.warantywise.ui.dashboard.home.adapter.PlansAdapter;
import com.app.warantywise.ui.dialogfrag.OfferDialogFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class InsuranceChoiceFragment extends DashboardFragment implements InsurancePlansAdapter.PlanInsuranceListener,
        PlansAdapter.PlanListener,OfferDialogFragment.OfferDialogListener {

    private FragmentInsuranceBinding mBinding;
    private PlansAdapter mPlanAdapter;
    private InsurancePlansAdapter mInsurancePlanAdapter;
    private ArrayList<Plans> planList;
    private ArrayList<InsurancePlan> insurancePlanList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_insurance, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {
      getPresenter().attachView(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return InsuranceChoiceFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.insurance));
        //For Plan
        LinearLayoutManager plansManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvPlan.setLayoutManager(plansManager);
        planList = new ArrayList<>();
        CommonUtility.setPlan(planList);
        CommonUtility.setRecyclerViewHeight(mBinding.rvPlan, planList, AppConstants.PLANHEIGHT);
        mPlanAdapter = new PlansAdapter(getDashboardActivity(), planList, this);
        mBinding.rvPlan.setAdapter(mPlanAdapter);

        //for Insurance plan
        LinearLayoutManager insurancePlanManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvInsurancePlan.setLayoutManager(insurancePlanManager);
        insurancePlanList = new ArrayList<>();
        CommonUtility.setInsurancePlan(insurancePlanList);
        CommonUtility.setRecyclerViewHeight(mBinding.rvInsurancePlan, planList, AppConstants.INSURANCE_PLANHEIGHT);
        mInsurancePlanAdapter = new InsurancePlansAdapter(getDashboardActivity(), insurancePlanList, this);
        mBinding.rvInsurancePlan.setAdapter(mInsurancePlanAdapter);
        //end
        getPresenter().getProductInsurance(getDashboardActivity(),new ProductInsuranceRequest("1"));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void setOnInsuranceItemClick(int position) {
        for (int i = 0; i < insurancePlanList.size(); i++) {
            InsurancePlan plan = insurancePlanList.get(i);
            if (i == position) {
                plan.setSelected(true);
            } else {
                plan.setSelected(false);
            }
            insurancePlanList.set(i, plan);
        }
        mInsurancePlanAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnPlanClicked(int position) {
        //CommonUtility.showOfferDialog(getDashboardActivity(),null,this);
        for (int i = 0; i < planList.size(); i++) {
            Plans plan = planList.get(i);
            if (i == position) {
                plan.setChecked(true);
            } else {
                plan.setChecked(false);
            }
            planList.set(i, plan);
        }
        mPlanAdapter.notifyDataSetChanged();
    }
}
