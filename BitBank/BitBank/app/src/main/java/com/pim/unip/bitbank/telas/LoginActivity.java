package com.pim.unip.bitbank.telas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pim.unip.bitbank.Cliente;
import com.pim.unip.bitbank.R;
import com.pim.unip.bitbank.uteis.Mascara;

import static com.pim.unip.bitbank.uteis.ClienteUteis.recuperarLogin;
import static com.pim.unip.bitbank.uteis.ClienteUteis.recuperarUsuarioLogado;
import static com.pim.unip.bitbank.uteis.ClienteUteis.salvarUsuarioLogado;

public class LoginActivity extends AppCompatActivity {

    private ConstraintLayout cl1;
    private LinearLayout ll2;
    private Button btnEntrar;
    private Button btnConta;
    private Button btnLogin;
    private EditText editCpf;
    private EditText editSenha;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;

        verificarConectividadeNet();

        cl1 = findViewById(R.id.cl1);
        ll2 = findViewById(R.id.ll2);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnConta = findViewById(R.id.btnConta);
        btnLogin = findViewById(R.id.btnLogin);
        editCpf = findViewById(R.id.editCpf);
        editSenha = findViewById(R.id.editSenha);

        if (!recuperarUsuarioLogado(context).equals("-1")) {
            startActivity(new Intent(context, MainActivity.class));
            finish();
        }

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCpf.getText().clear();
                editSenha.getText().clear();
                cl1.setVisibility(View.GONE);
                ll2.setVisibility(View.VISIBLE);
                setTitle("Login");
            }
        });

        btnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CriarContaActivity.class);
                startActivity(intent);
            }
        });

        editCpf.addTextChangedListener(Mascara.mask(editCpf, Mascara.FORMAT_CPF));

        btnLogin.setOnClickListener(view -> {
            String txtCpf = editCpf.getText().toString();
            String txtSenha = editSenha.getText().toString();

            if (!txtCpf.isEmpty()) {
                if (!txtSenha.isEmpty()) {
                    Cliente cliente = new Cliente(txtCpf, txtSenha);
                    if (recuperarLogin(cliente, context)) {
                        Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        salvarUsuarioLogado(cliente, context);

                        startActivity(new Intent(context, MainActivity.class));
                        finish();

                    } else {
                        Toast.makeText(context, "CPF ou senha errado!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context,
                            "Preencha a senha!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context,
                        "Preencha o CPF!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void verificarConectividadeNet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();

        if (net != null && net.isConnectedOrConnecting()) {

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Sem conexão com a internet")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("Precisamos de conexão com a internet. Por favor, ative e tente novamente")
                    .setCancelable(false)
                    .setPositiveButton("TENTAR NOVAMENTE", (dialogInterface, i) -> verificarConectividadeNet())
                    .setNegativeButton("FECHAR", (dialogInterface, i) -> {
                        Toast.makeText(getApplicationContext(),
                                "Tente mais tarde",
                                Toast.LENGTH_LONG).show();
                        finishAffinity();
                    });

            alert.create();
            alert.show();
        }
    }


    @Override
    public void onBackPressed() {
        cl1.setVisibility(View.VISIBLE);
        ll2.setVisibility(View.GONE);
        setTitle("Bit Bank");
    }
}