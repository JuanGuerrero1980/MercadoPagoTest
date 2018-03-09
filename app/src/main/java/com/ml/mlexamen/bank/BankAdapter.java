package com.ml.mlexamen.bank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ml.mlexamen.R;
import com.ml.mlexamen.data.source.data.CardIssuers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 3/7/2018.
 */

public class BankAdapter extends  RecyclerView.Adapter<BankAdapter.ViewHolder> {

    private Context context;
    private List<CardIssuers> data;
    private View.OnClickListener listener = null;

    public BankAdapter(Context context, List<CardIssuers> data, View.OnClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public BankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_bank, parent, false);

        return new BankAdapter.ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(BankAdapter.ViewHolder holder, int position) {
        CardIssuers issuers = data.get(position);
        holder.mTextView.setText(issuers.getName());
        Glide.with(context)
                .load(issuers.getThumbnail())
                .into(holder.mImageView);

        holder.itemView.setTag(issuers);
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public CardIssuers getItem(int position) {
        return getData().get(position);
    }

    public List<CardIssuers> getData() {
        if(data==null){
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<CardIssuers> data) {
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
