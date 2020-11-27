package com.pim.unip.bitbank.telas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pim.unip.bitbank.Cliente;
import com.pim.unip.bitbank.R;
import com.pim.unip.bitbank.uteis.Mascara;

import static com.pim.unip.bitbank.uteis.ClienteUteis.salvarListaEmail;
import static com.pim.unip.bitbank.uteis.ClienteUteis.salvarUsuario;
import static com.pim.unip.bitbank.uteis.ClienteUteis.salvarUsuarioLogado;
import static com.pim.unip.bitbank.uteis.ClienteUteis.verificarCpf;
import static com.pim.unip.bitbank.uteis.ClienteUteis.verificarEmail;

public class CriarContaActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editEmailConf, editDtNasc, editCpf, editSenha;
    private Button btnCadastrar;
    private ImageView imgVoltar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        setTitle("Abrir conta");

        context = CriarContaActivity.this;

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editEmailConf = findViewById(R.id.editEmailConf);
        editDtNasc = findViewById(R.id.editDtNasc);
        editCpf = findViewById(R.id.editCpf);
        editSenha = findViewById(R.id.editSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        imgVoltar = findViewById(R.id.imgVoltar);


        editCpf.addTextChangedListener(Mascara.mask(editCpf, Mascara.FORMAT_CPF));
        editDtNasc.addTextChangedListener(Mascara.mask(editDtNasc, Mascara.NASCIMENTO));

        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();
                String emailConf = editEmailConf.getText().toString();
                String cpf = editCpf.getText().toString();
                String dtNasci = editDtNasc.getText().toString();
                String senha = editSenha.getText().toString();

                if (nome.length() == 0) {
                    Toast.makeText(context, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                } else if (email.length() == 0 || !email.contains("@")) {
                    Toast.makeText(context, "Preencha o email!", Toast.LENGTH_SHORT).show();
                } else if (emailConf.length() == 0 || !emailConf.contains("@")) {
                    Toast.makeText(context, "Preencha o email de confirmação!", Toast.LENGTH_SHORT).show();
                } else if (cpf.length() < 14) {
                    Toast.makeText(context, "Preencha o CPF!", Toast.LENGTH_SHORT).show();
                } else if (dtNasci.length() < 10) {
                    Toast.makeText(context, "Preencha a data de nascimento!", Toast.LENGTH_SHORT).show();
                } else if (senha.length() < 8) {
                    Toast.makeText(context, "A senha deve ter no mínimo 8 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                    Cliente cliente = new Cliente(cpf, dtNasci, nome, email, senha);

                    if (!email.equals(emailConf)) {
                        Toast.makeText(context, "Preencha os emails igualmente", Toast.LENGTH_LONG).show();
                    } else if (verificarCpf(cliente, context)) {
                        Toast.makeText(context, "CPF já cadastrado!", Toast.LENGTH_LONG).show();
                    } else if (verificarEmail(cliente, context)) {
                        Toast.makeText(context, "Email já cadastrado!", Toast.LENGTH_LONG).show();
                    } else {
                        salvarUsuario(cliente, context);
                        salvarListaEmail(cliente, context);
                        salvarUsuarioLogado(cliente, context);

                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, MainActivity.class));
                        finish();
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}