package com.infotrack.talleres.presentacion.componentes.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.vistamodelos.MenuVM;
import com.infotrack.talleres.transversal.enumeradores.ItemsMenuEnum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdaptador extends RecyclerView.Adapter<MenuAdaptador.ViewHolder> {

    //region Atributos
    private List<MenuVM> listaMenu;
    private ListadoMenuCallback listener;
    private Context contexto;
    //endregion

    //region Constructor
    MenuAdaptador(Context contexto, List<MenuVM> listaMenu, ListadoMenuCallback listener) {
        this.listaMenu = listaMenu;
        this.listener = listener;
        this.contexto = contexto;
    }
    //endregion

    //region Sobrecarga
    @NonNull
    @Override
    public MenuAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdaptador.ViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }
    //endregion

    //region ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_menu)
        CardView cardItem;
        @BindView(R.id.enumerador)
        LinearLayout lytEnumerador;
        @BindView(R.id.txtTituloMenu)
        TextView txtTitulo;
        @BindView(R.id.txtContador)
        TextView txtContador;
        @BindView(R.id.txtCasos)
        TextView txtCasos;
        @BindView(R.id.ivIcono)
        ImageView imgIcono;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int position) {
            final MenuVM menu = listaMenu.get(position);
            final ItemsMenuEnum menuEnum = ItemsMenuEnum.valueOf(menu.getCodigoRelacionMovil());
            lytEnumerador.setVisibility(menu.getCantidad() > 0 ? View.VISIBLE : View.INVISIBLE);
            txtCasos.setVisibility(menu.getCantidad() > 0 ? View.VISIBLE : View.INVISIBLE);
            txtTitulo.setText(menu.getDescripcionItem());
            txtContador.setText(String.valueOf(menu.getCantidad()));
            txtCasos.setText(String.format(contexto.getString(R.string.item_menu_label_casos), menu.getCantidad()));
            cardItem.setOnClickListener(v -> listener.itemSelected(menu, position));
            Glide.with(contexto)
                    .load(menuEnum.getRecursoId())
                    .apply(new RequestOptions().placeholder(R.drawable.gradient_carga_imagen))
                    //.apply(RequestOptions.bitmapTransform(new BlurTransformation(5)))
                    .into(imgIcono);
        }
    }
    //endregion

    //region Callback
    interface ListadoMenuCallback {
        void itemSelected(MenuVM menu, int position);
    }
    //endregion
}
