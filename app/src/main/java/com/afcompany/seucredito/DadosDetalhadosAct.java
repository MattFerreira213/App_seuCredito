package com.afcompany.seucredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DadosDetalhadosAct extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtCpf;
    private TextView txtIdade;
    private TextView txtRendaMensal;
    private TextView txtPagamento;
    private TextView txtValoEmprestimo;
    private TextView txtTaxa;
    private TextView txtParcelas;
    private TextView txtValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_detalhados);

        txtNome = findViewById(R.id.txt_view_nome);
        txtCpf = findViewById(R.id.txt_view_cpf);
        txtIdade = findViewById(R.id.txt_view_idade);
        txtRendaMensal = findViewById(R.id.txt_view_renda_mensal);
        txtPagamento = findViewById(R.id.txt_pagamento);
        txtValoEmprestimo = findViewById(R.id.txt_view_emprestimo);
        txtTaxa = findViewById(R.id.txt_taxa);
        txtParcelas = findViewById(R.id.txt_view_parcelas);
        txtValorTotal = findViewById(R.id.txt_total);

        SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
        txtNome.setText(dados.getString("nome", "Dados não encontrado"));
        txtCpf.setText(dados.getString("cpf", "Dados não encontrado"));
        txtIdade.setText(dados.getString("idade", "Dados não encontrado"));
        txtRendaMensal.setText(dados.getString("rendaMensal", "Dados não encontrado"));
        txtValoEmprestimo.setText(dados.getString("valorEmprestimo", "Dados não encontrado"));
        txtParcelas.setText(dados.getString("parcelas", "Dados não encontrado"));
        txtPagamento.setText(dados.getString("pagamento", "Dados não encontrado"));
        txtTaxa.setText(dados.getString("taxa", "Dados não encontrado"));
        txtValorTotal.setText(dados.getString("valorTotal", "Dados não encontrado"));

        ImageButton btnVoltar = findViewById(R.id.btn_voltar5);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DadosDetalhadosAct.this, PrimeiraOpcaoPagamentoAct.class);
                startActivity(in);
            }
        });

        Button btnConfirmar = findViewById(R.id.btn_confirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(DadosDetalhadosAct.this, MainActivity.class);
                startActivity(in);

                SharedPreferences dados = getSharedPreferences("apagarDadso", MODE_PRIVATE);
                SharedPreferences.Editor apagar = dados.edit();

                apagar.remove("nome");
                apagar.remove("cpf");
                apagar.remove("idade");
                apagar.remove("rendaMensal");
                apagar.remove("valorEmprestimo");
                apagar.remove("parcelas");
                apagar.remove("pagamento");
                apagar.remove("taxa");
                apagar.remove("valorTotal");
                apagar.apply();
            }
        });

        Button btnCancelar = findViewById(R.id.btn_cancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DadosDetalhadosAct.this, MainActivity.class);
                startActivity(in);

                SharedPreferences dados = getSharedPreferences("apagarDadso", MODE_PRIVATE);
                SharedPreferences.Editor apagar = dados.edit();

                apagar.remove("nome");
                apagar.remove("cpf");
                apagar.remove("idade");
                apagar.remove("rendaMensal");
                apagar.remove("valorEmprestimo");
                apagar.remove("parcelas");
                apagar.remove("pagamento");
                apagar.remove("taxa");
                apagar.remove("valorTotal");
                apagar.apply();
            }
        });
    }
}