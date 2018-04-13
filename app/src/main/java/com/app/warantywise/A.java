package com.app.warantywise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 13/04/18.
 */

public class A implements Parcelable {
    private boolean aBoolean;

    protected A(Parcel in) {
        aBoolean = in.readByte() != 0;
    }

    public static final Creator<A> CREATOR = new Creator<A>() {
        @Override
        public A createFromParcel(Parcel in) {
            return new A(in);
        }

        @Override
        public A[] newArray(int size) {
            return new A[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (aBoolean ? 1 : 0));
    }
}
