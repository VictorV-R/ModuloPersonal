package com.example.myapplication;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPersonal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalInsert extends AppCompatActivity {

    EditText txtNombre, txtDni, txtFecNac, txtCargo, txtPais, txtEstReg;
    Button btnGuardar;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_insert);

        txtNombre = findViewById(R.id.txtNombrePersonal);
        txtDni = findViewById(R.id.txtDniPersonal);
        txtFecNac = findViewById(R.id.txtFecPersonal);
        txtCargo = findViewById(R.id.txtCargoPersonal);
        txtPais = findViewById(R.id.txtPaisPersonal);
        txtEstReg = findViewById(R.id.txtEstRegPersonal);
        btnGuardar = findViewById(R.id.btnGuardar);

        txtFecNac.setInputType(InputType.TYPE_NULL);
        txtFecNac.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.datepicker_layout);
            AlertDialog alertDialog = builder.show();
            CalendarView calendarView = alertDialog.findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener((calendarView1, i, i1, i2) -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = simpleDateFormat.parse(i + "-" + i1 + "-" + i2);
                    txtFecNac.setText(simpleDateFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                    date = null;
                }
            });
        });

        btnGuardar.setOnClickListener(view -> {
            DbPersonal dbPersonal = new DbPersonal(PersonalInsert.this);
            long id;
            id = dbPersonal.insertarPersonal(txtNombre.getText().toString(), Integer.parseInt(String.valueOf(txtDni.getText())), txtFecNac.getText().toString(), Integer.parseInt(txtCargo.getText().toString()), Integer.parseInt(txtPais.getText().toString()), txtEstReg.getText().toString());
            if (id > 0){
                Toast.makeText(PersonalInsert.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                limpiar();
            } else
                Toast.makeText(PersonalInsert.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
        });
    }

    private void limpiar(){
        txtEstReg.setText("");
        txtNombre.setText("");
        txtNombre.setText("");
        txtNombre.setText("");
        txtNombre.setText("");
        txtNombre.setText("");
    }
}