package presentacion;

import logica.Sendero;

public class Main {
    /**
     * Cambie el menu
     * agregue un metodo que cargar datos
     * 
     * @param args 
     */
    public static void main(String[] args) {
//        AppIE aplicacion = new AppIE();
//        
//        aplicacion.ejecutar();

LectorSendero l = new LectorSendero();
        Sendero s = l.cargar();
        s.mostrarInfo();
    }
    
}
