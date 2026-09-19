package Logica;

import Utilidades.*;

/**
 * Clase que representa la información común a todos los puntos de interés.
 * 
 * Esta clase sirve como clase base para los distintos tipos de puntos
 * de interés del sistema.
 */

public abstract class PuntoInteres {
    private final String[] nivAccesVal = {"muy dificil", "dificil", "moderado", "facil", "muy facil"};
    
    protected int codigo;
    protected String nombre;
    protected double altitud;
    protected int nivelAccesivilidad;

    public PuntoInteres() {
        this.codigo = 0;
        this.nombre = "";
        this.altitud = 0;
        this.nivelAccesivilidad = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    
    /**
     * Solicita y carga los datos generales del punto de interés.
     *
     * @throws DatoInvalidoException si alguno de los datos ingresados
     *         no cumple con las condiciones establecidas.
     */
    public void cargarDato(int codigo) throws DatoInvalidoException{
        setCodigo(codigo);
        leerNombre();
        leerAltitud();
        leerNivelAccesivilidad();
    }
    
    public void mostrarDatoComunes(){
        mostCodNom();
        Consola.emitirMensajeLN("| Altitud: "+altitud+" metros |");
        Consola.emitirMensajeLN("| Tipo de accesibilidad: "+nivAccesVal[nivelAccesivilidad]+" |");
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
     * Se consideran niveles altos "muy difícil" y "difícil".
     *
     * @return true si posee accesibilidad alta; false en caso contrario.
     */
    public boolean esAccesibilidadAlta(){
        return this.nivelAccesivilidad == 0 || this.nivelAccesivilidad == 1;
    }
    
    public abstract void mostrarInformacion();
    public abstract String obtenerTipo();
    
    
    // ============================================= Metodos Privado ============================================= //

    
    private void leerNombre() throws DatoInvalidoException{
        String nombre;
        boolean nombreVacio;
        
        Consola.emitirMensaje("Ingrese nombre: ");
        nombre = Lector.leerString();
        
        nombreVacio = Validador.esStringVacio(nombre);
        
        if(nombreVacio){
            throw new DatoInvalidoException("El nombre esta vacio.");
        }
        
        setNombre(nombre);
    }

    private void leerAltitud() throws DatoInvalidoException{
        double altitud;
        boolean altitudValido;
         
        Consola.emitirMensaje("Ingrese altitud: "); 
        altitud = Lector.leerDouble();
        
        altitudValido = Validador.esDecimalValido(altitud, 0, 8849);
        
        if(!altitudValido){
            throw new DatoInvalidoException("La altura esta fuera del rango valido.");
        }
        
        setAltitud(altitud);
    }

    private void leerNivelAccesivilidad() throws DatoInvalidoException{
        int opc;
        boolean opcValido;
        
        Consola.emitirMensajeLN("Tipo de accesivilidad:");
        Consola.emitirLista(nivAccesVal);
        
        Consola.emitirMensaje("Respuesta: ");
        opc = Lector.leerInt();
        
        opcValido = Validador.esNroValido(opc, 1, nivAccesVal.length);
        
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de nivel de acceso no valida.");
        }
        
        setNivelAccesivilidad(opc-1);
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

    public int getNivelAccesivilidad() {
        return nivelAccesivilidad;
    }
    
    
    // ============================================= Setter ============================================= //
    

    private void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setAltitud(double altitud) {
        this.altitud = altitud;
    }

    private void setNivelAccesivilidad(int nivelAccesivilidad) {
        this.nivelAccesivilidad = nivelAccesivilidad;
    }
}
