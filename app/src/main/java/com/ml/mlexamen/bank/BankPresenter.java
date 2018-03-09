package com.ml.mlexamen.bank;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.ml.mlexamen.data.source.DataSource;
import com.ml.mlexamen.data.source.MLRepository;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.local.ContentValuesFactory;
import com.ml.mlexamen.data.source.local.PersistenceContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 3/7/2018.
 */

public class BankPresenter implements BankContract.Presenter, LoaderManager.LoaderCallbacks<Cursor> {

    public final static int LOADER = 750;
    private LoaderManager loaderManager;
    private ContentResolver contentResolver;
    private BankContract.View view;
    private Context context;
    private Bundle bundle;
    private MLRepository repository;
    private String payment_method_id;

    public BankPresenter(Context context, MLRepository mlRepository, BankContract.View view, ContentResolver contentResolver, LoaderManager loaderManager, String payment_method_id){
        this.loaderManager = loaderManager;
        this.contentResolver = contentResolver;
        this.view = view;
        this.context = context;
        this.repository = mlRepository;
        this.payment_method_id = payment_method_id;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        view.buildView();
        view.showLoading(true);
        bundle = null;
        repository.getCardIssuers(new DataSource.CardIssuersCallback(){
            @Override
            public void onCardIssuersLoaded(List<CardIssuers> list) {
                repository.saveCardIssuers(list);
                view.showLoading(false);
            }

            @Override
            public void onCardIssuersNotAvailable() {
                view.showLoading(false);
                view.showErrorMessage("Not Available");
            }

        }, payment_method_id);
        initLoader();
    }

    @Override
    public void initLoader() {
        bundle = new Bundle();
        if(loaderManager.getLoader(LOADER) == null){
            loaderManager.initLoader(LOADER, bundle, BankPresenter.this);
        }else{
            loaderManager.restartLoader(LOADER, bundle, BankPresenter.this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context, PersistenceContract.IssuerTable.buildUri(),
                null,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(loader.getId() == LOADER){
            if(data.moveToFirst()){
                List<CardIssuers> list = new ArrayList<>();
                do{
                    CardIssuers cardIssuers = ContentValuesFactory.getCardIssuers(data);
                    list.add(cardIssuers);
                }while (data.moveToNext());
                view.getData(list);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.getData(new ArrayList<CardIssuers>());
    }
}
