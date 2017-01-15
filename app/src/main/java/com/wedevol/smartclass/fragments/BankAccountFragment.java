package com.wedevol.smartclass.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListBankAccountAdapter;
import com.wedevol.smartclass.models.BankAccount;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class BankAccountFragment extends Fragment{
    boolean bankAccountType;

    public static BankAccountFragment newInstance(boolean type) {

        Bundle args = new Bundle();
        args.putBoolean(Constants.BUNDLE_INSTRUCTOR, type);

        BankAccountFragment fragment = new BankAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bank_account, container, false);

        bankAccountType = getArguments().getBoolean(Constants.BUNDLE_INSTRUCTOR);

        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view) {
        TextView tv_payment_type_announcement = (TextView) view.findViewById(R.id.tv_payment_type_announcement);
        if(bankAccountType){
            tv_payment_type_announcement.setText("Para habilitar un nuevo curso, debes depositar en el banco 20 soles");
        }else{
            tv_payment_type_announcement.setText("Para habilitar un nuevo curso, debes depositar en el banco 10 soles");
        }

        List<BankAccount> bankAccountList = new ArrayList<>();
        //bankAccountList.add(new BankAccount("BCP", "Cuenta en soles", "1991-8189-1198", "Cuenta interbancaria en soles", "1198-1811-1561"));
        //bankAccountList.add(new BankAccount("Interbank", "Cuenta en soles", "1291-8139-3198", "Cuenta interbancaria en soles", "1111-1111-1561"));
        //bankAccountList.add(new BankAccount("Paypal", "Cuenta en soles", "1291-8189-1138", "Cuenta interbancaria en soles", "1198-1811-1110"));

        RecyclerView rv_bank_accounts = (RecyclerView) view.findViewById(R.id.rv_bank_accounts);
        rv_bank_accounts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_bank_accounts.setAdapter(new ListBankAccountAdapter(getActivity(), bankAccountList));
    }

    private void setActions() {

    }
}