package logica; 

import logica.arbol.*;
import logica.excepciones.*;
import dato.*;
import logica.puntoInteres.Mirador;
import logica.puntoInteres.PuntoInteres;



public class GestorPuntosInteres {
    private RepositorioPuntosInteres repositorio;
    private ArbolABB<PuntoInteres> arbol;
    private GrafoSenderos grafo;
    
    
    public GestorPuntosInteres(){
        repositorio = new RepositorioPuntosArreglo();
        arbol = new ArbolABB<PuntoInteres>();
        grafo = new GrafoSenderos();
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
    public void validarCodigoNoDuplicado(int codigo) throws CodigoDuplicadoException{
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
        grafo.agregarVertice(nuevoPunto);
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
            actual.encabezado();
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
    
    public int getCantPInteres(){
        return repositorio.cantidad();
    }
    
    
    // ================================================================================================================= //
    // ==============================================  Metodos con arbol  ============================================== //
    // ================================================================================================================= //
    
    
    // -------------------------------------------------
    // Verifica si el arbol esta vacio
    // -------------------------------------------------
    
    
    public void validarArbolNoVacio() throws ArbolVacioExcepcion{
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
    
    // preguntar a la profe como hacer que muestre el metodo mostrarInformacion
    public void mostrarInOrden() throws ArbolVacioExcepcion{
        validarArbolNoVacio();
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
        return arbol.buscar(new Mirador(codigo));
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
    
    
    public int[] getEstadistica() {
        return arbol.getEstadistica();
    }
    
    
    // ================================================================================================================= //
    // ==============================================  Metodos con Grafo  ============================================== //
    // ================================================================================================================= //
    
    
    public void verificarGrafoVacio() throws GrafoVacioException{
        if(grafo.esVacio()){
            throw new GrafoVacioException("No hay punto de interes guardados.");
        }
    }
    
    public void validarConexionesDisponibles() throws ConexionAristaLlenoException{
        if(grafo.esCompleto()){
            throw new ConexionAristaLlenoException("Se alcanzo el limite de senderos.");
        }
    }
    
    public void validarHayConexiones() throws ConexionAristaVaciaException{
        if(grafo.noTieneAristas()){
            throw new ConexionAristaVaciaException("No hay senderos guardados.");
        }
    }
    
    public boolean existeConexion(PuntoInteres vertice1, PuntoInteres vertice2){
        return grafo.existeConexion(vertice1, vertice2);
    }
    
    public void agregarSendero(PuntoInteres vertice1, PuntoInteres vertice2, Sendero s){
        grafo.agregarSendero(vertice1, vertice2, s);
    }
    
    public void mostrarSendero(){
        grafo.mostrarConexiones();
    }
    
    public void mostrarPorPI(PuntoInteres vertice){
        grafo.mostrarPorVertice(vertice);
    }
    
    public boolean eliminarSendero(PuntoInteres vertice1, PuntoInteres vertice2){
        return grafo.eliminarSendero(vertice1, vertice2) != null;
    }
    
    public boolean existeCamino(PuntoInteres verticeOrigen, PuntoInteres verticeDestino){
        return grafo.existeCamino(verticeOrigen, verticeDestino);
    }
    
    public void recorridoDFS(PuntoInteres verticeInicio){
        grafo.recorridoDFS(verticeInicio);
    }
    
    public void recorridoBFS(PuntoInteres verticeInicio){
        grafo.recorridoBFS(verticeInicio);
    }
    
    public int cantidadComponente(){
        return grafo.contarComponentes();
    }
}
