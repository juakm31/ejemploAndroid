package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoalistamientos;

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
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;
import com.infotrack.talleres.transversal.enumeradores.EtapasListadoAlistamientoEnum;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListadoAlistamientoAdaptador extends RecyclerView.Adapter<ListadoAlistamientoAdaptador.ViewHolder> {

    //region atributos
    private List<AlistamientoLocalVM> listaAlistamiento;
    private ListadoAlistamientoCallback listadoAlistamientoCallback;
    private Context contexto;
    //endregion

    //region Constructor
    ListadoAlistamientoAdaptador(Context contexto,
                                 List<AlistamientoLocalVM> listaAlistamiento,
                                 ListadoAlistamientoCallback listadoAlistamientoCallback) {
        this.listaAlistamiento = listaAlistamiento;
        this.listadoAlistamientoCallback = listadoAlistamientoCallback;
        this.contexto = contexto;
    }
    //endregion

    //region Propios
    //endregion

    //region Sobrecarga
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listado_alistamiento, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bind(i);

    }

    @Override
    public int getItemCount() {
        return listaAlistamiento.size();
    }
    //endregion

    //region Contenedor
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_alist_placa_vehiculo)
        TextView txtPlacaVehiculo;
        @BindView(R.id.txt_alist_transportadora)
        TextView txtTransportadora;
        @BindView(R.id.txt_alist_conductor)
        TextView txtConductor;
        @BindView(R.id.txt_alist_cantidad_equipos)
        TextView txtCantidadEquipos;
        @BindView(R.id.txt_alist_creador)
        TextView txtCreador;
        @BindView(R.id.card_item_resumen_alistamiento)
        CardView card;
        @BindView(R.id.color_etapa_alistamiento)
        LinearLayout colorEtapa;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int position) {
            final AlistamientoLocalVM alistamiento = listaAlistamiento.get(position);
            txtPlacaVehiculo.setText(alistamiento.getPlaca());
            txtTransportadora.setText(alistamiento.getTransportadora());
            txtConductor.setText(alistamiento.getConductor());
            txtCantidadEquipos.setText(String.format(Locale.getDefault(), "%d", alistamiento.getCantidadEquipos()));
            txtCreador.setText(alistamiento.getCreador());
            colorEtapa.getBackground().setTint(contexto.getResources().getColor(EtapasListadoAlistamientoEnum.valueOf(alistamiento.getEtapa()).getColorEtapa()));
            card.setOnClickListener(v -> listadoAlistamientoCallback.seleccionAlistamientoOnClick(v, position));
        }
    }
    //endregion

    //region Callback
    interface ListadoAlistamientoCallback {
        void seleccionAlistamientoOnClick(View v, int position);
    }
    //endregion
}
