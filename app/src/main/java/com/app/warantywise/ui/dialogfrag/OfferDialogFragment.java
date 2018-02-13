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
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.widget.CustomTextView;


public class OfferDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private OfferDialogBinding mBinding;
    private OfferDialogListener listener;

    public interface OfferDialogListener {
    }
    public void addListener(OfferDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.offer_dialog, container, false);
        dialog = getDialog();
        CommonUtility.setDialog(dialog);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtility.setPadding(dialog,getActivity());
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {

        }
        setMessage(mBinding.tvOffer,"Get Offer 50 rs");
    }
    private void setMessage(CustomTextView tvOffer, String message) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (CommonUtility.isNotNull(message)) {
            SpannableString str1 = new SpannableString(message);
            str1.setSpan(new ForegroundColorSpan(CommonUtility.getColor(getContext(), R.color.color_blue)), 0, 8, 0);
            builder.append(str1);
        }if (CommonUtility.isNotNull(message)) {
            SpannableString str2 = new SpannableString(message);
            str2.setSpan(new ForegroundColorSpan(CommonUtility.getColor(getContext(), R.color.red_color)), 9, str2.length(), 0);
            builder.append(str2);
        }
        tvOffer.setText(builder, TextView.BufferType.SPANNABLE);
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
               
            }
        }
    }
}