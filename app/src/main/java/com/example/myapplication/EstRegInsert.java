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

        txtCodigo = findViewById(R.id.txtCodigoEstReg);
        txtNombre = findViewById(R.id.txtNombrePais);
        txtEstReg = findViewById(R.id.txtEstRegPais);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener( view -> {
            DbEstReg dbEstReg = new DbEstReg(EstRegInsert.this);
            long id = dbEstReg.insertarEstReg(txtCodigo.getText().toString(), txtNombre.getText().toString(), txtEstReg.getText().toString());
            if (id > 0){
                Toast.makeText(EstRegInsert.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                limpiar();
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