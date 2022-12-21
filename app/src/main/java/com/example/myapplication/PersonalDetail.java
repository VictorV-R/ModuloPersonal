package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbEstReg;
import com.example.myapplication.db.DbPersonal;
import com.example.myapplication.entidades.Personal;

public class PersonalDetail extends AppCompatActivity {

    EditText txtNombrePersonal, txtCodigoPersonal, txtEstRegPersonal;
    Button btnEditar, btnEliminar, btnActivar, btnInactivar;

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
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnActivar = findViewById(R.id.btnActivar);
        btnInactivar = findViewById(R.id.btnInactivar);

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
            txtCodigoPersonal.setInputType(InputType.TYPE_NULL);
            txtNombrePersonal.setInputType(InputType.TYPE_NULL);
            txtEstRegPersonal.setInputType(InputType.TYPE_NULL);
        }

        if(personal.getPerEstReg().equals("I")){
            bloquearBotones(false);
        }else {
            bloquearBotones(true);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalDetail.this, PersonalEditar.class);
            intent.putExtra("ID", codigo);
            intent.putExtra("EstRegCod", codigoEstReg);
            intent.putExtra("Tabla", tabla);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            loadAlert();
        });

        btnActivar.setOnClickListener(view -> {
            dbPersonal.operationPersonal(codigo, "A");
            loadActivityPersonal();
        });

        btnInactivar.setOnClickListener(view -> {
            dbPersonal.operationPersonal(codigo, "I");
            loadActivityPersonal();
        });
    }


    public void loadAlert(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Esta seguro de eliminar el registro de estado ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();
    }
    public void aceptar() {
        DbPersonal dbPersonal = new DbPersonal(PersonalDetail.this);
        dbPersonal.operationPersonal(codigo, "*");

        Toast t=Toast.makeText(this,"Eliminacion exitosa.", Toast.LENGTH_SHORT);
        t.show();

        loadActivityPersonal();
    }

    public void cancelar() {
        finish();
    }

    public void loadActivityPersonal(){
        Intent intent = new Intent(this, PersonalLista.class);
        startActivity(intent);
    }

    public void bloquearBotones(Boolean flag){
        btnEditar.setEnabled(flag);
        btnEliminar.setEnabled(flag);

    }

}