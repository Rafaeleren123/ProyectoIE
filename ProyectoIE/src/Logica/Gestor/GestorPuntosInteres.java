package Logica.Gestor; 

import Logica.Excepciones.*;
import Dato.*;
import Logica.Arbol.ArbolABB;
import Logica.Mirador;
import Logica.Arbol.NodoABB;
import Logica.PuntoInteres;

public class GestorPuntosInteres {
    private RepositorioPuntosInteres repositorio;
    private ArbolABB<PuntoInteres> arbol;
    
    public GestorPuntosInteres(){
        repositorio = new RepositorioPuntosArreglo();
        this.arbol = new ArbolABB<PuntoInteres>();
    }
    
    
    // ================================================================================================================= //
    // ===========================================  Metodos con repositorio  =========================================== //
    // ================================================================================================================= //
    
    
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
    
    
    // -------------------------------------------------
    // Metodo cargar
    // -------------------------------------------------
    
    /**
     * Agrega un punto de interés al repositorio.
     *
     * @param nuevoPunto punto de interés que se desea almacenar.
     */
    public void cargar(PuntoInteres nuevoPunto) throws RepositorioLlenoException{
        repositorio.agregar(nuevoPunto);
        arbol.insertar(nuevoPunto);
    }
    
    
    // -------------------------------------------------
    // Metodos mostrar
    // -------------------------------------------------
    
