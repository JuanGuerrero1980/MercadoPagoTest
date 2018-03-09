package com.ml.mlexamen.data.source.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * Created by Juan on 3/4/2018.
 */

public class MLProvider extends ContentProvider {

    private static final int PAYMENT_METHOD = 100;
    private static final int ISSUER = 200;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MLDbHelper dbHelper;

    public MLProvider(){

    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PersistenceContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, PersistenceContract.PaymentMethodTable.TABLE_NAME, PAYMENT_METHOD);
        matcher.addURI(authority, PersistenceContract.IssuerTable.TABLE_NAME, ISSUER);

        return matcher;
    }

        @Override
    public boolean onCreate() {
        dbHelper = new MLDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PAYMENT_METHOD:
                return PersistenceContract.CONTENT_PAYMENT_METHOD_TYPE;
            case ISSUER:
                return PersistenceContract.CONTENT_ISSUER_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case PAYMENT_METHOD:

                retCursor = dbHelper.getReadableDatabase().query(
                        PersistenceContract.PaymentMethodTable.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case ISSUER:
                retCursor = dbHelper.getReadableDatabase().query(
                        PersistenceContract.IssuerTable.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }



    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        long id_inserted;

        switch (match) {
            case PAYMENT_METHOD:
                id_inserted = insertOrUpdateById(db, uri, PersistenceContract.PaymentMethodTable.TABLE_NAME, values, PersistenceContract.PaymentMethodTable.COLUMN_NAME_ID);
                if (id_inserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null, false);
                }
                return PersistenceContract.PaymentMethodTable.buildUriWith(values.getAsString(PersistenceContract.PaymentMethodTable.COLUMN_NAME_ID));
            case ISSUER:
                id_inserted = insertOrUpdateById(db, uri, PersistenceContract.IssuerTable.TABLE_NAME, values, PersistenceContract.IssuerTable.COLUMN_NAME_ID);
                if (id_inserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null, false);
                }
                return PersistenceContract.IssuerTable.buildUriWith(values.getAsString(PersistenceContract.IssuerTable.COLUMN_NAME_ID));

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case PAYMENT_METHOD:
                rowsDeleted = db.delete(
                        PersistenceContract.PaymentMethodTable.TABLE_NAME, selection, selectionArgs);
                break;
            case ISSUER:
                rowsDeleted = db.delete(
                        PersistenceContract.IssuerTable.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null, false);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case PAYMENT_METHOD:
                rowsUpdated = db.update(PersistenceContract.PaymentMethodTable.TABLE_NAME, values, selection,
                        selectionArgs
                );
                break;
            case ISSUER:
                rowsUpdated = db.update(PersistenceContract.IssuerTable.TABLE_NAME, values, selection,
                        selectionArgs
                );
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }


    private long insertOrUpdateById(SQLiteDatabase db, Uri uri, String table,
                                    ContentValues values, String column) {
        long id;
        try {
            id = db.insertOrThrow(table, null, values);
        } catch (SQLiteConstraintException e) {
            id = update(uri, values, column + "=?",
                    new String[]{values.getAsString(column)});
            if (id == 0)
                throw e;
        }
        return id;
    }
}
