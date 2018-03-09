package com.ml.mlexamen.data.source;

import android.support.annotation.NonNull;

import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;

import java.util.List;


/**
 * Created by Juan on 3/4/2018.
 */

public class MLRepository implements DataSource {

    private static MLRepository INSTANCE = null;

    private final DataSource mRemoteDataSource;

    private final DataSource mLocalDataSource;

    private MLRepository(@NonNull DataSource remoteDataSource,
                             @NonNull DataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static MLRepository getInstance(DataSource remoteDataSource,
                                               DataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MLRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void getPaymentMethods(PaymentMethodCallback paymentMethodCallback) {
        mRemoteDataSource.getPaymentMethods(paymentMethodCallback);
    }

    @Override
    public void savePaymentMethods(List<PaymentMethod> list) {
        mLocalDataSource.savePaymentMethods(list);
    }

    @Override
    public void getCardIssuers(CardIssuersCallback cardIssuersCallback,  String payment_method_id) {
        mRemoteDataSource.getCardIssuers(cardIssuersCallback, payment_method_id);
    }

    @Override
    public void saveCardIssuers(List<CardIssuers> list) {
        mLocalDataSource.saveCardIssuers(list);
    }

    @Override
    public void getInstallments(InstallmentsCallback installmentsCallback, String payment_method_id, String amount, String issuer_id) {
        mRemoteDataSource.getInstallments(installmentsCallback, payment_method_id, amount, issuer_id);
    }
}
