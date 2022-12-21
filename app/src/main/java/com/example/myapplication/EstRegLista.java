package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.adaptadores.ListaEstRegAdapter;
import com.example.myapplication.db.DbEstReg;

public class EstRegLista extends AppCompatActivity {

    RecyclerView listaEstReg;
    Button btnInsertarEstReg;
    EditText edt_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_reg_lista);

        listaEstReg = findViewById(R.id.lista_est_reg);
        listaEstReg.setLayoutManager(new LinearLayoutManager(this));
        DbEstReg dbEstReg = new DbEstReg(EstRegLista.this);

        ListaEstRegAdapter adapter = new ListaEstRegAdapter(dbEstReg.mostrarEstRegs(),getApplicationContext());
        listaEstReg.setAdapter(adapter);

        edt_nombre = findViewById(R.id.edt_nombre);
        edt_nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ListaEstRegAdapter adapter = new ListaEstRegAdapter(dbEstReg.filtrarEstReg(editable.toString()),getApplicationContext());
                listaEstReg.setAdapter(adapter);
            }
        });

        btnInsertarEstReg = findViewById(R.id.btnInsertar);
        btnInsertarEstReg.setOnClickListener(view -> {
            Intent intent = new Intent(EstRegLista.this, EstRegInsert.class);
            startActivity(intent);
        });
    }
}