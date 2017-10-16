package com.bitcoinexchange.splash_screen.security_pin_login_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.dashboard_screen.DashboardScreen;

import java.util.ArrayList;
import java.util.List;



public class SecurityPinLoginScreen extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private List<String> mListPin;

    private ImageView mPin1;
    private ImageView mPin2;
    private ImageView mPin3;
    private ImageView mPin4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pin_screen);

        viewInitialisation();
    }


    private void viewInitialisation() {
        context = this;
        mListPin = new ArrayList<>();

        mPin1 = (ImageView) findViewById(R.id.textView_pin1);
        mPin2 = (ImageView) findViewById(R.id.textView_pin2);
        mPin3 = (ImageView) findViewById(R.id.textView_pin3);
        mPin4 = (ImageView) findViewById(R.id.textView_pin4);
        TextView pinHeaderText = (TextView) findViewById(R.id.pinHeaderLine);
        TextView doneButton = (TextView) findViewById(R.id.frameLayout_next);

        pinHeaderText.setText(getString(R.string.login_pin_desc));
        doneButton.setText(getString(R.string.done));

        findViewById(R.id.frameLayout_number1).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number2).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number3).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number4).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number5).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number6).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number7).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number8).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number9).setOnClickListener(onDigitClick());
        findViewById(R.id.frameLayout_number0).setOnClickListener(onDigitClick());

        findViewById(R.id.frameLayout_close_keyboard).setOnClickListener(this);
        findViewById(R.id.frameLayout_deletePin).setOnClickListener(this);
        doneButton.setOnClickListener(this);
    }


    /**
     * this method used to save the digit clicked to list
     */
    private View.OnClickListener onDigitClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView digitView = (TextView) view;
                if(mListPin.size() <=3){
                    mListPin.add(digitView.getText().toString());
                    conditioningPinButton();
                }
            }
        };
    }



    /**
     * this method used to set the background of indicator pin when has filled by a digit
     */
    private void conditioningPinButton(){
        if(mListPin.size()==1){
            mPin1.setImageResource(R.drawable.selected_item);
        }else if(mListPin.size()==2){
            mPin2.setImageResource(R.drawable.selected_item);
        }else if(mListPin.size()==3){
            mPin3.setImageResource(R.drawable.selected_item);
        }else if(mListPin.size()==4){
            mPin4.setImageResource(R.drawable.selected_item);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.frameLayout_close_keyboard:
                onBackPressed();
                break;

            case R.id.frameLayout_deletePin:
                if(mListPin.size() != 0){
                    mListPin.remove(mListPin.size()-1);
                    if(mListPin.size()==3){
                        mPin4.setImageResource(R.drawable.nonselected_item);
                    }else if(mListPin.size()==2){
                        mPin3.setImageResource(R.drawable.nonselected_item);
                    }else if(mListPin.size()==1){
                        mPin2.setImageResource(R.drawable.nonselected_item);
                    }else if(mListPin.size()==0){
                        mPin1.setImageResource(R.drawable.nonselected_item);
                    }
                }
                break;


            case R.id.frameLayout_next:
                if(mListPin.size()<4){
                    Snackbar.make(v, R.string.err_complete_your_pin, Snackbar.LENGTH_LONG).show();
                    return;
                }else {
                    Intent dashboardIntent = new Intent(context, DashboardScreen.class);
                    startActivity(dashboardIntent);
                    finish();
                }
                break;
        }
    }
}
