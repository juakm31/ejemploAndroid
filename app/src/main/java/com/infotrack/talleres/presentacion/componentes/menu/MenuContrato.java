package com.infotrack.talleres.presentacion.componentes.menu;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.presentacion.vistamodelos.MenuVM;

import java.util.List;

public interface MenuContrato extends PresentadorBase.VistaBase {

    void llenarMenu(List<MenuVM> menuItems);
}
