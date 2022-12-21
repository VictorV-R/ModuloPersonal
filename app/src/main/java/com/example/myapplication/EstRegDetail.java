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

import com.example.myapplication.db.DbEstReg;
import com.example.myapplication.entidades.EstReg;

public class EstRegDetail extends AppCompatActivity {

    EditText txtNombre, txtEstReg, txtCodigo;
    Button btnEditar, btnEliminar, btnActivar, btnInactivar;

    EstReg estRegs;
    String codigo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_reg_detail);

        txtCodigo = findViewById(R.id.edt_codeCargo);
        txtNombre = findViewById(R.id.edt_nombreCargo);
        txtEstReg = findViewById(R.id.edt_estRegCargo);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnActivar = findViewById(R.id.btnActivar);
        btnInactivar = findViewById(R.id.btnInactivar);


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
            txtCodigo.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtEstReg.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(EstRegDetail.this, EstRegEditar.class);
            intent.putExtra("ID", codigo);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            loadAlert();
        });

        btnActivar.setOnClickListener(view -> {
            dbEstReg.operationEstReg(codigo, "A");
            loadActivityEstado();
        });

        btnInactivar.setOnClickListener(view -> {
            dbEstReg.operationEstReg(codigo, "I");
            loadActivityEstado();
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
        DbEstReg dbEstReg = new DbEstReg(EstRegDetail.this);
        dbEstReg.operationEstReg(codigo, "*");

        Toast t=Toast.makeText(this,"Eliminacion exitosa.", Toast.LENGTH_SHORT);
        t.show();

        loadActivityEstado();
    }

    public void cancelar() {
        finish();
    }

    public void loadActivityEstado(){
        Intent intent = new Intent(this, EstRegLista.class);
        startActivity(intent);
    }
}