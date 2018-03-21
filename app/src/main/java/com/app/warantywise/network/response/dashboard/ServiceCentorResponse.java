package com.app.warantywise.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 18/03/18.
 */

public class ServiceCentorResponse implements Parcelable {
    private String id;
    private String image;
    private String manufacturer_id;
    private String address;
    private String city;
    private String pincode;
    private String phoneno;
    private String latitude;
    private String longitude;
    private String logo;
    private String rating;
    private String status;
    private String store_name;
    private String owner_name;
    private String banner_image;
    private String open_timings;
    private String net_total_reviews;
    private String close_timings;
    private String m_category_names;
    private String created_at;
    private String updated_at;
    private String isactive;
    private String userid;
    private String username;
    private String email;
    private String login_password;
    private String min_order;
    private String store_descr;
    private String preferred_payment_options;
    private String country;
    private String state;
    

    protected ServiceCentorResponse(Parcel in) {
        id = in.readString();
        image = in.readString();
        manufacturer_id = in.readString();
        address = in.readString();
        city = in.readString();
        pincode = in.readString();
        phoneno = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        logo = in.readString();
        rating = in.readString();
        status = in.readString();
        store_name = in.readString();
        owner_name = in.readString();
        banner_image = in.readString();
        open_timings = in.readString();
        net_total_reviews = in.readString();
        close_timings = in.readString();
        m_category_names = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
        userid = in.readString();
        username = in.readString();
        email = in.readString();
        login_password = in.readString();
        min_order = in.readString();
        store_descr = in.readString();
        preferred_payment_options = in.readString();
        country = in.readString();
        state = in.readString();
    }

    public static final Creator<ServiceCentorResponse> CREATOR = new Creator<ServiceCentorResponse>() {
        @Override
        public ServiceCentorResponse createFromParcel(Parcel in) {
            return new ServiceCentorResponse(in);
        }

        @Override
        public ServiceCentorResponse[] newArray(int size) {
            return new ServiceCentorResponse[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(String manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getOpen_timings() {
        return open_timings;
    }

    public void setOpen_timings(String open_timings) {
        this.open_timings = open_timings;
    }

    public String getNet_total_reviews() {
        return net_total_reviews;
    }

    public void setNet_total_reviews(String net_total_reviews) {
        this.net_total_reviews = net_total_reviews;
    }

    public String getClose_timings() {
        return close_timings;
    }

    public void setClose_timings(String close_timings) {
        this.close_timings = close_timings;
    }

    public String getM_category_names() {
        return m_category_names;
    }

    public void setM_category_names(String m_category_names) {
        this.m_category_names = m_category_names;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getMin_order() {
        return min_order;
    }

    public void setMin_order(String min_order) {
        this.min_order = min_order;
    }

    public String getStore_descr() {
        return store_descr;
    }

    public void setStore_descr(String store_descr) {
        this.store_descr = store_descr;
    }

    public String getPreferred_payment_options() {
        return preferred_payment_options;
    }

    public void setPreferred_payment_options(String preferred_payment_options) {
        this.preferred_payment_options = preferred_payment_options;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(image);
        parcel.writeString(manufacturer_id);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(pincode);
        parcel.writeString(phoneno);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(logo);
        parcel.writeString(rating);
        parcel.writeString(status);
        parcel.writeString(store_name);
        parcel.writeString(owner_name);
        parcel.writeString(banner_image);
        parcel.writeString(open_timings);
        parcel.writeString(net_total_reviews);
        parcel.writeString(close_timings);
        parcel.writeString(m_category_names);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(isactive);
        parcel.writeString(userid);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(login_password);
        parcel.writeString(min_order);
        parcel.writeString(store_descr);
        parcel.writeString(preferred_payment_options);
        parcel.writeString(country);
        parcel.writeString(state);
    }
}
