package com.ml.mlexamen.bank;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.ml.mlexamen.R;
import com.ml.mlexamen.data.MLInjection;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.databinding.ActivityBankBinding;

import java.util.List;

public class BankActivity extends AppCompatActivity implements BankContract.View{

    public static final String KEY_PAYMENT_METHOD_ID = "KEY_PAYMENT_METHOD_ID";
    public static final String KEY_ISSUER_ID = "KEY_ISSUER_ID";
    public static final String KEY_AMOUNT = "KEY_AMOUNT";
    private BankContract.Presenter presenter;
    private ActivityBankBinding binding;
    private BankAdapter adapter;
    private String payment_method_id="visa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void buildView() {
        binding = DataBindingUtil.setContentView(BankActivity.this, R.layout.activity_bank);
        setSupportActionBar(binding.toolbarInclude.simpleToolbar);
        getSupportActionBar().setTitle(R.string.bank);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new BankAdapter(BankActivity.this, null, onListClick);
        binding.list.setHasFixedSize(true);
        binding.list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);
        payment_method_id = getIntent().getStringExtra(KEY_PAYMENT_METHOD_ID);
    }

    private View.OnClickListener onListClick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // Return to parent
            Intent returnIntent = new Intent();
            CardIssuers issuers = (CardIssuers) v.getTag();
            returnIntent.putExtra(KEY_ISSUER_ID, issuers.getId());
            returnIntent.putExtra(KEY_PAYMENT_METHOD_ID, payment_method_id);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().start();
    }


    @Override
    public void setPresenter(BankContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public BankContract.Presenter getPresenter() {
        if(presenter==null){
            presenter = new BankPresenter(BankActivity.this, MLInjection.provideMLRepository(BankActivity.this), BankActivity.this, getContentResolver(), getSupportLoaderManager(), payment_method_id);
        }
        return presenter;
    }

    @Override
    public void showLoading(boolean show) {
        if(show){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(BankActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData(List<CardIssuers> list) {
        adapter.setData(list);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
