package presentacion;

public class MenuEcuRoute {
    private Menu menu;
    
    public MenuEcuRoute(){
        menu = new Menu();
    }
    
    public int ejecutar(){
        return menu.ejecutar();
    }
    
        public void cargar(){
        String[] opciones = {
            "Cargar",
            "Buscar",
            "Mostrar",
            "Eliminar",
            "Estadisticas y calculos",
            "Recorridos",
            "Salir"
        };
        
        menu.cargarDato("Menu Ecu Route", opciones);
    }

    public int getCantOpc() {
        return menu.getCantOpciones();
    }
}
