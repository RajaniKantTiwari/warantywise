package com.app.warantywise.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;


import com.app.warantywise.R;
import com.app.warantywise.databinding.ReviewRowItemBinding;
import com.app.warantywise.network.response.dashboard.ReviewResponse;
import com.app.warantywise.utility.CommonUtility;
import com.app.warantywise.utility.GlideUtils;
import com.app.warantywise.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<ReviewResponse> reviewList;
    private final AppCompatActivity activity;

    public ReviewAdapter(AppCompatActivity activity, ArrayList<ReviewResponse> reviewList){
        mInflater=LayoutInflater.from(activity);
        this.activity=activity;
        this.reviewList=reviewList;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReviewRowItemBinding mBinding=DataBindingUtil.inflate(mInflater, R.layout.review_row_item, parent, false);
        return new ReviewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
       if(CommonUtility.isNotNull(reviewList)&&reviewList.size()>position){
           holder.setResponse(reviewList.get(position));
           GlideUtils.loadImageProfilePic(activity,reviewList.get(position).getAvatar_base_url()+"/"+reviewList.get(position).getAvatar_path(),holder.ivReview,null,R.drawable.avatar);
           holder.postDate.setText(CommonUtility.getCreatedDate(reviewList.get(position).getCreated_at()));
           holder.ratingBar.setRating(CommonUtility.setRating(reviewList.get(position).getRating()));
       }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(reviewList)?reviewList.size()+5:0;
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
        private final ImageView ivReview;
        private final RatingBar ratingBar;
        private final CustomTextView postDate;
        private ReviewRowItemBinding itemView;
       public ReviewHolder(ReviewRowItemBinding itemView) {
           super(itemView.getRoot());
           this.itemView=itemView;
           ivReview=itemView.ivReview;
           ratingBar=itemView.ratingBar;
           postDate=itemView.postDate;
       }

        public void setResponse(ReviewResponse reviewResponse) {
            itemView.setResponse(reviewResponse);
            try {
                itemView.ratingBar.setRating(Float.parseFloat(CommonUtility.twoDecimalPlaceString(reviewResponse.getRating())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
