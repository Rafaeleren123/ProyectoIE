package presentacion;

import Logica.CodigoDuplicadoException;
import Logica.CreadorPuntoInteres;
import Logica.DatoInvalidoException;
import Logica.GestorPuntosInteres;
import Logica.PuntoInteres;
import Logica.RepositorioLlenoException;
import Logica.Menu;
import Logica.RepositorioVacioException;
import Utilidades.*;

/**
 * Clase principal de la capa de presentación.
 *
 * Se encarga de coordinar la interacción con el usuario, mostrar
 * información y solicitar datos. Las operaciones relacionadas con
 * la lógica del sistema son delegadas al GestorPuntosInteres.
 */

public class AppIE {
    private MenuEcuRoute mEcuRoute;
    private Menu mTipoPuntoInt;
    private GestorPuntosInteres gestor;
    
    public AppIE() {
        this.mEcuRoute = new MenuEcuRoute();
        this.mTipoPuntoInt = new Menu(3);
        this.gestor = new GestorPuntosInteres();
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    /**
     * Metodo principal
     * Inicia la ejecución de la aplicación y procesa las opciones
     * seleccionadas por el usuario hasta elegir la opción de salida.
     */
    public void ejecutar(){
        int opc;
        cargarMenu();
        
        do{
            opc = mEcuRoute.ejecutar();
            procesarOpc(opc);
        }while(opc != 8);
        
    }
    
    
    // ============================================= Metodos Privados ============================================= //
    
    
    private void procesarOpc(int opc){
        switch(opc){
            case 1:
                altaPuntoInteres();
                break;
                
            case 2:
                mostrarPuntosInteres();
                break;
                
            case 3:
                buscarPuntoInteres();
                break;
                
            case 4:
                contarPorTipo();
                break;
                
            case 5:
                mostrarMayorAltitud();
                break;
                
            case 6:
                mostrarPromedioAltitud();
                break;
                
            case 7:
                mostrarAccesibilidadAlta();
                break;
                
            case 8:
                Consola.emitirMensajeLN("Cerrando programa ...");
                break;
        }
    }
    
    private void cargarMenu(){
        //carga el munu principal
        mEcuRoute.cargar();
        
        //carga el menu que tendra los tipo de punto de interes
        String[] opciones = {"Mirador", "Recurso natural", "Puesto servicio"};
        mTipoPuntoInt.cargarDato("Tipo de Punto de interes", opciones);
    }
    
    private int leerCodigo() throws DatoInvalidoException{
        int codigo;
        boolean codigoValido;
        
        Consola.emitirBordeLN(40, "=");
        Consola.emitirMensaje("Ingrese codigo: ");
        codigo = Lector.leerInt();
        
        Consola.emitirBordeLN(40, "=");
        
        codigoValido = Validador.esNroPositivo(codigo);
        
        if(!codigoValido){
            throw new DatoInvalidoException("El codigo no puede ser negativo.");
        }
        
        return codigo;
    }
    
    private void altaPuntoInteres(){
        try{
            gestor.estaLleno(); // lanza una excepcion si esta llena
            
            PuntoInteres p;
            int tipoPuntInteres; // guarda el tipo de punto de interes que selecciona el usuario
            boolean objCargado = false; // variable que indica si se cargo bien el objeto

            Consola.emitirTitulo(3, 30, "=", "Alta de Punto Interes");

            tipoPuntInteres = mTipoPuntoInt.ejecutar();
            p = CreadorPuntoInteres.getTipoPInteres(tipoPuntInteres);

            do{
                try{
                    int codigo = leerCodigo(); // lanza una excepcion si se ingreso mal un codigo
                    gestor.existeCodigo(codigo); // lanza una excepcion si existe un codigo duplicado

                    p.cargarDato(codigo);

                    objCargado = true; 

                }catch(CodigoDuplicadoException c){
                    Consola.emitirError(c.getMessage());
                }catch(DatoInvalidoException d){
                        Consola.emitirError(d.getMessage());
                }
            }while(!objCargado);

            gestor.cargar(p);
            Consola.emitirMensajeLN("Punto de interes guardado correctamente.");

        }catch(RepositorioLlenoException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarPuntosInteres(){
        try{
            gestor.estaVacio();
            gestor.mostrar();
        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void buscarPuntoInteres(){
        try{
            gestor.estaVacio();
            Consola.emitirMensajeLN("");
            Consola.emitirMensajeLN("Codigos disponibles:");
            Consola.emitirBordeLN(40, "-");
            gestor.mostCodDisponible();

            int codigo = leerCodigo();

            PuntoInteres p = gestor.buscarCodigo(codigo);

            if(p == null){
                Consola.emitirMensajeLN("");
                Consola.emitirError("No se encontró un punto de interés con el código "+codigo+".");
            }else{
                p.mostrarInformacion();
            }

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }catch(DatoInvalidoException d){
            Consola.emitirError(d.getMessage());
        }
    }
    
    private void contarPorTipo(){
        try{
            gestor.estaVacio();

            int tipoPInte = mTipoPuntoInt.ejecutar();

            String stringTipoPInte = obtenerNombreTipo(tipoPInte);
            int cantidad = gestor.CantPorTipo(stringTipoPInte);
            Consola.emitirResultado(40, "-", "Cantidad de tipo "+stringTipoPInte+": "+cantidad);

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarMayorAltitud(){
        try{
            gestor.estaVacio();

            PuntoInteres p = gestor.determinarMayorAltitud();

            Consola.emitirMensajeLN("");
            Consola.emitirMensajeLN("Punto de interes mas alto:");
            Consola.emitirBordeLN(40, "-");
            p.mostrarInformacion();
            Consola.emitirBordeLN(40, "-");

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarPromedioAltitud(){
        try{
            gestor.estaVacio();
            Consola.emitirMensajeLN("");
            Consola.emitirBordeLN(40, "-");
            Consola.emitirMensajeLN("El promedio de altitud es: "+gestor.promedioAltitud());
            Consola.emitirBordeLN(40, "-");

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarAccesibilidadAlta(){
        try{
            gestor.estaVacio();

            int cantAccesdAlta = gestor.contarAccesibilidadAlta();
            Consola.emitirResultado(40, "-", "La cantidad accesibilidad alta es: "+cantAccesdAlta);

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private String obtenerNombreTipo(int tipoPInte){
        if(tipoPInte == 1){
            return "Mirador";
        }
        
        if(tipoPInte == 2){
            return "Recurso natural";
        }
        
        return "Puesto servicio";
    }
}
