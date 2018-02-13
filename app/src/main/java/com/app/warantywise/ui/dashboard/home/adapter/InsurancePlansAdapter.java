package com.app.warantywise.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.warantywise.R;
import com.app.warantywise.databinding.InsurancePlanRowItemBinding;
import com.app.warantywise.network.request.dashboard.InsurancePlan;
import com.app.warantywise.ui.dashboard.home.YourProductFragment;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class InsurancePlansAdapter extends RecyclerView.Adapter<InsurancePlansAdapter.InsurancePlanHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ArrayList<InsurancePlan> insurancePlanList;
    private PlanInsuranceListener listener;
    public interface PlanInsuranceListener{
        void setOnInsuranceItemClick(int position);
    }

    public InsurancePlansAdapter(AppCompatActivity activity, ArrayList<InsurancePlan> insurancePlanList, PlanInsuranceListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.insurancePlanList = insurancePlanList;
        this.listener=listener;
    }

    @Override
    public InsurancePlanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        InsurancePlanRowItemBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.insurance_plan_row_item, parent, false);
        return new InsurancePlanHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(InsurancePlanHolder holder, int position) {
        if (CommonUtility.isNotNull(insurancePlanList) && insurancePlanList.size() > position) {
            holder.setPlans(insurancePlanList.get(position),holder);
        }
    }


    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(insurancePlanList) ? insurancePlanList.size() : 0;
    }

    class InsurancePlanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RelativeLayout layoutPlans;
        private InsurancePlanRowItemBinding itemView;

        public InsurancePlanHolder(InsurancePlanRowItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            layoutPlans=itemView.layoutPlans;
            itemView.layoutPlans.setOnClickListener(this);

        }

        public void setPlans(InsurancePlan plans, InsurancePlanHolder holder) {
            itemView.setInsurancePlan(plans);
            setStatus(plans,holder);
        }
        private void setStatus(InsurancePlan plans, InsurancePlanHolder holder) {
            if (plans.isSelected()) {
                holder.layoutPlans.setBackground(activity.getResources().getDrawable(R.drawable.grey_bg));

            } else {
                holder.layoutPlans.setBackground(activity.getResources().getDrawable(R.drawable.grey_corner_bg));

            }
        }

        @Override
        public void onClick(View view) {
            listener.setOnInsuranceItemClick(getAdapterPosition());
        }
    }
}
