package com.app.warantywise.ui.dashboard.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentUserBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.presenter.CommonPresenter;
import com.app.warantywise.ui.authentication.EditProfileActivity;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.utility.ExplicitIntent;
import com.app.warantywise.utility.GlideUtils;
import com.app.warantywise.utility.PreferenceUtils;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class UserProfileFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private FragmentUserBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_user,container,false);
        GlideUtils.loadImageProfilePic(getContext(), PreferenceUtils.getImage(),mBinding.ivProfile,null,R.drawable.avatar);
        getDashboardActivity().setHeaderTitle(getString(R.string.user));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
     mBinding.ivEdit.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return UserProfileFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
     if(view==mBinding.ivEdit){
         ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), EditProfileActivity.class);
     }
    }
}
