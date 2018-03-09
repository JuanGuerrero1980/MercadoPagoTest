package com.ml.mlexamen.data.source.remote.api;

import com.ml.mlexamen.BuildConfig;

/**
 * Created by Juan on 3/4/2018.
 */

public class Constants {

    public class Api {

        public static final String BASE_URL = BuildConfig.API_SERVICE;
        public static final String VERSION = BuildConfig.API_VERSION;
        public static final String KEY = BuildConfig.KEY;
        public static final String PUBLIC_KEY = "444a9ef5-8a6b-429f-abdf-587639155d88";

        public static final String GET_PAYMENTS_METHODS = VERSION + "payment_methods";
        public static final String GET_CARD_ISSUERS = VERSION + "payment_methods/card_issuers";
        public static final String GET_INSTALLMENTS = VERSION + "payment_methods/installments";
    }

}
