package logica.puntoInteres;

import logica.excepciones.DatoInvalidoException;
import Utilidades.*;

/**
 * Clase que representa la información común a todos los puntos de interés.
 * 
 * Esta clase sirve como clase base para los distintos tipos de puntos
 * de interés del sistema.
 */

public abstract class PuntoInteres implements Comparable<PuntoInteres>{
    private final String[] nivAccesVal = {"muy dificil", "dificil", "moderado", "facil", "muy facil"};
    
    protected int codigo;
    protected String nombre;
    protected double altitud;
    protected int nivelAccesibilidad;

    public PuntoInteres() {
        this.codigo = 0;
        this.nombre = "";
        this.altitud = 0;
        this.nivelAccesibilidad = 0;
    }
    
    public PuntoInteres(int codigo){
        this.codigo = codigo;
        this.nombre = "";
        this.altitud = 0;
        this.nivelAccesibilidad = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    
    public void mostrarDatoComunes(){
        mostCodNom();
        Consola.emitirMensajeLN("| Altitud: "+altitud+" metros |");
        Consola.emitirMensajeLN("| Tipo de accesibilidad: "+nivAccesVal[nivelAccesibilidad]+" |");
    }
    
    public void mostCodNom(){
        Consola.emitirMensajeLN("| Codigo: "+codigo+" | Nombre: "+nombre+" |");
    }
    
    public boolean esMismoCodigo(int codigo){
        return this.codigo == codigo;
    }
    
    /**
     * Determina si el punto de interés posee un nivel de accesibilidad alto.
     *
     * Se consideran niveles altos "muy facil" y "facil".
     *
     * @return true si posee accesibilidad alta; false en caso contrario.
     */
    public boolean esAccesibilidadAlta(){
        return this.nivelAccesibilidad == 3 || this.nivelAccesibilidad == 4;
    }
    
    public abstract void mostrarInformacion();
    public abstract String obtenerTipo();
    
    @Override
    public int compareTo(PuntoInteres otro) {
        if(codigo == otro.getCodigo()){
            return 0;
        }else if(codigo > otro.getCodigo()){
            return 1;
        }
        
        return -1;
    }
    
  
    // ============================================= Getter ============================================= //

    
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getAltitud() {
        return altitud;
    }

    public int getNivelAccesibilidad() {
        return nivelAccesibilidad;
    }

    public String[] getNivAccesVal() {
        return nivAccesVal;
    }
    
    
    // ============================================= Setter ============================================= //
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) throws DatoInvalidoException{
        boolean nombreVacio = Validador.esStringVacio(nombre);
        if(nombreVacio){
            throw new DatoInvalidoException("El nombre esta vacio.");
        }
        
        this.nombre = nombre;
    }

    public void setAltitud(double altitud) throws DatoInvalidoException{
        boolean altitudValido = Validador.esDecimalValido(altitud, 0, 8849);
        if(!altitudValido){
            throw new DatoInvalidoException("La altura esta fuera del rango valido.");
        }
        this.altitud = altitud;
    }

    public void setNivelAccesibilidad(int nivelAccesibilidad) throws DatoInvalidoException{
        boolean opcValido = Validador.esNroValido(nivelAccesibilidad, 1, nivAccesVal.length);
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de nivel de acceso no valida.");
        }
        
        this.nivelAccesibilidad = nivelAccesibilidad-1;
    }
}
