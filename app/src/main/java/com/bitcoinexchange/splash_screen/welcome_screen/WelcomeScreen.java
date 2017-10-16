package com.bitcoinexchange.splash_screen.welcome_screen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.login_screen.LoginScreen;
import com.bitcoinexchange.splash_screen.signup_screen.SignupScreen;
import com.bitcoinexchange.splash_screen.utils.Utilities;



public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilities.setStatusBarGradiant(WelcomeScreen.this);
        setContentView(R.layout.activity_welcome_screen);

        viewInitialisation();
    }

    private void viewInitialisation() {
        context = this;

        findViewById(R.id.loginNavButton).setOnClickListener(this);
        findViewById(R.id.signupNavButton).setOnClickListener(this);

        findViewById(R.id.aboutUsNav).setOnClickListener(this);
        findViewById(R.id.helpNav).setOnClickListener(this);
        findViewById(R.id.tncNav).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginNavButton:
                Intent loginIntent = new Intent(context, LoginScreen.class);
                startActivity(loginIntent);
                break;

            case R.id.signupNavButton:
                Intent signupIntent = new Intent(context, SignupScreen.class);
                startActivity(signupIntent);
                break;

            case R.id.aboutUsNav:
                Utilities.openLinkInBrowser(context, "http://www.google.com");
                break;

            case R.id.helpNav:
                Utilities.openLinkInBrowser(context, "http://www.google.com");
                break;

            case R.id.tncNav:
                Utilities.openLinkInBrowser(context, "http://www.google.com");
                break;
        }
    }


}
