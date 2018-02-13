package com.app.warantywise.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.warantywise.R;
import com.app.warantywise.databinding.FeedbackDialogBinding;
import com.app.warantywise.utility.CommonUtility;


public class ProfileDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private FeedbackDialogBinding mBinding;
    private ProfileDialogListener listener;

    public interface ProfileDialogListener {
        void update(String submit);
    }
    public void addListener(ProfileDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.profile_dialog, container, false);
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
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
                listener.update(mBinding.edFeedBack.getText().toString());
            }
        }
    }
}