package com.ml.mlexamen.installments;

import android.content.Context;

import com.ml.mlexamen.data.source.DataSource;
import com.ml.mlexamen.data.source.MLRepository;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.remote.api.model.Installment;

import java.util.List;

/**
 * Created by Juan on 3/9/2018.
 */

public class InstallmentPresenter implements InstallmentContract.Presenter {

    private Context context;
    private InstallmentContract.View view;
    private MLRepository repository;
    private String payment_method_id, amount, issuer_id;

    public InstallmentPresenter(Context context, MLRepository mlRepository, InstallmentContract.View view, String payment_method_id, String amount,String issuer_id){
        this.context = context;
        this.repository = mlRepository;
        this.view = view;
        this.payment_method_id = payment_method_id;
        this.amount = amount;
        this.issuer_id = issuer_id;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        view.buildView();
        view.showLoading(true);
        repository.getInstallments(new DataSource.InstallmentsCallback() {
            @Override
            public void onInstallmentsLoaded(List<Installment> list) {
                view.getData(list);
                view.showLoading(false);
            }

            @Override
            public void onInstallmentsNotAvailable() {
                view.showLoading(false);
                view.showErrorMessage("Not Available");
            }
        }, payment_method_id, amount, issuer_id);
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
    }
}
