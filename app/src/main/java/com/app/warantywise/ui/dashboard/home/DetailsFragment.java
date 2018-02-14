package com.app.warantywise.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentDetailsBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.network.request.dashboard.MerchantRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.MerchantResponseData;
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.network.response.dashboard.ReviewResponse;
import com.app.warantywise.network.response.dashboard.ReviewResponseData;
import com.app.warantywise.network.response.dashboard.StoreImages;
import com.app.warantywise.ui.adapter.DetailsAdapter;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.DashboardInsidePresenter;
import com.app.warantywise.ui.dashboard.adapter.ImageAdapter;
import com.app.warantywise.ui.dashboard.home.adapter.ReviewAdapter;
import com.app.warantywise.ui.dialogfrag.FeedbackDialogFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DetailsFragment extends DashboardFragment implements
        FeedbackDialogFragment.FeedbackDialogListener, ImageAdapter.ImageListener, DetailsAdapter.DetailsListener {

    private FragmentDetailsBinding mBinding;
    private ReviewAdapter mReviewAdapter;
    private ArrayList<ReviewResponse> reviewList;

    private ProductResponse merchantResponse;
    @Inject
    DashboardInsidePresenter presenter;

    private DetailsAdapter detailsAdapter;
    private List<Product> productList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {

//for setting product Image
        setProductList();
        GridLayoutManager layoutManager = new GridLayoutManager(getDashboardActivity(),3);
        mBinding.rvDocument.setLayoutManager(layoutManager);
        detailsAdapter = new DetailsAdapter(getDashboardActivity(), productList, this);
        mBinding.rvDocument.setAdapter(detailsAdapter);
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public void setListener() {
        mBinding.tvUnderWarranty.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return DetailsFragment.class.getSimpleName();
    }

    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            merchantResponse = bundle.getParcelable(AppConstants.RESPONSE);
        }
        reviewList = new ArrayList<>();

        mReviewAdapter = new ReviewAdapter(getDashboardActivity(), reviewList);
        mBinding.rvReview.setAdapter(mReviewAdapter);
        if (CommonUtility.isNotNull(merchantResponse)) {
            presenter.getMerchantDetails(getDashboardActivity(), new MerchantRequest(Integer.parseInt(merchantResponse.getId())));
            presenter.getMerchantReviews(getDashboardActivity(), new MerchantRequest(Integer.parseInt("8")));
        }

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
            CommonUtility.showOrderDialog(getDashboardActivity(), null, this);
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == 1) {
            if (CommonUtility.isNotNull(response) && response instanceof MerchantResponseData) {
                MerchantResponseData data = (MerchantResponseData) response;
                if (CommonUtility.isNotNull(data)) {
                    ArrayList<ProductResponse> infoList = data.getInfo();
                    if (CommonUtility.isNotNull(infoList) && infoList.size() > 0) {
                        ProductResponse merchantResponse = infoList.get(0);
                        CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
                        if (CommonUtility.isNotNull(merchantResponse)) {
                            mBinding.setMerchantResponse(merchantResponse);
                            setImage(merchantResponse);
                        } else {
                            CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
                        }
                    }
                } else {
                    CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
                }
            } else {
                CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
            }
        } else if (requestCode == 2) {
            reviewList.clear();
            if (CommonUtility.isNotNull(response) && response instanceof ReviewResponseData) {
                ReviewResponseData data = (ReviewResponseData) response;
                ArrayList<ReviewResponse> responseArrayList = data.getInfo();
                if (CommonUtility.isNotNull(responseArrayList)) {
                    reviewList.addAll(responseArrayList);
                    mReviewAdapter.notifyDataSetChanged();
                }
            }
        }


    }

    private void setImage(ProductResponse merchantResponse) {
        if (CommonUtility.isNotNull(merchantResponse.getRating())) {
            //mBinding.ratingBar.setRating(CommonUtility.setRating(merchantResponse.getRating()));
        }

    }


    @Override
    public void submit(String submit) {
        getDashboardActivity().showToast("" + submit);
    }

    @Override
    public void onItemClick(int position) {
       // showImageChooserDialog();
    }
   /* private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

        BottomSheetDialog dialog = new BottomSheetDialog(getDashboardActivity());
        dialog.setContentView(R.layout.profile_dialog_layout);
        View layoutCamera = dialog.findViewById(R.id.layoutCamera);
        View layoutGallery = dialog.findViewById(R.id.layoutGallery);
        View layoutCancel = dialog.findViewById(R.id.layoutCancel);
        layoutCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openImageCamera();
            }
        });
        layoutGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openImageGallery();
            }
        });
        layoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                clearImage();
            }
        });
        dialog.show();

    }
    *//**
     * Open camera to capture image
     *//*
    private void openImageCamera() {
        new ImagePicker.Builder(getDashboardActivity())
                .mode(ImagePicker.Mode.CAMERA)
                .compressLevel(ImagePicker.ComperesLevel.HARD)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.JPG)
                .scale(512, 512)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
    }

    *//**
     * Open camera to capture image
     *//*
    private void openImageGallery() {
        new ImagePicker.Builder(getDashboardActivity())
                .mode(ImagePicker.Mode.GALLERY)
                .compressLevel(ImagePicker.ComperesLevel.HARD)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.JPG)
                .scale(512, 512)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
    }

    private void clearImage() {
        profilePicFilePath = "";
    }
    private void loadImageToServer() {
        try {
            //int age = Integer.parseInt(mBinding.edtAge.getText().toString().trim());
            //String name = mBinding.edtName.getText().toString().trim();
            //String phoneNumber = mBinding.edtPhone.getText().toString().trim();
            if (TextUtils.isEmpty(profilePicFilePath)) {
                // presenter.updateProfile(this, name, phoneNumber, age, currentGender, null);
            } else {
                MultipartBody.Part body = CommonUtility.createMultipart(profilePicFilePath, AppConstants.PROFILE_UPDATE_PARAMETER);
                if (body != null) {
                    // presenter.updateProfile(this, name, phoneNumber, age, currentGender, body);
                } else {
                    //presenter.updateProfile(this, name, phoneNumber, age, currentGender, null);
                }

            }

        } catch (Exception e) {
            LogUtils.LOGE("ProfileUpdate", e.toString());
        }


    }*/
}
