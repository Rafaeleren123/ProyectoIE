    package Dato;

import Logica.Excepciones.RepositorioLlenoException;
import Logica.PuntoInteres;

public class RepositorioPuntosArreglo implements RepositorioPuntosInteres{
    private PuntoInteres[] punto;
    private int contador;

    public RepositorioPuntosArreglo() {
        punto = new PuntoInteres[30];
        contador = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    
    /**
     * Agrega un objeto de tipo PuntoInteres
     */
    @Override
    public void agregar(PuntoInteres punto) throws RepositorioLlenoException{
        if(estaLleno()){
            throw new RepositorioLlenoException("No queda espacio.");
        }
        
        this.punto[contador] = punto;
        contador++; // incrementa la posicion
    }
    
    @Override
    public boolean existeCodigo(int codigo){
        return buscarPorCodigo(codigo) != null;
    }

    /**
     * Retorna un objeto de una posicion del arreglo punto 
     * 
     * @return un objeto del arreglo punto
     */
    @Override
    public PuntoInteres obtener(int posicion) {
        return punto[posicion];
    }

    /**
     * Recibe un int por parametro, si encuentra el codigo retorna un objeto del arreglo,
     * caso contrario retorna un null, si esta esta vacia tambien retorna null
     * 
     * @return un objeto del arreglo o un null 
     */
    @Override
    public PuntoInteres buscarPorCodigo(int codigo) {
        return buscarPorRecursividad(0, codigo);
    }

    /**
     * Retorna la cantidad objeto en el arreglo 
     * 
     * @return Cantidad de objeto en el arreglo
     */
    @Override
    public int cantidad() {
        return contador;
    }

    @Override
    public boolean estaLleno() {
        return contador == punto.length;
    }
    
    @Override
    public boolean estaVacio(){ // metodo agregado
        return contador == 0;
    }
    
    
    // ============================================= Metodos Privado ============================================= //
    
    
     /**
     * Recibe un int por parametro, busca el codigo de manera recursiva,
     * si objeto no se encuentra retorna null, caso contrario si el objeto
     * es encontrado se lo retorna
     * 
     * @return un objeto del arreglo o un null 
     */
    private PuntoInteres buscarPorRecursividad(int posicion, int codigo) {

        if (posicion >= cantidad()) {
            return null;
        }

        PuntoInteres actual = punto[posicion];

        if (actual.esMismoCodigo(codigo)) {
            return actual;
        }

        return buscarPorRecursividad(posicion + 1, codigo);
    }
}
