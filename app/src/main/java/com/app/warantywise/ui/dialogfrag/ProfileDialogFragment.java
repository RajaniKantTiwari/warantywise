package com.app.warantywise.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.warantywise.R;
import com.app.warantywise.databinding.ProfileDialogBinding;
import com.app.warantywise.network.request.Profile;
import com.app.warantywise.utility.CommonUtility;


public class ProfileDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private ProfileDialogBinding mBinding;
    private ProfileDialogListener listener;
    public interface ProfileDialogListener {
        void update(Profile profile);
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
        mBinding.tvUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvUpdate) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
                Profile profile=new Profile();
                profile.setUserName(mBinding.edProductName.getText().toString());
                profile.setMobileNumber(mBinding.edMobileNumber.getText().toString());
                profile.setDateOfBirth(mBinding.tvDateOfBirth.getText().toString());
                profile.setAniversery(mBinding.tvAniversary.getText().toString());
                listener.update(profile);
            }
        }
    }
}