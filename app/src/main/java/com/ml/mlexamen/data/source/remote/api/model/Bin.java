
package com.ml.mlexamen.data.source.remote.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bin implements Parcelable
{

    @SerializedName("pattern")
    @Expose
    private String pattern;
    @SerializedName("installments_pattern")
    @Expose
    private String installmentsPattern;
    @SerializedName("exclusion_pattern")
    @Expose
    private String exclusionPattern;
    public final static Parcelable.Creator<Bin> CREATOR = new Creator<Bin>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Bin createFromParcel(Parcel in) {
            return new Bin(in);
        }

        public Bin[] newArray(int size) {
            return (new Bin[size]);
        }

    }
    ;

    protected Bin(Parcel in) {
        this.pattern = ((String) in.readValue((String.class.getClassLoader())));
        this.installmentsPattern = ((String) in.readValue((String.class.getClassLoader())));
        this.exclusionPattern = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Bin() {
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getInstallmentsPattern() {
        return installmentsPattern;
    }

    public void setInstallmentsPattern(String installmentsPattern) {
        this.installmentsPattern = installmentsPattern;
    }

    public String getExclusionPattern() {
        return exclusionPattern;
    }

    public void setExclusionPattern(String exclusionPattern) {
        this.exclusionPattern = exclusionPattern;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pattern);
        dest.writeValue(installmentsPattern);
        dest.writeValue(exclusionPattern);
    }

    public int describeContents() {
        return  0;
    }

}
