package com.afcompany.seucredito;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.*;

public class SolicitacaoActivity extends AppCompatActivity {

    private static EditText edtNome;
    private static EditText edtCpf;
    private static EditText edtIdade;
    private static EditText edtRendaMensal;
    private static EditText edtValorEmprestimo;

    private static final double SALARIO_MINIMO = 1100.00;
    private static final int IDADE = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

        //PEGANDO AS INFORMAÇÕES PASSADAS DE ACORDO COM O ID
        edtNome = findViewById(R.id.edt_nome);
        edtCpf = findViewById(R.id.edt_cpf);
        edtIdade = findViewById(R.id.edt_idade);
        edtRendaMensal = findViewById(R.id.edt_renda_mensal);
        edtValorEmprestimo = findViewById(R.id.edt_valor_emprestimo);

        ImageButton btnVoltar = findViewById(R.id.btn_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SolicitacaoActivity.this, MainActivity.class);
                startActivity(in);
            }
        });

        Button buttonOfertas = findViewById(R.id.btn_oferta_credito);
        buttonOfertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //FAZENDO A VALIDAÇÃO DOS CAMPOS
                if (!validate()) {
                    Toast.makeText(SolicitacaoActivity.this, R.string.error_msg2, LENGTH_SHORT).show();
                    return;
                }

                //CONVERTENDO OS VALORES PARA REALIZAR A VERIFICAÇÃO DOS CRITÉRIOS PARA SOLICITAÇÃO DO EMPRÉSTIMO
                //OBS: TEM UM ARQUIVO EXPLICANDO TODA A REGRA DE NEGÓCIO E OS CRITÉRIOS PARA SOLICITAÇÃO DO EMPRÉSTIMO
                double valorEmprestimo = Double.parseDouble(edtValorEmprestimo.getText().toString());
                double rendaMesnal = Double.parseDouble(edtRendaMensal.getText().toString());
                int idade = Integer.parseInt(edtIdade.getText().toString());

                //VERIFICAÇÃO COM BASE NOS CRITÉRIOS PARA SOLICITAÇÃO DO EMPRÉSTIMO
                if (idade >= IDADE && rendaMesnal >= SALARIO_MINIMO) {

                    //LIMITE DE CRÉDITO É DE ATÉ 5x A RENDA MENSAL
                    if (valorEmprestimo >= 150 && valorEmprestimo <= 5 * rendaMesnal) {
                        Intent in = new Intent(SolicitacaoActivity.this, OpcaoPagamentoAct.class);
                        in.putExtra("valorEmprestimo", edtValorEmprestimo.getText().toString());
                        startActivity(in);

                        GuardaDados();
                    } else {
                        Toast.makeText(SolicitacaoActivity.this, R.string.msg_negado, LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(SolicitacaoActivity.this, R.string.error_msg, LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
    //OBS: ESSES NÃO ESTÃO SENDO PERSISTIDOS EM UMA BASE DE DADOS, APENAS GUARDADOS PARA SER UTILIZADOS NO PRÓPRIO APP
    private void GuardaDados() {
        SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
        SharedPreferences.Editor salvar = dados.edit();

        salvar.putString("nome", edtNome.getText().toString());
        salvar.putString("cpf", edtCpf.getText().toString());
        salvar.putString("idade", edtIdade.getText().toString());
        salvar.putString("rendaMensal", edtRendaMensal.getText().toString());
        salvar.putString("valorEmprestimo", edtValorEmprestimo.getText().toString());
        salvar.apply();
    }

    //MÉTODO QUE FAZ A VALIDAÇÃO DOS CAMPOS PARA NÃO ACEITAR VALORES NULOS
    private boolean validate() {
        return (!edtIdade.getText().toString().isEmpty() && !edtRendaMensal.getText().toString().isEmpty()
                && !edtNome.getText().toString().isEmpty() && !edtCpf.getText().toString().isEmpty()
                && !edtValorEmprestimo.getText().toString().isEmpty());
    }
}