    /**
     * Inicia el recorrido recursivo de los puntos de interés almacenados.
     */
    public void mostrar() throws RepositorioVacioException{
        estaVacio();
        
        mostrarRecursivo(0);
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
    
    
    // -------------------------------------------------
    // Metodo Buscar
    // -------------------------------------------------
    
    /**
     * Busca un punto de interés mediante su código.
     *
     * @param codigo código del punto de interés a buscar.
     * @return el punto de interés encontrado o null si no existe.
     */
    public PuntoInteres buscarCodigo(int codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }
    
    
    // -------------------------------------------------
    // Metodos contadores
    // -------------------------------------------------
    
    
    /**
     * Cuenta la cantidad de puntos de interés que poseen un nivel
     * de accesibilidad considerado alto.
     *
     * @return cantidad de puntos con accesibilidad alta.
     */
    public int contarAccesibilidadAlta() throws RepositorioVacioException{
        estaVacio();
        
        return contarAccesibilidadAltaRecursivo(0);
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
    
    
    // -------------------------------------------------
    // Metodo para determinar mayo altitud
    // -------------------------------------------------
    
    /**
     * Obtiene el punto de interés que posee la mayor altitud.
     *
     * @return punto de interés con mayor altitud.
     */
    public PuntoInteres determinarMayorAltitud() throws RepositorioVacioException{
        estaVacio();
        
        return mayorAltitudRecursivo(0);
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
    
    
    // -------------------------------------------------
    // Metodo para determinar promedio
    // -------------------------------------------------
    
    /**
     * Calcula el promedio de altitud de los puntos de interés almacenados.
     *
     * @return promedio de las altitudes.
     */
    public double promedioAltitud(){
        if(repositorio.estaVacio()){
            return 0;
        }
        
        double sumaTotal = sumarAltitudesRecursivo(0);
        int cantidad = repositorio.cantidad();
        
        return sumaTotal/cantidad;
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
     * Verifica si el repositorio se encuentra vacío.
     *
     * @throws RepositorioVacioException si no existen puntos de interés almacenados.
     */
    public void estaVacio()throws RepositorioVacioException{
        if(repositorio.estaVacio()){
            throw new RepositorioVacioException("No hay puntos de intereses guardados.");
        }
    }
    
    
    // ================================================================================================================= //
    // ==============================================  Metodos con arbol  ============================================== //
    // ================================================================================================================= //
    
    
    // -------------------------------------------------
    // Verifica si el arbol esta vacio
    // -------------------------------------------------
    
    
    public void arbolVacio() throws ArbolVacioExcepcion{
        if(arbol.estaVacio()){
            throw new ArbolVacioExcepcion("No hay punto de interes guardado.");
        }
    }
    
    
    // -------------------------------------------------
    // Reconstruir
    // -------------------------------------------------
    
    
    /**
    * Reconstruye el índice del árbol a partir de todos los puntos
    * almacenados en el repositorio.
    *
    */
    public void construirIndice() {
        arbol = new ArbolABB<PuntoInteres>();
        
        for (int i = 0; i < repositorio.cantidad(); i++) {
            arbol.insertar(repositorio.obtener(i));
        }
    }
    
    
    // -------------------------------------------------
    // Mostrar inOrden
    // -------------------------------------------------
    
    
    public void mostrarInOrden() throws ArbolVacioExcepcion{
        arbolVacio();
        mostrarInOrdenRecursivo(arbol.getRaiz());
    }
    
    private void mostrarInOrdenRecursivo(NodoABB<PuntoInteres> nodo){
        if (nodo != null) {
            mostrarInOrdenRecursivo(nodo.getIzquierdo());
            
            PuntoInteres p = nodo.getDato();
            
            p.mostrarInformacion();
            
            mostrarInOrdenRecursivo(nodo.getDerecho());
        }
    }
    
    
    // -------------------------------------------------
    // Buscar Punto interes por codigo
    // -------------------------------------------------
    
    
    /**
    * Busca un punto de interés mediante su código.
    *
    * @param codigo código del punto de interés a buscar.
    * @return el punto encontrado o null si no existe.
    */
    public PuntoInteres buscarPorCodigo(int codigo) {
        return buscarCodigoRecursivo(arbol.getRaiz(), new Mirador(codigo));
    }

    private PuntoInteres buscarCodigoRecursivo(NodoABB<PuntoInteres> nodo, PuntoInteres dato) {
        if (nodo == null) {
            return null;
        }
        
        PuntoInteres actual = nodo.getDato();
        int comparacion = dato.compareTo(actual);
        
        if (comparacion == 0) {
            return actual;
        }
        if (comparacion < 0) {
            return buscarCodigoRecursivo(nodo.getIzquierdo(), dato);
        }
        return buscarCodigoRecursivo(nodo.getDerecho(), dato);
    }
    
    
    // -------------------------------------------------
    // Eliminar un PuntoInteres por codigo
    // -------------------------------------------------
    
    
    public PuntoInteres eliminarPorCodigo(int codigo){
        PuntoInteres p = buscarCodigo(codigo);
        
        if(p != null){
            arbol.eliminar(p);
        }
        
        return p;
    }
    
    
    // -------------------------------------------------
    // Retornar las estadisticas del arbol
    // -------------------------------------------------
    
    
    /**
    * Obtiene estadísticas del árbol: cantidad de nodos,
    * altura, cantidad de hojas y cantidad de nodos internos.
    *
    * @return arreglo con las estadísticas del árbol.
    * @throws ArbolVacioExcepcion si el árbol está vacío.
    */
    public int[] getEstadistica() throws ArbolVacioExcepcion{
        arbolVacio();
        
        int[] estadisticas = new int[4];
        estadisticas[0] = cantNodoRecursivo(arbol.getRaiz());
        estadisticas[1] = alturaArbolRecursivo(arbol.getRaiz());
        estadisticas[2] = cantHojasRecursivo(arbol.getRaiz());
        estadisticas[3] = canNodoInterRecursivo(arbol.getRaiz());
        
        return estadisticas;
    }
    
    private int cantNodoRecursivo(NodoABB<PuntoInteres> nodo){
        if (nodo == null) {
            return 0;
        }
        
        return 1 + cantNodoRecursivo(nodo.getIzquierdo()) + cantNodoRecursivo(nodo.getDerecho());
    }
    
    /**
    * Calcula recursivamente la altura del árbol considerando
    * la cantidad de nodos del camino más largo desde la raíz.
    */
    private int alturaArbolRecursivo(NodoABB<PuntoInteres> nodo){
        if (nodo == null) {
            return 0;
        }

        int alturaIzquierda = alturaArbolRecursivo(nodo.getIzquierdo());
        int alturaDerecha = alturaArbolRecursivo(nodo.getDerecho());

        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
    
    private int cantHojasRecursivo(NodoABB<PuntoInteres> nodo){
        if (nodo == null) {
            return 0;
        }

        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            return 1;
        }

        return cantHojasRecursivo(nodo.getIzquierdo()) + cantHojasRecursivo(nodo.getDerecho());
    }

    private int canNodoInterRecursivo(NodoABB<PuntoInteres> nodo) {
        if (nodo == null) {
            return 0;
        }

        int cantidad = canNodoInterRecursivo(nodo.getIzquierdo()) + canNodoInterRecursivo(nodo.getDerecho());

        if (nodo.getIzquierdo() != null || nodo.getDerecho() != null) {
            cantidad++;
        }

        return cantidad;
    }
    
    
    
    
    
    
    
    
    
}
