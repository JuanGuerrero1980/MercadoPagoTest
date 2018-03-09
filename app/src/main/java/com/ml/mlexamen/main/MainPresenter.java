package com.ml.mlexamen.main;

import android.content.ContentResolver;
import android.database.Cursor;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;
import com.ml.mlexamen.data.source.local.ContentValuesFactory;
import com.ml.mlexamen.data.source.local.PersistenceContract;
import com.ml.mlexamen.data.source.remote.api.model.PayerCost;

/**
 * Created by Juan on 3/4/2018.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private ContentResolver contentResolver;

    public MainPresenter(MainContract.View view,  ContentResolver contentResolver){
        this.view = view;
        this.contentResolver = contentResolver;
        this.view.setPresenter(this);


    }
    @Override
    public void start() {
        view.buildView();
    }

    @Override
    public void getResultData(String payment_method_id, String amount, String issuer_id, String p) {
        Cursor cursorPayment = contentResolver.query(PersistenceContract.PaymentMethodTable.buildUri(),
                null,
                PersistenceContract.PaymentMethodTable.COLUMN_NAME_ID.concat("=?"),
                new String[]{payment_method_id}, null);
        if(cursorPayment.moveToFirst()){
            PaymentMethod paymentMethod = ContentValuesFactory.getPaymentMethod(cursorPayment);
            Cursor cursorCard = contentResolver.query(PersistenceContract.IssuerTable.buildUri(),
                    null,
                    PersistenceContract.IssuerTable.COLUMN_NAME_ID.concat("=?"),
                    new String[]{issuer_id}, null);
            if(cursorCard.moveToFirst()){
                CardIssuers cardIssuers = ContentValuesFactory.getCardIssuers(cursorCard);
                if(!TextUtils.isEmpty(p)){
                    PayerCost payerCost = new Gson().fromJson(p, PayerCost.class);
                    view.showDialogResult(cardIssuers, paymentMethod, payerCost, amount);
                    return;
                }
            }
        }
        view.showErrorMessage("No Data");

    }
}
