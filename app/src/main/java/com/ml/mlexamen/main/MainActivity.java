package com.ml.mlexamen.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ml.mlexamen.R;
import com.ml.mlexamen.bank.BankActivity;
import com.ml.mlexamen.creditcard.CreditCardActivity;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.data.PaymentMethod;
import com.ml.mlexamen.data.source.remote.api.model.PayerCost;
import com.ml.mlexamen.databinding.ActivityMainBinding;
import com.ml.mlexamen.databinding.DialogResultsBinding;
import com.ml.mlexamen.installments.InstallmentActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private static final int PM_REQUEST_CODE = 100;
    private static final int I_REQUEST_CODE = 101;
    private static final int INSTALLMENT_REQUEST_CODE = 102;
    ActivityMainBinding mainBinding;
    private MainContract.Presenter presenter;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().start();
    }

    @Override
    public void buildView() {
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        setSupportActionBar(mainBinding.toolbarInclude.simpleToolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        mainBinding.btnCancel.setEnabled(false);
        mainBinding.btnAccept.setEnabled(false);
        mainBinding.amount.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    clickAccept();
                    return true;
                }
                return false;
            }
        });
        mainBinding.amount.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s.toString())){
                    mainBinding.btnCancel.setEnabled(true);
                    mainBinding.btnAccept.setEnabled(true);
                }else {
                    mainBinding.btnCancel.setEnabled(false);
                    mainBinding.btnAccept.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mainBinding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAccept();
            }
        });
        mainBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PM_REQUEST_CODE) {

            paymentMethodRequest(resultCode, data);

        } else if (requestCode == I_REQUEST_CODE) {

            issuersRequest(resultCode, data);

        }else if( requestCode == INSTALLMENT_REQUEST_CODE){

            installmentRequest(resultCode, data);
        }
    }

    private void paymentMethodRequest(int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(MainActivity.this, BankActivity.class);
            intent.putExtra(BankActivity.KEY_PAYMENT_METHOD_ID, data.getStringExtra(BankActivity.KEY_PAYMENT_METHOD_ID));
            startActivityForResult(intent, I_REQUEST_CODE);
        }
    }

    private void installmentRequest(int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            String payment_method_id = data.getStringExtra(BankActivity.KEY_PAYMENT_METHOD_ID);
            String amount = data.getStringExtra(BankActivity.KEY_AMOUNT);
            String issuer_id = data.getStringExtra(BankActivity.KEY_ISSUER_ID);
            String json = data.getStringExtra(InstallmentActivity.KEY_PAYER_COST);
           getPresenter().getResultData(payment_method_id, amount,issuer_id,json);
        }
    }

    private void issuersRequest(int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(MainActivity.this, InstallmentActivity.class);
            intent.putExtra(BankActivity.KEY_PAYMENT_METHOD_ID, data.getStringExtra(BankActivity.KEY_PAYMENT_METHOD_ID));
            intent.putExtra(BankActivity.KEY_AMOUNT, amount);
            intent.putExtra(BankActivity.KEY_ISSUER_ID, data.getStringExtra(BankActivity.KEY_ISSUER_ID));
            startActivityForResult(intent, INSTALLMENT_REQUEST_CODE);
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public MainContract.Presenter getPresenter() {
        if(presenter==null){
            presenter = new MainPresenter(MainActivity.this, getContentResolver());
        }
        return presenter;
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void clickCancel() {
        mainBinding.amount.getEditText().setText("");
    }

    @Override
    public void clickAccept() {
        if(!TextUtils.isEmpty(mainBinding.amount.getEditText().getText())){
            Intent intent = new Intent(MainActivity.this, CreditCardActivity.class);
            amount = mainBinding.amount.getEditText().getText().toString();
            startActivityForResult(intent, PM_REQUEST_CODE);
        }
    }

    @Override
    public void showDialogResult(CardIssuers cardIssuers, PaymentMethod paymentMethod, PayerCost payerCost, String amount) {

        final Dialog dialog = new Dialog(MainActivity.this);
        /////dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        DialogResultsBinding dialogResultsBinding = DataBindingUtil.inflate(LayoutInflater.from(MainActivity.this), R.layout.dialog_results, null, false);
        Glide.with(MainActivity.this)
                .load(paymentMethod.getThumbnail())
                .into(dialogResultsBinding.imageView);
        dialogResultsBinding.textView.setText(paymentMethod.getName());
        dialogResultsBinding.textViewAmount.setText(getString(R.string.importe).concat(" ").concat(amount));
        Glide.with(MainActivity.this)
                .load(cardIssuers.getThumbnail())
                .into(dialogResultsBinding.imageView2);
        dialogResultsBinding.textView2.setText(cardIssuers.getName());
        dialogResultsBinding.textView3.setText(payerCost.getRecommendedMessage());
        dialog.setContentView(dialogResultsBinding.getRoot());

        dialog.show();

    }



}
