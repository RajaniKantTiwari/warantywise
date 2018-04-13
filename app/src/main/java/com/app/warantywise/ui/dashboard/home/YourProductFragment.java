package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentYourProductBinding;
import com.app.warantywise.network.request.dashboard.ExtendeWarrantyRequest;
import com.app.warantywise.network.request.dashboard.InsurancePlan;
import com.app.warantywise.network.request.dashboard.Plans;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.ExtendedWarrantyCard;
import com.app.warantywise.network.response.dashboard.ExtendedWarrantyCardData;
import com.app.warantywise.network.response.dashboard.YourProduct;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashBoardActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.home.adapter.InsurancePlansAdapter;
import com.app.warantywise.ui.dashboard.home.adapter.PlansAdapter;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;

import java.util.ArrayList;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class YourProductFragment extends DashboardFragment implements InsurancePlansAdapter.PlanInsuranceListener,
        PlansAdapter.PlanListener {

    private FragmentYourProductBinding mBinding;
    private PlansAdapter mPlanAdapter;
    private InsurancePlansAdapter mInsurancePlanAdapter;
    private ArrayList<ExtendedWarrantyCard> warrantyCardList;
    private ArrayList<InsurancePlan> insurancePlanList;
    private YourProduct yourProduct;
    private DashBoardActivity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_your_product, container, false);
        activity = getDashboardActivity();
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void setListener() {
        mBinding.tvBuy.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return YourProductFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            yourProduct = bundle.getParcelable(BundleConstants.PRODUCT);
            if (CommonUtility.isNotNull(yourProduct)) {
                GlideUtils.loadImage(getContext(), yourProduct.getProduct_image(), mBinding.ivProductImage, null, R.drawable.icon_placeholder);
                mBinding.tvProductName.setText(yourProduct.getProductname());
                mBinding.tvDate.setText(CommonUtility.dateYYYYMMDD(yourProduct.getWarranty_from()));
                mBinding.tvExpireDate.setText(CommonUtility.dateYYYYMMDD(yourProduct.getWarranty_to()));
                getPresenter().getExtendedWarranty(getDashboardActivity(), new ExtendeWarrantyRequest(yourProduct.getMaster_product_id()));
                if (CommonUtility.isNotNull(yourProduct) && CommonUtility.isNotNull(yourProduct.getWarranty_to())) {
                    mBinding.tvUnderWarranty.setText(CommonUtility.
                            dateComparision(yourProduct.getWarranty_to(), "2018-09-09 00:00:00") ?
                            activity.getResources().getString(R.string.warranty_expire) : activity.getResources().getString(R.string.under_warranty));
                    mBinding.tvWarranty.setText(CommonUtility.
                            dateComparision(yourProduct.getWarranty_to(), "2018-09-09 00:00:00") ?
                            activity.getResources().getString(R.string.warranty_expire) : activity.getResources().getString(R.string.under_warranty));
                    if (CommonUtility.dateComparision(yourProduct.getWarranty_to(), "2018-09-09 00:00:00")) {
                        mBinding.tvUnderWarranty.setBackgroundResource(R.drawable.red_round);
                    } else {
                        mBinding.tvUnderWarranty.setBackgroundResource(R.drawable.blue_round);
                    }
                }

            }
        }


        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.your_product));
        //For Plan
        LinearLayoutManager plansManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvPlan.setLayoutManager(plansManager);
        warrantyCardList = new ArrayList<>();
        mPlanAdapter = new PlansAdapter(getDashboardActivity(), warrantyCardList, this);
        mBinding.rvPlan.setAdapter(mPlanAdapter);

        //for Insurance plan
        LinearLayoutManager insurancePlanManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvInsurancePlan.setLayoutManager(insurancePlanManager);
        insurancePlanList = new ArrayList<>();
        CommonUtility.setInsurancePlan(insurancePlanList);
        CommonUtility.setRecyclerViewHeight(mBinding.rvInsurancePlan, insurancePlanList, AppConstants.INSURANCE_PLANHEIGHT);
        mInsurancePlanAdapter = new InsurancePlansAdapter(getDashboardActivity(), insurancePlanList, this);
        mBinding.rvInsurancePlan.setAdapter(mInsurancePlanAdapter);
        //end

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvBuy) {
            CommonUtility.clicked(mBinding.tvBuy);
            getDashboardActivity().addFragmentInContainer(new InsurancePaymentFragment(), null, true
                    , true, BaseActivity.AnimationType.NONE, false);
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 1) {
             setInsuranceList(response);
            }else if(requestCode==2){

            }
        }
    }

    private void setInsuranceList(BaseResponse response) {
        ExtendedWarrantyCardData data=(ExtendedWarrantyCardData)response;
        warrantyCardList.clear();
        if(CommonUtility.isNotNull(data.getInfo())){
            warrantyCardList.addAll(data.getInfo());
            CommonUtility.setRecyclerViewHeight(mBinding.rvPlan, warrantyCardList, AppConstants.PLANHEIGHT);
            mPlanAdapter.notifyDataSetChanged();
        }
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
    public void onPlanClicked(int position) {
        for (int i = 0; i < warrantyCardList.size(); i++) {
            ExtendedWarrantyCard warrantyCard = warrantyCardList.get(i);
            if (i == position) {
                warrantyCard.setChecked(true);
                CommonUtility.showPlanDetailDialogFragment(getBaseActivity(), null);
            } else {
                warrantyCard.setChecked(false);
            }
            warrantyCardList.set(i, warrantyCard);
        }
        mPlanAdapter.notifyDataSetChanged();
    }
}
