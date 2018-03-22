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
import com.app.warantywise.network.response.dashboard.ProfileDetail;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;


public class ProfileDialogFragment extends DialogFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Dialog dialog;
    private ProfileDialogBinding mBinding;
    private ProfileDialogListener listener;
    //1 for date of birth 2 for Aniversary
    private int dateOfBirth;
    private ProfileDetail profileDetail;

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
        CommonUtility.setPadding(dialog, getActivity());
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            profileDetail = bundle.getParcelable(BundleConstants.PROFILE_DATA);
            if (CommonUtility.isNotNull(profileDetail)) {
                mBinding.edProductName.setText(profileDetail.getUsername());
                mBinding.tvMobileNumber.setText(profileDetail.getMobile());
                mBinding.tvDateOfBirth.setText(CommonUtility.dateYYYYMMDD(profileDetail.getDob()));
                mBinding.tvAniversary.setText(CommonUtility.dateYYYYMMDD(profileDetail.getAnniversary_date()));
            }
        }
    }

    public void setListener() {
        mBinding.tvDateOfBirth.setOnClickListener(this);
        mBinding.tvUpdate.setOnClickListener(this);
        mBinding.tvAniversary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvUpdate) {
            dialog.cancel();
            if (CommonUtility.isNotNull(listener)) {
                Profile profile = new Profile();
                profile.setUserName(mBinding.edProductName.getText().toString());
                profile.setMobileNumber(mBinding.tvMobileNumber.getText().toString());
                profile.setDateOfBirth(mBinding.tvDateOfBirth.getText().toString());
                profile.setAniversery(mBinding.tvAniversary.getText().toString());
                listener.update(profile);
            }
        } else if (view == mBinding.tvDateOfBirth) {
            dateOfBirth = AppConstants.DATE_OF_BIRTH;
            CommonUtility.openDatePickerProfile(this, getActivity().getFragmentManager());
        } else if (view == mBinding.tvAniversary) {
            dateOfBirth = AppConstants.ANIVERSARY;
            CommonUtility.openDatePickerProfile(this, getActivity().getFragmentManager());
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "-" + CommonUtility.getIntMonth(monthOfYear + 1) + "-" + year;
        if (dateOfBirth == AppConstants.DATE_OF_BIRTH) {
            mBinding.tvDateOfBirth.setText(date);
        } else if (dateOfBirth == AppConstants.ANIVERSARY) {
            mBinding.tvAniversary.setText(date);
        }
    }
}