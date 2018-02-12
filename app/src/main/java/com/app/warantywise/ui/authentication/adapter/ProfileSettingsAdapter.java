package com.app.warantywise.ui.authentication.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.SettingsRowBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class ProfileSettingsAdapter extends RecyclerView.Adapter<ProfileSettingsAdapter.ProfileViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ArrayList<String> settingList;

    private int[] drawerImageList = new int[]{R.drawable.ic_home,
            R.drawable.waranty, R.drawable.my_insurance,};
    private ProductListener listener;

    public interface ProductListener {
        void onItemClick(int position);
    }

    public ProfileSettingsAdapter(AppCompatActivity activity,ProductListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener = listener;
        settingList.addAll(Arrays.asList(activity.getResources().getStringArray(R.array.update)));

    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SettingsRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.settings_row, parent, false);
        return new ProfileViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        holder.setData(position);


    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(drawerImageList) ? drawerImageList.length : 0;
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final SettingsRowBinding mBinding;

        public ProfileViewHolder(SettingsRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.layoutProfile.setOnClickListener(this);
        }
        public void setData(int position) {
            mBinding.ivSetting.setImageResource(drawerImageList[position]);
            mBinding.tvSetting.setText(settingList.get(position));
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
