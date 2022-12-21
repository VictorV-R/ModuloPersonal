package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPais;
import com.example.myapplication.entidades.Pais;

public class PaisEditar extends AppCompatActivity {

    EditText txtNombrePais, txtCodigoPais, txtEstRegPais;
    Button btnGuardar, btnCancelar;

    Pais pais;
    int codigo = 0;
    String codigoEstReg, tabla;
    boolean correcto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_editar);

        txtCodigoPais = findViewById(R.id.edt_codigoPais);
        txtNombrePais = findViewById(R.id.edt_nombrePais);
        txtEstRegPais = findViewById(R.id.edt_estRegPais);
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

        DbPais dbPais = new DbPais(PaisEditar.this);
        pais = dbPais.verPais(codigo);

        if(pais != null){
            txtCodigoPais.setText(String.valueOf(pais.getCodigo()));
            txtNombrePais.setText(pais.getNombre());
            txtEstRegPais.setText(codigoEstReg);
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

        btnCancelar.setOnClickListener(view -> {
            onNavigateUp();
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, PaisDetail.class);
        intent.putExtra("ID", codigo);
        startActivity(intent);
    }
}