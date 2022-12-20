package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPersonal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PersonalInsert extends AppCompatActivity {

    EditText txtNombre, txtDni, txtFecNac, txtCargo, txtPais, txtEstReg;
    Button btnGuardar;
    Date date;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Locale.setDefault(Locale.ENGLISH);

        setContentView(R.layout.activity_personal_insert);

        txtNombre = findViewById(R.id.txtNombrePersonal);
        txtDni = findViewById(R.id.txtDniPersonal);
        txtFecNac = findViewById(R.id.txtFecPersonal);
        txtCargo = findViewById(R.id.txtCargoPersonal);
        txtPais = findViewById(R.id.txtPaisPersonal);
        txtEstReg = findViewById(R.id.txtEstRegPersonal);
        btnGuardar = findViewById(R.id.btnGuardar);

        txtFecNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtFecNac.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }
                        , year, month, day);

                datePickerDialog.show();
            }
        });

        btnGuardar.setOnClickListener(view -> {
            DbPersonal dbPersonal = new DbPersonal(PersonalInsert.this);
            long id;
            id = dbPersonal.insertarPersonal(txtNombre.getText().toString(), Integer.parseInt(String.valueOf(txtDni.getText())), txtFecNac.getText().toString(), Integer.parseInt(txtCargo.getText().toString()), Integer.parseInt(txtPais.getText().toString()), txtEstReg.getText().toString());
            if (id > 0){
                Toast.makeText(PersonalInsert.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                limpiar();
                onNavigateUp();
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