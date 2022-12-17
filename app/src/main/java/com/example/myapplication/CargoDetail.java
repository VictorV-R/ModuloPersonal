package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.db.DbCargo;
import com.example.myapplication.entidades.Cargo;

public class CargoDetail extends AppCompatActivity {

    EditText txtNombreCargo, txtCodigoCargo, txtEstRegCargo;
    Button btnGuardar, btnEditar;

    Cargo cargo;
    int codigo = 0;
    String codigoEstReg, tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_detail);

        txtCodigoCargo = findViewById(R.id.txtCodigoCargo);
        txtNombreCargo = findViewById(R.id.txtNombreCargo);
        txtEstRegCargo = findViewById(R.id.txtEstRegCargo);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnEditar = findViewById(R.id.btnEditar);

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

        DbCargo dbCargo = new DbCargo(CargoDetail.this);
        cargo = dbCargo.verCargo(codigo);

        if(cargo!= null){
            txtCodigoCargo.setText(String.valueOf(cargo.getCodigo()));
            txtNombreCargo.setText(cargo.getNombre());
            txtEstRegCargo.setText(cargo.getEstReg());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtCodigoCargo.setInputType(InputType.TYPE_NULL);
            txtNombreCargo.setInputType(InputType.TYPE_NULL);
            txtEstRegCargo.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(CargoDetail.this, CargoEditar.class);
            intent.putExtra("ID", codigo);
            intent.putExtra("EstRegCod", codigoEstReg);
            intent.putExtra("Tabla", tabla);
            startActivity(intent);
        });
    }
}