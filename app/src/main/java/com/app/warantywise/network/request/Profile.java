package com.app.warantywise.network.request;

/**
 * Created by rajnikant on 14/02/18.
 */

public class Profile {
    private String userName;
    private String mobileNumber;
    private String dateOfBirth;
    private String aniversery;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAniversery() {
        return aniversery;
    }

    public void setAniversery(String aniversery) {
        this.aniversery = aniversery;
    }
}
