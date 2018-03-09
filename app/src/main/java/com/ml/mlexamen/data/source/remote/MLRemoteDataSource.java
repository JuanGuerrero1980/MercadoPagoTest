package com.ml.mlexamen.data.source.remote;

import com.ml.mlexamen.data.source.DataSource;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;
import com.ml.mlexamen.data.source.remote.api.MLApiService;
import com.ml.mlexamen.data.source.remote.api.model.CardIssuer;
import com.ml.mlexamen.data.source.remote.api.model.Installment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 3/4/2018.
 */

public class MLRemoteDataSource implements DataSource {

    private static MLRemoteDataSource instance;
    private MLApiService apiService;

    private MLRemoteDataSource(MLApiService apiService){
        this.apiService = apiService;
    }

    public static MLRemoteDataSource getInstance(MLApiService apiService){
        if(instance==null){
            instance = new MLRemoteDataSource(apiService);
        }
        return instance;
    }

    @Override
    public void getPaymentMethods(final PaymentMethodCallback paymentMethodCallback) {
        apiService.getPaymentMethods(new MLApiService.ApiServiceCallback() {
            @Override
            public void onLoaded(Object object) {
                if(object instanceof ArrayList){
                    List<com.ml.mlexamen.data.source.remote.api.model.PaymentMethod> list = (ArrayList<com.ml.mlexamen.data.source.remote.api.model.PaymentMethod>) object;
                    ArrayList<PaymentMethod> methods = new ArrayList<>();
                    for (com.ml.mlexamen.data.source.remote.api.model.PaymentMethod m:list) {
                        PaymentMethod p = new PaymentMethod();
                        p.setId(m.getId());
                        p.setPaymentTypeId(m.getPaymentTypeId());
                        p.setName(m.getName());
                        p.setStatus(m.getStatus());
                        p.setThumbnail(m.getThumbnail());
                        p.setSecureThumbnail(m.getSecureThumbnail());
                        p.setDeferredCapture(m.getDeferredCapture());
                        methods.add(p);
                    }
                    paymentMethodCallback.onPaymentMehodsLoaded(methods);
                }
            }

            @Override
            public void onError(Throwable t) {
                paymentMethodCallback.onPaymentMethodsNotAvailable();
            }

            @Override
            public void onConnectionError() {
                paymentMethodCallback.onPaymentMethodsNotAvailable();
            }
        });
    }

    @Override
    public void savePaymentMethods(List<PaymentMethod> list) {

    }

    @Override
    public void getCardIssuers(final CardIssuersCallback cardIssuersCallback,  String payment_method_id) {
        apiService.getCardIssuers(new MLApiService.ApiServiceCallback() {
            @Override
            public void onLoaded(Object object) {
                if(object instanceof ArrayList){
                    List<CardIssuer> list = (ArrayList<CardIssuer>)object;
                    List<CardIssuers> data=new ArrayList<>();
                    for ( CardIssuer c:list ) {
                        CardIssuers issuers = new CardIssuers();
                        issuers.setId(c.getId());
                        issuers.setName(c.getName());
                        issuers.setProcessingMode(c.getProcessingMode());
                        issuers.setMerchantAccountId((String) c.getMerchantAccountId());
                        issuers.setSecureThumbnail(c.getSecureThumbnail());
                        issuers.setThumbnail(c.getThumbnail());
                        data.add(issuers);
                    }
                    cardIssuersCallback.onCardIssuersLoaded(data);
                }

            }

            @Override
            public void onError(Throwable t) {
                cardIssuersCallback.onCardIssuersNotAvailable();
            }

            @Override
            public void onConnectionError() {
                cardIssuersCallback.onCardIssuersNotAvailable();
            }
        }, payment_method_id);
    }

    @Override
    public void saveCardIssuers(List<CardIssuers> list) {

    }

    @Override
    public void getInstallments(final InstallmentsCallback installmentsCallback, String payment_method_id, String amount, String issuer_id) {
        apiService.getInstallments(new MLApiService.ApiServiceCallback() {
            @Override
            public void onLoaded(Object object) {
                if(object instanceof ArrayList) {
                    List<Installment> list = (ArrayList<Installment>) object;
                    installmentsCallback.onInstallmentsLoaded(list);
                }
            }

            @Override
            public void onError(Throwable t) {
                installmentsCallback.onInstallmentsNotAvailable();
            }

            @Override
            public void onConnectionError() {
                installmentsCallback.onInstallmentsNotAvailable();
            }
        }, payment_method_id, amount, issuer_id);
    }
}
