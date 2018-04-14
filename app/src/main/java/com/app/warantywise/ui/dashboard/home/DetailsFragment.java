package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentDetailsBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.request.dashboard.ProductsRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.ProductDetails;
import com.app.warantywise.network.response.dashboard.ProductDetailsData;
import com.app.warantywise.network.response.dashboard.ReviewResponse;
import com.app.warantywise.network.response.dashboard.WarrantyCardImageData;
import com.app.warantywise.network.response.dashboard.YourProduct;
import com.app.warantywise.ui.activity.ZoomAnimationImageActivity;
import com.app.warantywise.ui.adapter.DetailsAdapter;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.home.adapter.ReviewAdapter;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.GlideUtils;

import java.util.ArrayList;

public class DetailsFragment extends DashboardFragment implements DetailsAdapter.DetailsListener {

    private FragmentDetailsBinding mBinding;
    private ReviewAdapter mReviewAdapter;
    private ArrayList<ReviewResponse> reviewList;
    private DetailsAdapter detailsAdapter;
    private ArrayList<Product> productList = new ArrayList<>();
    private YourProduct yourProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.details));
        //for setting product Image
        setProductList();
        GridLayoutManager layoutManager = new GridLayoutManager(getDashboardActivity(), 3);
        mBinding.rvDocument.setLayoutManager(layoutManager);
        detailsAdapter = new DetailsAdapter(getDashboardActivity(), productList, this);
        mBinding.rvDocument.setAdapter(detailsAdapter);
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    public void setListener() {
        mBinding.tvUnderWarranty.setOnClickListener(this);
        mBinding.tvLocation.setOnClickListener(this);
        mBinding.layoutPolicyDetails.layoutWarranty.setOnClickListener(this);
        mBinding.layoutPolicyDetails.layoutExtendedWarranty.setOnClickListener(this);
        mBinding.layoutPolicyDetails.layoutBuyInsurance.setOnClickListener(this);
        mBinding.layoutPolicyDetails.layoutOffer.setOnClickListener(this);

    }

    private void onLocationClicked() {
        getDashboardActivity().addFragmentInContainer(new ProductMapFragment(), null, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    @Override
    public String getFragmentName() {
        return DetailsFragment.class.getSimpleName();
    }

    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            yourProduct = bundle.getParcelable(BundleConstants.PRODUCT);
            getPresenter().getMyProductDetails(getDashboardActivity(), new ProductsRequest(yourProduct.getWw_productid()));
            getPresenter().getMyProductFeedback(getDashboardActivity(), new ProductsRequest(yourProduct.getWw_productid()));
        }
        reviewList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvReview.setLayoutManager(layoutManager);
        mReviewAdapter = new ReviewAdapter(getDashboardActivity(), reviewList);
        mBinding.rvReview.setAdapter(mReviewAdapter);
    }

    private void setProductList() {
        productList.clear();
        Product product1 = new Product();
        product1.setProductName("Product Image");
        productList.add(product1);
        Product product2 = new Product();
        product2.setProductName("Bill Image");
        productList.add(product2);

        Product product3 = new Product();
        product3.setProductName("BarCode Image");
        productList.add(product3);

        Product product4 = new Product();
        product4.setProductName("Warranty card Image");
        productList.add(product4);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvUnderWarranty) {
            CommonUtility.clicked(mBinding.tvUnderWarranty);
            getDashboardActivity().addFragmentInContainer(new ServiceCenterDetailsFragment(), null, true
                    , true, BaseActivity.AnimationType.NONE, false);
        } else if (view == mBinding.tvLocation) {
            onLocationClicked();
        } else if (view == mBinding.layoutPolicyDetails.layoutWarranty) {
            warranty();
        } else if (view == mBinding.layoutPolicyDetails.layoutExtendedWarranty) {
            extendWarranty();
        } else if (view == mBinding.layoutPolicyDetails.layoutBuyInsurance) {
            buyInsurance();
        } else if (view == mBinding.layoutPolicyDetails.layoutOffer) {
            offers();
        }
    }

    private void offers() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleConstants.PRODUCT, yourProduct);
        //It would be modifiy
        getDashboardActivity().addFragmentInContainer(new OfferFragment(), bundle, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    private void buyInsurance() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleConstants.PRODUCT, yourProduct);
        getDashboardActivity().addFragmentInContainer(new InsuranceChoiceFragment(), bundle, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    private void warranty() {
        getPresenter().getWarrantyCardImage(this, getDashboardActivity(), new WarrantyCardImageRequest(yourProduct.getWw_productid()));
    }

    private void extendWarranty() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleConstants.PRODUCT, yourProduct);
        getDashboardActivity().addFragmentInContainer(new YourProductFragment(), bundle, true
                , true, BaseActivity.AnimationType.NONE, false);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 2) {
                setProductData(response);
            } else if (requestCode == 3) {
                setReviewsData(response);
            } else if (requestCode == 4) {
                WarrantyCardImageData data = (WarrantyCardImageData) response;
                setWarrantyCardData(data);
            }
        }
    }

    private void setWarrantyCardData(WarrantyCardImageData data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleConstants.WARRANTY_IMAGE, data.getInfo().size() > 0 ? data.getInfo().get(0) : null);
        CommonUtility.showWarrantyImageDialog(getDashboardActivity(), bundle);
    }

    private void setReviewsData(BaseResponse response) {

    }

    private void setProductData(BaseResponse response) {
        ProductDetailsData data = (ProductDetailsData) response;
        if (CommonUtility.isNotNull(data) && CommonUtility.isNotNull(data.getInfo()) && data.getInfo().size() > 0) {
            ProductDetails details = data.getInfo().get(0);
            if (CommonUtility.isNotNull(details)) {
                GlideUtils.loadImage(getContext(), details.getProduct_main_image(), mBinding.storeImage, null, R.drawable.icon_placeholder);
                mBinding.tvTotalRating.setText(details.getTotal_reviews());
                if(CommonUtility.isNotNull(details.getRating())){
                    mBinding.ratingBar.setRating(CommonUtility.setRating(details.getRating()));
                }
                mBinding.productName.setText(details.getProduct_name());
                mBinding.tvDetails.setText(details.getModel_no());
                mBinding.tvDate.setText(CommonUtility.dateYYYYMMDD(details.getWarranty_from()));
                mBinding.tvCompanyName.setText(null);
                mBinding.tvCustomerName.setText(null);
                mBinding.tvPurchaseDate.setText(CommonUtility.dateYYYYMMDD(details.getPurchase_date()));
                mBinding.tvWarrantyPeriod.setText(CommonUtility.dateYYYYMMDD(details.getWarranty_period()));
                productList.get(0).setImageUrl(details.getProduct_image());
                productList.get(1).setImageUrl(details.getBill_image());
                productList.get(2).setImageUrl(details.getBarcode_image());
                productList.get(3).setImageUrl(details.getWc_image());
                detailsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(BundleConstants.POSITION, position);
        bundle.putParcelableArrayList(BundleConstants.IMAGE_LIST, productList);
        ExplicitIntent.getsInstance().navigateToZoom(getDashboardActivity(), ZoomAnimationImageActivity.class, bundle);
    }

}
