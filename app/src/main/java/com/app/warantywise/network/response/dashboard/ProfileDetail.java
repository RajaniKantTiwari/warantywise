package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 22/03/18.
 */

public class ProfileDetail implements Parcelable {

    private String user_id;
    private String firstname;
    private String net_products;
    private String net_claim;
    private String net_insurance;
    private String dob;
    private String middlename;
    private String lastname;
    private String avatar_path;
    private String avatar_base_url;
    private String locale;
    private String gender;
    private String phone;
    private String address;
    private String facebook_id;
    private String linkedin_id;
    private String anniversary_date;
    private String id;
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
    private String country_id;
    private String city;
    private String landmark;
    private String pincode;
    private String lat;
    private String lng;
    private String created_at;
    private String updated_at;
    private String logged_at;
    private String mobile;

    public ProfileDetail() {

    }

    protected ProfileDetail(Parcel in) {
        user_id = in.readString();
        firstname = in.readString();
        net_products = in.readString();
        net_claim = in.readString();
        net_insurance = in.readString();
        dob = in.readString();
        middlename = in.readString();
        lastname = in.readString();
        avatar_path = in.readString();
        avatar_base_url = in.readString();
        locale = in.readString();
        gender = in.readString();
        phone = in.readString();
        address = in.readString();
        facebook_id = in.readString();
        linkedin_id = in.readString();
        anniversary_date = in.readString();
        id = in.readString();
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
        country_id = in.readString();
        city = in.readString();
        landmark = in.readString();
        pincode = in.readString();
        lat = in.readString();
        lng = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        logged_at = in.readString();
        mobile = in.readString();
    }

    public static final Creator<ProfileDetail> CREATOR = new Creator<ProfileDetail>() {
        @Override
        public ProfileDetail createFromParcel(Parcel in) {
            return new ProfileDetail(in);
        }

        @Override
        public ProfileDetail[] newArray(int size) {
            return new ProfileDetail[size];
        }
    };

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNet_products() {
        return net_products;
    }

    public void setNet_products(String net_products) {
        this.net_products = net_products;
    }

    public String getNet_claim() {
        return net_claim;
    }

    public void setNet_claim(String net_claim) {
        this.net_claim = net_claim;
    }

    public String getNet_insurance() {
        return net_insurance;
    }

    public void setNet_insurance(String net_insurance) {
        this.net_insurance = net_insurance;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }

    public String getAvatar_base_url() {
        return avatar_base_url;
    }

    public void setAvatar_base_url(String avatar_base_url) {
        this.avatar_base_url = avatar_base_url;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getLinkedin_id() {
        return linkedin_id;
    }

    public void setLinkedin_id(String linkedin_id) {
        this.linkedin_id = linkedin_id;
    }

    public String getAnniversary_date() {
        return anniversary_date;
    }

    public void setAnniversary_date(String anniversary_date) {
        this.anniversary_date = anniversary_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
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
        parcel.writeString(user_id);
        parcel.writeString(firstname);
        parcel.writeString(net_products);
        parcel.writeString(net_claim);
        parcel.writeString(net_insurance);
        parcel.writeString(dob);
        parcel.writeString(middlename);
        parcel.writeString(lastname);
        parcel.writeString(avatar_path);
        parcel.writeString(avatar_base_url);
        parcel.writeString(locale);
        parcel.writeString(gender);
        parcel.writeString(phone);
        parcel.writeString(address);
        parcel.writeString(facebook_id);
        parcel.writeString(linkedin_id);
        parcel.writeString(anniversary_date);
        parcel.writeString(id);
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
        parcel.writeString(country_id);
        parcel.writeString(city);
        parcel.writeString(landmark);
        parcel.writeString(pincode);
        parcel.writeString(lat);
        parcel.writeString(lng);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(logged_at);
        parcel.writeString(mobile);
    }
}
