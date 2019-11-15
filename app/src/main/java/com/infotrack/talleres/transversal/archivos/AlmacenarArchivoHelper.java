package com.infotrack.talleres.transversal.archivos;

import com.infotrack.artefactos.consumomicroservicios.proxy.archivos.descriptores.ArchivoExtensionDescriptor;
import com.infotrack.artefactos.consumomicroservicios.proxy.archivos.descriptores.ParametrosArchivoDescriptor;
import com.infotrack.artefactos.consumomicroservicios.proxy.archivos.entidades.ArchivoBuilder;
import com.infotrack.artefactos.consumomicroservicios.proxy.archivos.proxies.ArchivosProxy;
import com.infotrack.artefactos.utilitarios.base.entidades.ParametroConfiguracion;
import com.infotrack.talleres.presentacion.vistamodelos.ArchivoChecklist;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import java.util.LinkedList;
import java.util.List;

public class AlmacenarArchivoHelper {

    private static AlmacenarArchivoHelper helper;
    private ArchivosProxy proxy;

    private AlmacenarArchivoHelper() throws Exception {
        List<ParametroConfiguracion> listaParametros = new LinkedList<>();
        listaParametros.add(construirParametro(ParametrosArchivoDescriptor.IdAplicacion, ConstantesCompartidas.IDENTIFICADOR_APLICACION));
        listaParametros.add(construirParametro(ParametrosArchivoDescriptor.UrlServicio, ConstantesCompartidas.RETROFIT_ARCHIVOS));
        listaParametros.add(construirParametro(ParametrosArchivoDescriptor.ComprimeImagen, "false"));
        listaParametros.add(construirParametro(ParametrosArchivoDescriptor.PorcentajeCompresion, "0"));
        proxy = new ArchivosProxy(listaParametros);
    }

    public static AlmacenarArchivoHelper obtenerInstancia() throws Exception {
        if (helper == null) {
            helper = new AlmacenarArchivoHelper();
        }
        return helper;
    }

    private ParametroConfiguracion construirParametro(String nombre, String valor) {
        ParametroConfiguracion parametroConfiguracion = new ParametroConfiguracion();
        parametroConfiguracion.setNombreParametroConfiguracion(nombre);
        parametroConfiguracion.setValorParametroConfiguracion(valor);
        return parametroConfiguracion;
    }

    public void almacenar(ArchivoChecklist archivoChecklist) {
        ArchivoBuilder archivo = ArchivoBuilder.builder()
                .setRuta(archivoChecklist.getRutaLocal())
                .setExtension(ArchivoExtensionDescriptor.JPG)
                .setFechaArchivo(archivoChecklist.getFechaArchivo())
                .setMetadata(archivoChecklist.getMetadatos())
                .setNombre(archivoChecklist.getNombreArchivo())
                .setUsuario(archivoChecklist.getIdUsuario())
                .build();

        proxy.AlmacenarArchivo(archivo);
    }
}
