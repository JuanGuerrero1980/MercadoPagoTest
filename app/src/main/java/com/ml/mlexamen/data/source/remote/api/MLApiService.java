package com.ml.mlexamen.data.source.remote.api;

/**
 * Created by Juan on 3/4/2018.
 */

public interface MLApiService {

    void getPaymentMethods(ApiServiceCallback apiServiceCallback);
    void getCardIssuers(ApiServiceCallback apiServiceCallback, String payment_method_id);
    void getInstallments(ApiServiceCallback apiServiceCallback,  String payment_method_id, String amount,String issuer_id);

    interface ApiServiceCallback<T>{

        void onLoaded(T object);

        void onError(Throwable t);

        void onConnectionError();
    }
}
