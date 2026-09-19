package Logica; 

import Dato.*;

public class GestorPuntosInteres {
    private RepositorioPuntosInteres repositorio;
    
    public GestorPuntosInteres(){
        repositorio = new RepositorioPuntosArreglo();
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    
    /**
     * Verifica que el código recibido no se encuentre registrado.
     *
     * @param codigo código que se desea comprobar.
     * @throws CodigoDuplicadoException si el código ya se encuentra registrado.
     */
    public void existeCodigo(int codigo) throws CodigoDuplicadoException{
        if(repositorio.existeCodigo(codigo)){
            throw new CodigoDuplicadoException("El codigo '"+codigo+"' ya existe.");
        }
    }
    
    /**
     * Agrega un punto de interés al repositorio.
     *
     * @param nuevoPunto punto de interés que se desea almacenar.
     */
    public void cargar(PuntoInteres nuevoPunto) {
        repositorio.agregar(nuevoPunto);
    }
    
    /**
     * Inicia el recorrido recursivo de los puntos de interés almacenados.
     */
    public void mostrar(){
        mostrarRecursivo(0);
    }
    
    /**
     * Muestra los códigos disponibles junto con el nombre de cada punto.
     */
    public void mostCodDisponible(){
        PuntoInteres actual;
        for (int i = 0; i < repositorio.cantidad(); i++) {
            actual = repositorio.obtener(i);
            actual.mostCodNom();
        }
    }
    
    /**
     * Busca un punto de interés mediante su código.
     *
     * @param codigo código del punto de interés a buscar.
     * @return el punto de interés encontrado o null si no existe.
     */
    public PuntoInteres buscarCodigo(int codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }
    
    /**
     * Cuenta la cantidad de puntos de interés que poseen un nivel
     * de accesibilidad considerado alto.
     *
     * @return cantidad de puntos con accesibilidad alta.
     */
    public int contarAccesibilidadAlta(){
        return contarAccesibilidadAltaRecursivo(0);
    }
    
    /**
     * Cuenta la cantidad de puntos de interés que pertenecen al tipo indicado.
     * El recorrido se realiza mediante un algoritmo recursivo.
     *
     * @param tipoPInte tipo de punto de interés que se desea contar.
     * @return cantidad de puntos que pertenecen al tipo indicado.
     */
    public int CantPorTipo(String tipoPInte){
        return contPorTipoRecursivo(0, tipoPInte);
    }
    
    /**
     * Obtiene el punto de interés que posee la mayor altitud.
     *
     * @return punto de interés con mayor altitud.
     */
    public PuntoInteres determinarMayorAltitud(){
        return mayorAltitudRecursivo(0);
    }
    
    /**
     * Calcula el promedio de altitud de los puntos de interés almacenados.
     *
     * @return promedio de las altitudes.
     */
    public double promedioAltitud(){
        double sumaTotal = sumarAltitudesRecursivo(0);
        int cantidad = repositorio.cantidad();
        
        return sumaTotal/cantidad;
    }
    
    /**
     * Verifica si el repositorio se encuentra lleno.
     *
     * @throws RepositorioLlenoException si no queda espacio disponible
     *         para almacenar nuevos puntos de interés.
     */
    public void estaLleno() throws RepositorioLlenoException{
        if(repositorio.estaLleno()){
            throw new RepositorioLlenoException("No queda mas espacio.");
        }
    }
    
    /**
     * Verifica si el repositorio se encuentra vacío.
     *
     * @throws RepositorioVacioException si no existen puntos de interés almacenados.
     */
    public void estaVacio()throws RepositorioVacioException{
        if(repositorio.estaVacio()){
            throw new RepositorioVacioException("No hay puntos de intereses guardados.");
        }
    }
    
    
    // ============================================= Metodos Privado ============================================= //
    
    
    /**
     * Recorre recursivamente los puntos de interés y cuenta aquellos
     * cuyo tipo coincide con el tipo recibido.
     *
     * @param posicion posición actual dentro del repositorio.
     * @param tipoPInte tipo de punto de interés que se desea contar.
     * @return cantidad de puntos encontrados del tipo indicado.
     */
    private int contPorTipoRecursivo(int posicion, String tipoPInte){
        //caso base si llegamos al final
        if(posicion >= repositorio.cantidad()){
            return 0;
        }
        
        PuntoInteres actual = repositorio.obtener(posicion);
        
        if(actual.obtenerTipo().equalsIgnoreCase(tipoPInte)){
            return 1 + contPorTipoRecursivo(posicion+1, tipoPInte);
        }
        
        return contPorTipoRecursivo(posicion+1, tipoPInte);
    }
    
    /**
     * Recorre recursivamente los puntos de interés y cuenta aquellos
     * que poseen accesibilidad alta.
     *
     * @param posicion posición actual dentro del repositorio.
     * @return cantidad de puntos con accesibilidad alta.
     */
    private int contarAccesibilidadAltaRecursivo(int posicion){
        //caso base si se llega al final
        if(posicion >= repositorio.cantidad()){
            return 0;
        }
        
        PuntoInteres actual = repositorio.obtener(posicion);
        
        if(actual.esAccesibilidadAlta()){
            return 1 + contarAccesibilidadAltaRecursivo(posicion+1);
        }
        
        return contarAccesibilidadAltaRecursivo(posicion+1);
    }
    
    /**
     * Suma recursivamente las altitudes de todos los puntos de interés.
     *
     * @param posicion posición actual dentro del repositorio.
     * @return suma de las altitudes.
     */
    private double sumarAltitudesRecursivo(int posicion){
        PuntoInteres actual = repositorio.obtener(posicion);
        
        //caso base si se llega al final
        if (posicion == repositorio.cantidad() - 1) {
            return actual.getAltitud();
        }
        
        return actual.getAltitud() + sumarAltitudesRecursivo(posicion+1);
    }
    
    /**
     * Busca recursivamente el punto de interés con mayor altitud.
     *
     * @param posicion posición actual desde la cual se realiza la búsqueda.
     * @return punto de interés con mayor altitud.
     */
    private PuntoInteres mayorAltitudRecursivo(int posicion){
        //caso base si llega al final del arreglo
        if (posicion == repositorio.cantidad() - 1) {
            return repositorio.obtener(posicion);
        }

        PuntoInteres actual = repositorio.obtener(posicion);
        PuntoInteres mayor = mayorAltitudRecursivo(posicion + 1);

        if (actual.getAltitud() > mayor.getAltitud()) {
            return actual;
        }

        return mayor;
    }
    
    /**
     * Recorre recursivamente todos los puntos de interés almacenados
     * y solicita a cada objeto que muestre su información.
     *
     * @param posicion posición actual dentro del repositorio.
     */
    private void mostrarRecursivo(int posicion){
        //caso base retorna y sale cuando la posicion alcance la cantidad
        if(posicion == repositorio.cantidad()){
            return;
        }
        
        PuntoInteres actual = repositorio.obtener(posicion);
        actual.mostrarInformacion();
        
        mostrarRecursivo(posicion+1);
    }
}
