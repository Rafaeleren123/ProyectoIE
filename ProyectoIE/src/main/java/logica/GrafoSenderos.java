package logica;

import logica.puntoInteres.PuntoInteres;
import org.jgrapht.graph.SimpleGraph;

public class GrafoSenderos {
    
    private SimpleGraph<PuntoInteres, Sendero> grafo;
    
    public void agregarVertice(PuntoInteres p){
        grafo.addVertex(p);
    }
    
    public void agregarSendero(PuntoInteres punto1, PuntoInteres punto2, Sendero s){
        grafo.addEdge(punto1, punto2, s);
        
    }
    
    public void eliminarSendero(){
        
    }
    
    public void mostrarConexiones(){
        
    }
    
    public void existeCamino(){
        
    }
    
    public void recorridoDFS(){
        
    }
    
    public void recorridoBFS(){
        
    }
    
    public void contarComponentes(){
        
    }
}
