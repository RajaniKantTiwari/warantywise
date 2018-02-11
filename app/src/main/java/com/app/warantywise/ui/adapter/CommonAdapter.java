package com.app.warantywise.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ProductRowBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.utility.CommonUtility;

import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final List<Product> productList;
    private ProductListener listener;
    public interface ProductListener{
        void onItemClick(int position);
    }
    public CommonAdapter(AppCompatActivity activity, List<Product> productList, ProductListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.productList = productList;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.payment_row, parent, false);
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
        private ImageView productImage;

        public ProductViewHolder(ProductRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.productImage.setOnClickListener(this);
        }

        public void setData(Product product) {
            //mBinding.setPayment(product);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
