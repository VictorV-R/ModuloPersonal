package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPais;
import com.example.myapplication.entidades.Pais;

public class PaisDetail extends AppCompatActivity {

    EditText txtNombrePais, txtCodigoPais, txtEstRegPais;
    Button btnEditar, btnEliminar, btnActivar, btnInactivar;

    Pais pais;
    int codigo = 0;
    String codigoEstReg, tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_detail);

        txtCodigoPais = findViewById(R.id.txtCodigoPais);
        txtNombrePais = findViewById(R.id.edt_nombreCargo);
        txtEstRegPais = findViewById(R.id.edt_estRegCargo);
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

        DbPais dbPais = new DbPais(PaisDetail.this);
        pais = dbPais.verPais(codigo);

        if(pais != null){
            txtCodigoPais.setText(String.valueOf(pais.getCodigo()));
            txtNombrePais.setText(pais.getNombre());
            txtEstRegPais.setText(pais.getEstReg());
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

        btnEliminar.setOnClickListener(view -> {
            loadAlert();
        });

        btnActivar.setOnClickListener(view -> {
            dbPais.operationPais(codigo, "*");
            loadActivityPais();
        });

        btnInactivar.setOnClickListener(view -> {
            dbPais.operationPais(codigo, "I");
            loadActivityPais();
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
        DbPais dbPais = new DbPais(PaisDetail.this);
        pais = dbPais.verPais(codigo);
        dbPais.operationPais(codigo, "*");

        Toast t=Toast.makeText(this,"Eliminacion exitosa.", Toast.LENGTH_SHORT);
        t.show();

        loadActivityPais();
    }

    public void cancelar() {
        finish();
    }

    public void loadActivityPais(){
        Intent intent = new Intent(this, PaisLista.class);
        startActivity(intent);
    }

}