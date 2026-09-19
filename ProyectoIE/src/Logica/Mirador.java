package Logica;

import Utilidades.*;

public class Mirador extends PuntoInteres{
    private final String[] tipoVistaVal = {"Panoramica", "Paisaje", "Fauna"};
    
    private int tipoVista;
    
    public Mirador(){
        tipoVista = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    
    @Override
    public void cargarDato(int codigo) throws DatoInvalidoException{
        Consola.emitirTitulo(3, 40, "=", "Mirador");
        super.cargarDato(codigo);
        leerTipoVista();
        
        Consola.emitirBordeLN(40,"=");
    }

    @Override
    public void mostrarInformacion() {
        Consola.emitirTitulo(3, 40, "-", "Mirador");
        super.mostrarDatoComunes();
        Consola.emitirMensajeLN("| Tipo de vista: "+tipoVistaVal[tipoVista]+" |");
        
        Consola.emitirBordeLN(40, "-");
    }

    @Override
    public String obtenerTipo() {
        return "Mirador";
    }
    
    
    // ============================================= Metodos Privado ============================================= //
    

    private void leerTipoVista() throws DatoInvalidoException{
        int opc;
        boolean opcValido;
        
        Consola.emitirMensajeLN("Tipo de Vista:");
        Consola.emitirLista(tipoVistaVal);
        
        Consola.emitirMensaje("Respuesta: ");
        opc = Lector.leerInt();
        
        opcValido = Validador.esNroValido(opc, 1, tipoVistaVal.length);
        
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de tipo de vista no valida.");
        }
        
        setTipoVista(opc-1);
    }

    
    // ============================================= Getter ============================================= //

    
    public String[] getTipoVistaVal() {
        return tipoVistaVal;
    }
    
    public int getTipoVista() {
        return tipoVista;
    }
    
    
    // ============================================= Setter ============================================= //
    

    private void setTipoVista(int tipoVista) {
        this.tipoVista = tipoVista;
    }
}
