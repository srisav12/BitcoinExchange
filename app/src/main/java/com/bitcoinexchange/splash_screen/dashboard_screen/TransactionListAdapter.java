package com.bitcoinexchange.splash_screen.dashboard_screen;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitcoinexchange.R;

import java.util.List;



public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {

    private Context context;
    private  List<TransactionPojo> transactionList;

    public TransactionListAdapter(Context context, List<TransactionPojo> transactionList){
        this.context = context;
        this.transactionList = transactionList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_items_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransactionPojo item = transactionList.get(position);
        if(position%2 == 0){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.light_grey_bg_color));
        }else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.dark_grey_bg_color));
        }

        holder.logoImage.setImageResource(R.mipmap.ic_logo);
        /*if(position%2 != 0 && position%3 == 0){
            holder.logoImage.setImageResource(R.drawable.red_circle);
        }else if(position%2 == 0 && position%3 != 0){
            holder.logoImage.setImageResource(R.drawable.green_circle);
        }else{
            holder.logoImage.setImageResource(R.drawable.blue_circle);
        }*/
        holder.companyName.setText(item.getCompanyName());
        holder.transactionDate.setText(item.getDate());
        holder.transactionAmt.append(item.getAmount());
        if(item.getSendReceive() == 1){
            holder.transactionAmt.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_recieve_32,0,0);
            holder.transactionAmt.setTextColor(ContextCompat.getColor(context, R.color.color_green));
        }else if(item.getSendReceive() == 2){
            holder.transactionAmt.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_send_32,0,0);
            holder.transactionAmt.setTextColor(ContextCompat.getColor(context, R.color.color_red));
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView logoImage;
        TextView companyName, transactionDate, transactionAmt;

        public ViewHolder(View itemView) {
            super(itemView);
            logoImage = (ImageView) itemView.findViewById(R.id.logoImage);
            companyName = (TextView) itemView.findViewById(R.id.nameText);
            transactionDate = (TextView) itemView.findViewById(R.id.dateText);
            transactionAmt = (TextView) itemView.findViewById(R.id.amountText);
        }
    }
}
