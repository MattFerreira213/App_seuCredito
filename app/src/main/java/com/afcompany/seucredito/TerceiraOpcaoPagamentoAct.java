package com.afcompany.seucredito;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.math.BigDecimal;

import static android.widget.Toast.LENGTH_SHORT;

public class TerceiraOpcaoPagamentoAct extends AppCompatActivity {

    private EditText edtParcelas;
    private TextView txtOpcao1;
    private TextView txtOpcao2;
    private TextView txtOpcao3;
    private TextView txtOpcao4;

    private Button btnOpcoesParcelas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceira_opcao_pagamento);

        Toolbar();

        edtParcelas = findViewById(R.id.edt_parcelas);

        txtOpcao1 = findViewById(R.id.txt_opcao1);
        txtOpcao2 = findViewById(R.id.txt_opcao2);
        txtOpcao3 = findViewById(R.id.txt_opcao3);
        txtOpcao4 = findViewById(R.id.txt_opcao4);

        txtOpcao1.setVisibility(View.GONE);
        txtOpcao2.setVisibility(View.GONE);
        txtOpcao3.setVisibility(View.GONE);
        txtOpcao4.setVisibility(View.GONE);

        btnOpcoesParcelas = findViewById(R.id.btn_opcoes_pagamento);
        btnOpcoesParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtParcelas.getText().toString().isEmpty()) {
                    Toast.makeText(TerceiraOpcaoPagamentoAct.this, R.string.error_msg3, LENGTH_SHORT).show();
                    return;
                }

                String sParcelas = edtParcelas.getText().toString();
                String sValorEmprestimo = getIntent().getStringExtra("valorEmprestimo");
                int parcelas = Integer.parseInt(sParcelas);
                double valorEmprestimo = Double.parseDouble(sValorEmprestimo);
                double valorFinal;

                if (parcelas >= 1 && parcelas <= 12) {
                    if (parcelas == 1) {
                        valorFinal = (valorEmprestimo * 0.035) + valorEmprestimo;

                        BigDecimal pagamento = BigDecimal.valueOf(valorFinal);
                        pagamento = pagamento.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                        txtOpcao1.setText("R$" + pagamento);
                        txtOpcao1.setVisibility(View.VISIBLE);

                        txtOpcao2.setVisibility(View.GONE);
                        txtOpcao3.setVisibility(View.GONE);
                        txtOpcao4.setVisibility(View.GONE);
                    } else if (parcelas == 2) {
                        valorFinal = (valorEmprestimo * 2 * 0.035) + valorEmprestimo;

                        BigDecimal pagamento = BigDecimal.valueOf(valorFinal / 2);
                        pagamento = pagamento.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                        txtOpcao1.setText("2x de R$" + pagamento);
                        txtOpcao1.setVisibility(View.VISIBLE);

                        txtOpcao2.setVisibility(View.GONE);
                        txtOpcao3.setVisibility(View.GONE);
                        txtOpcao4.setVisibility(View.GONE);
                    } else {
                        valorFinal = (valorEmprestimo * parcelas * 0.035) + valorEmprestimo;

                        BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas);
                        BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.1)) / (parcelas - 1));
                        BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1));
                        BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1));

                        pagamentoOpcao1 = pagamentoOpcao1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        pagamentoOpcao2 = pagamentoOpcao2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        pagamentoOpcao3 = pagamentoOpcao3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        pagamentoOpcao4 = pagamentoOpcao4.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                        BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.1);
                        BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.2);
                        BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3);

                        primeiroPagamentoOpcao2 = primeiroPagamentoOpcao2.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        primeiroPagamentoOpcao3 = primeiroPagamentoOpcao3.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        primeiroPagamentoOpcao4 = primeiroPagamentoOpcao4.setScale(2,BigDecimal.ROUND_HALF_EVEN);

                        txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                        txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                        txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                        txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                        txtOpcao1.setVisibility(View.VISIBLE);
                        txtOpcao2.setVisibility(View.VISIBLE);
                        txtOpcao3.setVisibility(View.VISIBLE);
                        txtOpcao4.setVisibility(View.VISIBLE);
                    }
                } else if(parcelas > 12 && parcelas <= 36){
                    valorFinal = (valorEmprestimo * parcelas * 0.042) + valorEmprestimo;

                    BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas);
                    BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.1)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1));

                    pagamentoOpcao1 = pagamentoOpcao1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao2 = pagamentoOpcao2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao3 = pagamentoOpcao3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao4 = pagamentoOpcao4.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.1);
                    BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.2);
                    BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3);

                    primeiroPagamentoOpcao2 = primeiroPagamentoOpcao2.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao3 = primeiroPagamentoOpcao3.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao4 = primeiroPagamentoOpcao4.setScale(2,BigDecimal.ROUND_HALF_EVEN);

                    txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                    txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                    txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                    txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                    txtOpcao1.setVisibility(View.VISIBLE);
                    txtOpcao2.setVisibility(View.VISIBLE);
                    txtOpcao3.setVisibility(View.VISIBLE);
                    txtOpcao4.setVisibility(View.VISIBLE);
                } else if( parcelas > 36){
                    valorFinal = (valorEmprestimo * parcelas * 0.05) + valorEmprestimo;

                    BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas);
                    BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.1)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1));

                    pagamentoOpcao1 = pagamentoOpcao1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao2 = pagamentoOpcao2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao3 = pagamentoOpcao3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao4 = pagamentoOpcao4.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.1);
                    BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.2);
                    BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3);

                    primeiroPagamentoOpcao2 = primeiroPagamentoOpcao2.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao3 = primeiroPagamentoOpcao3.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao4 = primeiroPagamentoOpcao4.setScale(2,BigDecimal.ROUND_HALF_EVEN);

                    txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                    txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                    txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                    txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                    txtOpcao1.setVisibility(View.VISIBLE);
                    txtOpcao2.setVisibility(View.VISIBLE);
                    txtOpcao3.setVisibility(View.VISIBLE);
                    txtOpcao4.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void Toolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
    }
}