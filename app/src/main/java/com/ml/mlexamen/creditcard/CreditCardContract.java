package com.ml.mlexamen.creditcard;

import com.ml.mlexamen.BasePresenter;
import com.ml.mlexamen.BaseView;
import com.ml.mlexamen.data.source.data.PaymentMethod;

import java.util.List;

/**
 * Created by Juan on 3/6/2018.
 */

public interface CreditCardContract {

    interface Presenter extends BasePresenter
    {
        void initLoader();

    }

    interface View extends BaseView<Presenter> {

        void showErrorMessage(String error);
        void getData(List<PaymentMethod> list);



    }
}
