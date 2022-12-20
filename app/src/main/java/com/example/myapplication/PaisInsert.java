package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbPais;

public class PaisInsert extends AppCompatActivity {

    EditText txtNombre, txtEstReg;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_insert);

        txtEstReg = findViewById(R.id.txtEstRegPais);
        txtNombre = findViewById(R.id.txtNombrePais);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(view -> {
            DbPais dbPais = new DbPais(PaisInsert.this);
            long id = dbPais.insertarPais(txtNombre.getText().toString(), txtEstReg.getText().toString());
            if (id > 0){
                Toast.makeText(PaisInsert.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                limpiar();
                onNavigateUp();
            } else
                Toast.makeText(PaisInsert.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
        });
    }
    private void limpiar(){
        txtEstReg.setText("");
        txtNombre.setText("");
    }
}