package com.app.warantywise.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.app.warantywise.R;
import com.app.warantywise.databinding.InsuranPlanRowItemBinding;
import com.app.warantywise.network.request.dashboard.Plans;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class InsurancePlanAdapters extends RecyclerView.Adapter<InsurancePlanAdapters.PlanHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private InsuranPlanRowItemBinding mBinding;
    private ArrayList<Plans> plansList;
    private PlanListener listener;

    public interface PlanListener {
        void onPlanClicked(int position);
    }

    public InsurancePlanAdapters(AppCompatActivity activity, ArrayList<Plans> daysList, PlanListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.plansList = daysList;
        this.listener = listener;
    }

    @Override
    public PlanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.insuran_plan_row_item, parent, false);
        return new PlanHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(PlanHolder holder, int position) {
        if (CommonUtility.isNotNull(plansList) && plansList.size() > position) {
            holder.setPlans(plansList.get(position), holder);
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(plansList) ? plansList.size() : 0;
    }

    class PlanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final RadioButton radioBtn;
        private InsuranPlanRowItemBinding itemView;

        public PlanHolder(InsuranPlanRowItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            radioBtn = itemView.radio;
            itemView.layoutPlans.setOnClickListener(this);

        }

        public void setPlans(Plans plans, PlanHolder holder) {
            if (CommonUtility.isNotNull(plans)) {
                if (plans.isChecked()) {
                    holder.radioBtn.setChecked(true);
                } else {
                    holder.radioBtn.setChecked(false);
                }
            }
        }

        @Override
        public void onClick(View view) {
            listener.onPlanClicked(getAdapterPosition());
        }
    }
}
