package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPais;
import com.example.myapplication.entidades.Pais;

public class PaisEditar extends AppCompatActivity {

    EditText txtNombrePais, txtCodigoPais, txtEstRegPais;
    Button btnGuardar, btnEditar;

    Pais pais;
    int codigo = 0;
    String codigoEstReg, tabla;
    boolean correcto;

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

        DbPais dbPais = new DbPais(PaisEditar.this);
        pais = dbPais.verPais(codigo);

        if(pais != null){
            txtCodigoPais.setText(String.valueOf(pais.getCodigo()));
            txtNombrePais.setText(pais.getNombre());
            txtEstRegPais.setText(codigoEstReg);
            btnEditar.setVisibility(View.INVISIBLE);
            txtCodigoPais.setInputType(InputType.TYPE_NULL);
            txtEstRegPais.setInputType(InputType.TYPE_NULL);
        }

        txtEstRegPais.setOnClickListener(view -> {
            Intent intent = new Intent(this, EstRegSelect.class);
            intent.putExtra("Tabla", tabla);
            intent.putExtra("ID", codigo);
            startActivity(intent);
        });

        btnGuardar.setOnClickListener(view -> {
            if (!txtNombrePais.getText().toString().equals("") && !txtEstRegPais.getText().toString().equals("")){
                correcto = dbPais.editarPais(codigo, txtNombrePais.getText().toString(), txtEstRegPais.getText().toString());
                if(correcto){
                    Toast.makeText(PaisEditar.this, "Registro Modificado", Toast.LENGTH_LONG).show();
                    verRegistro();
                    onNavigateUp();

                }
                else
                    Toast.makeText(PaisEditar.this, "Error al Modificar Registro", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(PaisEditar.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, PaisDetail.class);
        intent.putExtra("ID", codigo);
        startActivity(intent);
    }
}