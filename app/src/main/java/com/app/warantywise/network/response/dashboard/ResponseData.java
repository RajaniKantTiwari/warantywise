package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 17/03/18.
 */

public class ResponseData implements Parcelable {
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
    private String offer_descr;
    private String offer_t_and_c;
    private String offer_from;
    private String offer_to;
    private String offer_supplier;
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

    protected ResponseData(Parcel in) {
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
        offer_descr = in.readString();
        offer_t_and_c = in.readString();
        offer_from = in.readString();
        offer_to = in.readString();
        offer_supplier = in.readString();
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

    public static final Creator<ResponseData> CREATOR = new Creator<ResponseData>() {
        @Override
        public ResponseData createFromParcel(Parcel in) {
            return new ResponseData(in);
        }

        @Override
        public ResponseData[] newArray(int size) {
            return new ResponseData[size];
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

    public String getOffer_descr() {
        return offer_descr;
    }

    public void setOffer_descr(String offer_descr) {
        this.offer_descr = offer_descr;
    }

    public String getOffer_t_and_c() {
        return offer_t_and_c;
    }

    public void setOffer_t_and_c(String offer_t_and_c) {
        this.offer_t_and_c = offer_t_and_c;
    }

    public String getOffer_from() {
        return offer_from;
    }

    public void setOffer_from(String offer_from) {
        this.offer_from = offer_from;
    }

    public String getOffer_to() {
        return offer_to;
    }

    public void setOffer_to(String offer_to) {
        this.offer_to = offer_to;
    }

    public String getOffer_supplier() {
        return offer_supplier;
    }

    public void setOffer_supplier(String offer_supplier) {
        this.offer_supplier = offer_supplier;
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
        parcel.writeString(offer_descr);
        parcel.writeString(offer_t_and_c);
        parcel.writeString(offer_from);
        parcel.writeString(offer_to);
        parcel.writeString(offer_supplier);
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
