
package com.ml.mlexamen.data.source.remote.api.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Installment implements Parcelable
{

    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("issuer")
    @Expose
    private Issuer issuer;
    @SerializedName("processing_mode")
    @Expose
    private String processingMode;
    @SerializedName("merchant_account_id")
    @Expose
    private Object merchantAccountId;
    @SerializedName("payer_costs")
    @Expose
    private List<PayerCost> payerCosts = null;
    public final static Parcelable.Creator<Installment> CREATOR = new Creator<Installment>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Installment createFromParcel(Parcel in) {
            return new Installment(in);
        }

        public Installment[] newArray(int size) {
            return (new Installment[size]);
        }

    }
    ;

    protected Installment(Parcel in) {
        this.paymentMethodId = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.issuer = ((Issuer) in.readValue((Issuer.class.getClassLoader())));
        this.processingMode = ((String) in.readValue((String.class.getClassLoader())));
        this.merchantAccountId = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.payerCosts, (PayerCost.class.getClassLoader()));
    }

    public Installment() {
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
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

    public List<PayerCost> getPayerCosts() {
        return payerCosts;
    }

    public void setPayerCosts(List<PayerCost> payerCosts) {
        this.payerCosts = payerCosts;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(paymentMethodId);
        dest.writeValue(paymentTypeId);
        dest.writeValue(issuer);
        dest.writeValue(processingMode);
        dest.writeValue(merchantAccountId);
        dest.writeList(payerCosts);
    }

    public int describeContents() {
        return  0;
    }

}
