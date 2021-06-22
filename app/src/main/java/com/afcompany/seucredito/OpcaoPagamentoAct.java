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

public class OpcaoPagamentoAct extends AppCompatActivity {

    private EditText edtParcelas;
    private TextView txtOpcao1;
    private TextView txtOpcao2;
    private TextView txtOpcao3;
    private TextView txtOpcao4;

    private Button btnOpcoesParcelas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcao_pagamento);

        edtParcelas = findViewById(R.id.edt_parcelas);

        txtOpcao1 = findViewById(R.id.txt_opcao1);
        txtOpcao2 = findViewById(R.id.txt_opcao2);
        txtOpcao3 = findViewById(R.id.txt_opcao3);
        txtOpcao4 = findViewById(R.id.txt_opcao4);

        //DEIXANDO CAMPOS SEM VISIBILIDADE
        txtOpcao1.setVisibility(View.GONE);
        txtOpcao2.setVisibility(View.GONE);
        txtOpcao3.setVisibility(View.GONE);
        txtOpcao4.setVisibility(View.GONE);

        ImageButton btnVoltar = findViewById(R.id.btn_voltar1);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OpcaoPagamentoAct.this, SolicitacaoActivity.class);
                startActivity(in);
            }
        });

        btnOpcoesParcelas = findViewById(R.id.btn_opcoes_pagamento);
        btnOpcoesParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtParcelas.getText().toString().isEmpty()) {
                    Toast.makeText(OpcaoPagamentoAct.this, R.string.error_msg3, LENGTH_SHORT).show();
                    return;
                }

                //CONVERTENDO OS VALORES PARA REALIZAR A VERIFICAÇÃO DO VALOR DA TAXA E CÁLCULO DO EMPRÉSTIMO
                int parcelas = Integer.parseInt(edtParcelas.getText().toString());
                double valorEmprestimo = Double.parseDouble(getIntent().getStringExtra("valorEmprestimo"));
                double valorFinal;

                //ATÉ 12x TAXA DE 1,2a.m
                if (parcelas >= 1 && parcelas <= 12) {
                    //DADOS FORMATADOS PARA PAGAMENTO EM 1 PARCELA
                    if (parcelas == 1) {

                        //CALCULANDO VALOR TOTAL A SER PAGO
                        valorFinal = (valorEmprestimo * 0.012) + valorEmprestimo;

                        //CONVERTENDO VALOR PARA O TIPO BIGDECIMAL E ESPECIFICANDO O NÚMERO DE CASAS DECIMAIS
                        BigDecimal pagamento = BigDecimal.valueOf(valorFinal).setScale(2, BigDecimal.ROUND_HALF_EVEN);

                        //PASSANDO OS DADOS PARA O CAMPO DE TEXTO E TORNANDO O CAMPO VISIVEL
                        txtOpcao1.setText("R$" + pagamento);
                        txtOpcao1.setVisibility(View.VISIBLE);

                        txtOpcao1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao1.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "1,2");
                                salvar.apply();
                            }
                        });

                        //MANTENDO ESSE CAMPOS SEM VISIBILIDADE
                        txtOpcao2.setVisibility(View.GONE);
                        txtOpcao3.setVisibility(View.GONE);
                        txtOpcao4.setVisibility(View.GONE);

                        //DADOS FORMATADOS PARA PAGAMENTO EM 2 PARCELA
                    } else if (parcelas == 2) {

                        //CALCULANDO VALOR TOTAL A SER PAGO
                        valorFinal = (valorEmprestimo * 2 * 0.012) + valorEmprestimo;

                        //CONVERTENDO VALOR PARA O TIPO BIGDECIMAL E ESPECIFICANDO O NÚMERO DE CASAS DECIMAIS
                        BigDecimal pagamento = BigDecimal.valueOf(valorFinal / 2).setScale(2, BigDecimal.ROUND_HALF_EVEN);

                        //PASSANDO OS DADOS PARA O CAMPO DE TEXTO E TORNANDO O CAMPO VISIVEL
                        txtOpcao1.setText("2x de R$" + pagamento);
                        txtOpcao1.setVisibility(View.VISIBLE);

                        txtOpcao1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao1.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "1,2");
                                salvar.apply();
                            }
                        });

                        //MANTENDO ESSE CAMPOS SEM VISIBILIDADE
                        txtOpcao2.setVisibility(View.GONE);
                        txtOpcao3.setVisibility(View.GONE);
                        txtOpcao4.setVisibility(View.GONE);
                    } else {
                        //CALCULANDO VALOR TOTAL A SER PAGO
                        valorFinal = (valorEmprestimo * parcelas * 0.012) + valorEmprestimo;

                        //CALCULANDO VALOR DA PRIMEIRA PARCELA E ESPECIFICANDO NÚMERO DE CASAS DECIMAIS
                        BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.2).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.25).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3).setScale(2,BigDecimal.ROUND_HALF_EVEN);

                        //CALCULANDO VALOR DAS PRÓXIMAS PARCELAS E ESPECIFICANDO NÚMERO DE CASAS DECIMAIS
                        BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1)).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.25)) / (parcelas - 1)).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1)).setScale(2,BigDecimal.ROUND_HALF_EVEN);

                        //PASSANDO OS DADOS PARA OS CAMPOS DE TEXTO
                        txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                        txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                        txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                        txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                        txtOpcao1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao1.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "1,2");
                                salvar.apply();
                            }
                        });

                        txtOpcao2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao2.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "1,2");
                                salvar.apply();
                            }
                        });

                        txtOpcao3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao3.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "1,2");
                                salvar.apply();
                            }
                        });

                        txtOpcao4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                                startActivity(in);

                                //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                                SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                                SharedPreferences.Editor salvar = dados.edit();

                                salvar.putString("parcelas", edtParcelas.getText().toString());
                                salvar.putString("pagamento", txtOpcao4.getText().toString());
                                salvar.putString("valorTotal", String.valueOf(valorFinal));
                                salvar.putString("taxa", "1,2");
                                salvar.apply();
                            }
                        });

                        //TORNANDO OS CAMPOS VISIVEIS
                        txtOpcao1.setVisibility(View.VISIBLE);
                        txtOpcao2.setVisibility(View.VISIBLE);
                        txtOpcao3.setVisibility(View.VISIBLE);
                        txtOpcao4.setVisibility(View.VISIBLE);
                    }
                    //ATÉ 36x TAXA DE 1,8a.m
                } else if(parcelas > 12 && parcelas <= 36){

                    //CALCULANDO VALOR TOTAL A SER PAGO
                    valorFinal = (valorEmprestimo * parcelas * 0.018) + valorEmprestimo;

                    //CALCULANDO VALOR DA PRIMEIRA PARCELA E ESPECIFICANDO NÚMERO DE CASAS DECIMAIS
                    BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.2).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.25).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3).setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    //CALCULANDO VALOR DAS PRÓXIMAS PARCELAS E ESPECIFICANDO NÚMERO DE CASAS DECIMAIS
                    BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.25)) / (parcelas - 1)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1)).setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    //PASSANDO OS DADOS PARA OS CAMPOS DE TEXTO
                    txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                    txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                    txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                    txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                    txtOpcao1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao1.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "1,8");
                            salvar.apply();
                        }
                    });

                    txtOpcao2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao2.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "1,8");
                            salvar.apply();
                        }
                    });

                    txtOpcao3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao3.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "1,8");
                            salvar.apply();
                        }
                    });

                    txtOpcao4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "1,8");
                            salvar.apply();
                        }
                    });

                    //TORNANDO OS CAMPOS VISIVEIS
                    txtOpcao1.setVisibility(View.VISIBLE);
                    txtOpcao2.setVisibility(View.VISIBLE);
                    txtOpcao3.setVisibility(View.VISIBLE);
                    txtOpcao4.setVisibility(View.VISIBLE);

                    //MAIOR QUE 36X TAXA DE 2,4a.m
                } else if( parcelas > 36){

                    //CALCULANDO VALOR TOTAL A SER PAGO
                    valorFinal = (valorEmprestimo * parcelas * 0.024) + valorEmprestimo;

                    //CALCULANDO VALOR DA PRIMEIRA PARCELA E ESPECIFICANDO NÚMERO DE CASAS DECIMAIS
                    BigDecimal primeiroPagamentoOpcao2 = BigDecimal.valueOf(valorFinal * 0.2).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal primeiroPagamentoOpcao3 = BigDecimal.valueOf(valorFinal * 0.25).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal primeiroPagamentoOpcao4 = BigDecimal.valueOf(valorFinal * 0.3).setScale(2,BigDecimal.ROUND_HALF_EVEN);

                    //CALCULANDO VALOR DAS PRÓXIMAS PARCELAS E ESPECIFICANDO NÚMERO DE CASAS DECIMAIS
                    BigDecimal pagamentoOpcao1 = BigDecimal.valueOf(valorFinal / parcelas).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal pagamentoOpcao2 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.2)) / (parcelas - 1)).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal pagamentoOpcao3 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.25)) / (parcelas - 1)).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal pagamentoOpcao4 = BigDecimal.valueOf((valorFinal - (valorFinal * 0.3)) / (parcelas - 1)).setScale(2,BigDecimal.ROUND_HALF_EVEN);

                    //PASSANDO OS DADOS PARA OS CAMPOS DE TEXTO
                    txtOpcao1.setText(parcelas + "x de R$" + pagamentoOpcao1);
                    txtOpcao2.setText("1x de R$" + primeiroPagamentoOpcao2 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao2);
                    txtOpcao3.setText("1x de R$" + primeiroPagamentoOpcao3 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao3);
                    txtOpcao4.setText("1x de R$" + primeiroPagamentoOpcao4 + " + " + (parcelas - 1) + "x de R$" + pagamentoOpcao4);

                    txtOpcao1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao1.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "2,4");
                            salvar.apply();
                        }
                    });

                    txtOpcao2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao2.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "2,4");
                            salvar.apply();
                        }
                    });

                    txtOpcao3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao3.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "2,4");
                            salvar.apply();
                        }
                    });

                    txtOpcao4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(OpcaoPagamentoAct.this, DadosDetalhadosAct.class);
                            startActivity(in);

                            //ARMAZENANDO DADOS INTERNAMENTE, PORQUE SERÃO UTILIZADOS NA TELA DE *DadosDetalhados*.
                            SharedPreferences dados = getSharedPreferences("salvarDados", MODE_PRIVATE);
                            SharedPreferences.Editor salvar = dados.edit();

                            salvar.putString("parcelas", edtParcelas.getText().toString());
                            salvar.putString("pagamento", txtOpcao4.getText().toString());
                            salvar.putString("valorTotal", String.valueOf(valorFinal));
                            salvar.putString("taxa", "2,4");
                            salvar.apply();
                        }
                    });

                    //TORNANDO OS CAMPOS VISIVEIS
                    txtOpcao1.setVisibility(View.VISIBLE);
                    txtOpcao2.setVisibility(View.VISIBLE);
                    txtOpcao3.setVisibility(View.VISIBLE);
                    txtOpcao4.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
