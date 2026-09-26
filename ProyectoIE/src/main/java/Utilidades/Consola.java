package Utilidades;

public class Consola {
    public static void emitirResultado(int tamaño, String estilo, String msj){
        System.out.println("");
        
        Consola.emitirBordeLN(tamaño, estilo);
        System.out.println(msj);
        Consola.emitirBordeLN(tamaño, estilo);
    }
    
    public static void emitirMensaje(String msj){
        System.out.print(msj);
    }
    
    public static void emitirMensajeLN(String msj){
        System.out.println(msj);
    }
    
    public static void emitirError(String msj){
        System.out.println("Error: "+msj);
    }
    
    public static void emitirLista(String[] lista){
        for (int i = 0; i < lista.length; i++) {
            System.out.println((i+1)+"_ "+lista[i]);
        }
    }
    
    /**
    * Imprime un título centrado dentro de un ancho total especificado.
    * Los bordes izquierdo y derecho se calculan automáticamente.
    *
    * @param anchoTotal Cantidad total de caracteres que ocupará la línea.
    * @param estilo Texto utilizado para formar los bordes.
    * @param texto Texto que se mostrará como título.
    */
    public static void emitirTitulo(int anchoTotal, String estilo, String texto){
        int sobrante = (anchoTotal -  texto.length()) - 2;
        int mitadBorde = sobrante/2;
        
        emitirTitulo(mitadBorde, anchoTotal, estilo, texto);
    }
    
    /**
    * Imprime un título indicando la cantidad de borde izquierdo.
    * El borde derecho se calcula automáticamente a partir del ancho total.
    *
    * @param bordeIzq Cantidad de caracteres de borde a la izquierda.
    * @param anchoTotal Cantidad total de caracteres que ocupará la línea.
    * @param estilo Texto utilizado para formar los bordes.
    * @param texto Texto que se mostrará como título.
    */
    public static void emitirTitulo(int bordeIzq, int anchoTotal, String estilo, String texto){
        int bordeDer = ( ( anchoTotal - texto.length() ) - 2 ) - bordeIzq;
        System.out.println("");
        
        emitirBorde(bordeIzq, estilo);

        System.out.print(" " + texto + " ");

        emitirBordeLN(bordeDer, estilo);
    }
    
    /**
    * Imprime una secuencia de caracteres utilizada como borde.
    *
    * @param borde Cantidad de veces que se imprimirá el estilo.
    * @param estilo Texto que compone el borde.
    */
    public static void emitirBorde(int borde, String estilo){
        for(int i = 0; i < borde; i++){
            System.out.print(estilo);
        }
    }
    
    /**
     * Imprime un borde y luego realiza un salto de línea.
     *
     * @param borde Cantidad de repeticiones.
     * @param estilo Texto que formará el borde.
     */
    public static void emitirBordeLN(int borde, String estilo){
        emitirBorde(borde, estilo);
        System.out.println();
    }
}
