package com.example.augur.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.augur.R;
import com.example.augur.databinding.ActivityRisultatoBinding;
import com.example.augur.databinding.ActivityScommessaBinding;

public class Risultato extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityRisultatoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRisultatoBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_risultato);
        final Button logoutButton = binding.bottonelogout;

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                //Qui fare logout, per ora rimane qui
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                finish();
            }
        });
    }
}