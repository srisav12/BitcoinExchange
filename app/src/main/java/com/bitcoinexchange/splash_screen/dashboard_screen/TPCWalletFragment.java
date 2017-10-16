package com.bitcoinexchange.splash_screen.dashboard_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.send_tpc_to_other_screen.SendTPCtoOtherScreen;
import com.bitcoinexchange.splash_screen.utils.Utilities;

/**
 * Created by Shashank Rawat on 10/11/2017.
 */

public class TPCWalletFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private TextView tcpAddressText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        if(container == null){
            return null;
        }else {
            view = inflater.inflate(R.layout.fragment_tpc_wallet, container, false);
            return view;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        tcpAddressText = (TextView) view.findViewById(R.id.tcpAddressTV);
        view.findViewById(R.id.ivbarcode).setOnClickListener(this);
        view.findViewById(R.id.ivshare).setOnClickListener(this);
        view.findViewById(R.id.btrecievetpc).setOnClickListener(this);
        view.findViewById(R.id.btsendtpc).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ivbarcode:
                Utilities.shareTextUrl(context, getString(R.string.mytpc_address), tcpAddressText.getText().toString());
                break;

            case R.id.ivshare:
                Utilities.shareTextUrl(context, getString(R.string.mytpc_address), tcpAddressText.getText().toString());
                break;

            case R.id.btrecievetpc:
                Utilities.shareTextUrl(context, getString(R.string.mytpc_address), tcpAddressText.getText().toString());
                break;

            case R.id.btsendtpc:
                Intent sendTPCIntent = new Intent(context, SendTPCtoOtherScreen.class);
                startActivity(sendTPCIntent);
                break;
        }
    }
}
