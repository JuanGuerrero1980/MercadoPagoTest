package com.ml.mlexamen.installments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ml.mlexamen.R;
import com.ml.mlexamen.bank.BankAdapter;
import com.ml.mlexamen.data.source.data.CardIssuers;
import com.ml.mlexamen.data.source.remote.api.model.Installment;
import com.ml.mlexamen.data.source.remote.api.model.PayerCost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 3/9/2018.
 */

public class InstallmentAdapter extends  RecyclerView.Adapter<InstallmentAdapter.ViewHolder> {

    private Context context;
    private List<PayerCost> data;
    private View.OnClickListener listener = null;

    public InstallmentAdapter(Context context, List<PayerCost> data, View.OnClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public InstallmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_bank, parent, false);

        return new InstallmentAdapter.ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(InstallmentAdapter.ViewHolder holder, int position) {
        PayerCost payerCost = data.get(position);
        holder.mTextView.setText(payerCost.getRecommendedMessage());


        holder.itemView.setTag(payerCost);
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public PayerCost getItem(int position) {
        return getData().get(position);
    }

    public List<PayerCost> getData() {
        if(data==null){
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<PayerCost> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;


        public ViewHolder(View v, View.OnClickListener listener) {

            super(v);
            mTextView = v.findViewById(R.id.textView);
            if (listener != null) {
                v.setOnClickListener(listener);
            }
        }
    }
}
