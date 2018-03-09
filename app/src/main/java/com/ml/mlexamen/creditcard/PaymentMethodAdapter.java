package com.ml.mlexamen.creditcard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ml.mlexamen.R;
import com.ml.mlexamen.data.source.data.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 3/7/2018.
 */

public class PaymentMethodAdapter extends  RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {

    private Context context;
    private List<PaymentMethod> data;
    private View.OnClickListener listener = null;

    public PaymentMethodAdapter(Context context, List<PaymentMethod> data, View.OnClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_payment_method, parent, false);

        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PaymentMethod paymentMethod = data.get(position);
        holder.mTextView.setText(paymentMethod.getName());
        Glide.with(context)
                .load(paymentMethod.getThumbnail())
                .into(holder.mImageView);

        holder.itemView.setTag(paymentMethod);
}

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public PaymentMethod getItem(int position) {
        return getData().get(position);
    }

    public List<PaymentMethod> getData() {
        if(data==null){
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<PaymentMethod> data) {
        this.data = data;
        notifyDataSetChanged();
    }

public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;


        public ViewHolder(View v, View.OnClickListener listener) {

            super(v);
            mTextView = v.findViewById(R.id.textView);
            mImageView = v.findViewById(R.id.imageView);
            if (listener != null) {
                v.setOnClickListener(listener);
            }
        }
    }
}
