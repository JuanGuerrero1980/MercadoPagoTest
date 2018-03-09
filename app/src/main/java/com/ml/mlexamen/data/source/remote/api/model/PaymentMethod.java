
package com.ml.mlexamen.data.source.remote.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentMethod implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("secure_thumbnail")
    @Expose
    private String secureThumbnail;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("deferred_capture")
    @Expose
    private String deferredCapture;
    @SerializedName("settings")
    @Expose
    private List<Setting> settings = null;
    @SerializedName("additional_info_needed")
    @Expose
    private List<String> additionalInfoNeeded = null;
    @SerializedName("min_allowed_amount")
    @Expose
    private double minAllowedAmount;
    @SerializedName("max_allowed_amount")
    @Expose
    private double maxAllowedAmount;
    @SerializedName("accreditation_time")
    @Expose
    private int accreditationTime;
    @SerializedName("financial_institutions")
    @Expose
    private List<Object> financialInstitutions = null;
    @SerializedName("processing_modes")
    @Expose
    private List<String> processingModes = null;
    public final static Parcelable.Creator<PaymentMethod> CREATOR = new Creator<PaymentMethod>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PaymentMethod createFromParcel(Parcel in) {
            return new PaymentMethod(in);
        }

        public PaymentMethod[] newArray(int size) {
            return (new PaymentMethod[size]);
        }

    }
    ;

    protected PaymentMethod(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.secureThumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.deferredCapture = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.settings, (com.ml.mlexamen.data.source.remote.api.model.Setting.class.getClassLoader()));
        in.readList(this.additionalInfoNeeded, (java.lang.String.class.getClassLoader()));
        this.minAllowedAmount = ((double) in.readValue((int.class.getClassLoader())));
        this.maxAllowedAmount = ((double) in.readValue((int.class.getClassLoader())));
        this.accreditationTime = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.financialInstitutions, (java.lang.Object.class.getClassLoader()));
        in.readList(this.processingModes, (java.lang.String.class.getClassLoader()));
    }

    public PaymentMethod() {
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

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDeferredCapture() {
        return deferredCapture;
    }

    public void setDeferredCapture(String deferredCapture) {
        this.deferredCapture = deferredCapture;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public List<String> getAdditionalInfoNeeded() {
        return additionalInfoNeeded;
    }

    public void setAdditionalInfoNeeded(List<String> additionalInfoNeeded) {
        this.additionalInfoNeeded = additionalInfoNeeded;
    }

    public double getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public void setMinAllowedAmount(double minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public double getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(double maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public int getAccreditationTime() {
        return accreditationTime;
    }

    public void setAccreditationTime(int accreditationTime) {
        this.accreditationTime = accreditationTime;
    }

    public List<Object> getFinancialInstitutions() {
        return financialInstitutions;
    }

    public void setFinancialInstitutions(List<Object> financialInstitutions) {
        this.financialInstitutions = financialInstitutions;
    }

    public List<String> getProcessingModes() {
        return processingModes;
    }

    public void setProcessingModes(List<String> processingModes) {
        this.processingModes = processingModes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(paymentTypeId);
        dest.writeValue(status);
        dest.writeValue(secureThumbnail);
        dest.writeValue(thumbnail);
        dest.writeValue(deferredCapture);
        dest.writeList(settings);
        dest.writeList(additionalInfoNeeded);
        dest.writeValue(minAllowedAmount);
        dest.writeValue(maxAllowedAmount);
        dest.writeValue(accreditationTime);
        dest.writeList(financialInstitutions);
        dest.writeList(processingModes);
    }

    public int describeContents() {
        return  0;
    }

}
