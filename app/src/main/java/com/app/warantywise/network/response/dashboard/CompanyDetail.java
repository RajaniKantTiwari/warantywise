package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.warantywise.network.response.BaseResponse;

/**
 * Created by rajnikant on 21/03/18.
 */

public class CompanyDetail implements Parcelable {
    private String id;
    private String name;

    protected CompanyDetail(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<CompanyDetail> CREATOR = new Creator<CompanyDetail>() {
        @Override
        public CompanyDetail createFromParcel(Parcel in) {
            return new CompanyDetail(in);
        }

        @Override
        public CompanyDetail[] newArray(int size) {
            return new CompanyDetail[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
