package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado;

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
import com.infotrack.talleres.presentacion.vistamodelos.CasoVM;
import com.infotrack.talleres.transversal.enumeradores.EtapasListadoDiagnosticoRechazoEnum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListadoDiagnosticoRechazoAdaptador extends RecyclerView.Adapter<ListadoDiagnosticoRechazoAdaptador.ViewHolder> {

    //region atributos
    private List<CasoVM> listaCasos;
    private ListadoDiagnosticoRechazoCallback listadoDiagnosticoRechazoCallback;
    private Context contexto;
    //endregion

    //region Constructor
    ListadoDiagnosticoRechazoAdaptador(Context contexto,
                                       List<CasoVM> listaCasos,
                                       ListadoDiagnosticoRechazoCallback listadoDiagnosticoRechazoCallback) {
        this.listaCasos = listaCasos;
        this.listadoDiagnosticoRechazoCallback = listadoDiagnosticoRechazoCallback;
        this.contexto = contexto;
    }
    //endregion

    //region Propios
    //endregion

    //region Sobrecarga
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listado_diagnostico_rechazo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bind(i);

    }

    @Override
    public int getItemCount() {
        return listaCasos.size();
    }
    //endregion

    //region Contenedor
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_diagrech_placa_congelador)
        TextView txtPlacaCongelador;
        @BindView(R.id.txt_diagrech_caso)
        TextView txtCaso;
        @BindView(R.id.txt_diagrech_clase)
        TextView txtClase;
        @BindView(R.id.txt_diagrech_fabricante)
        TextView txtFabricante;
        @BindView(R.id.txt_diagrech_capacidad)
        TextView txtCapacidad;
        @BindView(R.id.txt_diagrech_centro)
        TextView txtCentro;
        @BindView(R.id.txt_diagrech_tecnico)
        TextView txtTecnico;
        @BindView(R.id.card_item_diagnostico_rechazo)
        CardView card;
        @BindView(R.id.color_etapa_caso)
        LinearLayout colorEtapaCaso;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int position) {
            final CasoVM caso = listaCasos.get(position);
            txtPlacaCongelador.setText(caso.getPlaca());
            txtCaso.setText(caso.getIdCaso());
            txtClase.setText(caso.getClase());
            txtFabricante.setText(caso.getFabricante());
            txtCapacidad.setText(caso.getCapacidad());
            txtCentro.setText(caso.getCentroplanificador());
            txtTecnico.setText(caso.getTecnico());
            colorEtapaCaso.getBackground().setTint(contexto.getResources().getColor(EtapasListadoDiagnosticoRechazoEnum.valueOf(caso.getEtapa()).getColorEtapa()));
            card.setOnClickListener(v -> listadoDiagnosticoRechazoCallback.seleccionAlistamientoOnClick(v, position));
        }
    }
    //endregion

    //region Callback
    interface ListadoDiagnosticoRechazoCallback {
        void seleccionAlistamientoOnClick(View v, int position);
    }
    //endregion
}
