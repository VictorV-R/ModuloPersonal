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

import com.example.myapplication.adaptadores.ListaCargoAdapter;
import com.example.myapplication.adaptadores.ListaEstRegAdapter;
import com.example.myapplication.db.DbCargo;

public class CargoLista extends AppCompatActivity {

    RecyclerView listaCargos;
    Button btnInsertarCargo;
    EditText edt_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_lista);

        listaCargos = findViewById(R.id.listaCargo);
        listaCargos.setLayoutManager(new LinearLayoutManager(this));
        DbCargo dbCargo = new DbCargo(CargoLista.this);

        ListaCargoAdapter adapter = new ListaCargoAdapter(dbCargo.mostrarCargos(),getApplicationContext());
        listaCargos.setAdapter(adapter);

        btnInsertarCargo = findViewById(R.id.btnInsertar);
        btnInsertarCargo.setOnClickListener(view -> {
            Intent intent = new Intent(CargoLista.this, CargoInsert.class);
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
                ListaCargoAdapter adapter = new ListaCargoAdapter(dbCargo.filtrarCargos(editable.toString()),getApplicationContext());
                listaCargos.setAdapter(adapter);
            }
        });
    }
}