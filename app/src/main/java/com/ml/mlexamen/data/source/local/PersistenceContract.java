package com.ml.mlexamen.data.source.local;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.ml.mlexamen.BuildConfig;

/**
 * Created by Juan on 3/4/2018.
 */

public final class PersistenceContract {

    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;
    private static final String CONTENT_SCHEME = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY);

    private static final String VND_ANDROID_CURSOR_DIR_VND = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".";
    public static final String VND_ANDROID_CURSOR_ITEM_VND = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".";
    private static final String SEPARATOR = "/";

    public static final String CONTENT_PAYMENT_METHOD_TYPE = VND_ANDROID_CURSOR_DIR_VND  + PaymentMethodTable.TABLE_NAME;
    public static final String CONTENT_PAYMENT_METHOD_ITEM_TYPE = VND_ANDROID_CURSOR_ITEM_VND + PaymentMethodTable.TABLE_NAME;

    public static final String CONTENT_ISSUER_TYPE = VND_ANDROID_CURSOR_DIR_VND  + IssuerTable.TABLE_NAME;
    public static final String CONTENT_ISSUER_ITEM_TYPE = VND_ANDROID_CURSOR_ITEM_VND + IssuerTable.TABLE_NAME;

    private PersistenceContract() {}


    public static abstract class PaymentMethodTable implements BaseColumns {

        public static final String TABLE_NAME = "payment_method";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PAYMENT_TYPE = "payment_type_id";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_SECURE_THUMB = "secure_thumbnail";
        public static final String COLUMN_NAME_THUMB = "thumbnail";



        public static String[] _COLUMNS = new String[]{
                PaymentMethodTable._ID,
                PaymentMethodTable.COLUMN_NAME_ID,
                PaymentMethodTable.COLUMN_NAME_NAME,
                PaymentMethodTable.COLUMN_NAME_PAYMENT_TYPE,
                PaymentMethodTable.COLUMN_NAME_STATUS,
                PaymentMethodTable.COLUMN_NAME_SECURE_THUMB,
                PaymentMethodTable.COLUMN_NAME_THUMB
        };

        public static final Uri CONTENT_PAYMENT_METHOD_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static Uri buildUriWith(long id) {
            return ContentUris.withAppendedId(CONTENT_PAYMENT_METHOD_URI, id);
        }

        public static Uri buildUriWith(String id) {
            Uri uri = CONTENT_PAYMENT_METHOD_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildUri() {
            return CONTENT_PAYMENT_METHOD_URI.buildUpon().build();
        }

    }

    public static abstract class IssuerTable implements BaseColumns {

        public static final String TABLE_NAME = "issuer";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PROCESSING_MODE = "processing_mode";
        public static final String COLUMN_NAME_MERCHANT_ACCOUNT_ID = "merchant_account_id";
        public static final String COLUMN_NAME_SECURE_THUMB = "secure_thumbnail";
        public static final String COLUMN_NAME_THUMB = "thumbnail";



        public static String[] _COLUMNS = new String[]{
                IssuerTable._ID,
                IssuerTable.COLUMN_NAME_ID,
                IssuerTable.COLUMN_NAME_NAME,
                IssuerTable.COLUMN_NAME_PROCESSING_MODE,
                IssuerTable.COLUMN_NAME_MERCHANT_ACCOUNT_ID,
                IssuerTable.COLUMN_NAME_SECURE_THUMB,
                IssuerTable.COLUMN_NAME_THUMB
        };

        public static final Uri CONTENT_ISSUER_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static Uri buildUriWith(long id) {
            return ContentUris.withAppendedId(CONTENT_ISSUER_URI, id);
        }

        public static Uri buildUriWith(String id) {
            Uri uri = CONTENT_ISSUER_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildUri() {
            return CONTENT_ISSUER_URI.buildUpon().build();
        }

    }
}
