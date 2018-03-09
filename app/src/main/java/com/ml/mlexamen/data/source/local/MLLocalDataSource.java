package com.ml.mlexamen.data.source.local;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.ml.mlexamen.data.source.DataSource;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;

import java.util.List;

/**
 * Created by Juan on 3/4/2018.
 */

public class MLLocalDataSource implements DataSource {

    private static MLLocalDataSource instance;
    private ContentResolver mContentResolver;

    private MLLocalDataSource(ContentResolver mContentResolver){
        this.mContentResolver = mContentResolver;
    }

    public static MLLocalDataSource getInstance(ContentResolver mContentResolver){
        if(instance==null){
            instance = new MLLocalDataSource(mContentResolver);
        }
        return instance;
    }

    @Override
    public void getPaymentMethods(PaymentMethodCallback paymentMethodCallback) {

    }

    @Override
    public void savePaymentMethods(List<PaymentMethod> list) {
        if(list!=null && !list.isEmpty()){
            for ( PaymentMethod method: list ) {
                ContentValues values = ContentValuesFactory.getPaymentMethodValues(method);
                mContentResolver.insert(PersistenceContract.PaymentMethodTable.buildUri(), values);
            }
        }
    }

    @Override
    public void getCardIssuers(CardIssuersCallback cardIssuersCallback, String payment_method_id) {

    }

    @Override
    public void saveCardIssuers(List<CardIssuers> list) {
        if(list!=null && !list.isEmpty()){
            mContentResolver.delete(PersistenceContract.IssuerTable.buildUri(),null,null);
            for ( CardIssuers issuers: list ) {
                ContentValues values = ContentValuesFactory.getCardIssuersValues(issuers);
                mContentResolver.insert(PersistenceContract.IssuerTable.buildUri(), values);
            }
        }
    }

    @Override
    public void getInstallments(InstallmentsCallback installmentsCallback, String payment_method_id, String amount, String issuer_id) {

    }
}
