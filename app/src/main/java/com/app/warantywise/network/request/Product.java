package com.app.warantywise.network.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 11/02/18.
 */

public class Product implements Parcelable {
    private String productName;
    private String imageUrl;

    public Product() {
    }

    protected Product(Parcel in) {
        productName = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productName);
        parcel.writeString(imageUrl);
    }
}
