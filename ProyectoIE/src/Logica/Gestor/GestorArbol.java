package Logica.Gestor;

import Logica.ArbolABB;
import Logica.Excepciones.ArbolVacioExcepcion;
import Logica.Mirador;
import Logica.NodoABB;
import Logica.PuntoInteres;
import Dato.RepositorioPuntosInteres;

public class GestorArbol {
    private ArbolABB<PuntoInteres> arbol;

    public GestorArbol() {
        this.arbol = new ArbolABB<PuntoInteres>();
    }
    
    public void arbolVacio() throws ArbolVacioExcepcion{
        if(arbol.estaVacio()){
            throw new ArbolVacioExcepcion("No hay punto de interes guardado.");
        }
    }
    
    public void insertar(PuntoInteres punto) {
        arbol.insertar(punto);
    }
    
    /**
    * Reconstruye el índice del árbol a partir de todos los puntos
    * almacenados en el repositorio.
    *
    * @param repo repositorio que contiene los puntos de interés.
    */
    public void construirIndice(RepositorioPuntosInteres repo) {
        arbol = new ArbolABB<PuntoInteres>();
        
        for (int i = 0; i < repo.cantidad(); i++) {
            arbol.insertar(repo.obtener(i));
        }
    }
    
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
    
    /**
    * Busca un punto de interés mediante su código.
    *
    * @param codigo código del punto de interés a buscar.
    * @return el punto encontrado o null si no existe.
    */
    public PuntoInteres buscarCodigo(int codigo) {
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
    
    public PuntoInteres eliminarPorCodigo(int codigo){
        PuntoInteres p = buscarCodigo(codigo);
        arbol.eliminar(p);
        
        return p;
    }
    
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
