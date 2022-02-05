package com.example.augur.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }
}