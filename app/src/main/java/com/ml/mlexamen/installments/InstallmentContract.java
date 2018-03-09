package com.ml.mlexamen.installments;

import com.ml.mlexamen.BasePresenter;
import com.ml.mlexamen.BaseView;
import com.ml.mlexamen.data.source.remote.api.model.Installment;

import java.util.List;

/**
 * Created by Juan on 3/9/2018.
 */

public interface InstallmentContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<InstallmentContract.Presenter> {

        void showErrorMessage(String error);
        void getData(List<Installment> list);
        void clickCancel();
        void clickAccept();


    }

}
