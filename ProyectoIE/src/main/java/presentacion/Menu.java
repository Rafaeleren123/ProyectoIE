package presentacion;

import Utilidades.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String titulo;  
    private List<String> opciones;
    
    public Menu() {
        this.titulo=null;
        this.opciones = new ArrayList<String>();
    }
    
    
    // ============================================= Metodos Publicos ============================================= //

    
    /**
     * Metodo principal
     * muestra las opciones del menu y lee un dato de tipo int 
     * por teclado
     * 
     * @return un dato de tipo int 
     */
    public int ejecutar(){
        visualizar();
        Consola.emitirBordeLN(40, "=");
        int opcion=leerOpcion();
        Consola.emitirBordeLN(40, "=");
        
        return opcion;
    }
    
    public void cargarDato(String titulo, String[] opciones){
        setTitulo(titulo);
        
        for (int i = 0; i < opciones.length; i++) {
            this.opciones.add(opciones[i]);
        }
    }
   
   
    // ============================================= Metodos Privado ============================================= //
    
   
    /**
     * Lee un dato de tipo int por tecldo y valida que este en una rango valido
     * y lo retorna
     * 
     * @return dato de tipo int
     */
    private int leerOpcion(){
        int opcion=-1;
        
        do{
            Consola.emitirMensajeLN("Respuesta:");
            opcion=Lector.leerInt();
            
            if(!Validador.esNroValido(opcion, 1, opciones.size())){
                Consola.emitirError("Opcion no valida.");
            }
            
        } while(!Validador.esNroValido(opcion, 1, opciones.size()));
        
        return opcion;
    }
    
    private void visualizar(){
        Consola.emitirTitulo(40,"=",titulo); //metodo que muestra el menu centrado y con un tipo de estilo
        int i = 0;
        for(String opc : opciones) {
            i++;
            Consola.emitirMensajeLN(i+"_ "+opc);
        }
    }
    
    
    // ============================================= Getter ============================================= //
    
    
    public String getTitulo() {
        return titulo;
    }
    
    public List<String> getOpciones() {
        return opciones;
    }
    
    public int getCantOpciones(){
        return opciones.size();
    }
    
    
    // ============================================= Setter ============================================= //

    
    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    private void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
}
