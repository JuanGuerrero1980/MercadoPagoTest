package com.ml.mlexamen.creditcard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ml.mlexamen.R;
import com.ml.mlexamen.bank.BankActivity;
import com.ml.mlexamen.data.MLInjection;
import com.ml.mlexamen.data.source.data.PaymentMethod;
import com.ml.mlexamen.databinding.ActivityCreditCardBinding;

import java.util.List;

public class CreditCardActivity extends AppCompatActivity implements CreditCardContract.View {

    private CreditCardContract.Presenter presenter;
    private ActivityCreditCardBinding binding;
    private PaymentMethodAdapter paymentMethodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().start();
    }

    @Override
    public void buildView() {
        binding = DataBindingUtil.setContentView(CreditCardActivity.this, R.layout.activity_credit_card);
        setSupportActionBar(binding.toolbarInclude.simpleToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        paymentMethodAdapter = new PaymentMethodAdapter(CreditCardActivity.this, null, onListClick);
        binding.list.setHasFixedSize(true);
        binding.list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(paymentMethodAdapter);
    }

    private View.OnClickListener onListClick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // Return to parent
            Intent returnIntent = new Intent();
            PaymentMethod paymentMethod = (PaymentMethod) v.getTag();
            returnIntent.putExtra(BankActivity.KEY_PAYMENT_METHOD_ID, paymentMethod.getId());
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    };

    @Override
    public void setPresenter(CreditCardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public CreditCardContract.Presenter getPresenter() {
        if(presenter==null){
            presenter = new CreditCardPresenter(CreditCardActivity.this, MLInjection.provideMLRepository(CreditCardActivity.this), CreditCardActivity.this, getContentResolver(), getSupportLoaderManager());
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
        Toast.makeText(CreditCardActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData(List<PaymentMethod> list) {
        paymentMethodAdapter.setData(list);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
