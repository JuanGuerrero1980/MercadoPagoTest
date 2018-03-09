
package com.ml.mlexamen.data.source.remote.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardIssuer implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("secure_thumbnail")
    @Expose
    private String secureThumbnail;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("processing_mode")
    @Expose
    private String processingMode;
    @SerializedName("merchant_account_id")
    @Expose
    private Object merchantAccountId;
    public final static Parcelable.Creator<CardIssuer> CREATOR = new Creator<CardIssuer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CardIssuer createFromParcel(Parcel in) {
            return new CardIssuer(in);
        }

        public CardIssuer[] newArray(int size) {
            return (new CardIssuer[size]);
        }

    }
    ;

    protected CardIssuer(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.secureThumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.processingMode = ((String) in.readValue((String.class.getClassLoader())));
        this.merchantAccountId = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public CardIssuer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String processingMode) {
        this.processingMode = processingMode;
    }

    public Object getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(Object merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(secureThumbnail);
        dest.writeValue(thumbnail);
        dest.writeValue(processingMode);
        dest.writeValue(merchantAccountId);
    }

    public int describeContents() {
        return  0;
    }

}
