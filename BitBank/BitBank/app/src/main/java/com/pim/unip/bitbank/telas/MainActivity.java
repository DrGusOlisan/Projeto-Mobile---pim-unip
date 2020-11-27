package com.pim.unip.bitbank.telas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pim.unip.bitbank.R;

import static com.pim.unip.bitbank.uteis.ClienteUteis.limparUsuarioLogado;

public class MainActivity extends AppCompatActivity {

    private ImageView imgVoltar;
    private Button btn;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = MainActivity.this;

        imgVoltar = findViewById(R.id.imgVoltar);
        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        imgVoltar.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Atenção!")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("Deseja sair do app?")
                    .setPositiveButton("SIM", (dialogInterface, i) -> {
                        limparUsuarioLogado(context);
                        startActivity(new Intent(context, LoginActivity.class));
                        finish();
                    })
                    .setNegativeButton("NÃO", (dialogInterface, i) -> { });

            alert.create();
            alert.show();
        });

        String msg = "App em desenvolvimento";

        btn.setOnClickListener(view -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
        btn1.setOnClickListener(view -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
        btn2.setOnClickListener(view -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
        btn3.setOnClickListener(view -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
        btn4.setOnClickListener(view -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
    }


    @Override
    public void onBackPressed() {

    }
}