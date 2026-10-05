package logica;

import Utilidades.Consola;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import logica.puntoInteres.PuntoInteres;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

public class GrafoSenderos {
    private SimpleGraph<PuntoInteres, Sendero> grafo;
    
    public GrafoSenderos() {
        this.grafo = new SimpleGraph<PuntoInteres, Sendero>(Sendero.class);
    }
    
    public void agregarVertice(PuntoInteres p){
        grafo.addVertex(p);
    }
    
    public void agregarSendero(PuntoInteres vertice1, PuntoInteres vertice2, Sendero s){
        grafo.addEdge(vertice1, vertice2, s);
    }
    
    public boolean esCompleto(){
        // n contiene la cantidad de vertices del grafo
        int n = grafo.vertexSet().size(); 
        
        // calcula la cantidad maxima de aristas que puede tener el grafo
        int maximo = n * (n - 1) / 2; 
        
        // compara si la cantidad de aristas es igual a la CANTIDAD MAXIMA de aristas
        return grafo.edgeSet().size() == maximo;
    }
    
    public Sendero eliminarSendero(PuntoInteres vertice1, PuntoInteres vertice2){
        return grafo.removeEdge(vertice1, vertice2);
    }
    
    public void mostrarConexiones(){
        if(!esVacio()){
            
            for(PuntoInteres vertice1 : grafo.vertexSet()){
                Consola.emitirMensajeLN("");
                vertice1.tipoYnombre();
                
                mostrarPorVertice(vertice1);
            }
        }else{
            Consola.emitirError("No hay Puntos de interes guardados.");
        }
    }
    
    public void mostrarPorVertice(PuntoInteres vertice){
        PuntoInteres vertice2;
        
        if(!estaDesconectado(vertice)){
            for(Sendero s : grafo.edgesOf(vertice)){
                vertice2 = Graphs.getOppositeVertex(grafo, s, vertice);

                Consola.emitirMensajeLN("Conectado con: ");
                vertice2.encabezado();
                s.mostrarInfo();
            }
        }else{
            Consola.emitirMensajeLN("No posee senderos");
        }
    }
    
    public boolean existeConexion(PuntoInteres vertice1, PuntoInteres vertice2){
        return grafo.containsEdge(vertice1, vertice2);
    }
    
    public boolean existeCamino(PuntoInteres verticeOrigen, PuntoInteres verticeDestino){
        DepthFirstIterator<PuntoInteres, Sendero> dfs = new DepthFirstIterator<>(grafo, verticeOrigen);
        
        while (dfs.hasNext()) {
            PuntoInteres actual = dfs.next();

            if (actual.esMismoCodigo(verticeDestino.getCodigo())) {
                return true;
            }
        }

        return false;
    }
    
    public List<PuntoInteres> recorridoDFS(PuntoInteres verticeInicio){
        DepthFirstIterator<PuntoInteres, Sendero> dfs = new DepthFirstIterator<>(grafo, verticeInicio);
        
        return recorrer(dfs);
    }
    
    public List<PuntoInteres> recorridoBFS(PuntoInteres verticeInicio){
        BreadthFirstIterator<PuntoInteres, Sendero> bfs = new BreadthFirstIterator<>(grafo, verticeInicio);
        
        return recorrer(bfs);
    }
    
    private List<PuntoInteres> recorrer(Iterator<PuntoInteres> iterador) {
        List<PuntoInteres> lista = new ArrayList<>();
        while (iterador.hasNext()) {
            lista.add(iterador.next());
        }
        
        return lista;
    }
    
    public int contarComponentes(){
        ConnectivityInspector<PuntoInteres, Sendero> coneciones = new ConnectivityInspector<>(grafo);
        
        return coneciones.connectedSets().size();
    }
    
    // verifica si el grafo tiene almenos una arista
    public boolean noTieneAristas(){
        return grafo.edgeSet().isEmpty();
    }
    
    // verifica si el grafo esta vacio
    public boolean esVacio(){
        return grafo.vertexSet().isEmpty();
    }
    
    // verifica si un vertice tiene aristas
    private boolean estaDesconectado(PuntoInteres p){
        return grafo.edgesOf(p).isEmpty();
    }
}
