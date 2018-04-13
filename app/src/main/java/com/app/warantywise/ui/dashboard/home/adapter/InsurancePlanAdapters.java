package com.app.warantywise.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.app.warantywise.R;
import com.app.warantywise.databinding.PlanRowsItemBinding;
import com.app.warantywise.network.response.dashboard.ExtendedWarrantyCard;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlanHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private PlanRowsItemBinding mBinding;
    private ArrayList<ExtendedWarrantyCard> plansList;
    private PlanListener listener;

    public interface PlanListener {
        void onPlanClicked(int position);
    }

    public PlansAdapter(AppCompatActivity activity, ArrayList<ExtendedWarrantyCard> daysList, PlanListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.plansList = daysList;
        this.listener = listener;
    }

    @Override
    public PlanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.plan_rows_item, parent, false);
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
        private final CustomTextView tvAmount;
        private final CustomTextView tvWarranty;
        private final CustomTextView tvWarrantyDescription;
        private PlanRowsItemBinding itemView;

        public PlanHolder(PlanRowsItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            radioBtn = itemView.radio;
            tvAmount = itemView.tvAmount;
            tvWarranty = itemView.tvWarranty;
            tvWarrantyDescription = itemView.tvWarrantyDescription;
            itemView.layoutPlans.setOnClickListener(this);

        }

        public void setPlans(ExtendedWarrantyCard plans, PlanHolder holder) {
            holder.tvAmount.setText(CommonUtility.addStrings(activity.getResources().getString(R.string.rs), plans.getAmount()));
            holder.tvWarranty.setText(null);
            holder.tvWarrantyDescription.setText(plans.getWarranty_descr());
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