package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbCargo;
import com.example.myapplication.entidades.Cargo;

public class CargoEditar extends AppCompatActivity {

    EditText txtNombreCargo, txtCodigoCargo, txtEstRegCargo;
    Button btnGuardar, btnCancelar;

    Cargo cargo;
    int codigo = 0;
    String codigoEstReg, tabla;
    boolean correcto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_editar);

        txtCodigoCargo = findViewById(R.id.edt_codeCargo);
        txtNombreCargo = findViewById(R.id.edt_nombreCargo);
        txtEstRegCargo = findViewById(R.id.edt_estRegCargo);
        btnGuardar = findViewById(R.id.btnGuardar);
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

        DbCargo dbCargo = new DbCargo(CargoEditar.this);
        cargo = dbCargo.verCargo(codigo);

        if(cargo!= null){
            txtCodigoCargo.setText(String.valueOf(cargo.getCodigo()));
            txtNombreCargo.setText(cargo.getNombre());
            txtEstRegCargo.setText(codigoEstReg);
            txtCodigoCargo.setInputType(InputType.TYPE_NULL);
            txtEstRegCargo.setInputType(InputType.TYPE_NULL);
        }

        txtEstRegCargo.setOnClickListener(view -> {
            Intent intent = new Intent(this, EstRegSelect.class);
            intent.putExtra("Tabla", tabla);
            intent.putExtra("ID", codigo);
            startActivity(intent);
        });

        btnGuardar.setOnClickListener(view -> {
            if (!txtNombreCargo.getText().toString().equals("") && !txtEstRegCargo.getText().toString().equals("")){
                correcto = dbCargo.editarCargo(codigo, txtNombreCargo.getText().toString(), txtEstRegCargo.getText().toString());
                if(correcto){
                    Toast.makeText(CargoEditar.this, "Registro Modificado", Toast.LENGTH_LONG).show();
                    verRegistro();
                    onNavigateUp();
                }
                else
                    Toast.makeText(CargoEditar.this, "Error al Modificar Registro", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(CargoEditar.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
        });

        btnCancelar.setOnClickListener(view -> {
            onNavigateUp();
        });

    }
    private void verRegistro(){
        Intent intent = new Intent(this, CargoDetail.class);
        intent.putExtra("ID", codigo);
        startActivity(intent);
    }
}