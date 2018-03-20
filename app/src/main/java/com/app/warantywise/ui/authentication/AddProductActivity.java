package com.app.warantywise.ui.authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ActivityAddProductBinding;
import com.app.warantywise.event.EncodedBitmap;
import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.Product;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.ProductDetail;
import com.app.warantywise.network.response.dashboard.ProductDetailData;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.adapter.ProductAdapter;
import com.app.warantywise.ui.dashboard.DashBoardActivity;
import com.app.warantywise.ui.dashboard.home.adapter.ProductNameCustomArrayAdapter;
import com.app.warantywise.ui.uploadfile.UploadImage;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.LogUtils;
import com.app.warantywise.utility.PreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by rajnikant on 11/02/18.
 */

public class AddProductActivity extends CommonActivity implements ProductAdapter.ProductListener,
        DatePickerDialog.OnDateSetListener {
    @Inject
    CommonPresenter presenter;
    private ActivityAddProductBinding mBinding;
    private ProductAdapter productAdapter;
    private List<Product> productList = new ArrayList<>();
    private static String TAG = AddProductActivity.class.getSimpleName();

    private String profilePicFilePath;
    private int adapterPosition = -1;
    private String productName;
    private String companyName;
    private String serialNumber;
    private String purchaseDate;
    private String warrantyPeriod;
    private int docNumber;
    private AddProductRequest request;
    // adapter for auto-complete
    private ArrayAdapter<ProductDetail> productNameAdapter;
    private ArrayList<ProductDetail> productDetailList;
    private String productId = "";
    private String manufacturer_id = "";
    private String modelNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtility.register(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_product);
        initializeData();
        setListener();
    }

    private void setListener() {
        mBinding.headerLayout.ivDrawer.setOnClickListener(this);
        mBinding.tvSubmit.setOnClickListener(this);
        mBinding.tvPurchaseDate.setOnClickListener(this);
        mBinding.layoutYes.setOnClickListener(this);
        mBinding.layoutNo.setOnClickListener(this);
        mBinding.edProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence searchText, int start, int before, int count) {
                productId = "";
                manufacturer_id = "";
                String searchProductName = searchText.toString();
                if (searchText.toString().length() >= 3) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            presenter.getProductDetails(AddProductActivity.this, new ProductDetailsRequest(searchProductName.toLowerCase()));
                        }
                    }, AppConstants.API_SERVICE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.edProductName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View layout, int pos, long id) {
                ProductDetail detail = productDetailList.get(pos);
                productId = String.valueOf(detail.getId());
                manufacturer_id = detail.getManufacturer_id();
                mBinding.edProductName.setText(detail.getProduct_name());
                mBinding.edCompanyName.setText(detail.getName());
            }

        });
    }

    private void initializeData() {
        request = new AddProductRequest();
        setList();
        mBinding.headerLayout.ivDrawer.setVisibility(View.GONE);
        mBinding.headerLayout.tvHeading.setText(getResources().getString(R.string.add_product));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvDocument.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productList, this);
        mBinding.rvDocument.setAdapter(productAdapter);
        presenter.getAllProductList(this);
        productDetailList = new ArrayList<>();
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
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (mBinding.tvSubmit == view) {
            CommonUtility.clicked(mBinding.tvSubmit);
            if (isValid()) {
                setData(request);
                presenter.addProduct(this, request);
            }
        } else if (mBinding.tvPurchaseDate == view) {
            CommonUtility.openDatePicker(this);
        } else if (mBinding.layoutYes == view) {
            mBinding.radioYes.setChecked(true);
            mBinding.radioNo.setChecked(false);

        } else if (mBinding.layoutNo == view) {
            mBinding.radioYes.setChecked(false);
            mBinding.radioNo.setChecked(true);
        } else if (mBinding.headerLayout.ivDrawer == view) {
            finish();
        }
    }

    private void setData(AddProductRequest request) {
        request.setProduct_id(productId);
        request.setProduct_name(productName);
        request.setModelno(modelNumber);
        request.setSerialno(serialNumber);
        request.setPurchasedate(purchaseDate);
        request.setExtendedwarranty(mBinding.radioYes.isChecked() ? "yes" : "no");
        request.setWarrantyfrom(warrantyPeriod);
        request.setProductownerid(PreferenceUtils.getAuthToken());
        request.setManufacturer_id(manufacturer_id);
        request.setManufacturer_name(companyName);
    }

    private boolean isValid() {
        docNumber = 0;
        for (Product product : productList) {
            if (CommonUtility.isNotNull(product) && CommonUtility.isNotNull(product.getImageUrl()) && product.getImageUrl().length() > 0) {
                docNumber = docNumber + 1;
            }
        }
        productName = mBinding.edProductName.toString();
        companyName = mBinding.edCompanyName.getText().toString();
        serialNumber = mBinding.tvSerialNumber.getText().toString();
        purchaseDate = mBinding.tvPurchaseDate.getText().toString();
        warrantyPeriod = mBinding.tvWarrantyPeriod.getText().toString();
        modelNumber = mBinding.tvModelNumber.getText().toString();
        if ((isNotNull(productName) && productName.trim().length() > 0)
                && (isNotNull(companyName) && companyName.trim().length() > 0)
                && (isNotNull(serialNumber) && serialNumber.trim().length() > 0)
                && (isNotNull(purchaseDate) && purchaseDate.trim().length() > 0)
                && (isNotNull(warrantyPeriod) && warrantyPeriod.trim().length() > 0)
                && (isNotNull(modelNumber) && modelNumber.trim().length() > 0)
                && docNumber >= 2) {
            return true;
        } else if (isNull(productId) || productId.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_product_name));
            return false;
        } else if (isNull(companyName) || companyName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_company_name));
            return false;
        } else if (isNull(serialNumber) || serialNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_serial_number));
            return false;
        } else if (isNull(modelNumber) || modelNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_model_number));
            return false;
        } else if (isNull(purchaseDate) || purchaseDate.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_purchase_date));
            return false;
        } else if (isNull(warrantyPeriod) || warrantyPeriod.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_Warranty_period));
            return false;
        } else if (docNumber < 2) {
            showToast(getResources().getString(R.string.please_select_two_document));
            return false;
        }
        return false;
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 1) {
                ProductDetailData data = (ProductDetailData) response;
                productDetailList.clear();
                productDetailList.addAll(data.getInfo());
                productNameAdapter = new ProductNameCustomArrayAdapter(this, R.layout.product_name_row, productDetailList);
                mBinding.edProductName.setAdapter(productNameAdapter);
            } else if (requestCode == AppConstants.SUBMIT && response.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
                PreferenceUtils.setLogin(true);
                ExplicitIntent.getsInstance().clearPreviousNavigateTo(this, DashBoardActivity.class);
            }
        }

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
            if (CommonUtility.isNotNull(profilePicFilePath) && profilePicFilePath.length() > 0) {
                storeImage(adapterPosition, profilePicFilePath);
                product.setImageUrl(profilePicFilePath);
            }
            productList.set(adapterPosition, product);
            productAdapter.notifyDataSetChanged();
        }
    }

    private void storeImage(int type, String imageUrl) {
        try {
            UploadImage postImage = new UploadImage(this, CommonUtility.getBitmap(imageUrl), type);
            postImage.execute();
        } catch (Exception e) {
            LogUtils.LOGE("StoreImage", e.toString());
        }
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


    @Subscribe
    public void onImageEncoded(EncodedBitmap event) {
        int type = event.getType();
        if (type == AppConstants.PRODUCT_IMAGE) {
            request.setProductimage(event.getEncodeImage());
        } else if (type == AppConstants.BILL_IMAGE) {
            request.setBillimage(event.getEncodeImage());
        } else if (type == AppConstants.BARCODE_IMAGE) {
            request.setBarcodeimage(event.getEncodeImage());
        } else if (type == AppConstants.WARRANTY_CARD_IMAGE) {
            request.setWcimage(event.getEncodeImage());
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String month = CommonUtility.getMonth(monthOfYear);
        String date = dayOfMonth + " " + month + " " + year;
        mBinding.tvPurchaseDate.setText(date);
    }

    @Override
    protected void onDestroy() {
        CommonUtility.unregister(this);
        super.onDestroy();
    }
}
