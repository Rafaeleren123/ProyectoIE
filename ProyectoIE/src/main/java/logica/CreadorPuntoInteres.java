package logica;

import logica.puntoInteres.*;

public class CreadorPuntoInteres {
    /**
     * Crea una instancia del tipo de punto de interés correspondiente
     * según la opción recibida.
     *
     * @param tipoPinteres  opción que determina el tipo de punto de interés a crear.
     *             1: Mirador, 2: Recurso Natural, otra opción: Puesto de Servicio.
     * @return una instancia de una clase concreta que hereda de PuntoInteres.
     */
    public static PuntoInteres getTipoPInteres(int tipoPinteres) {
        if(tipoPinteres == 1){
            return new Mirador();
        }
        
        if(tipoPinteres == 2){
            return new RecursoNatural();
        }
       
        return new PuestoServicio();
    }
}
