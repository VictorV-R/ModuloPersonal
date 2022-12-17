package com.example.myapplication.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PaisDetail;
import com.example.myapplication.R;
import com.example.myapplication.entidades.Pais;

import java.util.ArrayList;

public class ListaPaisAdapter extends RecyclerView.Adapter<ListaPaisAdapter.PaisHolder> {

    ArrayList<Pais> listaPaises;
    String tabla = "Pais";

    public ListaPaisAdapter(ArrayList<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @NonNull
    @Override
    public ListaPaisAdapter.PaisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_pais, null, false);
        return new PaisHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPaisAdapter.PaisHolder holder, int position) {
        holder.viewCodigoPai.setText(String.valueOf(listaPaises.get(position).getCodigo()));
        holder.viewNombre.setText(listaPaises.get(position).getNombre());
        holder.viewEstReg.setText(listaPaises.get(position).getEstReg());
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    public class PaisHolder extends RecyclerView.ViewHolder {

        TextView viewCodigoPai, viewNombre, viewEstReg;

        public PaisHolder(@NonNull View itemView) {
            super(itemView);

            viewCodigoPai = itemView.findViewById(R.id.viewCodPais);
            viewNombre = itemView.findViewById(R.id.viewNombrePai);
            viewEstReg = itemView.findViewById(R.id.viewPaiEstReg);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, PaisDetail.class);
                intent.putExtra("ID", listaPaises.get(getAdapterPosition()).getCodigo());
                intent.putExtra("EstRegCod", listaPaises.get(getAdapterPosition()).getEstReg());
                intent.putExtra("Tabla", tabla);
                context.startActivity(intent);
            });
        }
    }
}
