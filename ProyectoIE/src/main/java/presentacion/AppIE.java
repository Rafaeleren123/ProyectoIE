package presentacion;

import logica.excepciones.*;
import logica.GestorPuntosInteres;
import Utilidades.*;
import java.util.List;
import logica.Sendero;
import logica.puntoInteres.*;

/**
 * Clase principal de la capa de presentación.
 *
 * Se encarga de coordinar la interacción con el usuario, mostrar información y
 * solicitar datos. Las operaciones relacionadas con la lógica del sistema son
 * delegadas al GestorPuntosInteres.
 */
public class AppIE {

    // Tipo de menu
    private MenuEcuRoute mEcuRoute; // Menu principal
    private Menu mCargar; // Menu que muestra las opciones de carga
    private Menu mMostrar; // Menu que muestra las opciones de mostrar
    private Menu mBuscar; // Menu que muestra las opciones de busqueda
    private Menu mEliminar; // Menu que muestra las opciones de eliminar
    private Menu mEstCalc;
    private Menu mRecorrido;
    private Menu mTipoPuntoInt; // Menu que muestra los tipos de Punto de interes

    private GestorPuntosInteres gestorPInteres;

    public AppIE() {
        mEcuRoute = new MenuEcuRoute();
        mCargar = new Menu();
        mMostrar = new Menu();
        mBuscar = new Menu();
        mEliminar = new Menu();
        mTipoPuntoInt = new Menu();
        mEstCalc = new Menu();
        mRecorrido = new Menu();

        gestorPInteres = new GestorPuntosInteres();
    }

    
    // ============================================================================================================ //
    // ============================================  Metodo Principal  ============================================ //
    // ============================================================================================================ //
    
    
    /**
     * Metodo principal Inicia la ejecución de la aplicación y procesa las
     * opciones seleccionadas por el usuario hasta elegir la opción de salida.
     */
    public void ejecutar() {
        int opc;
        cargarMenu();

        do {
            opc = mEcuRoute.ejecutar();
            procesarOpc(opc);
        } while (opc != mEcuRoute.getCantOpc());

    }
    

    // ============================================================================================================ //
    // =====================================  Metodos para Procesar opciones  ===================================== //
    // ============================================================================================================ //
    
    
    private void procesarOpc(int opc) {
        int opc2;
        switch (opc) {
            case 1:
                do {
                    opc2 = mCargar.ejecutar();
                    procesarCarga(opc2);
                } while (opc2 != mCargar.getCantOpciones());

                break;

            case 2:
                do {
                    opc2 = mBuscar.ejecutar();
                    procesarBusqueda(opc2);
                } while (opc2 != mBuscar.getCantOpciones());

                break;

            case 3:
                do {
                    opc2 = mMostrar.ejecutar();
                    procesarMostrar(opc2);
                } while (opc2 != mMostrar.getCantOpciones());
                
                break;

            case 4:
                do {
                    opc2 = mEliminar.ejecutar();
                    procesarEliminacion(opc2);
                } while (opc2 != mEliminar.getCantOpciones());

                break;
                
            case 5: 
                do {
                    opc2 = mEstCalc.ejecutar();
                    procesarEstCalc(opc2);
                } while (opc2 != mEstCalc.getCantOpciones());
                
                break;
                
            case 6: 
                do {
                    opc2 = mRecorrido.ejecutar();
                    procesarRecorrido(opc2);
                } while (opc2 != mRecorrido.getCantOpciones());
                
                break;

            default:
                Consola.emitirMensajeLN("Cerrando programa ...");
                break;
        }
    }

