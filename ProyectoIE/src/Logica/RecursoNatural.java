package Logica;

import Utilidades.*;

public class RecursoNatural extends PuntoInteres {
    private final String[] tipoServVal = {"Guardaparques", "Primeros auxilios", "Informacion al visitante"};
    
    private int tipoServicio;
    
    public RecursoNatural(){
        tipoServicio = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    
    @Override
    public void cargarDato(int codigo) throws DatoInvalidoException{
        Consola.emitirTitulo(3, 40, "=", "Recurso naturales");
        super.cargarDato(codigo);
        leerTipoServ();
        
        Consola.emitirBordeLN(40,"=");
    }

    @Override
    public void mostrarInformacion() {
        Consola.emitirTitulo(3, 40, "-", "Recurso natural");
        super.mostrarDatoComunes();
        Consola.emitirMensajeLN("| Tipo de servicio: "+tipoServVal[tipoServicio]+" |");
        
        Consola.emitirBordeLN(40, "-");
    }

    @Override
    public String obtenerTipo() {
        return "Recurso natural";
    }
    
    
    // ============================================= Metodos Privados ============================================= //
    
    
    private void leerTipoServ() throws DatoInvalidoException{        
        int opc;
        boolean opcValido;
        
        Consola.emitirMensajeLN("Tipo de servicio:");
        Consola.emitirLista(tipoServVal);
        
        Consola.emitirMensaje("Respuesta: ");
        opc = Lector.leerInt();
        
        opcValido = Validador.esNroValido(opc, 1, tipoServVal.length);
        
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de categoria no valida.");
        }
        
        setTipoServicio(opc-1);
    }
    
    
    // ============================================= Getter ============================================= //
    

    public String[] getTipoServVal() {
        return tipoServVal;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }
    
    
    // ============================================= Setter ============================================= //
    

    private void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}
