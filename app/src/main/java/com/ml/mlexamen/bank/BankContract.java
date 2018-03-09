package com.ml.mlexamen.bank;

import com.ml.mlexamen.BasePresenter;
import com.ml.mlexamen.BaseView;
import com.ml.mlexamen.creditcard.CreditCardContract;
import com.ml.mlexamen.data.source.data.CardIssuers;

import java.util.List;

/**
 * Created by Juan on 3/7/2018.
 */

public interface BankContract {



    interface Presenter extends BasePresenter{
        void initLoader();

    }

    interface View extends BaseView<BankContract.Presenter> {

        void showErrorMessage(String error);
        void getData(List<CardIssuers> list);



    }
}
