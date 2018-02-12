package com.app.warantywise.ui.authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityAddProductBinding;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.adapter.ProductAdapter;
import com.app.warantywise.ui.dashboard.DashBoardActivity;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.LogUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.fabric.sdk.android.services.common.CommonUtils;
import okhttp3.MultipartBody;

/**
 * Created by rajnikant on 11/02/18.
 */

public class AddProductActivity extends CommonActivity implements ProductAdapter.ProductListener ,
 DatePickerDialog.OnDateSetListener{
    @Inject
    CommonPresenter presenter;
    private ActivityAddProductBinding mBinding;
    private ProductAdapter productAdapter;
    private List<Product> productList = new ArrayList<>();
    private static String TAG = AddProductActivity.class.getSimpleName();

    private String profilePicFilePath;
    private int adapterPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_product);
        initializeData();
        setListener();
    }

    private void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
        mBinding.tvCalendar.setOnClickListener(this);
    }

    private void initializeData() {
        setList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvDocument.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productList, this);
        mBinding.rvDocument.setAdapter(productAdapter);
    }

    private void setList() {
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
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (mBinding.tvSubmit == view) {
            //setToken();
            ExplicitIntent.getsInstance().clearPreviousNavigateTo(this, DashBoardActivity.class);
        }else if(mBinding.tvCalendar==view){
            CommonUtility.openDatePicker(this);
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE) {
                List<String> mPaths = (List<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);
                profilePicFilePath = mPaths.get(0);
                profilePicFilePath = "file:///" + profilePicFilePath;
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
        profilePicFilePath = filePath;
        if (productList.size() > adapterPosition) {
            Product product = productList.get(adapterPosition);
            product.setImageUrl(profilePicFilePath);
            productList.set(adapterPosition, product);
            productAdapter.notifyDataSetChanged();
        }
        //loadImageToServer();
    }

    private void gotoCropper(Uri sourceUri) {
        CropImage.activity(sourceUri).setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    public void onItemClick(int position) {
        adapterPosition = position;

        showImageChooserDialog();
    }

    private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
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

    /**
     * Open camera to capture image
     */
    private void openImageCamera() {
        new ImagePicker.Builder(this)
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
        new ImagePicker.Builder(this)
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


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        mBinding.tvCalendar.setText(date);
    }
}
