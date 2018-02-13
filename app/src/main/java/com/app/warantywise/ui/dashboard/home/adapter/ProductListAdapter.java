package com.app.warantywise.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ProductRowItemBinding;
import com.app.warantywise.network.request.Product;

/**
 * Created by ashok on 25/12/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ProductListListener listener;
    public interface ProductListListener {
        void onItemClick(int position);
    }
    public ProductListAdapter(AppCompatActivity activity, ProductListListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductRowItemBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.product_row_item, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ProductRowItemBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(ProductRowItemBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            //mBinding.productImage.setOnClickListener(this);
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
