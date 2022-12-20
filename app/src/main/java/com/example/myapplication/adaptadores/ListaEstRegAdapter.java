package com.example.myapplication.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.EstRegLista;
import com.example.myapplication.R;
import com.example.myapplication.EstRegDetail;
import com.example.myapplication.entidades.EstReg;

import java.util.ArrayList;

public class ListaEstRegAdapter extends RecyclerView.Adapter<ListaEstRegAdapter.EstRegHolder> {

    ArrayList<EstReg> listaEstRegs;
    Context context;
    public ListaEstRegAdapter(ArrayList<EstReg> listaEstRegs,Context context){
        this.listaEstRegs = listaEstRegs;
        this.context=context;
    }

    @NonNull
    @Override
    public EstRegHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_est_reg, null, false);
        return new EstRegHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstRegHolder holder, int position) {
        holder.viewCodigo.setText(listaEstRegs.get(position).getCodigo());
        holder.viewNombre.setText(listaEstRegs.get(position).getNombre());
        holder.viewEstReg.setText(listaEstRegs.get(position).getEstado_registro());
    }

    @Override
    public int getItemCount() {
        return listaEstRegs.size();
    }

    public class EstRegHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewEstReg, viewCodigo;

        public EstRegHolder(@NonNull View itemView) {
            super(itemView);

            viewCodigo = itemView.findViewById(R.id.viewEstRegCod);
            viewNombre = itemView.findViewById(R.id.viewNombreEstReg);
            viewEstReg = itemView.findViewById(R.id.viewEstRegPais);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, EstRegDetail.class);
                intent.putExtra("ID", listaEstRegs.get(getAdapterPosition()).getCodigo());
                context.startActivity(intent);
            });
        }


    }
}
