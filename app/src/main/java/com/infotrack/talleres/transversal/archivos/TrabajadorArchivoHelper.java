package com.infotrack.talleres.transversal.archivos;

import com.infotrack.artefactos.consumomicroservicios.proxy.worker.descriptores.ParametrosTrabajoArchivoDescriptor;
import com.infotrack.artefactos.consumomicroservicios.proxy.worker.proxies.WorkerProxy;
import com.infotrack.artefactos.utilitarios.base.entidades.ParametroConfiguracion;

import java.util.LinkedList;
import java.util.List;

public class TrabajadorArchivoHelper {

    private static TrabajadorArchivoHelper helper;
    private WorkerProxy proxy;

    private TrabajadorArchivoHelper() throws Exception {
        List<ParametroConfiguracion> listaParametros = new LinkedList<>();
        listaParametros.add(construirParametro(ParametrosTrabajoArchivoDescriptor.IntervaloTrabajo, "15"));
        proxy = new WorkerProxy(listaParametros);
    }

    public static TrabajadorArchivoHelper obtenerInstancia() throws Exception {
        if (helper == null) helper = new TrabajadorArchivoHelper();
        return helper;
    }

    private ParametroConfiguracion construirParametro(String nombre, String valor) {
        ParametroConfiguracion parametroConfiguracion = new ParametroConfiguracion();
        parametroConfiguracion.setNombreParametroConfiguracion(nombre);
        parametroConfiguracion.setValorParametroConfiguracion(valor);
        return parametroConfiguracion;
    }

    public boolean iniciarTrabajador() {
        return proxy.iniciarTrabajo();
    }

    public boolean detenerTrabajador() {
        return proxy.detenerTrabajo();
    }
}
