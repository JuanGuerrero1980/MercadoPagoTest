package com.ml.mlexamen.main;

import com.ml.mlexamen.BasePresenter;
import com.ml.mlexamen.BaseView;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;
import com.ml.mlexamen.data.source.remote.api.model.PayerCost;

/**
 * Created by Juan on 3/4/2018.
 */

public interface MainContract {

    interface Presenter extends BasePresenter {
        void getResultData( String payment_method_id, String amount , String issuer_id, String payerCost);
    }

    interface View extends BaseView<Presenter> {

        void showErrorMessage(String error);
        void clickCancel();
        void clickAccept();
        void showDialogResult(CardIssuers cardIssuers, PaymentMethod paymentMethod, PayerCost payerCost, String amount);

    }

}
