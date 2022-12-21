package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbCargo;
import com.example.myapplication.entidades.Cargo;

public class CargoDetail extends AppCompatActivity {

    EditText txtNombreCargo, txtCodigoCargo, txtEstRegCargo;
    Button btnEditar, btnEliminar, btnActivar, btnInactivar;

    Cargo cargo;
    int codigo = 0;
    String codigoEstReg, tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_detail);

        txtCodigoCargo = findViewById(R.id.edt_codeCargo);
        txtNombreCargo = findViewById(R.id.edt_nombreCargo);
        txtEstRegCargo = findViewById(R.id.edt_estRegCargo);
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

        DbCargo dbCargo = new DbCargo(CargoDetail.this);
        cargo = dbCargo.verCargo(codigo);

        if(cargo!= null){
            txtCodigoCargo.setText(String.valueOf(cargo.getCodigo()));
            txtNombreCargo.setText(cargo.getNombre());
            txtEstRegCargo.setText(cargo.getEstReg());
            txtCodigoCargo.setInputType(InputType.TYPE_NULL);
            txtNombreCargo.setInputType(InputType.TYPE_NULL);
            txtEstRegCargo.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(CargoDetail.this, CargoEditar.class);
            intent.putExtra("ID", codigo);
            intent.putExtra("EstRegCod", codigoEstReg);
            intent.putExtra("Tabla", tabla);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            loadAlert();
        });

        btnActivar.setOnClickListener(view -> {
            dbCargo.operationCargo(codigo, "A");
            loadActivityCargo();
        });

        btnInactivar.setOnClickListener(view -> {
            dbCargo.operationCargo(codigo, "I");
            loadActivityCargo();
        });
    }

    public void loadAlert(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Esta seguro de eliminar el registro de cargo ?");
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
        DbCargo dbCargo = new DbCargo(CargoDetail.this);
        dbCargo.operationCargo(codigo, "*");

        Toast t=Toast.makeText(this,"Eliminacion exitosa.", Toast.LENGTH_SHORT);
        t.show();

        loadActivityCargo();
    }

    public void cancelar() {
        finish();
    }

    public void loadActivityCargo(){
        Intent intent = new Intent(this, CargoLista.class);
        startActivity(intent);
    }
}