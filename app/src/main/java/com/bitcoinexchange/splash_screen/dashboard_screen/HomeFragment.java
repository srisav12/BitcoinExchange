package com.bitcoinexchange.splash_screen.dashboard_screen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.utils.SampleDataClass;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment {

    private Context context;
    private RecyclerView transactionsHistoryViews;
    private List<TransactionPojo> trasResultList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if(container == null){
            return null;
        }else {
            view = inflater.inflate(R.layout.fragment_home_screen, container, false);
            return view;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        transactionsHistoryViews = (RecyclerView) view.findViewById(R.id.transactionsListView);

        setupRecyclerView();

        getData();
    }


    // mehod for setting up recyclerview layout
    private void setupRecyclerView() {
        transactionsHistoryViews.setHasFixedSize(false);
        transactionsHistoryViews.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        transactionsHistoryViews.setLayoutManager(layoutManager);
    }


    private void getData() {
        trasResultList = new SampleDataClass().getListData();
        TransactionListAdapter adapter = new TransactionListAdapter(context, trasResultList);
        transactionsHistoryViews.setAdapter(adapter);
    }
}
