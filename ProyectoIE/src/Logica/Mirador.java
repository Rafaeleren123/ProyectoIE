package Logica;

import Logica.Excepciones.DatoInvalidoException;
import Utilidades.*;

public class Mirador extends PuntoInteres{
    private final String[] tipoVistaVal = {"Panoramica", "Paisaje", "Fauna"};
    
    private int tipoVista;
    
    public Mirador(){
        super();
        tipoVista = 0;
    }
    
    public Mirador(int codigo){
        super(codigo);
        tipoVista = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    

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

    
    // ============================================= Getter ============================================= //

    
    public String[] getTipoVistaVal() {
        return tipoVistaVal;
    }
    
    public int getTipoVista() {
        return tipoVista;
    }
    
    
    // ============================================= Setter ============================================= //
    

    public void setTipoVista(int tipoVista) throws DatoInvalidoException{
        boolean opcValido = Validador.esNroValido(tipoVista, 1, tipoVistaVal.length);
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de tipo vista no valida.");
        }
        
        this.tipoVista = tipoVista;
    }
}
