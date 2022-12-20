package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbPersonal;
import com.example.myapplication.entidades.Personal;

public class PersonalEditar extends AppCompatActivity {

    EditText txtNombrePersonal, txtCodigoPersonal, txtEstRegPersonal;
    Button btnGuardar, btnEditar;

    Personal personal;
    int codigo = 0;
    String codigoEstReg, tabla;
    boolean correcto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);

        txtCodigoPersonal = findViewById(R.id.txtCodigoPersonal);
        txtNombrePersonal = findViewById(R.id.txtNombrePersonal);
        txtEstRegPersonal = findViewById(R.id.txtEstRegPersonal);
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

        DbPersonal dbPersonal = new DbPersonal(PersonalEditar.this);
        personal = dbPersonal.verPersonal(codigo);

        if(personal != null){
            txtCodigoPersonal.setText(String.valueOf(personal.getPerCod()));
            txtNombrePersonal.setText(personal.getPerNom());
            txtEstRegPersonal.setText(codigoEstReg);
            btnEditar.setVisibility(View.INVISIBLE);
            txtCodigoPersonal.setInputType(InputType.TYPE_NULL);
            txtEstRegPersonal.setInputType(InputType.TYPE_NULL);
        }

        txtEstRegPersonal.setOnClickListener(view -> {
            Intent intent = new Intent(this, EstRegSelect.class);
            intent.putExtra("Tabla", tabla);
            intent.putExtra("ID", codigo);
            startActivity(intent);
        });

        btnGuardar.setOnClickListener(view -> {
            if (!txtNombrePersonal.getText().toString().equals("") && !txtEstRegPersonal.getText().toString().equals("")){
                correcto = dbPersonal.editarPersonal(codigo, txtNombrePersonal.getText().toString(), txtEstRegPersonal.getText().toString());
                if(correcto){
                    Toast.makeText(PersonalEditar.this, "Registro Modificado", Toast.LENGTH_LONG).show();
                    verRegistro();
                    onNavigateUp();

                }
                else
                    Toast.makeText(PersonalEditar.this, "Error al Modificar Registro", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(PersonalEditar.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, PersonalDetail.class);
        intent.putExtra("ID", codigo);
        startActivity(intent);
    }
}