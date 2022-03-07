package com.example.augur.ui.login;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.augur.R;
import com.example.augur.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private Bundle extras;
    private String token = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = new Intent(LoginActivity.this, Scommessa.class);
        extras = new Bundle();

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.em;
        final EditText passwordEditText = binding.pass;
        final Button loginButton = binding.button;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());

                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());

                //Richiesta del token al server Keycloak
                final String url = "http://192.168.1.249:8080/realms/augur-application/protocol/openid-connect/token";

                AndroidNetworking.post(url)
                        .addHeaders("Content-Type","application/x-www-form-urlencoded")
                        .addBodyParameter("grant_type","password")
                        .addBodyParameter("client_id","augur-application-client")
                        .addBodyParameter("username", usernameEditText.getText().toString())
                        .addBodyParameter("password", passwordEditText.getText().toString())
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("Risposta ricevuta");
                                try {
                                    token = response.get("access_token").toString();
                                    //Boundle da trasferire
                                    extras.putString("access_token", token);
                                    if(token!=null){
                                        //trasferimento boundle all'intent
                                        i.putExtras(extras);
                                        startActivity(i);
                                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                System.out.println("Si e' verificato un errore");
                                System.out.println(error);
                                if (error.getErrorCode() != 0) {
                                    // received error from server
                                    // error.getErrorCode() - the error code from server
                                    // error.getErrorBody() - the error body from server
                                    // error.getErrorDetail() - just an error detail
                                    Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                                    Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                    // get parsed error object (If ApiError is your class)
                                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                                } else {
                                    // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                }
                            }
                        });
            }
        });
    }

}