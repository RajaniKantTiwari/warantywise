package com.app.warantywise.ui.dashboard.home.mapview;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.warantywise.R;
import com.app.warantywise.databinding.ProductDialogBinding;
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.utility.BundleConstants;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;


@SuppressLint("ValidFragment")
public class ShowWindowFragment extends Fragment implements View.OnClickListener {

    private MarkerInfoListener listener;
    private ProductDialogBinding mBinding;
    private FragmentActivity activity;
    private ProductResponse response;
    @SuppressLint("ValidFragment")
    public ShowWindowFragment(MarkerInfoListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_dialog, container, false);
        activity=getActivity();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeData();
        setListener();
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if(CommonUtility.isNotNull(bundle)){
            GlideUtils.loadImageTwoRoundedCorner(activity,"https://api.androidhive.info/images/glide/large/cacw.jpg",
                    mBinding.ivProduct,null,R.drawable.shubh,6,6,0,0);
            response=bundle.getParcelable(BundleConstants.PRODUCT_RESPONSE);
        }
    }

    private void setListener() {
        mBinding.tvCall.setOnClickListener(this);
        mBinding.tvView.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (mBinding.tvCall == view) {
            call();
        } else if (mBinding.tvView == view) {
            view();
        }
    }

    private void view() {
        if(CommonUtility.isNotNull(listener)){
            listener.view("9821945433");
        }
    }

    private void call() {
        if(CommonUtility.isNotNull(listener)){
            listener.call("9821945433");
        }
    }

    public interface MarkerInfoListener {
        void call(String mobileNumber);

        void view(String view);
    }
}
