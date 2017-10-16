package com.bitcoinexchange.splash_screen.forget_password_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.utils.Utilities;
import com.bitcoinexchange.splash_screen.verify_otp_screen.VerifyOTPScreen;



public class ForgetPasswordScreen extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView title;
    private EditText recoveryOptionET;
    private RadioButton byEmailRB, byUserIdRB;
    private String recoveryOptionValue;
    private TextInputLayout recOptTI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_screen);

        viewInitialisation();

        title.setText(getString(R.string.forget_password));

        byEmailRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    recOptTI.setHint(getString(R.string.email_id));
                }
            }
        });

        byUserIdRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    recOptTI.setHint(getString(R.string.user_id));
                }
            }
        });

    }

    private void viewInitialisation() {
        context = this;
        title = (TextView) findViewById(R.id.toolbarTitle);
        recoveryOptionET = (EditText) findViewById(R.id.recoveryOptionET);
        byEmailRB = (RadioButton) findViewById(R.id.byEmailRB);
        byUserIdRB = (RadioButton) findViewById(R.id.byUserIdRB);
        recOptTI = (TextInputLayout) findViewById(R.id.recoveryOptionTL);

        findViewById(R.id.app_bar).setBackgroundColor(getResources().getColor(R.color.login_screen_toolbar_color));

        findViewById(R.id.submitButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submitButton:
                recoveryOptionValue = recoveryOptionET.getText().toString();
                if(!TextUtils.isEmpty(recoveryOptionValue)){
                    Intent verifyOtpIntent = new Intent(context, VerifyOTPScreen.class);
                    if(byEmailRB.isChecked()){
                        if(Utilities.validate(recoveryOptionValue)){
                            startActivityForResult(verifyOtpIntent, 101);
                        }else {
                            Snackbar.make(recoveryOptionET, getString(R.string.err_email_validation), Snackbar.LENGTH_LONG).show();
                        }
                    }else {
                        startActivityForResult(verifyOtpIntent, 101);
                    }
                }else {
                    String errorText = null;
                    if(byEmailRB.isChecked()){
                        errorText = getString(R.string.err_email_entry);
                    }else if(byUserIdRB.isChecked()){
                        errorText = getString(R.string.err_user_id_entry);
                    }
                    if (errorText != null) {
                        Snackbar.make(recoveryOptionET, errorText, Snackbar.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                finish();
            }
        }
    }
}
