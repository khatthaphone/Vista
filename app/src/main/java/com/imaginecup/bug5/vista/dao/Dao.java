package com.imaginecup.bug5.vista.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ted555 on 4/18/17.
 */

public class Dao implements Parcelable{
    private String Text;

    protected Dao(Parcel in) {
        Text = in.readString();
    }

    public static final Creator<Dao> CREATOR = new Creator<Dao>() {
        @Override
        public Dao createFromParcel(Parcel in) {
            return new Dao(in);
        }

        @Override
        public Dao[] newArray(int size) {
            return new Dao[size];
        }
    };

    public String getText() {
        return Text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Text);
    }
}
