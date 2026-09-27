package presentacion;

import Utilidades.*;
import logica.Sendero;
import logica.excepciones.DatoInvalidoException;

// preguntar a la profe si las excepciones se pueden 
// tratar solo en la clase app o en toda la capa presentacion

public class LectorSendero {
    private Sendero s;
    
    public LectorSendero(){
        s = new Sendero();
    }
    
    public Sendero cargar(){
        boolean cargaValida = false;
        int dificultad, tiempoEstimado;
        
        do{
            try {
                leerDistancia();
            
                dificultad = leerInt("Ingrese la dificultad (1 a 5) :");
                s.setDificultad(dificultad);

                tiempoEstimado = leerInt("Ingrese el tiempo de estimacion: ");
                s.setTiempoEstimado(tiempoEstimado);

                leerHabilitado();
                
                cargaValida = true;
            } catch (DatoInvalidoException e) {
                Consola.emitirError(e.getMessage());
            }
        }while(cargaValida);
        
        return s;
    }
    
    private void leerDistancia() throws DatoInvalidoException{
        Consola.emitirMensajeLN("Ingrese distancia: ");
        double distancia = Lector.leerDouble();
        
        s.setDistancia(distancia);
    }
    
    private int leerInt(String msj) {
        Consola.emitirMensajeLN(msj);
        return Lector.leerInt();
    }
    
    //
    private void leerHabilitado() throws DatoInvalidoException{
        Consola.emitirMensajeLN("Desea habilitarlo (si/no): ");
        String opcion = Lector.leerString();
        
        opcion = opcion.toUpperCase();
        
        if(opcion.equals("SI") || opcion.equals("NO")){
            s.setHabilitado(opcion.equals("SI"));
        }else{
            throw new DatoInvalidoException("Opcion no valida, solo puede ser si o no.");
        }
    }
}
