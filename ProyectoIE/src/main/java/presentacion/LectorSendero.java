package presentacion;

import Utilidades.*;
import logica.Sendero;
import logica.excepciones.DatoInvalidoException;

public class LectorSendero {
    private Sendero s;
    
    public LectorSendero(){
        s = new Sendero();
    }
    
    public Sendero cargar(){
        Consola.emitirTitulo(40,"=", "Alta de sendero");
        
        leerDistancia();
        leerDificultad();
        leerTiempoEstimado();
        leerHabilitado();
     
        return s;
    }
    
    private void leerDistancia() {
        boolean valido;
        do{
            Consola.emitirMensajeLN("Ingrese distancia: ");
            double distancia = Lector.leerDouble();
            
            try{
                s.setDistancia(distancia);
                valido = true;
            }catch(DatoInvalidoException e){
                Consola.emitirError(e.getMessage());
                valido = false;
            }
            
        }while(!valido);
    }
    
    private void leerDificultad(){
        boolean valido;
        int dificultad;
        
        do{
            dificultad = leerInt("Ingrese la dificultad (1 a 5) :");
            try {
                s.setDificultad(dificultad);
                valido = true;
            } catch (DatoInvalidoException e) {
                Consola.emitirError(e.getMessage());
                valido = false;
            }
        }while(!valido);
    }
    
    private void leerTiempoEstimado(){
        boolean valido;
        int tiempo;
        
        do{
            tiempo = leerInt("Ingrese el tiempo de estimacion: ");
            try {
                s.setTiempoEstimado(tiempo);
                valido = true;
            } catch (DatoInvalidoException e) {
                Consola.emitirError(e.getMessage());
                valido = false;
            }
        }while(!valido);
        
    }
    
    private int leerInt(String msj) {
        Consola.emitirMensajeLN(msj);
        return Lector.leerInt();
    }
    
    private void leerHabilitado() {
        boolean valido;
        
        do{
            Consola.emitirMensajeLN("Desea habilitarlo (si/no): ");
            String opcion = Lector.leerString();

            opcion = opcion.toUpperCase();

            if(opcion.equals("SI") || opcion.equals("NO")){
                valido = true;
                s.setHabilitado(opcion.equals("SI"));
            }else{
                Consola.emitirError("Opcion no valida, solo puede ser si o no.");
                valido = false;
            }
        }while(!valido);
    }
}
