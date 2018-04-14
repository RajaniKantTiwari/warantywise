package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 20/03/18.
 */

public class ProductDetails implements Parcelable{
    private String id;
    private String product_id;
    private String model_no;
    private String warranty_from;
    private String warranty_to;
    private String warranty_period;
    private String purchase_date;
    private String serial_no;
    private String extended_warranty;
    private String product_image;
    private String bill_image;
    private String barcode_image;
    private String wc_image;
    private String bill_no;
    private String barcode_number;
    private String wc_no;
    private String product_owner_id;
    private String user_type;
    private String company_id;
    private String TBD;
    private String total_sub_users;
    private String add_on_warrenty;
    private String created_at;
    private String updated_at;
    private String isactive;
    private String insrnce_count;
    private String ext_wrnty_count;
    private String offers_count;
    private String current_status;
    private String transfer_status;
    private String new_owner_id;
    private String product_name;
    private String description;
    private String product_main_image;
    private String default_warranty_days;
    private String lng;
    private String product_category;

    private String manufacturer_id;
    private String icon;
    private String mrp_price;
    private String avg_price;
    private String product_category_id;
    private String product_sub_category;
    private String rating;
    private String total_reviews;
    private String tags;



    public ProductDetails(){

    }
    protected ProductDetails(Parcel in) {
        id = in.readString();
        product_id = in.readString();
        model_no = in.readString();
        warranty_from = in.readString();
        warranty_to = in.readString();
        warranty_period = in.readString();
        purchase_date = in.readString();
        serial_no = in.readString();
        extended_warranty = in.readString();
        product_image = in.readString();
        bill_image = in.readString();
        barcode_image = in.readString();
        wc_image = in.readString();
        bill_no = in.readString();
        barcode_number = in.readString();
        wc_no = in.readString();
        product_owner_id = in.readString();
        user_type = in.readString();
        company_id = in.readString();
        TBD = in.readString();
        total_sub_users = in.readString();
        add_on_warrenty = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
        insrnce_count = in.readString();
        ext_wrnty_count = in.readString();
        offers_count = in.readString();
    }

    public static final Creator<ProductDetails> CREATOR = new Creator<ProductDetails>() {
        @Override
        public ProductDetails createFromParcel(Parcel in) {
            return new ProductDetails(in);
        }

        @Override
        public ProductDetails[] newArray(int size) {
            return new ProductDetails[size];
        }
    };

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public String getTransfer_status() {
        return transfer_status;
    }

    public void setTransfer_status(String transfer_status) {
        this.transfer_status = transfer_status;
    }

    public String getNew_owner_id() {
        return new_owner_id;
    }

    public void setNew_owner_id(String new_owner_id) {
        this.new_owner_id = new_owner_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct_main_image() {
        return product_main_image;
    }

    public void setProduct_main_image(String product_main_image) {
        this.product_main_image = product_main_image;
    }

    public String getDefault_warranty_days() {
        return default_warranty_days;
    }

    public void setDefault_warranty_days(String default_warranty_days) {
        this.default_warranty_days = default_warranty_days;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(String manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMrp_price() {
        return mrp_price;
    }

    public void setMrp_price(String mrp_price) {
        this.mrp_price = mrp_price;
    }

    public String getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(String avg_price) {
        this.avg_price = avg_price;
    }

    public String getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(String product_category_id) {
        this.product_category_id = product_category_id;
    }

    public String getProduct_sub_category() {
        return product_sub_category;
    }

    public void setProduct_sub_category(String product_sub_category) {
        this.product_sub_category = product_sub_category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTotal_reviews() {
        return total_reviews;
    }

    public void setTotal_reviews(String total_reviews) {
        this.total_reviews = total_reviews;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getExtended_warranty() {
        return extended_warranty;
    }

    public void setExtended_warranty(String extended_warranty) {
        this.extended_warranty = extended_warranty;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getBill_image() {
        return bill_image;
    }

    public void setBill_image(String bill_image) {
        this.bill_image = bill_image;
    }

    public String getBarcode_image() {
        return barcode_image;
    }

    public void setBarcode_image(String barcode_image) {
        this.barcode_image = barcode_image;
    }

    public String getWc_image() {
        return wc_image;
    }

    public void setWc_image(String wc_image) {
        this.wc_image = wc_image;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getBarcode_number() {
        return barcode_number;
    }

    public void setBarcode_number(String barcode_number) {
        this.barcode_number = barcode_number;
    }

    public String getWc_no() {
        return wc_no;
    }

    public void setWc_no(String wc_no) {
        this.wc_no = wc_no;
    }

    public String getProduct_owner_id() {
        return product_owner_id;
    }

    public void setProduct_owner_id(String product_owner_id) {
        this.product_owner_id = product_owner_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getTBD() {
        return TBD;
    }

    public void setTBD(String TBD) {
        this.TBD = TBD;
    }

    public String getTotal_sub_users() {
        return total_sub_users;
    }

    public void setTotal_sub_users(String total_sub_users) {
        this.total_sub_users = total_sub_users;
    }

    public String getAdd_on_warrenty() {
        return add_on_warrenty;
    }

    public void setAdd_on_warrenty(String add_on_warrenty) {
        this.add_on_warrenty = add_on_warrenty;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getInsrnce_count() {
        return insrnce_count;
    }

    public void setInsrnce_count(String insrnce_count) {
        this.insrnce_count = insrnce_count;
    }

    public String getExt_wrnty_count() {
        return ext_wrnty_count;
    }

    public void setExt_wrnty_count(String ext_wrnty_count) {
        this.ext_wrnty_count = ext_wrnty_count;
    }

    public String getOffers_count() {
        return offers_count;
    }

    public void setOffers_count(String offers_count) {
        this.offers_count = offers_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(product_id);
        parcel.writeString(model_no);
        parcel.writeString(warranty_from);
        parcel.writeString(warranty_to);
        parcel.writeString(warranty_period);
        parcel.writeString(purchase_date);
        parcel.writeString(serial_no);
        parcel.writeString(extended_warranty);
        parcel.writeString(product_image);
        parcel.writeString(bill_image);
        parcel.writeString(barcode_image);
        parcel.writeString(wc_image);
        parcel.writeString(bill_no);
        parcel.writeString(barcode_number);
        parcel.writeString(wc_no);
        parcel.writeString(product_owner_id);
        parcel.writeString(user_type);
        parcel.writeString(company_id);
        parcel.writeString(TBD);
        parcel.writeString(total_sub_users);
        parcel.writeString(add_on_warrenty);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(isactive);
        parcel.writeString(insrnce_count);
        parcel.writeString(ext_wrnty_count);
        parcel.writeString(offers_count);
    }
}
