package com.afcompany.seucredito;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.List;

public class SolicitacaoActivity extends AppCompatActivity {

    private ImageView imgDataNascimento;
    static EditText edtDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

        Toolbar();

        imgDataNascimento = findViewById(R.id.img_dataNascimento);
        imgDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePicker();
                dialogFragment.show(getSupportFragmentManager(), "DataNascimento");
            }
        });

        Calendar data = Calendar.getInstance();
        Integer dia = data.get(Calendar.DAY_OF_MONTH);
        Integer mes = data.get(Calendar.MONTH);
        Integer ano = data.get(Calendar.YEAR);

        edtDataNascimento = findViewById(R.id.edt_data_nascimento);
        edtDataNascimento.setText(dia+"/"+(mes+1)+"/"+ano);

        Button buttonOfertas = findViewById(R.id.btn_oferta_detalhes);
        buttonOfertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SolicitacaoActivity.this, CredOfertasActivity.class);
                startActivity(in);
            }
        });
    }

    public static class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog( Bundle savedInstanceState) {
            Calendar data = Calendar.getInstance();
            Integer dia = data.get(Calendar.DAY_OF_MONTH);
            Integer mes = data.get(Calendar.MONTH);
            Integer ano = data.get(Calendar.YEAR);

            DatePickerDialog dataNsc = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_DARK, this, ano, mes, dia);

            return dataNsc;
        }

        @Override
        public void onDateSet(android.widget.DatePicker view, int ano, int mes, int dia) {
            String dataSelecionada = getTag();
            if (dataSelecionada.equals("DataNascimento")){
                edtDataNascimento.setText(dia+"/"+(mes+1)+"/"+ano);
            }
        }
    }

    private void Toolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
    }
}
