package com.app.warantywise.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.warantywise.R;
import com.app.warantywise.databinding.CoverRowBinding;
import com.app.warantywise.databinding.ProductRowBinding;
import com.app.warantywise.network.request.Cover;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class CoverAdapter extends RecyclerView.Adapter<CoverAdapter.CoverViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final List<Cover> coverList;
    private CoverListener listener;
    public interface CoverListener {
        void onItemClick(int position);
    }
    public CoverAdapter(AppCompatActivity activity, ArrayList<Cover> coverList, CoverListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.coverList = coverList;
        this.listener=listener;
    }

    @Override
    public CoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CoverRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.cover_row, parent, false);
        return new CoverViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CoverViewHolder holder, int position) {
        if (CommonUtility.isNotNull(coverList) && coverList.size() > position) {
            holder.setData(coverList.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(coverList) ? coverList.size() : 0;
    }

    class CoverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CoverRowBinding mBinding;
        private ImageView productImage;

        public CoverViewHolder(CoverRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            //mBinding.productImage.setOnClickListener(this);
        }

        public void setData(Cover cover) {
            mBinding.setCover(cover);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
