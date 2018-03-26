package com.app.warantywise.ui.dashboard.home.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.warantywise.R;
import com.app.warantywise.network.response.dashboard.ProductDetail;

import java.util.ArrayList;

public class ProductNameCustomArrayAdapter extends ArrayAdapter<ProductDetail> {

    LayoutInflater mInflator=null;
    int layoutResourceId;
    ArrayList<ProductDetail> productList = null;
 
    public ProductNameCustomArrayAdapter(Activity activity, int layoutResourceId, ArrayList<ProductDetail> productList) {
        super(activity, layoutResourceId, productList);
        mInflator=LayoutInflater.from(activity);
        this.layoutResourceId = layoutResourceId;
        this.productList = productList;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{
            /*
             * The convertView argument is essentially a "ScrapView" as described is Lucas post 
             * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
             * It will have a non-null value when ListView is asking you recycle the row layout. 
             * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
             */
            if(convertView==null){
                // inflate the layout
                convertView = mInflator.inflate(layoutResourceId, parent, false);
            }
             
            // object item based on the position
            ProductDetail productDetail = productList.get(position);
 
            // get the TextView and then set the text (item name) and tag (item ID) values
            TextView textViewItem =convertView.findViewById(R.id.textViewItem);
            textViewItem.setText(productDetail.getProduct_name()+"("+productDetail.getName()+")");
             
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        return convertView;
         
    }
}