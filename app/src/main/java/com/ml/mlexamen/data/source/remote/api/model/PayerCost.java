
package com.ml.mlexamen.data.source.remote.api.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayerCost implements Parcelable
{

    @SerializedName("installments")
    @Expose
    private int installments;
    @SerializedName("installment_rate")
    @Expose
    private float installmentRate;
    @SerializedName("discount_rate")
    @Expose
    private int discountRate;
    @SerializedName("labels")
    @Expose
    private List<String> labels = null;
    @SerializedName("installment_rate_collector")
    @Expose
    private List<String> installmentRateCollector = null;
    @SerializedName("min_allowed_amount")
    @Expose
    private int minAllowedAmount;
    @SerializedName("max_allowed_amount")
    @Expose
    private int maxAllowedAmount;
    @SerializedName("recommended_message")
    @Expose
    private String recommendedMessage;
    @SerializedName("installment_amount")
    @Expose
    private float installmentAmount;
    @SerializedName("total_amount")
    @Expose
    private float totalAmount;
    public final static Parcelable.Creator<PayerCost> CREATOR = new Creator<PayerCost>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PayerCost createFromParcel(Parcel in) {
            return new PayerCost(in);
        }

        public PayerCost[] newArray(int size) {
            return (new PayerCost[size]);
        }

    }
    ;

    protected PayerCost(Parcel in) {
        this.installments = ((int) in.readValue((int.class.getClassLoader())));
        this.installmentRate = ((float) in.readValue((float.class.getClassLoader())));
        this.discountRate = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.labels, (java.lang.String.class.getClassLoader()));
        in.readList(this.installmentRateCollector, (java.lang.String.class.getClassLoader()));
        this.minAllowedAmount = ((int) in.readValue((int.class.getClassLoader())));
        this.maxAllowedAmount = ((int) in.readValue((int.class.getClassLoader())));
        this.recommendedMessage = ((String) in.readValue((String.class.getClassLoader())));
        this.installmentAmount = ((float) in.readValue((float.class.getClassLoader())));
        this.totalAmount = ((float) in.readValue((float.class.getClassLoader())));
    }

    public PayerCost() {
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

    public float getInstallmentRate() {
        return installmentRate;
    }

    public void setInstallmentRate(float installmentRate) {
        this.installmentRate = installmentRate;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getInstallmentRateCollector() {
        return installmentRateCollector;
    }

    public void setInstallmentRateCollector(List<String> installmentRateCollector) {
        this.installmentRateCollector = installmentRateCollector;
    }

    public int getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public void setMinAllowedAmount(int minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public int getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(int maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage) {
        this.recommendedMessage = recommendedMessage;
    }

    public float getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(float installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(installments);
        dest.writeValue(installmentRate);
        dest.writeValue(discountRate);
        dest.writeList(labels);
        dest.writeList(installmentRateCollector);
        dest.writeValue(minAllowedAmount);
        dest.writeValue(maxAllowedAmount);
        dest.writeValue(recommendedMessage);
        dest.writeValue(installmentAmount);
        dest.writeValue(totalAmount);
    }

    public int describeContents() {
        return  0;
    }

}
