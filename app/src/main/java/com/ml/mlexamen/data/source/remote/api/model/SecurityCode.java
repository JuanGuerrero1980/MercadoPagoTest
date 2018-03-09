
package com.ml.mlexamen.data.source.remote.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecurityCode implements Parcelable
{

    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("card_location")
    @Expose
    private String cardLocation;
    @SerializedName("length")
    @Expose
    private int length;
    public final static Parcelable.Creator<SecurityCode> CREATOR = new Creator<SecurityCode>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SecurityCode createFromParcel(Parcel in) {
            return new SecurityCode(in);
        }

        public SecurityCode[] newArray(int size) {
            return (new SecurityCode[size]);
        }

    }
    ;

    protected SecurityCode(Parcel in) {
        this.mode = ((String) in.readValue((String.class.getClassLoader())));
        this.cardLocation = ((String) in.readValue((String.class.getClassLoader())));
        this.length = ((int) in.readValue((int.class.getClassLoader())));
    }

    public SecurityCode() {
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCardLocation() {
        return cardLocation;
    }

    public void setCardLocation(String cardLocation) {
        this.cardLocation = cardLocation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mode);
        dest.writeValue(cardLocation);
        dest.writeValue(length);
    }

    public int describeContents() {
        return  0;
    }

}
