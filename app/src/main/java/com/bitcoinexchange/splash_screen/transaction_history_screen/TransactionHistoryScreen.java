package com.bitcoinexchange.splash_screen.transaction_history_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bitcoinexchange.R;

/**
 * Created by Shashank Rawat on 10/11/2017.
 */

public class TransactionHistoryScreen extends AppCompatActivity {

    private TextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        viewInitialisation();
    }


    private void viewInitialisation() {
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);

        toolbarTitle.setText(getString(R.string.transaction_history));
    }
}