    private void procesarCarga(int opc) {
        switch (opc) {
            case 1:
                altaPuntoInteres();
                break;

            case 2:
                construirIndice();
                break;

            case 3:
                altaSendero();
                break;

            case 4:
                cargarDatosPrueba();
                break;

            default:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }

    private void procesarMostrar(int opc) {
        switch (opc) {
            case 1:
                mostrarPuntosInteres();
                break;

            case 2:
                mostrarAscendente();
                break;

            case 3:
                mostrarSenderos();
                break;

            case 4:
                mostrarSendPorPI();
                break;

            default:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }

    private void procesarBusqueda(int opc) {
        switch (opc) {
            case 1:
                buscarPuntoInteres();
                break;

            case 2:
                buscarPorIndice();
                break;
                
            case 3:
                existeCamino();
                break;

            default:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }

    private void procesarEliminacion(int opc) {
        switch (opc) {
            case 1:
                eliminarIndice();
                break;
                
            case 2:
                eliminarSendero();
                break;

            default:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }
    
    private void procesarEstCalc(int opc){
        switch (opc) {
            case 1:
                contarPorTipo();
                break;
                
            case 2:
                mostrarMayorAltitud();
                break;
                
            case 3:
                mostrarPromedioAltitud();
                break;
                
            case 4:
                mostrarAccesibilidadAlta();
                break;
                
            case 5:
                mostrarEstadisticasArbol();
                break;
                
            case 6:
                contarComponentes();
                break;
                
            default:
                Consola.emitirMensajeLN("Volviendo al menu principal.");
                break;
        }
    }
    
    private void procesarRecorrido(int opc){
        switch (opc) {
            case 1:
                recorridoDFS();
                break;
                
            case 2:
                recorridoBFS();
                break;
                
                default:
                    Consola.emitirMensajeLN("Volviendo al menu principal.");
                    break;
        }
    }

    
    // =============================================================================================================== //
    // ============================================  Metodos para cargar  ============================================ //
    // =============================================================================================================== //
    
    
    private void cargarMenu() {
        String[] opciones;

        //carga el munu principal
        mEcuRoute.cargar();

        // mCargar
        opciones = new String[]{
            "cargar P. I.",
            "Restaurar (arbol)",
            "Agregar Sendero",
            "Agregar P. I. de prueba",
            "Volver"
        };
        mCargar.cargarDato("Opciones de carga", opciones);

        // mMostrar
        opciones = new String[]{
            "Todos los puntos de interés",
            "Menor a mayor por codigo(en arbol)",
            "Todos los Sendero", 
            "Mostrar Sendero por P. I.",
            "Volver"
        };
        mMostrar.cargarDato("Opciones de mostrar P. I.", opciones);

        // mBuscar
        opciones = new String[]{
            "Por código (arreglo)",
            "Por código (índice ABB)",
            "Verificar si existe camino",
            "Volver"
        };
        mBuscar.cargarDato("Opciones de Buscar P. I.", opciones);

        // mEliminar
        opciones = new String[]{
            "Rapida (Solo para el arbol)",
            "Eliminar un sendero",
            "Volver"
        };
        mEliminar.cargarDato("Opciones de eliminar P. I.", opciones);
        
        // mEstCalc
        opciones = new String[]{
            "Contar puntos por tipo",
            "Punto de mayor altitud",
            "Altitud promedio",
            "Contar accesibilidad alta",
            "Estadísticas del árbol",
            "Determinar sectores aislados",
            "Volver"
        };
        mEstCalc.cargarDato("Estadisticas y calculo", opciones);
        
        // mRecorrido
        opciones = new String[]{
            "Recorrer sector en profundidad",
            "Recorrido en amplitud",
            "Volver"
        };
        mRecorrido.cargarDato("Opciones de recorrido", opciones);

        // mTipoPuntoInt
        opciones = new String[]{"Mirador", "Recurso natural", "Puesto servicio"};
        mTipoPuntoInt.cargarDato("Tipo de Punto de interes", opciones);
    }

    // metodo que se usara para casos de prueba
    private void cargarDatosPrueba() {
        int codigo = 1000, i = 0;
        String[] nombre = {"Condor", "La Fauna", "Cascada Los Alisos", "Laguna", "Guardaparques", "Primeros Auxilios"};
        float altitud = 100;
        int[] accesibilidad = {0, 1, 2, 3, 4};
        int[] tipos = {0, 1, 2};

        while (i < nombre.length) {
            try {
                gestorPInteres.validarCodigoNoDuplicado(codigo);
                if (i <= 1) {
                    gestorPInteres.cargar(new Mirador(codigo, nombre[i], altitud, accesibilidad[i], tipos[0]));
                }

                if (i > 1 && i <= 3) {
                    gestorPInteres.cargar(new RecursoNatural(codigo, nombre[i], altitud, accesibilidad[i], tipos[1]));
                }

                if (i > 3) {
                    gestorPInteres.cargar(new PuestoServicio(codigo, nombre[i], altitud, accesibilidad[i - 1], tipos[2]));
                }

                i++;
                codigo++;
                altitud += altitud;
            } catch (CodigoDuplicadoException e) {
                i++;
                codigo++;
            } catch (RepositorioLlenoException e){
                Consola.emitirError(e.getMessage());
                return;
            }
        }
    }

    private void altaPuntoInteres() {
        try {
            int tipoPInteres; // guarda el tipo de punto de interes que selecciona el usuario
            boolean objCargado = false; // variable que indica si se cargo bien el objeto

            Consola.emitirTitulo(3, 30, "=", "Alta de Punto Interes");

            tipoPInteres = mTipoPuntoInt.ejecutar();
            PuntoInteres p = CreadorPuntoInteres.getTipoPInteres(tipoPInteres);

            LectorPuntoInteres lectorP = new LectorPuntoInteres();

            do {
                try {
                    int codigo = leerCodigo();
                    gestorPInteres.validarCodigoNoDuplicado(codigo);

                    lectorP.cargarPunto(p, codigo, tipoPInteres);
                    objCargado = true;

                } catch (CodigoDuplicadoException c) {
                    Consola.emitirError(c.getMessage());
                } catch (DatoInvalidoException d) {
                    Consola.emitirError(d.getMessage());
                }
            } while (!objCargado);

            gestorPInteres.cargar(p);

            Consola.emitirMensajeLN("Punto de interes guardado correctamente.");

        } catch (RepositorioLlenoException e) {
            Consola.emitirError(e.getMessage());
        }
    }

    private void altaSendero() {
        if (gestorPInteres.getCantPInteres() >= 2) { // pregunta si hay mas de un punto de interes
            try {
                gestorPInteres.validarConexionesDisponibles();// lanza una excepcion si el grafo esta completo o lleno
                
                PuntoInteres[] p = seleccionarParDePI();
                
                //verifica que no tengan una conexion
                if(!gestorPInteres.existeConexion(p[0], p[1])){
                    LectorSendero lector = new LectorSendero();
                    Sendero s = lector.cargar();
                    
                    gestorPInteres.agregarSendero(p[0], p[1], s);
                    Consola.emitirMensajeLN("Sendero cargado correctamente.");
                    
                }else{
                    Consola.emitirMensajeLN("");
                    Consola.emitirError("Ya existe un sendero entre estos dos Punto de interes.");
                }
        
            }catch(ConexionAristaLlenoException e){
                Consola.emitirError(e.getMessage());
            }
            
        }else{
            Consola.emitirError("No posee la cantidad de Puntos de interes necesario para agregar un sendero.");
        }
    }

    private void construirIndice() {
        try {
            gestorPInteres.construirIndice();
            Consola.emitirMensajeLN("Indice reconstruido correctamente.");
        } catch (RepositorioVacioException r) {
            Consola.emitirError(r.getMessage());
        }
    }
    
    
    // =============================================================================================================== //
    // ============================================  Metodos para buscar  ============================================ //
    // =============================================================================================================== //
    
    
    private void buscarPuntoInteres() {
        try {
            PuntoInteres p = pedirPuntoPorCodigo();
            
            if (p != null) {
                p.mostrarInformacion();
            }

        } catch (RepositorioVacioException | DatoInvalidoException e) {
            Consola.emitirError(e.getMessage());
        }
    }

    private void buscarPorIndice() {
        try {
            gestorPInteres.validarArbolNoVacio();

            int codigo = leerCodigo();
            PuntoInteres p = gestorPInteres.buscarPorCodigo(codigo);

            if (p != null) {
                p.mostrarInformacion();
            } else {
                Consola.emitirError("No se encontro el punto de interes con el codigo '" + codigo + "'.");
            }

        } catch (ArbolVacioExcepcion | DatoInvalidoException e) {
            Consola.emitirError(e.getMessage());
        } 
    }
    
    private void existeCamino(){
        try{
            gestorPInteres.verificarGrafoVacio();
            gestorPInteres.validarHayConexiones();
            
            PuntoInteres[] p = seleccionarParDePI();
            
            if(gestorPInteres.existeCamino(p[0], p[1])){
                Consola.emitirMensajeLN("Existe Camino entre los dos punto de interes.");
            }else{
                Consola.emitirMensajeLN("No existe camino entre los dos punto de interes.");
            }
            
        }catch(GrafoVacioException | ConexionAristaVaciaException e){
            Consola.emitirMensajeLN(e.getMessage());
        }
    }
    

    // ================================================================================================================ //
    // ============================================  Metodos para mostrar  ============================================ //
    // ================================================================================================================ //
    
    
    private void mostrarPuntosInteres() {
        try {
            gestorPInteres.mostrar();
        } catch (RepositorioVacioException e) {
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

    private void mostrarSenderos() {
        gestorPInteres.mostrarSendero();
    }
    
    private void mostrarSendPorPI(){
        try{
            gestorPInteres.verificarGrafoVacio();
            gestorPInteres.validarHayConexiones();
            PuntoInteres p = pedirPuntoPorCodigo();
            
            if(p != null){
                gestorPInteres.mostrarPorPI(p);
            }
        }catch(GrafoVacioException | ConexionAristaVaciaException e){
            Consola.emitirMensajeLN(e.getMessage());
        }
    }
    

    // ================================================================================================================= //
    // ============================================  Metodos para eliminar  ============================================ //
    // ================================================================================================================= //
    
    
    private void eliminarIndice() {
        try {
            gestorPInteres.validarArbolNoVacio();
            int codigo = leerCodigo();

            PuntoInteres p = gestorPInteres.eliminarPorCodigo(codigo);

            if (p != null) {
                Consola.emitirMensajeLN("El punto de interes con el codigo '" + codigo + "' fue eliminado correctamente.");
            } else {
                Consola.emitirError("No se encontro el punto de interes con el codigo '" + codigo + "'.");
            }
        } catch (ArbolVacioExcepcion | DatoInvalidoException e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void eliminarSendero(){
        try {
            gestorPInteres.verificarGrafoVacio();
            gestorPInteres.validarHayConexiones();
            
            PuntoInteres[] p = seleccionarParDePI();
            
            if(gestorPInteres.existeConexion(p[0], p[1])){
                
                if(gestorPInteres.eliminarSendero(p[0], p[1])){
                    Consola.emitirMensajeLN("Sendero eliminado correctamente");
                }
                
            }else{
                Consola.emitirMensajeLN("No se encontro un sendero entre estos dos punto de interes.");
            }
            
        }catch (GrafoVacioException | ConexionAristaVaciaException e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    
    // =============================================================================================================== //
    // ====================================  Metodos para estadisticas y calculos  =================================== //
    // =============================================================================================================== //
    
    
    private void contarPorTipo() {
        try {
            gestorPInteres.estaVacio();
            int tipoPInte = mTipoPuntoInt.ejecutar();

            String stringTipoPInte = obtenerNombreTipo(tipoPInte);
            int cantidad = gestorPInteres.cantPorTipo(stringTipoPInte);
            Consola.emitirResultado(40, "-", "Cantidad de tipo " + stringTipoPInte + ": " + cantidad);

        } catch (RepositorioVacioException e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarMayorAltitud() {
        try {
            PuntoInteres p = gestorPInteres.determinarMayorAltitud();

            Consola.emitirMensajeLN("");
            Consola.emitirMensajeLN("Punto de interes mas alto:");
            Consola.emitirBordeLN(40, "-");
            p.mostrarInformacion();
            Consola.emitirBordeLN(40, "-");

        } catch (RepositorioVacioException e) {
            Consola.emitirError(e.getMessage());
        }
    }

    private void mostrarPromedioAltitud() {
        Consola.emitirMensajeLN("");
        Consola.emitirBordeLN(40, "-");
        Consola.emitirMensajeLN("El promedio de altitud es: " + gestorPInteres.promedioAltitud());
        Consola.emitirBordeLN(40, "-");

    }

    private void mostrarAccesibilidadAlta() {
        try {
            int cantAccesdAlta = gestorPInteres.contarAccesibilidadAlta();
            Consola.emitirResultado(40, "-", "La cantidad accesibilidad alta es: " + cantAccesdAlta);

        } catch (RepositorioVacioException e) {
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void mostrarEstadisticasArbol() {
        int[] estadisticas = gestorPInteres.getEstadistica();

        Consola.emitirTitulo(40, "=", "Estadisticas del arbol");
        Consola.emitirMensajeLN("Cantidad de nodos: " + estadisticas[0]);
        Consola.emitirMensajeLN("Altura: " + estadisticas[1]);
        Consola.emitirMensajeLN("Cantidad de hojas: " + estadisticas[2]);
        Consola.emitirMensajeLN("Cantidad nodo internos: " + estadisticas[3]);
        Consola.emitirBordeLN(40, "=");
        
    }
    
    private void contarComponentes(){
        Consola.emitirMensajeLN("Canitidad de componentes: "+gestorPInteres.cantidadComponente());
    }
    
    
    // =============================================================================================================== //
    // ===========================================  Metodos para Recorrer  =========================================== //
    // =============================================================================================================== //
    
    
    private void recorridoDFS(){
        try {
            gestorPInteres.verificarGrafoVacio();
            
            PuntoInteres inicio = pedirPuntoPorCodigo();
            
            if(inicio != null){
                List<PuntoInteres> lista = gestorPInteres.recorridoDFS(inicio);
                
                for(PuntoInteres p : lista){
                    p.mostrarInformacion();
                    Consola.emitirMensajeLN("");
                }
            }
            
        } catch (GrafoVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    private void recorridoBFS(){
        try {
            gestorPInteres.verificarGrafoVacio();
            
            PuntoInteres inicio = pedirPuntoPorCodigo();
            
            if(inicio != null){
                List<PuntoInteres> lista = gestorPInteres.recorridoBFS(inicio);
                
                for(PuntoInteres p : lista){
                    p.mostrarInformacion();
                    Consola.emitirMensajeLN("");
                }
            }
            
        } catch (GrafoVacioException e){
            Consola.emitirError(e.getMessage());
        }
    }
    
    
    // ================================================================================================================= //
    // ================================================  Otros Metodos  ================================================ //
    // ================================================================================================================= //
    
    
    private int leerCodigo() throws DatoInvalidoException {
        Consola.emitirBordeLN(40, "=");
        Consola.emitirMensajeLN("Ingrese codigo: ");
        int codigo = Lector.leerInt();
        Consola.emitirBordeLN(40, "=");

        boolean codigoValido = Validador.esNroPositivo(codigo);

        if (!codigoValido) {
            throw new DatoInvalidoException("El codigo debe ser mayor que cero.");
        }
        return codigo;
    }
    
    private PuntoInteres[] seleccionarParDePI(){
        PuntoInteres[] p = new PuntoInteres[2];
        int i = 0;

        while (i < 2) {
            PuntoInteres pActual = pedirPuntoPorCodigo();

            if (pActual != null) {
                if (i == 1 && pActual.esMismoCodigo(p[0].getCodigo())) {
                    Consola.emitirMensajeLN("");
                    Consola.emitirError("Los codigos "+pActual.getCodigo()+" y "+p[0].getCodigo()+" no pueden ser iguales.");
                } else {
                    p[i] = pActual;
                    i++;
                }
            }
        }
        
        return p;
    }
    
    /**
    * Muestra los codigos disponibles, pide un codigo y busca el punto.
    * @return el punto encontrado, o null si el codigo es invalido o no existe.
    */
    private PuntoInteres pedirPuntoPorCodigo() {
        Consola.emitirMensajeLN("");
        Consola.emitirMensajeLN("Codigos disponibles:");
        Consola.emitirBordeLN(40, "-");
        gestorPInteres.mostCodDisponible();

        try {
            int codigo = leerCodigo();
            PuntoInteres p = gestorPInteres.buscarCodigo(codigo);

            if (p == null) {
                Consola.emitirMensajeLN("");
                Consola.emitirError("No se encontró un punto de interés con el código '" + codigo + "'.");
            }
            
            return p;

        } catch (DatoInvalidoException e) {
            Consola.emitirError(e.getMessage());
            return null;
        }
    }
    
    private String obtenerNombreTipo(int tipoPInte) {
        if (tipoPInte == 1) {
            return "Mirador";
        }

        if (tipoPInte == 2) {
            return "Recurso natural";
        }

        return "Puesto servicio";
    }
}
