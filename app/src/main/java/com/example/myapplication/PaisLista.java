package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adaptadores.ListaEstRegAdapter;
import com.example.myapplication.adaptadores.ListaPaisAdapter;
import com.example.myapplication.db.DbPais;

public class PaisLista extends AppCompatActivity {

    RecyclerView listaPaises;
    Button btnInsertarPais;
    EditText edt_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_lista);

        listaPaises = findViewById(R.id.lista_pais);
        listaPaises.setLayoutManager(new LinearLayoutManager(this));
        DbPais dbPais = new DbPais(PaisLista.this);

        ListaPaisAdapter adapter = new ListaPaisAdapter(dbPais.mostrarPaises(),getApplicationContext());
        listaPaises.setAdapter(adapter);

        btnInsertarPais = findViewById(R.id.btnInsertar);
        btnInsertarPais.setOnClickListener(view -> {
            Intent intent = new Intent(PaisLista.this, PaisInsert.class);
            startActivity(intent);
        });

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
                ListaPaisAdapter adapter = new ListaPaisAdapter(dbPais.filtrarPais(editable.toString()),getApplicationContext());
                listaPaises.setAdapter(adapter);

            }
        });
    }
}