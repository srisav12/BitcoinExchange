package com.bitcoinexchange.splash_screen.security_pin_creation_screen;

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



public class SecurityPinCreationScreen extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private List<String> mListPin;
    private List<String> mListConfirmPin;

    private ImageView mPin1;
    private ImageView mPin2;
    private ImageView mPin3;
    private ImageView mPin4;
    private TextView enterPinText;
    private TextView nextButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pin_screen);

        viewInitialisation();
    }

    private void viewInitialisation() {
        context = this;
        mListPin = new ArrayList<>();
        mListConfirmPin = new ArrayList<>();

        mPin1 = (ImageView) findViewById(R.id.textView_pin1);
        mPin2 = (ImageView) findViewById(R.id.textView_pin2);
        mPin3 = (ImageView) findViewById(R.id.textView_pin3);
        mPin4 = (ImageView) findViewById(R.id.textView_pin4);
        enterPinText = (TextView) findViewById(R.id.enterPinText);
        nextButton = (TextView) findViewById(R.id.frameLayout_next);

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
        nextButton.setOnClickListener(this);
    }


    /**
     * this method used to save the digit clicked to list
     */
    private View.OnClickListener onDigitClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView digitView = (TextView) view;
                if(nextButton.getText().toString().equalsIgnoreCase(getString(R.string.next))) {
                    if (mListPin.size() <= 3) {
                        mListPin.add(digitView.getText().toString());
                        conditioningPinButton(mListPin.size());
                    }
                }else if (nextButton.getText().toString().equalsIgnoreCase(getString(R.string.done))) {
                    if(mListConfirmPin.size() <= 3) {
                        mListConfirmPin.add(digitView.getText().toString());
                        conditioningPinButton(mListConfirmPin.size());
                    }
                }
            }
        };
    }



    /**
     * this method used to set the background of indicator pin when has filled by a digit
     */
    private void conditioningPinButton(int size){
        if(size==1){
            mPin1.setImageResource(R.drawable.selected_item);
        }else if(size==2){
            mPin2.setImageResource(R.drawable.selected_item);
        }else if(size==3){
            mPin3.setImageResource(R.drawable.selected_item);
        }else if(size==4){
            mPin4.setImageResource(R.drawable.selected_item);
        }
    }


    private void clearingPinButton(){
        mPin1.setImageResource(R.drawable.nonselected_item);
        mPin2.setImageResource(R.drawable.nonselected_item);
        mPin3.setImageResource(R.drawable.nonselected_item);
        mPin4.setImageResource(R.drawable.nonselected_item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.frameLayout_close_keyboard:
                onBackPressed();
                break;

            case R.id.frameLayout_deletePin:
                if(nextButton.getText().toString().equalsIgnoreCase(getString(R.string.next))) {
                    if (mListPin.size() != 0) {
                        mListPin.remove(mListPin.size() - 1);
                        if (mListPin.size() == 3) {
                            mPin4.setImageResource(R.drawable.nonselected_item);
                        } else if (mListPin.size() == 2) {
                            mPin3.setImageResource(R.drawable.nonselected_item);
                        } else if (mListPin.size() == 1) {
                            mPin2.setImageResource(R.drawable.nonselected_item);
                        } else if (mListPin.size() == 0) {
                            mPin1.setImageResource(R.drawable.nonselected_item);
                        }
                    }
                }else {
                    if (mListConfirmPin.size() != 0) {
                        mListConfirmPin.remove(mListConfirmPin.size() - 1);
                        if (mListConfirmPin.size() == 3) {
                            mPin4.setImageResource(R.drawable.nonselected_item);
                        } else if (mListConfirmPin.size() == 2) {
                            mPin3.setImageResource(R.drawable.nonselected_item);
                        } else if (mListConfirmPin.size() == 1) {
                            mPin2.setImageResource(R.drawable.nonselected_item);
                        } else if (mListConfirmPin.size() == 0) {
                            mPin1.setImageResource(R.drawable.nonselected_item);
                        }
                    }
                }
                break;


            case R.id.frameLayout_next:
                if(nextButton.getText().toString().equalsIgnoreCase(getString(R.string.next))){
                    if(mListPin.size()<4){
                        Snackbar.make(v, R.string.err_complete_your_pin, Snackbar.LENGTH_LONG).show();
                        return;
                    }else {
                        enterPinText.setText(getString(R.string.confirm_pin));
                        nextButton.setText(getString(R.string.done));
                        clearingPinButton();
                    }
                }else {
                    if(mListConfirmPin.size()<4){
                        Snackbar.make(v, R.string.err_complete_your_pin, Snackbar.LENGTH_LONG).show();
                        return;
                    }else {
                        boolean matched = isPinMatched();

                        if(matched) {
                            Intent dashboardIntent = new Intent(context, DashboardScreen.class);
                            startActivity(dashboardIntent);
                            finish();
                        }else {
                            Snackbar.make(v,getString(R.string.err_pin_mismatch), Snackbar.LENGTH_LONG).show();
                        }
                    }
                }

                break;
        }
    }


    private boolean isPinMatched(){
        for (int i=0; i<mListConfirmPin.size(); i++){
            if(!mListConfirmPin.get(i).equalsIgnoreCase(mListPin.get(i))){
                return false;
            }
        }
        return true;
    }
}
