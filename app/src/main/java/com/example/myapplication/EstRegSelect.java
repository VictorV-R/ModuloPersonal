package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adaptadores.ListaEstRegSelectAdapter;
import com.example.myapplication.adaptadores.ListaPaisAdapter;
import com.example.myapplication.db.DbEstReg;

import java.util.Objects;

public class EstRegSelect extends AppCompatActivity {

    RecyclerView listaEstReg;
    Button btnSeleccionar, btnCancelar;
    int codigo;
    String codigoEstReg, tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_reg_select);

        listaEstReg = findViewById(R.id.lista_est_reg_act);
        listaEstReg.setLayoutManager(new LinearLayoutManager(this));
        DbEstReg dbEstReg = new DbEstReg(EstRegSelect.this);

        ListaEstRegSelectAdapter adapter = new ListaEstRegSelectAdapter(dbEstReg.mostrarEstRegs());
        listaEstReg.setAdapter(adapter);

        btnSeleccionar = findViewById(R.id.btnSeleccionar);
        btnCancelar = findViewById(R.id.btnCancelar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                codigo = Integer.parseInt(null);
                codigoEstReg = null;
                tabla = null;
            }
            else{
                codigo = extras.getInt("ID");
                codigoEstReg = extras.getString("EstRegCod");
                tabla = extras.getString("Tabla");
            }
        } else{
            codigo = (int) savedInstanceState.getSerializable("ID");
            codigoEstReg = (String) savedInstanceState.getSerializable("EstRegCod");
            tabla = (String) savedInstanceState.getSerializable("Tabla");
        }

        btnSeleccionar.setOnClickListener(view -> {
            switch (tabla){
                case "Pais":
                    editarPais(adapter, codigo);
                    return;
                case "Cargo":
                    editarCargo(adapter, codigo);
                    return;
                case "Personal":
                    editarPersonal(adapter,codigo);
                    return;
                default:
                    Log.i("Q","No entro");
            }
        });

        btnCancelar.setOnClickListener(view -> {

        });
    }

    public void editarPais(ListaEstRegSelectAdapter adapter, int codigo){
        Intent intent = new Intent(EstRegSelect.this, PaisEditar.class);
        intent.putExtra("ID", codigo);
        intent.putExtra("EstRegCod", adapter.estRegSelected.getCodigo());
        startActivity(intent);
    }

    public void editarCargo(ListaEstRegSelectAdapter adapter, int codigo){
        Intent intent = new Intent(EstRegSelect.this, CargoEditar.class);
        intent.putExtra("ID", codigo);
        intent.putExtra("EstRegCod", adapter.estRegSelected.getCodigo());
        startActivity(intent);
    }

    public void editarPersonal(ListaEstRegSelectAdapter adapter, int codigo){
        Intent intent = new Intent(EstRegSelect.this, PersonalEditar.class);
        intent.putExtra("ID", codigo);
        intent.putExtra("EstRegCod", adapter.estRegSelected.getCodigo());
        startActivity(intent);
    }
}