package presentacion;

import logica.excepciones.*;
import logica.CreadorPuntoInteres;
import logica.GestorPuntosInteres;
import logica.puntoInteres.PuntoInteres;
import Utilidades.*;

/**
 * Clase principal de la capa de presentación.
 *
 * Se encarga de coordinar la interacción con el usuario, mostrar
 * información y solicitar datos. Las operaciones relacionadas con
 * la lógica del sistema son delegadas al GestorPuntosInteres.
 */

public class AppIE {
    // Tipo de menu
    private MenuEcuRoute mEcuRoute; // Menu principal
    private Menu mCargar; // Menu que muestrar las opciones de carga
    private Menu mMostrar; // Menu que muestra las opciones de mostrar
    private Menu mBuscar; // Menu que muestra las opciones de busqueda
    private Menu mEliminar; // Menu que muestra las opciones de eliminar
    private Menu mTipoPuntoInt; // Menu que muestra los tipos de Punto de interes
    
    
    private GestorPuntosInteres gestorPInteres; 
    
    public AppIE() {
        mEcuRoute = new MenuEcuRoute();
        mCargar = new Menu(3);
        mMostrar = new Menu(8);
        mBuscar = new Menu(3);
        mEliminar = new Menu(2);
        mTipoPuntoInt = new Menu(3);
        
        
        gestorPInteres = new GestorPuntosInteres();
    }
    
    
    // ============================================================================================================ //
    // ============================================  Metodo Principal  ============================================ //
    // ============================================================================================================ //
    
    
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
        }while(opc != mEcuRoute.getCantOpc());
        
    }
    
    
    // ============================================================================================================ //
    // =====================================  Metodos para Procesar opciones  ===================================== //
    // ============================================================================================================ //
    
    
    private void procesarOpc(int opc){
        int opc2;
        switch(opc){
            case 1:
                do{
                    opc2 = mCargar.ejecutar();
                    procesarCarga(opc2);
                }while(opc2 != mCargar.getCantOpciones());
                
                break;
                
            case 2:
                do{
                    opc2 = mMostrar.ejecutar();
                    procesarMostrar(opc2);
                }while(opc2 != mMostrar.getCantOpciones());
                
                break;
                
            case 3:
                do{
                    opc2 = mBuscar.ejecutar();
                    procesarBusqueda(opc2);
                }while(opc2 != mBuscar.getCantOpciones());
                
                break;
                
            case 4:
                do{
                    opc2 = mEliminar.ejecutar();
                    procesarEliminacion(opc2);
                }while(opc2 != mEliminar.getCantOpciones());
                
                break;
                
            case 5:
                Consola.emitirMensajeLN("Cerrando programa ...");
                break;
        }
    }
    
    private void procesarCarga(int opc){
        switch(opc){
            case 1:
                altaPuntoInteres();
                break;
                
            case 2:
                crearIndice();
                break;
                
            case 3:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }
    
    private void procesarMostrar(int opc){
        switch(opc){
            case 1:
                mostrarPuntosInteres();
                break;
                
            case 2:
                contarPorTipo();
                break;
                
            case 3:
                mostrarMayorAltitud();
                break;
                
            case 4:
                mostrarPromedioAltitud();
                break;
                
            case 5:
                mostrarAccesibilidadAlta();
                break;
                
            case 6:
                mostrarAscendente();
                break;
                
            case 7:
                estadisticasArbol();
                break;
                
            case 8:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }
    
    private void procesarBusqueda(int opc){
        switch(opc){
            case 1:
                buscarPuntoInteres();
                break;
                
            case 2:
                buscarPorIndice();
                break;
                
            case 3:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }
    
    private void procesarEliminacion(int opc){
        switch(opc){
            case 1:
                eliminarIndice();
                break;
                
            case 2:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }
    
    
    // =============================================================================================================== //
    // ============================================  Metodos para cargar  ============================================ //
    // =============================================================================================================== //
    
    
    private void cargarMenu(){
        String[] opciones;

        //carga el munu principal
        mEcuRoute.cargar();
        
        // mCargar
        opciones = new String[] {
            "cargar", 
            "Restaurar (arbol)",
            "Volver"
        };
        mCargar.cargarDato("Opciones de cargar P. I.", opciones);
        
        // mMostrar
        opciones = new String[] {
            "Todo", 
            "Cantidad de un tipo de P.I.", 
            "Mayor altitud", 
            "Promedio de altitud", 
            "P.I. con accesibilidad alta",
            "Menor a mayor codigo(Por arbol)",
            "Estadisticas (del arbol)",
            "Volver"
        };
        mMostrar.cargarDato("Opciones de mostrar P. I.", opciones);
        
        // mBuscar
        opciones = new String[] {
            "Normal",
            "Rapida (Por arbol)",
            "Volver"
        };
        mBuscar.cargarDato("Opciones de Buscar P. I.", opciones);
        
        // mEliminar
        opciones = new String[] {
            "Rapida (Solo para el arbol)",
            "Volver"
        };
        mEliminar.cargarDato("Opciones de eliminar P. I.", opciones);
        
        // mTipoPuntoInt
        opciones = new String[] {"Mirador", "Recurso natural", "Puesto servicio"};
        mTipoPuntoInt.cargarDato("Tipo de Punto de interes", opciones);
    }
    
    private void altaPuntoInteres(){
        try{
            int tipoPInteres; // guarda el tipo de punto de interes que selecciona el usuario
            boolean objCargado = false; // variable que indica si se cargo bien el objeto

            Consola.emitirTitulo(3, 30, "=", "Alta de Punto Interes");

            tipoPInteres = mTipoPuntoInt.ejecutar();
            PuntoInteres p = CreadorPuntoInteres.getTipoPInteres(tipoPInteres);
            
            LectorPuntoInteres lectorP = new LectorPuntoInteres();

            do{
                try{
                    int codigo = leerCodigo(); // lanza una excepcion si se ingreso mal un codigo
                    gestorPInteres.existeCodigo(codigo); 
                    
                    lectorP.cargarPunto(p, codigo, tipoPInteres);
                    objCargado = true; 

                }catch(CodigoDuplicadoException c){
                    Consola.emitirError(c.getMessage());
                }catch(DatoInvalidoException d){
                        Consola.emitirError(d.getMessage());
                }
            }while(!objCargado);

            gestorPInteres.cargar(p);
            
            Consola.emitirMensajeLN("Punto de interes guardado correctamente.");

        }catch(RepositorioLlenoException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private int leerCodigo() throws DatoInvalidoException {
        Consola.emitirBordeLN(40, "=");
        Consola.emitirMensajeLN("Ingrese codigo: ");
        int codigo = Lector.leerInt();
        Consola.emitirBordeLN(40, "=");
        
        boolean codigoValido = Validador.esNroPositivo(codigo);
        
        if (!codigoValido) {
            throw new DatoInvalidoException("El codigo no puede ser negativo.");
        }
        return codigo;
    }

    private void crearIndice() {
        try{
            gestorPInteres.construirIndice();
            Consola.emitirMensajeLN("Indice reconstruido correctamente.");
        }catch(RepositorioVacioException r){
            Consola.emitirError(r.getMessage());
        }
    }
    
    
    // ================================================================================================================ //
    // ============================================  Metodos para mostrar  ============================================ //
    // ================================================================================================================ //
    
    
    private void mostrarPuntosInteres(){
        try{
            gestorPInteres.mostrar();
        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void contarPorTipo(){
        try{
            gestorPInteres.estaVacio();
            int tipoPInte = mTipoPuntoInt.ejecutar();

            String stringTipoPInte = StringTipoP(tipoPInte);
            int cantidad = gestorPInteres.CantPorTipo(stringTipoPInte);
            Consola.emitirResultado(40, "-", "Cantidad de tipo "+stringTipoPInte+": "+cantidad);

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarMayorAltitud(){
        try{
            PuntoInteres p = gestorPInteres.determinarMayorAltitud();

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
            Consola.emitirMensajeLN("");
            Consola.emitirBordeLN(40, "-");
            Consola.emitirMensajeLN("El promedio de altitud es: "+gestorPInteres.promedioAltitud());
            Consola.emitirBordeLN(40, "-");

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarAccesibilidadAlta(){
        try{
            int cantAccesdAlta = gestorPInteres.contarAccesibilidadAlta();
            Consola.emitirResultado(40, "-", "La cantidad accesibilidad alta es: "+cantAccesdAlta);

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarAscendente() {
        try {
            gestorPInteres.mostrarInOrden();
        } catch (ArbolVacioExcepcion e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void estadisticasArbol(){
        try {
            int[] estadisticas = gestorPInteres.getEstadistica();
            
            Consola.emitirTitulo(40, "=", "Estadisticas del arbol");
            Consola.emitirMensajeLN("Cantidad de nodos: "+estadisticas[0]);
            Consola.emitirMensajeLN("Altura: "+estadisticas[1]);
            Consola.emitirMensajeLN("Cantidad de hojas: "+estadisticas[2]);
            Consola.emitirMensajeLN("Cantidad nodo internos: "+estadisticas[3]);
            Consola.emitirBordeLN(40, "=");
        } catch (ArbolVacioExcepcion e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    
    // =============================================================================================================== //
    // ============================================  Metodos para buscar  ============================================ //
    // =============================================================================================================== //
    
    
    private void buscarPuntoInteres(){
        try{
            gestorPInteres.estaVacio();
            Consola.emitirMensajeLN("");
            Consola.emitirMensajeLN("Codigos disponibles:");
            Consola.emitirBordeLN(40, "-");
            gestorPInteres.mostCodDisponible();

            int codigo = leerCodigo();

            PuntoInteres p = gestorPInteres.buscarCodigo(codigo);

            if(p == null){
                Consola.emitirMensajeLN("");
                Consola.emitirError("No se encontró un punto de interés con el código '"+codigo+"'.");
            }else{
                p.mostrarInformacion();
            }

        }catch(RepositorioVacioException e){
            Consola.emitirError(e.getMessage());
        }catch(DatoInvalidoException d){
            Consola.emitirError(d.getMessage());
        }
    }

    private void buscarPorIndice() {
        try {
            gestorPInteres.arbolVacio();
            
            int codigo = leerCodigo();
            PuntoInteres p = gestorPInteres.buscarPorCodigo(codigo);
            
            if(p != null){
                p.mostrarInformacion();
            }else{
                Consola.emitirError("No se encontrar el punto de interes con el codigo '"+codigo+"'.");
            }
            
        } catch (ArbolVacioExcepcion e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    
    // ================================================================================================================= //
    // ============================================  Metodos para eliminar  ============================================ //
    // ================================================================================================================= //
    

    private void eliminarIndice() {
        try {
            gestorPInteres.arbolVacio();
            int codigo = leerCodigo();
            
            PuntoInteres p = gestorPInteres.eliminarPorCodigo(codigo);
            
            if(p != null){
                Consola.emitirMensajeLN("El punto de interes con el codigo '" + codigo + "' fue eliminado correctamente.");
            }else{
                Consola.emitirError("No se encontro el punto de interes con el codigo '" + codigo + "'.");
            }
        } catch (ArbolVacioExcepcion e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    
    // ================================================================================================================= //
    // ================================================  Otros Metodos  ================================================ //
    // ================================================================================================================= //
    
    
    private String StringTipoP(int tipoPInte){
        if(tipoPInte == 1){
            return "Mirador";
        }
        
        if(tipoPInte == 2){
            return "Recurso natural";
        }
        
        return "Puesto servicio";
    }
}
