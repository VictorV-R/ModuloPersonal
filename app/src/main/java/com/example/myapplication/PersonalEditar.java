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

    EditText txtNombrePersonal, txtCodigoPersonal, txtEstRegPersonal, txtDniPersonal;
    Button btnGuardar, btnCancelar;

    Personal personal;
    int codigo = 0;
    String codigoEstReg, tabla;
    boolean correcto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_editar);

        txtCodigoPersonal = findViewById(R.id.edt_codigoPersonal);
        txtNombrePersonal = findViewById(R.id.edt_nombrePersonal);
        txtDniPersonal = findViewById(R.id.edt_dniPersonal);
        txtEstRegPersonal = findViewById(R.id.edt_estRegPersonal);
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

        DbPersonal dbPersonal = new DbPersonal(PersonalEditar.this);
        personal = dbPersonal.verPersonal(codigo);

        if(personal != null){
            txtCodigoPersonal.setText(String.valueOf(personal.getPerCod()));
            txtNombrePersonal.setText(personal.getPerNom());
            txtDniPersonal.setText(personal.getPerDni());
            txtEstRegPersonal.setText(codigoEstReg);
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
                correcto = dbPersonal.editarPersonal(codigo, txtNombrePersonal.getText().toString(), Integer.parseInt(txtDniPersonal.getText().toString()), txtEstRegPersonal.getText().toString());
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

        btnCancelar.setOnClickListener(view -> {
            onNavigateUp();
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, PersonalDetail.class);
        intent.putExtra("ID", codigo);
        startActivity(intent);
    }
}