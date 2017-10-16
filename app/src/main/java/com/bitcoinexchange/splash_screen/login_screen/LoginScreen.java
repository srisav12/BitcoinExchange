package com.bitcoinexchange.splash_screen.login_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bitcoinexchange.R;
import com.bitcoinexchange.splash_screen.forget_password_screen.ForgetPasswordScreen;
import com.bitcoinexchange.splash_screen.forget_user_id_screen.ForgetUserIDScreen;
import com.bitcoinexchange.splash_screen.utils.AppController;
import com.bitcoinexchange.splash_screen.utils.ConnectionDetector;
import com.bitcoinexchange.splash_screen.utils.ServiceConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;



public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private ConnectionDetector cd;
    private EditText userIdET, passwordET;
    private String SessionId,id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        viewInitialisation();
    }

    private void viewInitialisation() {
        context = this;
        userIdET = (EditText) findViewById(R.id.userIdET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        cd = new ConnectionDetector(context);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.forgetPassworNav).setOnClickListener(this);
        findViewById(R.id.forgetUserIdNav).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                if (!TextUtils.isEmpty(userIdET.getText().toString())) {
                    if (!TextUtils.isEmpty(passwordET.getText().toString())) {
                        if (cd.isConnectingToInternet()) {
                            signin_request();
                        }
                    /*    Intent securityPinIntent = new Intent(context, SecurityPinLoginScreen.class);
                        startActivity(securityPinIntent);
                        finish();*/
                    } else {
                        Snackbar.make(passwordET, getString(R.string.err_password_entry), Snackbar.LENGTH_LONG).show();
                        passwordET.requestFocus();
                    }
                } else {
                    Snackbar.make(userIdET, getString(R.string.err_user_id_entry), Snackbar.LENGTH_LONG).show();
                    userIdET.requestFocus();
                }
                break;

            case R.id.forgetPassworNav:
                Intent forgetPasswordIntent = new Intent(context, ForgetPasswordScreen.class);
                startActivity(forgetPasswordIntent);
                break;

            case R.id.forgetUserIdNav:
                Intent forgetUserIdIntent = new Intent(context, ForgetUserIDScreen.class);
                startActivity(forgetUserIdIntent);
                break;
        }
    }

    private void signin_request() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ServiceConstants.baseurl + ServiceConstants.signin, create_signinrequest(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("loginresponse", response.toString());
                otpsend();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
            }
        }) {
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {

                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));


                    String header_response = String.valueOf(response.headers.values());
                    int index1 = header_response.indexOf("ASP.NET_SessionId=");
                    int index2 = header_response.indexOf("; path");
                    id = header_response.substring(header_response.indexOf("=") + 1, header_response.indexOf(";"));

                    //Log.e(Utils.tag, "error is : " + index1 + "::" + index2);

                    SessionId = header_response.substring(index1, index2);
                    Log.e("session_id", SessionId);
                    Log.e("session",id);
                    // this is your session id put it in the variable and then you can use it any where you want to

                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));

                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void otpsend() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", userIdET.getText().toString());
            jsonObject.put("serviceUserName", "sid");
            jsonObject.put("servicePassword", "spwd");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ServiceConstants.baseurl +"getAllTranInfo", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("otp", response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Cookie", SessionId);
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }
    private JSONObject create_signinrequest() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", userIdET.getText().toString());
            jsonObject.put("pwd", passwordET.getText().toString());
            jsonObject.put("serviceUserName", "sid");
            jsonObject.put("servicePassword", "spwd");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
