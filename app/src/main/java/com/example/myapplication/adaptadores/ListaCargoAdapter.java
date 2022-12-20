package com.example.myapplication.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CargoDetail;
import com.example.myapplication.R;
import com.example.myapplication.db.DbEstReg;
import com.example.myapplication.entidades.Cargo;
import com.example.myapplication.entidades.EstReg;

import java.util.ArrayList;

public class ListaCargoAdapter extends RecyclerView.Adapter<ListaCargoAdapter.CargoHolder> {

    ArrayList<Cargo> listaCargo;
    String tabla = "Cargo";
    Context context;
    public ListaCargoAdapter(ArrayList<Cargo> listaCargo,Context context){
        this.listaCargo = listaCargo;
        this.context= context;
    }

    @NonNull
    @Override
    public ListaCargoAdapter.CargoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cargo, null, false);
        return new CargoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCargoAdapter.CargoHolder holder, int position) {
        DbEstReg dbEstReg = new DbEstReg(this.context);
        EstReg estReg = dbEstReg.verEstReg(listaCargo.get(position).getEstReg());

        holder.viewCodigoCargo.setText(String.valueOf(listaCargo.get(position).getCodigo()));
        holder.viewNombreCargo.setText(listaCargo.get(position).getNombre());
        holder.viewEstRegCargo.setText(estReg.getNombre());
    }

    @Override
    public int getItemCount() {
        return listaCargo.size();
    }

    public class CargoHolder extends RecyclerView.ViewHolder {

        TextView viewCodigoCargo, viewNombreCargo, viewEstRegCargo;

        public CargoHolder(@NonNull View itemView) {
            super(itemView);

            viewCodigoCargo = itemView.findViewById(R.id.viewCodCargo);
            viewNombreCargo = itemView.findViewById(R.id.viewNombreCargo);
            viewEstRegCargo = itemView.findViewById(R.id.viewEstRegCargo);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, CargoDetail.class);
                intent.putExtra("Tabla", tabla);
                intent.putExtra("ID", listaCargo.get(getAdapterPosition()).getCodigo());
                intent.putExtra("EstRegCod", listaCargo.get(getAdapterPosition()).getEstReg());
                context.startActivity(intent);
            });
        }
    }
}
