package com.ml.mlexamen;

/**
 * Created by Juan on 3/4/2018.
 */

public interface BaseView<T> {

    void buildView();
    void setPresenter(T presenter);
    T getPresenter();
    void showLoading(boolean show);
}
