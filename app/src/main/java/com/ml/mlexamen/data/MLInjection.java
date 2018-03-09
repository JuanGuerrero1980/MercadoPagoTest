package com.ml.mlexamen.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ml.mlexamen.data.source.DataSource;
import com.ml.mlexamen.data.source.MLRepository;
import com.ml.mlexamen.data.source.local.MLLocalDataSource;
import com.ml.mlexamen.data.source.remote.MLRemoteDataSource;
import com.ml.mlexamen.data.source.remote.api.MLApiClient;

/**
 * Created by Juan on 3/4/2018.
 */

public class MLInjection {

    public static MLRepository provideMLRepository(Context context){
        //EthicsFakeDataSource.getInstance()
        return MLRepository.getInstance(provideRemoteDataSource(), provideLocalDataSource(context));
    }

    private static DataSource provideRemoteDataSource(){
        return MLRemoteDataSource.getInstance(MLApiClient.getInstance());
    }

    private static DataSource provideLocalDataSource(@NonNull Context context){
        return MLLocalDataSource.getInstance(context.getContentResolver());
    }
}
