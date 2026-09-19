package Logica;

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

    
    public boolean buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    private boolean buscarRecursivo(NodoABB<T> nodo, T dato) {
        if (nodo == null) {
            return false;
        }
        
        int comparacion = dato.compareTo(nodo.getDato());
        
        if (comparacion == 0) {
            return true;
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
