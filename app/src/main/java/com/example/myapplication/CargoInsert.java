package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbCargo;

public class CargoInsert extends AppCompatActivity {

    EditText txtNombre, txtEstReg;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_insert);

        txtNombre = findViewById(R.id.txtNombreCargo);
        txtEstReg = findViewById(R.id.txtEstRegCargo);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(view -> {
            DbCargo dbCargo = new DbCargo(CargoInsert.this);
            long id = dbCargo.insertarCargo(txtNombre.getText().toString(), txtEstReg.getText().toString());
            if (id > 0){
                Toast.makeText(CargoInsert.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                limpiar();
                onNavigateUp();
            } else
                Toast.makeText(CargoInsert.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
        });
    }
    private void limpiar(){
        txtEstReg.setText("");
        txtNombre.setText("");
    }
}