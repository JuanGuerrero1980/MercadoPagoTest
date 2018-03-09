package com.ml.mlexamen.creditcard;

import android.content.ContentResolver;
import android.content.Context;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.ml.mlexamen.data.source.DataSource;
import com.ml.mlexamen.data.source.MLRepository;
import com.ml.mlexamen.data.source.data.PaymentMethod;
import com.ml.mlexamen.data.source.local.ContentValuesFactory;
import com.ml.mlexamen.data.source.local.PersistenceContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 3/6/2018.
 */

public class CreditCardPresenter implements CreditCardContract.Presenter, LoaderManager.LoaderCallbacks<Cursor>{

    public final static int LOADER = 700;
    private LoaderManager loaderManager;
    private ContentResolver contentResolver;
    private CreditCardContract.View view;
    private Context context;
    private Bundle bundle;
    private MLRepository repository;

    public CreditCardPresenter(Context context, MLRepository mlRepository, CreditCardContract.View view, ContentResolver contentResolver, LoaderManager loaderManager){
        this.loaderManager = loaderManager;
        this.contentResolver = contentResolver;
        this.view = view;
        this.context = context;
        this.repository = mlRepository;
    }

    @Override
    public void start() {
        view.buildView();
        view.showLoading(true);
        bundle = null;
        repository.getPaymentMethods(new DataSource.PaymentMethodCallback() {
            @Override
            public void onPaymentMehodsLoaded(List<PaymentMethod> list) {
                repository.savePaymentMethods(list);
                view.showLoading(false);
            }

            @Override
            public void onPaymentMethodsNotAvailable() {
                view.showLoading(false);
                view.showErrorMessage("Not Available");
            }
        });
        initLoader();
    }

    @Override
    public void initLoader() {
        bundle = new Bundle();
        bundle.putString("type", "credit_card");
        if(loaderManager.getLoader(LOADER) == null){
            loaderManager.initLoader(LOADER, bundle, CreditCardPresenter.this);
        }else{
            loaderManager.restartLoader(LOADER, bundle, CreditCardPresenter.this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String type = args.getString("type", "");
        return new CursorLoader(context, PersistenceContract.PaymentMethodTable.buildUri(),
                null,
                PersistenceContract.PaymentMethodTable.COLUMN_NAME_PAYMENT_TYPE.concat("=?"),
                new String[]{type},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(loader.getId() == LOADER){
            if(data.moveToFirst()){
                List<PaymentMethod> list = new ArrayList<>();
                do{
                    PaymentMethod paymentMethod = ContentValuesFactory.getPaymentMethod(data);
                    list.add(paymentMethod);
                    Log.d(CreditCardPresenter.class.getSimpleName(), paymentMethod.getName());
                }while (data.moveToNext());
                view.getData(list);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.getData(new ArrayList<PaymentMethod>());
    }
}
