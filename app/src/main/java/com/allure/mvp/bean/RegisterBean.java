package com.allure.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Allure on 15/7/31.
 */
public class RegisterBean implements Parcelable {
    private String userNumber;
    private  String  passWord;
    private String verCode;
    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userNumber);
        dest.writeString(this.passWord);
        dest.writeString(this.verCode);
    }

    public RegisterBean() {
    }

    protected RegisterBean(Parcel in) {
        this.userNumber = in.readString();
        this.passWord = in.readString();
        this.verCode = in.readString();
    }

    public static final Parcelable.Creator<RegisterBean> CREATOR = new Parcelable.Creator<RegisterBean>() {
        public RegisterBean createFromParcel(Parcel source) {
            return new RegisterBean(source);
        }

        public RegisterBean[] newArray(int size) {
            return new RegisterBean[size];
        }
    };
}
