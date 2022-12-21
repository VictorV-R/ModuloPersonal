package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_personal = findViewById(R.id.btn_personal);
        Button btn_pais = findViewById(R.id.btn_pais);
        Button btn_cargo = findViewById(R.id.btn_cargo);
        Button btn_estado = findViewById(R.id.btn_estado);

        btn_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadActivityPersonal();
            }
        });

        btn_pais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadActivityPais();
            }
        });

        btn_cargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadActivityCargo();
            }
        });

        btn_estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadActivityEstado();
            }
        });
    }

    public void loadActivityPersonal(){
        Intent intent = new Intent(this, PersonalLista.class);
        startActivity(intent);
    }

    public void loadActivityPais(){
        Intent intent = new Intent(this, PaisLista.class);
        startActivity(intent);
    }

    public void loadActivityCargo(){
        Intent intent = new Intent(this, CargoLista.class);
        startActivity(intent);
    }

    public void loadActivityEstado(){
        Intent intent = new Intent(this, EstRegLista.class);
        startActivity(intent);
    }
}