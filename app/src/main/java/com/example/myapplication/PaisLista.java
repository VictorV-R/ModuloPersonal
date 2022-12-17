package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adaptadores.ListaPaisAdapter;
import com.example.myapplication.db.DbPais;

public class PaisLista extends AppCompatActivity {

    RecyclerView listaPaises;
    Button btnInsertarPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_lista);

        listaPaises = findViewById(R.id.lista_pais);
        listaPaises.setLayoutManager(new LinearLayoutManager(this));
        DbPais dbPais = new DbPais(PaisLista.this);

        ListaPaisAdapter adapter = new ListaPaisAdapter(dbPais.mostrarPaises());
        listaPaises.setAdapter(adapter);

        btnInsertarPais = findViewById(R.id.btnInsertar);
        btnInsertarPais.setOnClickListener(view -> {
            Intent intent = new Intent(PaisLista.this, PaisInsert.class);
            startActivity(intent);
        });
    }
}