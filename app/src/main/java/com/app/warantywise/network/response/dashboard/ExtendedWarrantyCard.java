package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 17/03/18.
 */

public class ExtendedWarrantyCard implements Parcelable {
    private String id;
    private String mp_id;
    private String offer_id;
    private String ext_wrnty_id;
    private String supplier_id;
    private String insrnce_ID;
    private String from_date;
    private String to_date;
    private String lat;
    private String lng;
    private String country_id;
    private String servicing_radius;
    private String created_at;
    private String updated_at;
    private String isactive;
    private String warranty_descr;
    private String warranty_t_and_c;
    private String warranty_from;
    private String warranty_to;
    private String warranty_supplier;
    private String username;
    private String auth_key;
    private String access_token;
    private String password_hash;
    private String password_reset_token;
    private String oauth_client;
    private String oauth_client_user_id;
    private String email;
    private String status;
    private String otp;
    private String address;
    private String city;
    private String landmark;
    private String pincode;
    private String logged_at;
    private String mobile;

    public ExtendedWarrantyCard() {

    }

    protected ExtendedWarrantyCard(Parcel in) {
        id = in.readString();
        mp_id = in.readString();
        offer_id = in.readString();
        ext_wrnty_id = in.readString();
        supplier_id = in.readString();
        insrnce_ID = in.readString();
        from_date = in.readString();
        to_date = in.readString();
        lat = in.readString();
        lng = in.readString();
        country_id = in.readString();
        servicing_radius = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
        warranty_descr = in.readString();
        warranty_t_and_c = in.readString();
        warranty_from = in.readString();
        warranty_to = in.readString();
        warranty_supplier = in.readString();
        username = in.readString();
        auth_key = in.readString();
        access_token = in.readString();
        password_hash = in.readString();
        password_reset_token = in.readString();
        oauth_client = in.readString();
        oauth_client_user_id = in.readString();
        email = in.readString();
        status = in.readString();
        otp = in.readString();
        address = in.readString();
        city = in.readString();
        landmark = in.readString();
        pincode = in.readString();
        logged_at = in.readString();
        mobile = in.readString();
    }

    public static final Creator<ExtendedWarrantyCard> CREATOR = new Creator<ExtendedWarrantyCard>() {
        @Override
        public ExtendedWarrantyCard createFromParcel(Parcel in) {
            return new ExtendedWarrantyCard(in);
        }

        @Override
        public ExtendedWarrantyCard[] newArray(int size) {
            return new ExtendedWarrantyCard[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMp_id() {
        return mp_id;
    }

    public void setMp_id(String mp_id) {
        this.mp_id = mp_id;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getExt_wrnty_id() {
        return ext_wrnty_id;
    }

    public void setExt_wrnty_id(String ext_wrnty_id) {
        this.ext_wrnty_id = ext_wrnty_id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getInsrnce_ID() {
        return insrnce_ID;
    }

    public void setInsrnce_ID(String insrnce_ID) {
        this.insrnce_ID = insrnce_ID;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getServicing_radius() {
        return servicing_radius;
    }

    public void setServicing_radius(String servicing_radius) {
        this.servicing_radius = servicing_radius;
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

    public String getWarranty_descr() {
        return warranty_descr;
    }

    public void setWarranty_descr(String warranty_descr) {
        this.warranty_descr = warranty_descr;
    }

    public String getWarranty_t_and_c() {
        return warranty_t_and_c;
    }

    public void setWarranty_t_and_c(String warranty_t_and_c) {
        this.warranty_t_and_c = warranty_t_and_c;
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

    public String getWarranty_supplier() {
        return warranty_supplier;
    }

    public void setWarranty_supplier(String warranty_supplier) {
        this.warranty_supplier = warranty_supplier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_reset_token() {
        return password_reset_token;
    }

    public void setPassword_reset_token(String password_reset_token) {
        this.password_reset_token = password_reset_token;
    }

    public String getOauth_client() {
        return oauth_client;
    }

    public void setOauth_client(String oauth_client) {
        this.oauth_client = oauth_client;
    }

    public String getOauth_client_user_id() {
        return oauth_client_user_id;
    }

    public void setOauth_client_user_id(String oauth_client_user_id) {
        this.oauth_client_user_id = oauth_client_user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLogged_at() {
        return logged_at;
    }

    public void setLogged_at(String logged_at) {
        this.logged_at = logged_at;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(mp_id);
        parcel.writeString(offer_id);
        parcel.writeString(ext_wrnty_id);
        parcel.writeString(supplier_id);
        parcel.writeString(insrnce_ID);
        parcel.writeString(from_date);
        parcel.writeString(to_date);
        parcel.writeString(lat);
        parcel.writeString(lng);
        parcel.writeString(country_id);
        parcel.writeString(servicing_radius);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(isactive);
        parcel.writeString(warranty_descr);
        parcel.writeString(warranty_t_and_c);
        parcel.writeString(warranty_from);
        parcel.writeString(warranty_to);
        parcel.writeString(warranty_supplier);
        parcel.writeString(username);
        parcel.writeString(auth_key);
        parcel.writeString(access_token);
        parcel.writeString(password_hash);
        parcel.writeString(password_reset_token);
        parcel.writeString(oauth_client);
        parcel.writeString(oauth_client_user_id);
        parcel.writeString(email);
        parcel.writeString(status);
        parcel.writeString(otp);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(landmark);
        parcel.writeString(pincode);
        parcel.writeString(logged_at);
        parcel.writeString(mobile);
    }
}
