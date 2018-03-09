package com.ml.mlexamen.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan on 3/4/2018.
 */

public class MLDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Ethics.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String DEFAULT_0 = " DEFAULT 0 ";

    private static final String UNIQUE = " UNIQUE ";

    public MLDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PAYMENT_METHOD);
        db.execSQL(SQL_CREATE_ISSUER);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    private static final String SQL_CREATE_PAYMENT_METHOD =  "CREATE TABLE " + PersistenceContract.PaymentMethodTable.TABLE_NAME + " (" +
            PersistenceContract.PaymentMethodTable._ID + INTEGER_TYPE + " PRIMARY KEY," +
            PersistenceContract.PaymentMethodTable.COLUMN_NAME_ID + TEXT_TYPE + UNIQUE + COMMA_SEP +
            PersistenceContract.PaymentMethodTable.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.PaymentMethodTable.COLUMN_NAME_PAYMENT_TYPE + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.PaymentMethodTable.COLUMN_NAME_SECURE_THUMB + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.PaymentMethodTable.COLUMN_NAME_THUMB + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.PaymentMethodTable.COLUMN_NAME_STATUS + TEXT_TYPE +
            " )";

    private static final String SQL_CREATE_ISSUER =  "CREATE TABLE " + PersistenceContract.IssuerTable.TABLE_NAME + " (" +
            PersistenceContract.IssuerTable._ID + INTEGER_TYPE + " PRIMARY KEY," +
            PersistenceContract.IssuerTable.COLUMN_NAME_ID + TEXT_TYPE + UNIQUE + COMMA_SEP +
            PersistenceContract.IssuerTable.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.IssuerTable.COLUMN_NAME_PROCESSING_MODE + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.IssuerTable.COLUMN_NAME_SECURE_THUMB + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.IssuerTable.COLUMN_NAME_THUMB + TEXT_TYPE + COMMA_SEP +
            PersistenceContract.IssuerTable.COLUMN_NAME_MERCHANT_ACCOUNT_ID + TEXT_TYPE +
            " )";
}
