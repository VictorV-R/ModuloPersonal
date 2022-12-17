package com.example.myapplication.adaptadores;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entidades.EstReg;

import java.util.ArrayList;

public class ListaEstRegSelectAdapter extends RecyclerView.Adapter<ListaEstRegSelectAdapter.EstRegSelectHolder> {

    public ArrayList<EstReg> listaEstRegs;
    public EstReg estRegSelected;
    int selected_position = 0;

    public ListaEstRegSelectAdapter(ArrayList<EstReg> listaEstRegs){
        this.listaEstRegs = listaEstRegs;
        estRegSelected = listaEstRegs.get(0);
    }

    @NonNull
    @Override
    public ListaEstRegSelectAdapter.EstRegSelectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_select_est_reg, null, false);
        return new ListaEstRegSelectAdapter.EstRegSelectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstRegSelectHolder holder, int position) {
        holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);
        holder.viewNombre.setText(listaEstRegs.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaEstRegs.size();
    }

    public class EstRegSelectHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;

        public EstRegSelectHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombreEstReg);

            itemView.setOnClickListener(view -> {
                notifyItemChanged(selected_position);
                selected_position = getAdapterPosition();
                notifyItemChanged(selected_position);
                estRegSelected = listaEstRegs.get(getAdapterPosition());
            });
        }
    }
}
