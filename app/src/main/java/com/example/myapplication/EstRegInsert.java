package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbEstReg;

public class EstRegInsert extends AppCompatActivity {

    EditText txtNombre, txtEstReg, txtCodigo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_reg_insert);

        txtCodigo = findViewById(R.id.edt_codeCargo);
        txtNombre = findViewById(R.id.edt_nombreCargo);
        txtEstReg = findViewById(R.id.edt_estRegCargo);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener( view -> {
            DbEstReg dbEstReg = new DbEstReg(EstRegInsert.this);
            long id = dbEstReg.insertarEstReg(txtCodigo.getText().toString(), txtNombre.getText().toString(), txtEstReg.getText().toString());
            if (id > 0){
                Toast.makeText(EstRegInsert.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                limpiar();
                onNavigateUp();
            } else
                Toast.makeText(EstRegInsert.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
        });
    }

    private void limpiar(){
        txtCodigo.setText("");
        txtEstReg.setText("");
        txtNombre.setText("");
    }
}