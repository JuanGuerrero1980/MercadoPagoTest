
package com.ml.mlexamen.data.source.remote.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Setting implements Parcelable
{

    @SerializedName("security_code")
    @Expose
    private SecurityCode securityCode;
    @SerializedName("card_number")
    @Expose
    private CardNumber cardNumber;
    @SerializedName("bin")
    @Expose
    private Bin bin;
    public final static Parcelable.Creator<Setting> CREATOR = new Creator<Setting>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Setting createFromParcel(Parcel in) {
            return new Setting(in);
        }

        public Setting[] newArray(int size) {
            return (new Setting[size]);
        }

    }
    ;

    protected Setting(Parcel in) {
        this.securityCode = ((SecurityCode) in.readValue((SecurityCode.class.getClassLoader())));
        this.cardNumber = ((CardNumber) in.readValue((CardNumber.class.getClassLoader())));
        this.bin = ((Bin) in.readValue((Bin.class.getClassLoader())));
    }

    public Setting() {
    }

    public SecurityCode getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(SecurityCode securityCode) {
        this.securityCode = securityCode;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(securityCode);
        dest.writeValue(cardNumber);
        dest.writeValue(bin);
    }

    public int describeContents() {
        return  0;
    }

}
