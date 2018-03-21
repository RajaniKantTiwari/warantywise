package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.warantywise.network.response.BaseResponse;

/**
 * Created by rajnikant on 21/03/18.
 */

public class YourProduct implements Parcelable {
    private int id;
    private String model_no;
    private String warranty_from;
    private String warranty_to;
    private String warranty_period;
    private String product_image;
    private String serial_no;
    private String purchase_date;
    private String product_id;

    public YourProduct() {

    }

    protected YourProduct(Parcel in) {
        id = in.readInt();
        model_no = in.readString();
        warranty_from = in.readString();
        warranty_to = in.readString();
        warranty_period = in.readString();
        product_image = in.readString();
        serial_no = in.readString();
        purchase_date = in.readString();
        product_id = in.readString();
    }

    public static final Creator<YourProduct> CREATOR = new Creator<YourProduct>() {
        @Override
        public YourProduct createFromParcel(Parcel in) {
            return new YourProduct(in);
        }

        @Override
        public YourProduct[] newArray(int size) {
            return new YourProduct[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public String getWarranty_from() {
        return warranty_from;
    }

    public void setWarranty_from(String warranty_from) {
        this.warranty_from = warranty_from;
    }

    public String getWarranty_to() {
        return warranty_to;
    }

    public void setWarranty_to(String warranty_to) {
        this.warranty_to = warranty_to;
    }

    public String getWarranty_period() {
        return warranty_period;
    }

    public void setWarranty_period(String warranty_period) {
        this.warranty_period = warranty_period;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(model_no);
        parcel.writeString(warranty_from);
        parcel.writeString(warranty_to);
        parcel.writeString(warranty_period);
        parcel.writeString(product_image);
        parcel.writeString(serial_no);
        parcel.writeString(purchase_date);
        parcel.writeString(product_id);
    }
}
