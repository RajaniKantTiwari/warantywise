package com.app.warantywise.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.warantywise.R;
import com.app.warantywise.databinding.PaymentRowBinding;
import com.app.warantywise.databinding.ProductRowBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;

import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final List<Product> productList;
    private ProductListener listener;

    public interface ProductListener {
        void onItemClick(int position);
    }

    public ProductAdapter(AppCompatActivity activity, List<Product> productList, ProductListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.productList = productList;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.product_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(productList) && productList.size() > position) {
            holder.setData(productList.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(productList) ? productList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ProductRowBinding mBinding;

        public ProductViewHolder(ProductRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.productImage.setOnClickListener(this);
        }

        public void setData(Product product) {
            if (CommonUtility.isNotNull(product)) {
                mBinding.tvProductName.setText(product.getProductName());
                if (CommonUtility.isNotNull(product.getImageUrl())) {
                    mBinding.cameraImage.setVisibility(View.GONE);
                    GlideUtils.loadImageRoundedCorner(activity, product.getImageUrl(), mBinding.productImage, null,
                            R.drawable.icon_placeholder, AppConstants.CORNER_RADIUS);
                } else {
                    mBinding.cameraImage.setVisibility(View.VISIBLE);
                    mBinding.productImage.setImageResource(0);
                }
            }

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
