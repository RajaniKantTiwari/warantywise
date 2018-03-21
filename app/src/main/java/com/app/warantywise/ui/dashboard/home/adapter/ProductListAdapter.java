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
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.network.response.dashboard.YourProduct;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ProductListListener listener;
    private ArrayList<YourProduct> productList;

    public interface ProductListListener {
        void onOfferClicked(int position);

        void onBuyInsuranceClicked(int position);

        void onExtendClicked(int position);

        void onWarrantyClicked(int position);
    }

    public ProductListAdapter(AppCompatActivity activity, ArrayList<YourProduct> productList, ProductListListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.productList = productList;
        this.listener = listener;
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
        return CommonUtility.isNotNull(productList) ? productList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ProductRowItemBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(ProductRowItemBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.tvWarranty.setOnClickListener(this);
            mBinding.tvExtend.setOnClickListener(this);
            mBinding.tvBuyInsurance.setOnClickListener(this);
            mBinding.tvOffer.setOnClickListener(this);

        }

        public void setData(Product product) {
            //mBinding.setPayment(product);
        }

        @Override
        public void onClick(View view) {
            if (mBinding.tvWarranty == view) {
                CommonUtility.clicked(mBinding.tvWarranty);
                listener.onWarrantyClicked(getAdapterPosition());
            } else if (mBinding.tvExtend == view) {
                CommonUtility.clicked(mBinding.tvExtend);
                listener.onExtendClicked(getAdapterPosition());
            } else if (mBinding.tvBuyInsurance == view) {
                CommonUtility.clicked(mBinding.tvBuyInsurance);
                listener.onBuyInsuranceClicked(getAdapterPosition());

            } else if (mBinding.tvOffer == view) {
                CommonUtility.clicked(mBinding.tvOffer);

                listener.onOfferClicked(getAdapterPosition());

            }
        }
    }
}
