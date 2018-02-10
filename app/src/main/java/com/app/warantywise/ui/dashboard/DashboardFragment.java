package com.app.warantywise.ui.dashboard;

import android.content.Context;

import com.app.warantywise.injector.component.DashboardComponent;
import com.app.warantywise.ui.base.BaseFragment;


public abstract class DashboardFragment extends BaseFragment {
    private DashBoardActivity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DashBoardActivity) {
            DashBoardActivity activity = (DashBoardActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
        attachView();
    }

    public DashboardComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public DashBoardActivity getDashboardActivity(){
        return mActivity;
    }
    public DashboardPresenter getPresenter() {
        return mActivity.mPresenter;
    }

}
