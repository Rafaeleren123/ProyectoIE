    package Logica;

import Logica.Excepciones.DatoInvalidoException;
import Utilidades.*;

public class PuestoServicio extends PuntoInteres{
    private final String[] tipoServicioVal = {
        "Guardaparques", 
        "Primeros auxilios", 
        "Información al visitante"
    };
    
    private int tipoServicio;
    
    public PuestoServicio(){
        super();
        tipoServicio = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    

    @Override
    public void mostrarInformacion() {
        Consola.emitirTitulo(3, 40, "-", "Puesto de servicio");
        super.mostrarDatoComunes();
        Consola.emitirMensajeLN("| tipoServicio: "+tipoServicioVal[tipoServicio]+" |");
        
        Consola.emitirBordeLN(40, "-");
    }

    @Override
    public String obtenerTipo() {
        return "Puesto servicio";
    }
    
    
    // ============================================= Getter ============================================= //

    
    public String[] getTipoServVal() {
        return tipoServicioVal;
    }
    
    public int getTipoServicio() {
        return tipoServicio;
    }

    
    // ============================================= Setter ============================================= //
    
    
    public void setTipoServicio(int tipoServicio) throws DatoInvalidoException{
        boolean opcValido = Validador.esNroValido(tipoServicio, 1, tipoServicioVal.length);
        
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de tipoServicio no valida.");
        }
        
        this.tipoServicio = tipoServicio;
    }
}
