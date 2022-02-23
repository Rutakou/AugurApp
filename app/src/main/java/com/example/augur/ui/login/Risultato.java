package com.example.augur.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.augur.R;
import com.example.augur.databinding.ActivityRisultatoBinding;

public class Risultato extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityRisultatoBinding binding;
    private TextView c;
    private TextView c2;
    private TextView c3;
    private Bundle extras;
    private String value;
    private String value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRisultatoBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_risultato);
        final Button logoutButton = binding.bottonelogout;
        extras = getIntent().getExtras();
        c = findViewById(R.id.complimenti);
        if (extras != null) {
            value = extras.getString("risposta");
            value1 = extras.getString("valorefuturo");
            //The key argument here must match that used in the other activity
        }
        c2 = findViewById(R.id.scommessafatta);
        System.out.print(value1);
        c3 = findViewById(R.id.scommessafattaora);
        c2.setText(value1);
        c3.setText(value);
        determinaRisultato(value1,value);

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
    //token è da salvare dall'altra activity
    //addHeader("Bearer", token)

    //E' da fare su node!!
    public void determinaRisultato(String uno, String due){
        if(uno.equals(due)==true){
            c.setText("Complimenti!");
        }else{
            c.setText("Hai perso!");
        }
    }

}