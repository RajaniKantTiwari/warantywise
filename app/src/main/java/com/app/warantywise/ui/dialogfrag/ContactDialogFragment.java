package com.app.warantywise.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.DialogfragmentContactBinding;
import com.app.warantywise.network.response.dashboard.MerchantResponse;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;


public class ContactDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentContactBinding mBinding;
    private ContactDialogListener listener;
    private String mobileNumber;
    private MerchantResponse productInfo;

    public interface ContactDialogListener {
        void contact(String phoneNumber);
        void messageChat(String name, String mobileNumber);

    }
    public void addListener(ContactDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialogfragment_contact, container, false);
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
         productInfo=bundle.getParcelable(AppConstants.PRODUCT_INFO);
         if(CommonUtility.isNotNull(productInfo)){
             GlideUtils.loadImage(getActivity(),productInfo.getLogo(),mBinding.ivImage,null,0);
             mBinding.ivName.setText(productInfo.getName());
             mBinding.ivAddress.setText(productInfo.getAddress());
             //mobileNumber=productInfo.getPhoneno();

         }
        }
    }

    public void setListener() {
        mBinding.tvCall.setOnClickListener(this);
        mBinding.tvMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvCall) {
            dialog.cancel();
            listener.contact(mobileNumber);
        } else if (view == mBinding.tvMessage) {
            dialog.cancel();
            listener.messageChat(productInfo.getName(),productInfo.getMobileNumber());
        }
    }
}