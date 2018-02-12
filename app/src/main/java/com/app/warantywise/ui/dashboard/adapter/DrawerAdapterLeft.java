package com.app.warantywise.ui.dashboard.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.DrawerLeftRowItemBinding;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.widget.CustomTextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ashok on 25/12/17.
 */

public class DrawerAdapterLeft extends RecyclerView.Adapter<DrawerAdapterLeft.StoreViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private DrawerLeftListener listener;
    private ArrayList<String> drawerList;
    private int[] drawerImageList = new int[]{R.drawable.ic_home,
            R.drawable.waranty, R.drawable.my_insurance, R.drawable.edit_profile,
            R.drawable.help, R.drawable.senior_citizen, R.drawable.add_file};

    public interface DrawerLeftListener {
        void onLeftDrawerItemClicked(int adapterPosition);
    }

    public DrawerAdapterLeft(AppCompatActivity activity, DrawerLeftListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener = listener;
        drawerList = new ArrayList<>();
        drawerList.addAll(Arrays.asList(activity.getResources().getStringArray(R.array.left_drawer)));
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DrawerLeftRowItemBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.drawer_left_row_item, parent, false);
        return new StoreViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        if (CommonUtility.isNotNull(drawerList) && drawerList.size() > position) {
            holder.setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(drawerList) ? drawerList.size() : 0;
    }

    class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final DrawerLeftRowItemBinding mBinding;
        private final CustomTextView tvChange;

        public StoreViewHolder(DrawerLeftRowItemBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvChange = itemView.tvChange;
            itemView.layoutUserItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtility.isNotNull(listener)) {
                listener.onLeftDrawerItemClicked(getAdapterPosition());
            }
        }

        public void setData(int position) {
            mBinding.tvMenuName.setText(drawerList.get(position));
            mBinding.imageView.setImageResource(drawerImageList[position]);
        }
    }
}
