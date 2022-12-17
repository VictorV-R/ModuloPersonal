package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.adaptadores.ListaCargoAdapter;
import com.example.myapplication.db.DbCargo;

public class CargoLista extends AppCompatActivity {

    RecyclerView listaCargos;
    Button btnInsertarCargo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_lista);

        listaCargos = findViewById(R.id.listaCargo);
        listaCargos.setLayoutManager(new LinearLayoutManager(this));
        DbCargo dbCargo = new DbCargo(CargoLista.this);

        ListaCargoAdapter adapter = new ListaCargoAdapter(dbCargo.mostrarCargos());
        listaCargos.setAdapter(adapter);

        btnInsertarCargo = findViewById(R.id.btnInsertar);
        btnInsertarCargo.setOnClickListener(view -> {
            Intent intent = new Intent(CargoLista.this, CargoInsert.class);
            startActivity(intent);
        });
    }
}