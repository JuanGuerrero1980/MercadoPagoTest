
package com.ml.mlexamen.data.source.remote.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardNumber implements Parcelable
{

    @SerializedName("length")
    @Expose
    private int length;
    @SerializedName("validation")
    @Expose
    private String validation;
    public final static Parcelable.Creator<CardNumber> CREATOR = new Creator<CardNumber>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CardNumber createFromParcel(Parcel in) {
            return new CardNumber(in);
        }

        public CardNumber[] newArray(int size) {
            return (new CardNumber[size]);
        }

    }
    ;

    protected CardNumber(Parcel in) {
        this.length = ((int) in.readValue((int.class.getClassLoader())));
        this.validation = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CardNumber() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(length);
        dest.writeValue(validation);
    }

    public int describeContents() {
        return  0;
    }

}
