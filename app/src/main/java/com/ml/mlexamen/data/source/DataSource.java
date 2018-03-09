package com.ml.mlexamen.data.source;

import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;
import com.ml.mlexamen.data.source.remote.api.model.Installment;

import java.util.List;

/**
 * Created by Juan on 3/4/2018.
 */

public interface DataSource {

    void getPaymentMethods(PaymentMethodCallback paymentMethodCallback);
    void savePaymentMethods(List<PaymentMethod> list);

    interface PaymentMethodCallback{
        void onPaymentMehodsLoaded(List<PaymentMethod> list);
        void onPaymentMethodsNotAvailable();
    }

    void getCardIssuers(CardIssuersCallback cardIssuersCallback,  String payment_method_id);
    void saveCardIssuers(List<CardIssuers> list);

    interface CardIssuersCallback{
        void onCardIssuersLoaded(List<CardIssuers> list);
        void onCardIssuersNotAvailable();
    }

    void getInstallments(InstallmentsCallback installmentsCallback,  String payment_method_id, String amount,String issuer_id);

    interface InstallmentsCallback{
        void onInstallmentsLoaded(List<Installment> list);
        void onInstallmentsNotAvailable();
    }
}
