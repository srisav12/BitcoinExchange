package com.bitcoinexchange.splash_screen.verify_otp_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bitcoinexchange.R;



public class VerifyOTPScreen extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private EditText otpCodeET;
    private String otpCodeValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp_screen);

        viewInitialisation();

        title.setText(getString(R.string.verify_otp));
    }

    private void viewInitialisation() {
        title = (TextView) findViewById(R.id.toolbarTitle);
        otpCodeET = (EditText) findViewById(R.id.otpCodeET);

        findViewById(R.id.app_bar).setBackgroundColor(getResources().getColor(R.color.login_screen_toolbar_color));

        findViewById(R.id.verifyButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.verifyButton:
                otpCodeValue = otpCodeET.getText().toString();
                if(!TextUtils.isEmpty(otpCodeValue)){
                    setResult(RESULT_OK);
                    finish();
                }else {
                    Snackbar.make(otpCodeET, getString(R.string.err_otp_entry), Snackbar.LENGTH_LONG).show();
                }

                break;
        }
    }
}
