package com.wedevol.smartclass.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.BankAccount;

import java.util.List;

/** Created by paolo on 12/20/16.*/
public class ListBankAccountAdapter extends RecyclerView.Adapter<ListBankAccountAdapter.ViewHolder> {
    private final List<BankAccount> mItems;
    private final Activity context;

    public ListBankAccountAdapter(Activity context, List<BankAccount> list) {
        super();
        this.context = context;
        mItems = list;
    }

    @Override
    public ListBankAccountAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_bank_account, viewGroup, false);
        return new ListBankAccountAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListBankAccountAdapter.ViewHolder viewHolder, int i) {
        final BankAccount bank = mItems.get(i);
        viewHolder.tv_account_bank_and_type.setText(bank.getBankName() + " - " + bank.getBankAccountType());
        viewHolder.tv_account_id.setText(bank.getBankAccountId());
        viewHolder.tv_province_account_bank_and_type.setText(bank.getBankAccountProvinceType());
        viewHolder.tv_province_account_id.setText(bank.getBankAccountProvinceId());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_account_bank_and_type;
        final TextView tv_account_id;
        final TextView tv_province_account_bank_and_type;
        final TextView tv_province_account_id;

        ViewHolder(View itemView) {
            super(itemView);
            tv_account_bank_and_type = (TextView) itemView.findViewById(R.id.tv_account_bank_and_type);
            tv_account_id = (TextView) itemView.findViewById(R.id.tv_account_id);
            tv_province_account_bank_and_type = (TextView) itemView.findViewById(R.id.tv_province_account_bank_and_type);
            tv_province_account_id = (TextView) itemView.findViewById(R.id.tv_province_account_id);
        }
    }
}