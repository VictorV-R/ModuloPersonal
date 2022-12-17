package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPais;
import com.example.myapplication.entidades.Pais;

public class PaisDetail extends AppCompatActivity {

    EditText txtNombrePais, txtCodigoPais, txtEstRegPais;
    Button btnGuardar, btnEditar;

    Pais pais;
    int codigo = 0;
    String codigoEstReg, tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_detail);

        txtCodigoPais = findViewById(R.id.txtCodigoPais);
        txtNombrePais = findViewById(R.id.txtNombrePais);
        txtEstRegPais = findViewById(R.id.txtEstRegPais);
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

        DbPais dbPais = new DbPais(PaisDetail.this);
        pais = dbPais.verPais(codigo);

        if(pais != null){
            txtCodigoPais.setText(String.valueOf(pais.getCodigo()));
            txtNombrePais.setText(pais.getNombre());
            txtEstRegPais.setText(pais.getEstReg());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtCodigoPais.setInputType(InputType.TYPE_NULL);
            txtNombrePais.setInputType(InputType.TYPE_NULL);
            txtEstRegPais.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(PaisDetail.this, PaisEditar.class);
            intent.putExtra("ID", codigo);
            intent.putExtra("EstRegCod", codigoEstReg);
            intent.putExtra("Tabla", tabla);
            startActivity(intent);
        });
    }
}