package com.example.augur.ui.login;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.augur.R;
import com.example.augur.databinding.ActivityLoginBinding;
import com.example.augur.databinding.ActivityScommessaBinding;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.snackbar.Snackbar;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class Scommessa extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityScommessaBinding binding;
    private TextView uno;
    private String risposta;
    private double rispostaDouble;
    private String valorefuturo;
    private String quantitascommessa;
    private String indirizzoportafoglio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScommessaBinding.inflate(getLayoutInflater());
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(binding.getRoot());
        Intent i = new Intent(Scommessa.this, Risultato.class);
        Bundle extras = new Bundle();
        // Adding an Network Interceptor for Debugging purpose :
        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                        .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        final Button scommettiButton = binding.nextButton;

        System.out.println("Sto inviando la richiesta");
        final String url = "http://10.0.2.2:3000/";

        AndroidNetworking.get(url).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Risposta ricevuta");
                        try {
                            risposta = response.get("btc_price").toString();
                            rispostaDouble = Double.parseDouble(risposta);
                            rispostaDouble = Math.round(rispostaDouble*100.0)/100.0;
                            risposta = String.valueOf(rispostaDouble);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //System.out.println(response);

                    }
                    @Override
                    public void onError(ANError error) {
                        System.out.println("Si e' verificato un errore");
                        System.out.println(error);
                    }
                });

        scommettiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorefuturo = binding.vf.getText().toString();
                quantitascommessa = binding.qs.getText().toString();
                indirizzoportafoglio = binding.ipp.getText().toString();
                extras.putString("risposta", risposta);
                extras.putString("valorefuturo", valorefuturo);
                i.putExtras(extras);
                startActivity(i);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                finish();
            }
        });


    }

}