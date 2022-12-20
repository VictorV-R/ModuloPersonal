package com.example.myapplication.adaptadores;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PersonalDetail;
import com.example.myapplication.R;
import com.example.myapplication.db.DbCargo;
import com.example.myapplication.db.DbEstReg;
import com.example.myapplication.db.DbPais;
import com.example.myapplication.entidades.Cargo;
import com.example.myapplication.entidades.EstReg;
import com.example.myapplication.entidades.Pais;
import com.example.myapplication.entidades.Personal;

import java.util.ArrayList;

public class ListaPersonalAdapter extends RecyclerView.Adapter<ListaPersonalAdapter.PersonalHolder> {

    ArrayList<Personal> listaPersonal;
    String tabla = "Personal";
Context context;
    public ListaPersonalAdapter(ArrayList<Personal> listaPersonal,Context context){
        this.listaPersonal = listaPersonal;
        this.context=context;
    }

    @NonNull
    @Override
    public ListaPersonalAdapter.PersonalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_personal, null, false);
        return new PersonalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPersonalAdapter.PersonalHolder holder, int position) {
        DbPais dbPais = new DbPais(this.context);
        DbCargo dbCargo= new DbCargo(this.context);
        DbEstReg dbEstReg =new DbEstReg(this.context);

        Cargo cargo = dbCargo.verCargo(listaPersonal.get(position).getPerCodCar());
        Pais pais = dbPais.verPais(listaPersonal.get(position).getPerCodPai());
        EstReg estReg = dbEstReg.verEstReg(listaPersonal.get(position).getPerEstReg());

        holder.viewCodPersonal.setText(String.valueOf(listaPersonal.get(position).getPerCod()));
        holder.viewNomPersonal.setText(listaPersonal.get(position).getPerNom());
        holder.viewDniPersonal.setText(String.valueOf(listaPersonal.get(position).getPerDni()));
        holder.viewFecPersonal.setText(listaPersonal.get(position).getPerFecNac());
        holder.viewCarPersonal.setText(cargo.getNombre() );
        holder.viewPaiPersonal.setText(pais.getNombre());
        holder.viewEstPersonal.setText(estReg.getNombre());

    }

    @Override
    public int getItemCount() {
        return listaPersonal.size();
    }

    public class PersonalHolder extends RecyclerView.ViewHolder {

        TextView viewCodPersonal, viewNomPersonal, viewDniPersonal, viewFecPersonal, viewCarPersonal, viewPaiPersonal, viewEstPersonal;

        public PersonalHolder(@NonNull View itemView) {
            super(itemView);

            viewCodPersonal = itemView.findViewById(R.id.viewCodPersonal);
            viewNomPersonal = itemView.findViewById(R.id.viewNombrePersonal);
            viewDniPersonal = itemView.findViewById(R.id.viewDniPersonal);
            viewFecPersonal = itemView.findViewById(R.id.viewFecPersonal);
            viewCarPersonal = itemView.findViewById(R.id.viewCargoPersonal);
            viewPaiPersonal = itemView.findViewById(R.id.viewPaisPersonal);
            viewEstPersonal = itemView.findViewById(R.id.viewEstRegPersonal);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, PersonalDetail.class);
                intent.putExtra("ID", listaPersonal.get(getAdapterPosition()).getPerCod());
                intent.putExtra("EstRegCod", listaPersonal.get(getAdapterPosition()).getPerEstReg());
                intent.putExtra("Tabla", tabla);
                context.startActivity(intent);
            });

        }
    }
}
