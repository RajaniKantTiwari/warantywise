package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 17/03/18.
 */

public class WarrantyCardImage implements Parcelable {
    private String id;
    private String wc_image;

    protected WarrantyCardImage(Parcel in) {
        id = in.readString();
        wc_image = in.readString();
    }

    public static final Creator<WarrantyCardImage> CREATOR = new Creator<WarrantyCardImage>() {
        @Override
        public WarrantyCardImage createFromParcel(Parcel in) {
            return new WarrantyCardImage(in);
        }

        @Override
        public WarrantyCardImage[] newArray(int size) {
            return new WarrantyCardImage[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWc_image() {
        return wc_image;
    }

    public void setWc_image(String wc_image) {
        this.wc_image = wc_image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(wc_image);
    }
}
