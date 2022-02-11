package com.example.augur.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.augur.R;
import com.example.augur.databinding.ActivityLoginBinding;
import com.example.augur.databinding.ActivityScommessaBinding;

public class Scommessa extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityScommessaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScommessaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button loginButton = binding.nextButton;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Risultato.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                finish();
            }
        });
    }
}