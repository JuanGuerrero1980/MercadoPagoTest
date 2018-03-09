package com.ml.mlexamen.data.source.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;

/**
 * Created by Juan on 3/4/2018.
 */

public class ContentValuesFactory {

    public static ContentValues getPaymentMethodValues(PaymentMethod paymentMethod){
        ContentValues values = new ContentValues();
        values.put(PersistenceContract.PaymentMethodTable.COLUMN_NAME_ID, paymentMethod.getId());
        values.put(PersistenceContract.PaymentMethodTable.COLUMN_NAME_NAME, paymentMethod.getName());
        values.put(PersistenceContract.PaymentMethodTable.COLUMN_NAME_PAYMENT_TYPE, paymentMethod.getPaymentTypeId());
        values.put(PersistenceContract.PaymentMethodTable.COLUMN_NAME_SECURE_THUMB, paymentMethod.getSecureThumbnail());
        values.put(PersistenceContract.PaymentMethodTable.COLUMN_NAME_THUMB, paymentMethod.getThumbnail());
        values.put(PersistenceContract.PaymentMethodTable.COLUMN_NAME_STATUS, paymentMethod.getStatus());

        return values;
    }


    public static ContentValues getCardIssuersValues(CardIssuers cardIssuers){
        ContentValues values = new ContentValues();
        values.put(PersistenceContract.IssuerTable.COLUMN_NAME_ID, cardIssuers.getId());
        values.put(PersistenceContract.IssuerTable.COLUMN_NAME_NAME, cardIssuers.getName());
        values.put(PersistenceContract.IssuerTable.COLUMN_NAME_MERCHANT_ACCOUNT_ID, cardIssuers.getMerchantAccountId());
        values.put(PersistenceContract.IssuerTable.COLUMN_NAME_SECURE_THUMB, cardIssuers.getSecureThumbnail());
        values.put(PersistenceContract.IssuerTable.COLUMN_NAME_THUMB, cardIssuers.getThumbnail());
        values.put(PersistenceContract.IssuerTable.COLUMN_NAME_PROCESSING_MODE, cardIssuers.getProcessingMode());

        return values;
    }

    public static PaymentMethod getPaymentMethod(Cursor cursor){
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(cursor.getString(cursor.getColumnIndex(PersistenceContract.PaymentMethodTable.COLUMN_NAME_ID)));
        paymentMethod.setName(cursor.getString(cursor.getColumnIndex(PersistenceContract.PaymentMethodTable.COLUMN_NAME_NAME)));
        paymentMethod.setPaymentTypeId(cursor.getString(cursor.getColumnIndex(PersistenceContract.PaymentMethodTable.COLUMN_NAME_PAYMENT_TYPE)));
        paymentMethod.setSecureThumbnail(cursor.getString(cursor.getColumnIndex(PersistenceContract.PaymentMethodTable.COLUMN_NAME_SECURE_THUMB)));
        paymentMethod.setThumbnail(cursor.getString(cursor.getColumnIndex(PersistenceContract.PaymentMethodTable.COLUMN_NAME_THUMB)));
        paymentMethod.setStatus(cursor.getString(cursor.getColumnIndex(PersistenceContract.PaymentMethodTable.COLUMN_NAME_STATUS)));
        return paymentMethod;
    }

    public static CardIssuers getCardIssuers(Cursor cursor){
        CardIssuers cardIssuers = new CardIssuers();
        cardIssuers.setId(cursor.getString(cursor.getColumnIndex(PersistenceContract.IssuerTable.COLUMN_NAME_ID)));
        cardIssuers.setName(cursor.getString(cursor.getColumnIndex(PersistenceContract.IssuerTable.COLUMN_NAME_NAME)));
        cardIssuers.setMerchantAccountId(cursor.getString(cursor.getColumnIndex(PersistenceContract.IssuerTable.COLUMN_NAME_MERCHANT_ACCOUNT_ID)));
        cardIssuers.setSecureThumbnail(cursor.getString(cursor.getColumnIndex(PersistenceContract.IssuerTable.COLUMN_NAME_SECURE_THUMB)));
        cardIssuers.setThumbnail(cursor.getString(cursor.getColumnIndex(PersistenceContract.IssuerTable.COLUMN_NAME_THUMB)));
        cardIssuers.setProcessingMode(cursor.getString(cursor.getColumnIndex(PersistenceContract.IssuerTable.COLUMN_NAME_PROCESSING_MODE)));
        return cardIssuers;
    }

}
