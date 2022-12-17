package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.db.DbEstReg;
import com.example.myapplication.entidades.EstReg;

public class EstRegDetail extends AppCompatActivity {

    EditText txtNombre, txtEstReg, txtCodigo;
    Button btnGuardarEstReg, btnEditar;

    EstReg estRegs;
    String codigo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_reg_detail);

        txtCodigo = findViewById(R.id.txtCodigoEstReg);
        txtNombre = findViewById(R.id.txtNombrePais);
        txtEstReg = findViewById(R.id.txtEstRegPais);
        btnGuardarEstReg = findViewById(R.id.btnGuardar);
        btnEditar = findViewById(R.id.btnEditar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null)
                codigo = null;
            else
                codigo = extras.getString("ID");
        } else
            codigo = (String) savedInstanceState.getSerializable("ID");

        DbEstReg dbEstReg = new DbEstReg(EstRegDetail.this);
        estRegs = dbEstReg.verEstReg(codigo);

        if(estRegs != null){
            txtCodigo.setText(estRegs.getCodigo());
            txtNombre.setText(estRegs.getNombre());
            txtEstReg.setText(estRegs.getEstado_registro());
            btnGuardarEstReg.setVisibility(View.INVISIBLE);
            txtCodigo.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtEstReg.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(EstRegDetail.this, EstRegEditar.class);
            intent.putExtra("ID", codigo);
            startActivity(intent);
        });

    }
}