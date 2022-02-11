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
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class Scommessa extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityScommessaBinding binding;
    private TextView uno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScommessaBinding.inflate(getLayoutInflater());
        AndroidNetworking.initialize(getApplicationContext());

        setContentView(binding.getRoot());

        // Adding an Network Interceptor for Debugging purpose :
        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                        .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());



        final Button scommettiButton = binding.nextButton;


        scommettiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorefuturo = binding.vf.getText().toString();
                String quantitascommessa = binding.qs.getText().toString();
                String indirizzoportafoglio = binding.ipp.getText().toString();
                DatiScommessa recap = new DatiScommessa(valorefuturo,quantitascommessa,indirizzoportafoglio);

                startActivity(new Intent(getApplicationContext(), Risultato.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                finish();
            }
        });

        System.out.println("Sto inviando la richiesta");
        final String url = "http://10.0.2.2:3000/";

        AndroidNetworking.get(url).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Risposta ricevuta");
                        System.out.println(response);

                    }
                    @Override
                    public void onError(ANError error) {
                        System.out.println("Si e' verificato un errore");
                        System.out.println(error);
                    }
                });

    }
}