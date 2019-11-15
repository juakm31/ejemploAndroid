package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoequipos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.transversal.enumeradores.EtapasEquipoEnum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListadoEquiposAdaptador extends RecyclerView.Adapter<ListadoEquiposAdaptador.ViewHolder> {

    //region Atributos
    private List<EquipoLocalVM> listaEquipos;
    private ListadoEquiposCallback listener;
    private Context contexto;
    //endregion

    //region Constructor
    public ListadoEquiposAdaptador(Context contexto,
                                   List<EquipoLocalVM> listaEquipos,
                                   ListadoEquiposCallback listener) {
        this.contexto = contexto;
        this.listaEquipos = listaEquipos;
        this.listener = listener;
    }
    //endregion

    //region Sobrecarga
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listado_equipos, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }
    //endregion

    //region Contenedor
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_placa_congelador) TextView txtPlacaCongelador;
        @BindView(R.id.txt_clase) TextView txtClase;
        @BindView(R.id.txt_capacidad) TextView txtCapacidad;
        @BindView(R.id.txt_creador) TextView txtCreador;
        @BindView(R.id.color_etapa_equipo) LinearLayout colorEtapa;
        @BindView(R.id.card_item)
        CardView container;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int position) {
            final EquipoLocalVM equipo = listaEquipos.get(position);
            txtPlacaCongelador.setText(equipo.getPlaca());
            txtClase.setText(equipo.getClase());
            txtCapacidad.setText(String.valueOf(equipo.getCapacidad()));
            txtCreador.setText(equipo.getCreador());
            colorEtapa.getBackground().setTint(contexto.getResources().getColor(EtapasEquipoEnum.valueOf(equipo.getEtapa()).getColorEtapa()));
            container.setOnClickListener(v -> listener.seleccionEquipoOnClick(v, position));
        }
    }
    //endregion

    //region Callback
    interface ListadoEquiposCallback {
        void seleccionEquipoOnClick(View v, int position);
    }
    //endregion
}
