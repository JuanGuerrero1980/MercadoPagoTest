package com.ml.mlexamen.data.source.remote.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ml.mlexamen.data.source.remote.api.model.CardIssuer;
import com.ml.mlexamen.data.source.remote.api.model.Installment;
import com.ml.mlexamen.data.source.remote.api.model.PaymentMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by Juan on 3/4/2018.
 */

public class MLApiClient implements MLApiService {
    static MLApiClient instance;
    private RetrofitApiService mRetrofitApiService;
    public static MLApiClient getInstance(){
        if(instance==null){
            instance = new MLApiClient();
        }
        return instance;
    }

    private MLApiClient(){
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        mRetrofitApiService = retrofit.create(RetrofitApiService.class);
    }

    @Override
    public void getPaymentMethods(final ApiServiceCallback apiServiceCallback) {
        Map<String, String> data = new HashMap<>();
        data.put("public_key", Constants.Api.PUBLIC_KEY);
        mRetrofitApiService.paymentMethods(data).enqueue(new Callback<List<PaymentMethod>>() {
            @Override
            public void onResponse(Call<List<PaymentMethod>> call, Response<List<PaymentMethod>> response) {
                if(response.isSuccessful()){
                    apiServiceCallback.onLoaded(response.body());
                }else{
                    apiServiceCallback.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<List<PaymentMethod>> call, Throwable t) {
                apiServiceCallback.onError(t);
            }
        });
    }

    @Override
    public void getCardIssuers(final ApiServiceCallback apiServiceCallback, String payment_method_id) {
        Map<String, String> data = new HashMap<>();
        data.put("public_key", Constants.Api.PUBLIC_KEY);
        data.put("payment_method_id", payment_method_id);
        mRetrofitApiService.cardIssuers(data).enqueue(new Callback<List<CardIssuer>>() {
            @Override
            public void onResponse(Call<List<CardIssuer>> call, Response<List<CardIssuer>> response) {
                if(response.isSuccessful()){
                    apiServiceCallback.onLoaded(response.body());
                }else{
                    apiServiceCallback.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<List<CardIssuer>> call, Throwable t) {
                apiServiceCallback.onError(t);
            }
        });
    }

    @Override
    public void getInstallments(final ApiServiceCallback apiServiceCallback, String payment_method_id, String amount, String issuer_id) {
        Map<String, String> data = new HashMap<>();
        data.put("public_key", Constants.Api.PUBLIC_KEY);
        data.put("payment_method_id", payment_method_id);
        data.put("amount", amount);
        data.put("issuer.id", issuer_id);
        mRetrofitApiService.installments(data).enqueue(new Callback<List<Installment>>() {
            @Override
            public void onResponse(Call<List<Installment>> call, Response<List<Installment>> response) {
                if(response.isSuccessful()){
                    apiServiceCallback.onLoaded(response.body());
                }else {
                    apiServiceCallback.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<List<Installment>> call, Throwable t) {
                apiServiceCallback.onConnectionError();
            }
        });
    }
}
