package com.bitcoinexchange.splash_screen.change_password_screen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bitcoinexchange.R;

import static com.bitcoinexchange.R.id.toolbarTitle;

/**
 * Created by Shashank Rawat on 10/11/2017.
 */

public class ChangePasswordScreen extends AppCompatActivity implements View.OnClickListener {

    private TextView toolbarTitle;
    private Context context;
    private EditText current_passwordET, current_transactionET, new_passwordET, confirm_new_passwordET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        viewInitialisation();
    }

    private void viewInitialisation() {
        context = this;
        current_passwordET = (EditText) findViewById(R.id.current_passwordET);
        current_transactionET = (EditText) findViewById(R.id.current_transactionET);
        new_passwordET = (EditText) findViewById(R.id.new_passwordET);
        confirm_new_passwordET = (EditText) findViewById(R.id.confirm_new_passwordET);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);

        toolbarTitle.setText(getString(R.string.change_password));

        findViewById(R.id.btsubmit).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btsubmit:
                if (!TextUtils.isEmpty(current_passwordET.getText().toString())) {
                    if (!TextUtils.isEmpty(current_transactionET.getText().toString())) {
                        if (!TextUtils.isEmpty(new_passwordET.getText().toString())) {
                            if (!TextUtils.isEmpty(confirm_new_passwordET.getText().toString())) {
                                if (new_passwordET.equals(confirm_new_passwordET)) {

                                } else {
                                    Snackbar.make(new_passwordET, getString(R.string.err_password_mismatch), Snackbar.LENGTH_LONG).show();
                                    new_passwordET.requestFocus();


                                }
                            } else {
                                Snackbar.make(confirm_new_passwordET, getString(R.string.err_confirm_password_entry), Snackbar.LENGTH_LONG).show();
                                confirm_new_passwordET.requestFocus();


                            }
                        } else {
                            Snackbar.make(new_passwordET, "Please enter new password", Snackbar.LENGTH_LONG).show();
                            new_passwordET.requestFocus();


                        }

                    } else {
                        Snackbar.make(current_transactionET, "Please enter current transaction password", Snackbar.LENGTH_LONG).show();
                        current_transactionET.requestFocus();
                    }
                } else {
                    Snackbar.make(current_passwordET, "Please enter current password.", Snackbar.LENGTH_LONG).show();
                    current_passwordET.requestFocus();
                }
                break;

        }
    }

}
