package com.ml.mlexamen.installments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ml.mlexamen.R;
import com.ml.mlexamen.bank.BankActivity;
import com.ml.mlexamen.data.MLInjection;
import com.ml.mlexamen.data.source.remote.api.model.Installment;
import com.ml.mlexamen.data.source.remote.api.model.PayerCost;
import com.ml.mlexamen.databinding.ActivityInstallmentBinding;

import java.util.List;

public class InstallmentActivity extends AppCompatActivity implements InstallmentContract.View{

    public static final String KEY_PAYER_COST = "KEY_PAYER_COST";
    private InstallmentContract.Presenter presenter;
    private String payment_method_id, amount, issuer_id;
    private ActivityInstallmentBinding binding;
    private InstallmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        payment_method_id = getIntent().getStringExtra(BankActivity.KEY_PAYMENT_METHOD_ID);
        amount = getIntent().getStringExtra(BankActivity.KEY_AMOUNT);
        issuer_id = getIntent().getStringExtra(BankActivity.KEY_ISSUER_ID);
        getPresenter().start();
    }

    @Override
    public void buildView() {
        binding = DataBindingUtil.setContentView(InstallmentActivity.this, R.layout.activity_installment);
        setSupportActionBar(binding.toolbarInclude.simpleToolbar);
        getSupportActionBar().setTitle(R.string.recommended_message);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new InstallmentAdapter(InstallmentActivity.this, null, onListClick);
        binding.list.setHasFixedSize(true);
        binding.list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);

    }

    private View.OnClickListener onListClick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // Return to parent
            Intent returnIntent = new Intent();
            PayerCost payerCost = (PayerCost) v.getTag();
            Gson gson = new Gson();
            String json = gson.toJson(payerCost);
            returnIntent.putExtra(BankActivity.KEY_ISSUER_ID, issuer_id);
            returnIntent.putExtra(BankActivity.KEY_PAYMENT_METHOD_ID, payment_method_id);
            returnIntent.putExtra(BankActivity.KEY_AMOUNT, amount);
            returnIntent.putExtra(KEY_PAYER_COST, json);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    };

    @Override
    public void setPresenter(InstallmentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public InstallmentContract.Presenter getPresenter() {
        if(presenter==null){
            presenter = new InstallmentPresenter(InstallmentActivity.this,
                    MLInjection.provideMLRepository(InstallmentActivity.this),
                    InstallmentActivity.this, payment_method_id, amount, issuer_id );
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
        Toast.makeText(InstallmentActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData(List<Installment> list) {
        if(list.size()==1){
            List<PayerCost> costs = list.get(0).getPayerCosts();
            adapter.setData(costs);
        }
    }

    @Override
    public void clickCancel() {

    }

    @Override
    public void clickAccept() {

    }
}
