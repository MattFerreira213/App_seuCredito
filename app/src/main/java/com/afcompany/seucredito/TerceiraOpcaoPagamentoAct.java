package com.afcompany.seucredito;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        edtParcelas = findViewById(R.id.edt_view3_parcelas);

        txtOpcao1 = findViewById(R.id.txt_view3_opcao1);
        txtOpcao2 = findViewById(R.id.txt_view3_opcao2);
        txtOpcao3 = findViewById(R.id.txt_view3_opcao3);
        txtOpcao4 = findViewById(R.id.txt_view3_opcao4);

        txtOpcao1.setVisibility(View.GONE);
        txtOpcao2.setVisibility(View.GONE);
        txtOpcao3.setVisibility(View.GONE);
        txtOpcao4.setVisibility(View.GONE);

        ImageButton btnVoltar = findViewById(R.id.btn_voltar3);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, SolicitacaoActivity.class);
                startActivity(in);
            }
        });

        btnOpcoesParcelas = findViewById(R.id.btn_opcoes_pagamento_view3);
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

                        txtOpcao1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao1.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "3,5");
                                salvar.apply();
                            }
                        });

                        txtOpcao2.setVisibility(View.GONE);
                        txtOpcao3.setVisibility(View.GONE);
                        txtOpcao4.setVisibility(View.GONE);
                    } else if (parcelas == 2) {
                        valorFinal = (valorEmprestimo * 2 * 0.035) + valorEmprestimo;

                        BigDecimal pagamento = BigDecimal.valueOf(valorFinal / 2);
                        pagamento = pagamento.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                        txtOpcao1.setText("2x de R$" + pagamento);
                        txtOpcao1.setVisibility(View.VISIBLE);

                        txtOpcao1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao1.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "3,5");
                                salvar.apply();
                            }
                        });

                        txtOpcao2.setVisibility(View.GONE);
                        txtOpcao3.setVisibility(View.GONE);
                        txtOpcao4.setVisibility(View.GONE);
                    } else {
                        valorFinal = (valorEmprestimo * parcelas * 0.035) + valorEmprestimo;

                        BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas);
                        BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1));
                        BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.25)) / (parcelas - 1));
                        BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1));

                        pagamentoOpcao1 = pagamentoOpcao1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        pagamentoOpcao2 = pagamentoOpcao2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        pagamentoOpcao3 = pagamentoOpcao3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        pagamentoOpcao4 = pagamentoOpcao4.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                        BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.2);
                        BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.25);
                        BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3);

                        primeiroPagamentoOpcao2 = primeiroPagamentoOpcao2.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        primeiroPagamentoOpcao3 = primeiroPagamentoOpcao3.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        primeiroPagamentoOpcao4 = primeiroPagamentoOpcao4.setScale(2,BigDecimal.ROUND_HALF_EVEN);

                        txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                        txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                        txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                        txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                        txtOpcao1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao1.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "3,5");
                                salvar.apply();
                            }
                        });

                        txtOpcao2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao2.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "3,5");
                                salvar.apply();
                            }
                        });

                        txtOpcao3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao3.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "3,5");
                                salvar.apply();
                            }
                        });

                        txtOpcao4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao4.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "3,5");
                                salvar.apply();
                            }
                        });

                        txtOpcao1.setVisibility(View.VISIBLE);
                        txtOpcao2.setVisibility(View.VISIBLE);
                        txtOpcao3.setVisibility(View.VISIBLE);
                        txtOpcao4.setVisibility(View.VISIBLE);
                    }
                } else if(parcelas > 12 && parcelas <= 36){
                    valorFinal = (valorEmprestimo * parcelas * 0.042) + valorEmprestimo;

                    BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas);
                    BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.25)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1));

                    pagamentoOpcao1 = pagamentoOpcao1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao2 = pagamentoOpcao2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao3 = pagamentoOpcao3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao4 = pagamentoOpcao4.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.2);
                    BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.25);
                    BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3);

                    primeiroPagamentoOpcao2 = primeiroPagamentoOpcao2.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao3 = primeiroPagamentoOpcao3.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao4 = primeiroPagamentoOpcao4.setScale(2,BigDecimal.ROUND_HALF_EVEN);

                    txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                    txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                    txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                    txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                    txtOpcao1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "4,2");
                            salvar.apply();
                        }
                    });

                    txtOpcao2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "4,2");
                            salvar.apply();
                        }
                    });

                    txtOpcao3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "4,2");
                            salvar.apply();
                        }
                    });

                    txtOpcao4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "4,2");
                            salvar.apply();
                        }
                    });

                    txtOpcao1.setVisibility(View.VISIBLE);
                    txtOpcao2.setVisibility(View.VISIBLE);
                    txtOpcao3.setVisibility(View.VISIBLE);
                    txtOpcao4.setVisibility(View.VISIBLE);
                } else if( parcelas > 36){
                    valorFinal = (valorEmprestimo * parcelas * 0.05) + valorEmprestimo;

                    BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas);
                    BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.25)) / (parcelas - 1));
                    BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1));

                    pagamentoOpcao1 = pagamentoOpcao1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao2 = pagamentoOpcao2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao3 = pagamentoOpcao3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    pagamentoOpcao4 = pagamentoOpcao4.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.2);
                    BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.25);
                    BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3);

                    primeiroPagamentoOpcao2 = primeiroPagamentoOpcao2.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao3 = primeiroPagamentoOpcao3.setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    primeiroPagamentoOpcao4 = primeiroPagamentoOpcao4.setScale(2,BigDecimal.ROUND_HALF_EVEN);

                    txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                    txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                    txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                    txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                    txtOpcao1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "5");
                            salvar.apply();
                        }
                    });

                    txtOpcao2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "5");
                            salvar.apply();
                        }
                    });

                    txtOpcao3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "5");
                            salvar.apply();
                        }
                    });

                    txtOpcao4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(TerceiraOpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "5");
                            salvar.apply();
                        }
                    });

                    txtOpcao1.setVisibility(View.VISIBLE);
                    txtOpcao2.setVisibility(View.VISIBLE);
                    txtOpcao3.setVisibility(View.VISIBLE);
                    txtOpcao4.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
