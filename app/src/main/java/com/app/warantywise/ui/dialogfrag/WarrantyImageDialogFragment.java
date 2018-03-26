package com.app.warantywise.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.warantywise.R;
import com.app.warantywise.databinding.OfferDialogBinding;
import com.app.warantywise.databinding.WarrantyImageDialogBinding;
import com.app.warantywise.network.response.dashboard.WarrantyCardImage;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;
import com.app.warantywise.widget.CustomTextView;


public class WarrantyImageDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private WarrantyImageDialogBinding mBinding;
    private WarrantyCardImage warrantyCardImage;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.warranty_image_dialog, container, false);
        dialog = getDialog();
        CommonUtility.setDialog(dialog);
        initializeData();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtility.setPadding(dialog, getActivity());
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            warrantyCardImage = bundle.getParcelable(BundleConstants.WARRANTY_IMAGE);
            if (CommonUtility.isNotNull(warrantyCardImage)) {
                GlideUtils.loadImage(getContext(),warrantyCardImage.getWc_image(),mBinding.ivWarrantyImage,null,R.drawable.icon_placeholder);
            }
        }

    }

    @Override
    public void onClick(View view) {

    }
}