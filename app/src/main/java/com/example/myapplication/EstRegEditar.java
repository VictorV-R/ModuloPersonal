package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbEstReg;
import com.example.myapplication.entidades.EstReg;

public class EstRegEditar extends AppCompatActivity {

    EditText txtNombre, txtEstReg, txtCodigo;
    Button btnGuardarEstReg, btnEditar;

    EstReg estRegs;
    String codigo = "";
    boolean correcto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_reg_detail);

        txtCodigo = findViewById(R.id.txtCodigoEstReg);
        txtNombre = findViewById(R.id.txtNombrePais);
        txtEstReg = findViewById(R.id.txtEstRegPais);
        btnEditar = findViewById(R.id.btnEditar);
        btnGuardarEstReg = findViewById(R.id.btnGuardar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null)
                codigo = null;
            else
                codigo = extras.getString("ID");
        } else
            codigo = (String) savedInstanceState.getSerializable("ID");

        DbEstReg dbEstReg = new DbEstReg(EstRegEditar.this);
        estRegs = dbEstReg.verEstReg(codigo);

        if(estRegs != null){
            txtCodigo.setText(estRegs.getCodigo());
            txtNombre.setText(estRegs.getNombre());
            txtEstReg.setText(estRegs.getEstado_registro());
            btnEditar.setVisibility(View.INVISIBLE);
            txtCodigo.setInputType(InputType.TYPE_NULL);
        }

        btnGuardarEstReg.setOnClickListener(view -> {
            if (!txtNombre.getText().toString().equals("") && !txtEstReg.getText().toString().equals("")){
                correcto = dbEstReg.editarEstReg(codigo, txtNombre.getText().toString(), txtEstReg.getText().toString());
                if(correcto){
                    Toast.makeText(EstRegEditar.this, "Registro Modificado", Toast.LENGTH_LONG).show();
                    verRegistro();
                }
                else
                    Toast.makeText(EstRegEditar.this, "Error al Modificar Registro", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(EstRegEditar.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, EstRegDetail.class);
        intent.putExtra("ID", codigo);
        startActivity(intent);
    }
}