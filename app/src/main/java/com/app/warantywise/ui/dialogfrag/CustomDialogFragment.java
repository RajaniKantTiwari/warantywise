package com.app.warantywise.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.app.warantywise.R;
import com.app.warantywise.databinding.DialogfragmentBinding;
import com.app.warantywise.ui.base.BaseActivity;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;


public class CustomDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentBinding mBinding;
    private CustomDialogListener listener;


    public interface CustomDialogListener {
        void ok(String str);

        void cancel();
    }

    public void DialogListener(CustomDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialogfragment, container, false);
        dialog = getDialog();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }
    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            mBinding.tvMessage.setText(bundle.getString(BundleConstants.TITLE));
            if (bundle.getBoolean(BundleConstants.VISIBLE, false)) {
                mBinding.edMobileNumber.setVisibility(View.VISIBLE);
                ((BaseActivity) getActivity()).showKeyboard();
            }
        }
    }

    public void setListener() {
        mBinding.tvCancel.setOnClickListener(this);
        mBinding.tvOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvOk) {
            if(mBinding.edMobileNumber.getVisibility()==View.VISIBLE){
                String mobile = mBinding.edMobileNumber.getText().toString();
                if (CommonUtility.isNotNull(mobile) && mobile.length() > 0) {
                    dialog.cancel();
                    listener.ok(mobile);
                } else {
                    ((BaseActivity) getActivity()).showToast(getResources().getString(R.string.please_enter_mobile_number));
                }
            }else{
                listener.ok(null);
            }

        } else if (view == mBinding.tvCancel) {
            dialog.cancel();
            listener.cancel();
        }
    }
}