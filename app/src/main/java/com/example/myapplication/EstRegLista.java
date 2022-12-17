package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.adaptadores.ListaEstRegAdapter;
import com.example.myapplication.db.DbEstReg;

public class EstRegLista extends AppCompatActivity {

    RecyclerView listaEstReg;
    Button btnInsertarEstReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_reg_lista);

        listaEstReg = findViewById(R.id.lista_est_reg);
        listaEstReg.setLayoutManager(new LinearLayoutManager(this));
        DbEstReg dbEstReg = new DbEstReg(EstRegLista.this);

        ListaEstRegAdapter adapter = new ListaEstRegAdapter(dbEstReg.mostrarEstRegs());
        listaEstReg.setAdapter(adapter);

        btnInsertarEstReg = findViewById(R.id.btnInsertar);
        btnInsertarEstReg.setOnClickListener(view -> {
            Intent intent = new Intent(EstRegLista.this, EstRegInsert.class);
            startActivity(intent);
        });
    }
}