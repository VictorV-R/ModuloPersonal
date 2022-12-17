package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.db.DbPersonal;
import com.example.myapplication.entidades.Personal;

public class PersonalDetail extends AppCompatActivity {

    EditText txtNombrePersonal, txtCodigoPersonal, txtEstRegPersonal;
    Button btnGuardar, btnEditar;

    Personal personal;
    int codigo = 0;
    String codigoEstReg, tabla;

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

        DbPersonal dbPersonal = new DbPersonal(PersonalDetail.this);
        personal = dbPersonal.verPersonal(codigo);

        if(personal != null){
            txtCodigoPersonal.setText(String.valueOf(personal.getPerCod()));
            txtNombrePersonal.setText(personal.getPerNom());
            txtEstRegPersonal.setText(personal.getPerEstReg());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtCodigoPersonal.setInputType(InputType.TYPE_NULL);
            txtNombrePersonal.setInputType(InputType.TYPE_NULL);
            txtEstRegPersonal.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalDetail.this, PersonalEditar.class);
            intent.putExtra("ID", codigo);
            intent.putExtra("EstRegCod", codigoEstReg);
            intent.putExtra("Tabla", tabla);
            startActivity(intent);
        });
    }
}