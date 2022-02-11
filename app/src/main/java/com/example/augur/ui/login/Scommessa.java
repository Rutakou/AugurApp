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

import com.example.augur.R;
import com.example.augur.databinding.ActivityLoginBinding;
import com.example.augur.databinding.ActivityScommessaBinding;
import com.google.android.material.snackbar.Snackbar;

public class Scommessa extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityScommessaBinding binding;
    private TextView uno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScommessaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

    }
}