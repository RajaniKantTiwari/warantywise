package com.app.warantywise.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.DialogFeedbackBinding;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;


public class OrderFeedbackDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogFeedbackBinding mBinding;
    private OrderDialogListener listener;
    private int id;
    private String storeName;

    public interface OrderDialogListener {
        void submit(int id, float rating, String feedback);
    }
    public void addListener(OrderDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_feedback, container, false);
        dialog = getDialog();
        CommonUtility.setDialog(dialog);
        initializeData();
        setListener();
        CommonUtility.setRating(mBinding.ratingBar);
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
            id=bundle.getInt(AppConstants.ID);
            storeName=bundle.getString(AppConstants.STORE_NAME);
            mBinding.tvName.setText(storeName);
        }
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
                listener.submit(id,mBinding.ratingBar.getRating(),mBinding.edFeedBack.getText().toString());
            }
        }
    }
}