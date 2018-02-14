package com.app.warantywise.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.DetailsRowBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;

import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final List<Product> productList;
    private DetailsListener listener;

    public interface DetailsListener {
        void onItemClick(int position);
    }

    public DetailsAdapter(AppCompatActivity activity, List<Product> productList, DetailsListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.productList = productList;
        this.listener = listener;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DetailsRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.details_row, parent, false);
        return new DetailsViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        if (CommonUtility.isNotNull(productList) && productList.size() > position) {
            holder.setData(productList.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(productList) ? productList.size() : 0;
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final DetailsRowBinding mBinding;

        public DetailsViewHolder(DetailsRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.productImage.setOnClickListener(this);
        }

        public void setData(Product product) {
            if (CommonUtility.isNotNull(product)) {
                mBinding.tvProductName.setText(product.getProductName());
                if (CommonUtility.isNotNull(product.getImageUrl())) {
                    GlideUtils.loadImageRoundedCorner(activity, product.getImageUrl(), mBinding.productImage, null,
                            R.drawable.icon_placeholder, AppConstants.CORNER_RADIUS);
                }
            }

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
