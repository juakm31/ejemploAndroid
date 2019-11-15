package com.infotrack.talleres.presentacion.componentes.menu;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.infotrack.artefactos.utilitarios.base.FragmentoBase;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;
import com.infotrack.talleres.presentacion.vistamodelos.MenuVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.enumeradores.ItemsMenuEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

public class MenuFragmento extends FragmentoBase implements MenuContrato, MenuAdaptador.ListadoMenuCallback {

    //region Atributos
    @Inject
    MenuPresentador presentador;
    @Inject
    INavegador navegador;

    @BindView(R.id.recycler_menu)
    RecyclerView recycler;

    private UsuarioAutenticadoVM usuario;
    private MenuAdaptador adaptador;
    private List<MenuVM> listaMenu;
    //endregion

    //region Constructor
    public MenuFragmento() {
        this.listaMenu = new ArrayList<>();
    }
    //endregion

    //region Instancia
    public static MenuFragmento obtenerInstancia() {
        MenuFragmento fragmento = new MenuFragmento();
        Bundle argumentos = new Bundle();
        fragmento.setArguments(argumentos);
        return fragmento;
    }
    //endregion

    //region Sobrecargas
    @Override
    public int asignarLayout() {
        return R.layout.fragmento_menu;
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) Objects.requireNonNull(getActivity()).getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void extras() {
        usuario = UsuarioSingleton.obtenerInstancia(getContext()).obtenerUsuario();
    }

    @Override
    public void iniciarFragmento(View view, Bundle bundle) {
        ui();
        iniciarPresentador();
    }

    @Override
    public void ui() {
        adaptador = new MenuAdaptador(getContext(), listaMenu, this);
        recycler.addItemDecoration(new SpaceItemDecoration(16));
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler.setAdapter(adaptador);
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
        presentador.cargarBandejaEntrada(usuario.getIdUsuario(), usuario.getIdTaller());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presentador.establecerVista(null);
        presentador.destruir();
    }

    @Override
    public void tareaEnProceso() {

    }

    @Override
    public void tareaTerminada() {

    }

    @Override
    public void llenarMenu(List<MenuVM> menuItems) {
        listaMenu.clear();
        listaMenu.addAll(menuItems);
        adaptador.notifyDataSetChanged();
    }

    @Override
    public void itemSelected(MenuVM menu, int position) {
        ItemsMenuEnum itemMenu = ItemsMenuEnum.valueOf(menu.getCodigoRelacionMovil());
        switch (itemMenu) {
            case Recepcion:
                navegador.navegarListadoAlistamientoActividad(getContext(), TipoAlistamientoEnum.Recepcion);
                break;
            case Despacho:
                navegador.navegarListadoAlistamientoActividad(getContext(), TipoAlistamientoEnum.Despacho);
                break;
            case DiagnosticoInicial:
            case DiagnosticoFinal:
            case BandejaRechazoDiagnosticoInicial:
            case BandejaRechazosDiagnosticoFinal:
            case MantenimientoAgendado:
            case Higiene:
            case ConsultaEquipos:
            case ValidacionMantenimientoDiagnosticoInicial:
            case ValidacionMantenimientoDiagnosticoFinal:
            case BajaRecepcionDiagnosticoInicial:
            case BajaIntervencion:
            case ConsultaEquiposAprobador:
                Toast.makeText(getActivity(), "No implementado", Toast.LENGTH_SHORT).show();
        }
    }

    //endregion
}
