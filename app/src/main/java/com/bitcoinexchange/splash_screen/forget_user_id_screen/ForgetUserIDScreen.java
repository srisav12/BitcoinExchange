package com.bitcoinexchange.splash_screen.forget_user_id_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.utils.Utilities;
import com.bitcoinexchange.splash_screen.verify_otp_screen.VerifyOTPScreen;



public class ForgetUserIDScreen extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView title;
    private EditText userEmailET;
    private String userEmailValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_user_id_screen);

        viewInitialisation();

        title.setText(getString(R.string.forget_user_id));
    }

    private void viewInitialisation() {
        context = this;
        title = (TextView) findViewById(R.id.toolbarTitle);
        userEmailET = (EditText) findViewById(R.id.userEmailET);

        findViewById(R.id.app_bar).setBackgroundColor(getResources().getColor(R.color.login_screen_toolbar_color));

        findViewById(R.id.submitButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submitButton:
                userEmailValue = userEmailET.getText().toString();

                if(!TextUtils.isEmpty(userEmailValue)){
                    if(Utilities.validate(userEmailValue)){
                        Intent verifyOtpIntent = new Intent(context, VerifyOTPScreen.class);
                        startActivityForResult(verifyOtpIntent, 101);
                    }else {
                        Snackbar.make(userEmailET, getString(R.string.err_email_validation), Snackbar.LENGTH_LONG).show();
                        userEmailET.requestFocus();
                    }
                }else {
                    Snackbar.make(userEmailET, getString(R.string.err_email_entry), Snackbar.LENGTH_LONG).show();
                    userEmailET.requestFocus();
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
