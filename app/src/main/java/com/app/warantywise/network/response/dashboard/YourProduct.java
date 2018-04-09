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
    private String productname;
    private String ww_productid;
    private String master_product_id;
    private String extended_warranty;
    private String extend_warranty_period;



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
        productname=in.readString();
        ww_productid=in.readString();
        master_product_id=in.readString();
        extended_warranty=in.readString();
        extend_warranty_period=in.readString();

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

    public String getWw_productid() {
        return ww_productid;
    }

    public void setWw_productid(String ww_productid) {
        this.ww_productid = ww_productid;
    }

    public String getMaster_product_id() {
        return master_product_id;
    }

    public void setMaster_product_id(String master_product_id) {
        this.master_product_id = master_product_id;
    }

    public String getExtended_warranty() {
        return extended_warranty;
    }

    public void setExtended_warranty(String extended_warranty) {
        this.extended_warranty = extended_warranty;
    }

    public String getExtend_warranty_period() {
        return extend_warranty_period;
    }

    public void setExtend_warranty_period(String extend_warranty_period) {
        this.extend_warranty_period = extend_warranty_period;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

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
        parcel.writeString(ww_productid);
        parcel.writeString(master_product_id);
        parcel.writeString(extended_warranty);
        parcel.writeString(extend_warranty_period);

    }
}
