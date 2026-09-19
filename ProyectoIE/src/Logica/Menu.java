package Logica;

import Utilidades.*;

public class Menu {
    private String titulo;  
    private String[] opciones;
    
    public Menu(int cantOpc) {
        this.titulo=null;
        this.opciones = new String[cantOpc];
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
        setOpciones(opciones);
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
            Consola.emitirMensaje("Respuesta:");
            opcion=Lector.leerInt();
            
            if(!Validador.esNroValido(opcion, 1, opciones.length)){
                Consola.emitirError("Opcion no valida.");
            }
            
        } while(!Validador.esNroValido(opcion, 1, opciones.length));
        
        return opcion;
    }
    
    private void visualizar(){
        Consola.emitirTitulo(40,"=",titulo); //metodo que muestra el menu centrado y con un tipo de estilo
        
        for(int i=1 ; i <= opciones.length; i++) {
            Consola.emitirMensajeLN(i+"_"+opciones[i-1]);
        }
    }
    
    
    // ============================================= Getter ============================================= //
    
    
    public String getTitulo() {
        return titulo;
    }
    
    public String[] getOpciones() {
        return opciones;
    }
    
    
    // ============================================= Setter ============================================= //

    
    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    private void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }
}
