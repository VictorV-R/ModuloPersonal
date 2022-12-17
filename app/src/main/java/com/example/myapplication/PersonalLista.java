package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adaptadores.ListaEstRegAdapter;
import com.example.myapplication.adaptadores.ListaPersonalAdapter;
import com.example.myapplication.db.DbEstReg;
import com.example.myapplication.db.DbPersonal;

public class PersonalLista extends AppCompatActivity {

    RecyclerView listaPersonal;
    Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_lista);

        listaPersonal = findViewById(R.id.lista_personal);
        listaPersonal.setLayoutManager(new LinearLayoutManager(this));
        DbPersonal dbPersonal = new DbPersonal(PersonalLista.this);

        ListaPersonalAdapter adapter = new ListaPersonalAdapter(dbPersonal.mostrarPersonal());
        listaPersonal.setAdapter(adapter);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalLista.this, PersonalInsert.class);
            startActivity(intent);
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.estReg:
                EstReg();
                return true;
            case R.id.paises:
                Pais();
                return true;
            case R.id.cargo:
                Cargo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void EstReg(){
        Intent intent = new Intent(this, EstRegLista.class);
        startActivity(intent);
    }

    private void Pais(){
        Intent intent = new Intent(this, PaisLista.class);
        startActivity(intent);
    }
    private void Cargo(){
        Intent intent = new Intent(this, CargoLista.class);
        startActivity(intent);
    }
}