package com.app.warantywise.ui.dashboard.user;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentEdProfileBinding;
import com.app.warantywise.network.request.PaymentOption;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.ui.authentication.LoginActivity;
import com.app.warantywise.ui.authentication.adapter.ProfileSettingsAdapter;
import com.app.warantywise.ui.base.MvpView;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.GlideUtils;
import com.app.warantywise.utility.LogUtils;
import com.app.warantywise.utility.PreferenceUtils;
import com.app.warantywise.utility.SimpleDividerItemDecoration;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created by arvind on 06/11/17.
 */

public class ProfileFragment extends DashboardFragment implements MvpView, View.OnClickListener,ProfileSettingsAdapter.ProductListener {

    FragmentEdProfileBinding mBinding;
    private static String TAG = ProfileFragment.class.getSimpleName();
    private List<PaymentOption> paymentList = new ArrayList<>();
    private ProfileSettingsAdapter settingAdapter;
    private String profilePicFilePath;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding =DataBindingUtil.inflate(inflater,R.layout.fragment_ed_profile,container,false);
        return mBinding.getRoot();
    }

    public void setListener() {
        mBinding.ivProfile.setOnClickListener(this);
        mBinding.tvLogout.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return ProfileFragment.class.getSimpleName();
    }

    public void initializeData() {
        GlideUtils.loadImageProfilePic(getDashboardActivity(), PreferenceUtils.getImage(), mBinding.ivProfile, null, R.drawable.avatar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvPayment.setLayoutManager(layoutManager);
        mBinding.rvPayment.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        settingAdapter = new ProfileSettingsAdapter(getDashboardActivity(), this);
        mBinding.rvPayment.setAdapter(settingAdapter);
        CommonUtility.setRecyclerViewHeight(mBinding.rvPayment, paymentList, AppConstants.PAYMENT_HEIGHT);

    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
            if(mBinding.ivProfile==view){
                showImageChooserDialog();
            }else if(mBinding.tvLogout==view){
                ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), LoginActivity.class);
                getDashboardActivity().finish();
                //updateProfile();
            }
    }


    private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

            BottomSheetDialog dialog = new BottomSheetDialog(getContext());
            dialog.setContentView(R.layout.profile_dialog_layout);
            View layoutCamera = dialog.findViewById(R.id.layoutCamera);
            View layoutGallery = dialog.findViewById(R.id.layoutGallery);
            View layoutCancel=dialog.findViewById(R.id.layoutCancel);
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

    private void clearImage() {
        profilePicFilePath = "";
    }
    /**
     * Open camera to capture image
     */
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

    /**
     * Open camera to capture image
     */
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE) {
                List<String> mPaths = (List<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);
                profilePicFilePath = mPaths.get(0);
                profilePicFilePath="file:///"+profilePicFilePath;
                gotoCropper(Uri.parse(profilePicFilePath));
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                LogUtils.LOGD(TAG, "CROP");
                //handleCropResult(data);

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri imageUri = result.getUri();
                String path = imageUri.getPath();
                setImageFromLocal(path);

            }
        }
    }
    private void setImageFromLocal(String filePath) {
        profilePicFilePath=filePath;
        File f = new File(filePath);
        if (f.exists()) {
            GlideUtils.loadImageProfilePic(getDashboardActivity(), filePath, mBinding.ivProfile, null, R.drawable.avatar);
        }
    }
    private void gotoCropper(Uri sourceUri) {
        CropImage.activity(sourceUri).setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(getDashboardActivity());
    }

    private void updateProfile() {
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


    }


    @Override
    public void onItemClick(int position) {

    }
}
