package com.app.warantywise.ui.dashboard.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentSystemServiceBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.network.response.dashboard.MerchantResponseData;
import com.app.warantywise.network.response.dashboard.ReviewResponse;
import com.app.warantywise.network.response.dashboard.ReviewResponseData;
import com.app.warantywise.network.response.dashboard.StoreImages;
import com.app.warantywise.ui.activity.ZoomAnimationImageActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.adapter.ImageAdapter;
import com.app.warantywise.ui.dashboard.home.adapter.ReviewAdapter;
import com.app.warantywise.ui.dialogfrag.FeedbackDialogFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.SimpleDividerItemDecoration;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;
import static com.app.warantywise.utility.AppConstants.REQUEST_CALL;

public class SystemServiceFragment extends DashboardFragment implements FeedbackDialogFragment.FeedbackDialogListener,ImageAdapter.ImageListener {

    private FragmentSystemServiceBinding mBinding;
    private ImageAdapter mImageAdapter;
    private ReviewAdapter mReviewAdapter;
    private ArrayList<ReviewResponse> reviewList;

    private ProductResponse merchantResponse;

    private ArrayList<StoreImages> imageList;
    private Intent callIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_system_service, container, false);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        LinearLayoutManager photoManager = new LinearLayoutManager(getDashboardActivity());
        photoManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.photoRecycler.setLayoutManager(photoManager);
        LinearLayoutManager reviewManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvReview.setLayoutManager(reviewManager);
        mBinding.rvReview.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
    }

    @Override
    public void attachView() {

    }

    public void setListener() {
        mBinding.tvCall.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return SystemServiceFragment.class.getSimpleName();
    }

    public void initializeData() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.system_service));
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            merchantResponse = bundle.getParcelable(AppConstants.RESPONSE);
        }
        reviewList = new ArrayList<>();
        imageList=new ArrayList<>();
        setList();
        mImageAdapter = new ImageAdapter(getDashboardActivity(),imageList,this);
        mBinding.photoRecycler.setAdapter(mImageAdapter);
        mReviewAdapter = new ReviewAdapter(getDashboardActivity(), reviewList);
        mBinding.rvReview.setAdapter(mReviewAdapter);


    }

    private void setList() {
        try {
            StoreImages image = new StoreImages();
            image.setPath("https://api.androidhive.info/images/glide/small/deadpool.jpg");
            imageList.add(image);

            StoreImages image1 = new StoreImages();
            image1.setPath("https://api.androidhive.info/images/glide/large/bvs.jpg");
            imageList.add(image1);

            StoreImages image2 = new StoreImages();
            image2.setPath("https://api.androidhive.info/images/glide/large/cacw.jpg");
            imageList.add(image2);

            StoreImages image3 = new StoreImages();
            image3.setPath("https://api.androidhive.info/images/glide/large/bourne.jpg");
            imageList.add(image3);

            StoreImages image4 = new StoreImages();
            image4.setPath("https://api.androidhive.info/images/glide/large/squad.jpg");
            imageList.add(image4);

        } catch (Exception e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvCall) {
            CommonUtility.clicked(mBinding.tvCall);
            call("9821945433");
        }
    }

    private void call(String mobileNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + mobileNumber));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            return;
        } else
            startActivity(callIntent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if(requestCode==REQUEST_CALL){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            }else {
                getBaseActivity().showToast(getResources().getString(R.string.permition_denied));
            }
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
                        CommonUtility.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,true);
                        if (CommonUtility.isNotNull(merchantResponse)) {
                            mBinding.setMerchantResponse(merchantResponse);
                            setImage(merchantResponse);
                        }else{
                            CommonUtility.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,false);
                        }
                    }
                }
                else{
                    CommonUtility.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,false);
                }
            }else{
                CommonUtility.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,false);
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
        //GlideUtils.loadImage(getDashboardActivity(), merchantResponse.getBanner_image(), mBinding.storeImage, null, 0);
        if (CommonUtility.isNotNull(imageList)&&CommonUtility.isNotNull(merchantResponse.getStoreimages())) {
            imageList.addAll(merchantResponse.getStoreimages());
            mImageAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void submit(String submit) {
        getDashboardActivity().showToast("" + submit);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(BundleConstants.POSITION,position);
        bundle.putParcelableArrayList(BundleConstants.IMAGE_LIST,imageList);
        ExplicitIntent.getsInstance().navigateToZoom(getDashboardActivity(), ZoomAnimationImageActivity.class,bundle);
        /*getDashboardActivity().pushFragment(ZOOMIMAGE_FRAGMENT,bundle,android.R.id.content,
                true,true, BaseActivity.AnimationType.ZOOM);*/
    }
}
