package com.bitcoinexchange.splash_screen.send_tpc_to_other_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bitcoinexchange.R;



public class SendTPCtoOtherScreen extends AppCompatActivity {

    private TextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tpc_to_other);

        viewInitialisation();
    }

    private void viewInitialisation() {
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);

        toolbarTitle.setText(getString(R.string.sendtpc));
    }
}
