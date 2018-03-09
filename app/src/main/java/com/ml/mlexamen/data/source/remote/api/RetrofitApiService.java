package com.ml.mlexamen.data.source.remote.api;

import com.ml.mlexamen.data.source.remote.api.model.CardIssuer;
import com.ml.mlexamen.data.source.remote.api.model.Installment;
import com.ml.mlexamen.data.source.remote.api.model.PaymentMethod;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Juan on 3/4/2018.
 */

public interface RetrofitApiService {

    @Headers({
            "Sign-Request: true"
    })
    @GET(Constants.Api.GET_PAYMENTS_METHODS)
    Call<List<PaymentMethod>> paymentMethods(@QueryMap(encoded = false) Map<String, String> options);

    @GET(Constants.Api.GET_CARD_ISSUERS)
    Call<List<CardIssuer>> cardIssuers(@QueryMap(encoded = false) Map<String, String> options);

    @GET(Constants.Api.GET_INSTALLMENTS)
    Call<List<Installment>> installments(@QueryMap(encoded = false) Map<String, String> options);
}
