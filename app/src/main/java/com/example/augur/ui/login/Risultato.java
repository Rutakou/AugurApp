package com.example.augur.ui.login;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.augur.R;
import com.example.augur.databinding.ActivityRisultatoBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class Risultato extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityRisultatoBinding binding;
    private TextView c;
    private TextView c2;
    private TextView c3;
    private Bundle extras;
    private String value;
    private String value1;
    private String access_token;
    private String messaggio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRisultatoBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_risultato);
        final Button logoutButton = binding.bottonelogout;

        extras = getIntent().getExtras();
        c = findViewById(R.id.complimenti);
        access_token = extras.getString("access_token");

        if (extras != null) {
            value = extras.getString("risposta");
            value1 = extras.getString("valorefuturo");
        }
        c2 = findViewById(R.id.scommessafatta);
        System.out.print(value1);
        c3 = findViewById(R.id.scommessafattaora);
        c2.setText(value1);
        c3.setText(value);

        //Invio al server scommessa e valore futuro. Il messaggio restituito dal server in risposta sarà la stringa da assegnare alla TextView
        final String url1 = "http://192.168.1.249:4000/risultato";

        AndroidNetworking.post(url1)
                .addHeaders("Authorization", "Bearer " + access_token)
                .addBodyParameter("scommessa", value1)
                .addBodyParameter("valorefuturo", value)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Risposta ricevuta: " + response.toString());
                        try {
                            messaggio = response.get("message").toString();
                            System.out.println(messaggio);
                            c.setText(messaggio);
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
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Token salvato nel bundle. Logout, imposto il token a null
                Bundle bundle = new Bundle();
                Intent i = new Intent(Risultato.this, LoginActivity.class);
                bundle.putString("access_token", null);
                startActivity(i);
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                finish();
            }
        });
    }
}