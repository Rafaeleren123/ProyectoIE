package logica.arbol;

import logica.excepciones.ArbolVacioExcepcion;

public class ArbolABB <T extends Comparable<T>>{
    private NodoABB<T> raiz;
    
    public ArbolABB() {
        raiz = null;
    }
    
    
    // -------------------------------------------------
    // VERIFICAR SI EL ÁRBOL ESTÁ VACÍO
    // -------------------------------------------------

    
    public boolean estaVacio() {
        return raiz == null;
    }
    
    
    // -------------------------------------------------
    // INSERTAR
    // -------------------------------------------------

    
    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoABB<T> insertarRecursivo(NodoABB<T> nodo, T dato) {

        // Se encontró la posición donde insertar
        if (nodo == null) {
            return new NodoABB<>(dato);
        }

        int comparacion = dato.compareTo(nodo.getDato());

        if (comparacion < 0) {

            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(),dato));

        } else if (comparacion > 0) {

            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), dato));
        }

        // Si comparacion == 0 no se inserta
        // porque no permitimos duplicados

        return nodo;
    }
    
    
    // -------------------------------------------------
    // BUSCAR
    // -------------------------------------------------

    public T buscar(T dato){
        return buscarRecursivo(raiz, dato);
    }
    
    public boolean existe(T dato) {
        return buscarRecursivo(raiz, dato) != null;
    }

    private T buscarRecursivo(NodoABB<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }
        
        int comparacion = dato.compareTo(nodo.getDato());
        
        if (comparacion == 0) {
            return nodo.getDato();
        }
        if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), dato);
        }
        return buscarRecursivo(nodo.getDerecho(), dato);
    }
    
    
    // -------------------------------------------------
    // ELIMINAR
    // -------------------------------------------------

    public void eliminar(T dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private NodoABB<T> eliminarRecursivo(NodoABB<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }
        
        int comparacion = dato.compareTo(nodo.getDato());
        
        if (comparacion < 0) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), dato));
            
        } else if (comparacion > 0) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), dato));
            
        } else {
            
            // -----------------------------------------
            // CASO 1:
            // NodoABB sin hijo izquierdo
            // -----------------------------------------
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            // -----------------------------------------
            // CASO 2:
            // NodoABB sin hijo derecho
            // -----------------------------------------
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            // -----------------------------------------
            // CASO 3:
            // NodoABB con dos hijos
            // -----------------------------------------
            NodoABB<T> sucesor = buscarMinimo(nodo.getDerecho());
            // Copiar el dato del sucesor
            nodo.setDato(sucesor.getDato());
            // Eliminar el sucesor
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor.getDato()));
        }
        return nodo;
    }
    

    // -------------------------------------------------
    // BUSCAR EL MENOR ELEMENTO DE UN SUBÁRBOL
    // -------------------------------------------------

    
    private NodoABB<T> buscarMinimo(NodoABB<T> nodo) {
        NodoABB<T> actual = nodo;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual;
    }
    
    // -------------------------------------------------
    // Retornar las estadisticas del arbol
    // -------------------------------------------------
    
    
    /**
    * Obtiene estadísticas del árbol: cantidad de nodos,
    * altura, cantidad de hojas y cantidad de nodos internos.
    *
    * @return arreglo con las estadísticas del árbol.
    */
    public int[] getEstadistica() throws ArbolVacioExcepcion{
        int[] estadisticas = new int[4];
        estadisticas[0] = cantNodoRecursivo(raiz);
        estadisticas[1] = alturaArbolRecursivo(raiz);
        estadisticas[2] = cantHojasRecursivo(raiz);
        estadisticas[3] = contarNodoInterRecursivo(raiz, true);
        
        return estadisticas;
    }
    
    private int cantNodoRecursivo(NodoABB<T> nodo){
        if (nodo == null) {
            return 0;
        }
        
        return 1 + cantNodoRecursivo(nodo.getIzquierdo()) + cantNodoRecursivo(nodo.getDerecho());
    }
    
    /**
    * Calcula recursivamente la altura del árbol considerando
    * la cantidad de nodos del camino más largo desde la raíz.
    */
    private int alturaArbolRecursivo(NodoABB<T> nodo){
        if (nodo == null) {
            return 0;
        }

        int alturaIzquierda = alturaArbolRecursivo(nodo.getIzquierdo());
        int alturaDerecha = alturaArbolRecursivo(nodo.getDerecho());

        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
    
    private int cantHojasRecursivo(NodoABB<T> nodo){
        if (nodo == null) {
            return 0;
        }

        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            return 1;
        }

        return cantHojasRecursivo(nodo.getIzquierdo()) + cantHojasRecursivo(nodo.getDerecho());
    }

    private int contarNodoInterRecursivo(NodoABB<T> nodo, boolean esRaiz) {
        if (nodo == null) {
            return 0;
        }

        int cantidad = contarNodoInterRecursivo(nodo.getIzquierdo(), false)
                     + contarNodoInterRecursivo(nodo.getDerecho(), false);

        if (!esRaiz && (nodo.getIzquierdo() != null || nodo.getDerecho() != null)) {
            cantidad++;
        }

        return cantidad;
    }
    
    // -------------------------------------------------
    // RECORRIDO INORDEN
    // -------------------------------------------------

    
    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(NodoABB<T> nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.getIzquierdo());
            System.out.print(nodo.getDato() + " ");
            inOrdenRecursivo(nodo.getDerecho());
        }
    }
    
    
    // -------------------------------------------------
    // RECORRIDO PREORDEN
    // -------------------------------------------------

    
    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(NodoABB<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            preOrdenRecursivo(nodo.getIzquierdo());
            preOrdenRecursivo(nodo.getDerecho());
        }
    }
    
    
    // -------------------------------------------------
    // RECORRIDO POSTORDEN
    // -------------------------------------------------

    
    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(NodoABB<T> nodo) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.getIzquierdo());
            postOrdenRecursivo(nodo.getDerecho());
            System.out.print(nodo.getDato() + " ");
        }
    }

    public NodoABB<T> getRaiz() {
        return raiz;
    }
}
