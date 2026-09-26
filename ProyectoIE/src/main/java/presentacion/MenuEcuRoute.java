package presentacion;

public class MenuEcuRoute {
    private Menu menu;
    
    public MenuEcuRoute(){
        menu = new Menu(4);
    }
    
    public int ejecutar(){
        return menu.ejecutar();
    }
    
    public void cargar(){
        String[] opciones = {
            "Cargar",
            "Mostrar",
            "Buscar",
            "Eliminar",
            "Salir"
        };
        
        menu.cargarDato("Menu Ecu Route", opciones);
    }

    public int getCantOpc() {
        return menu.getCantOpciones();
    }
}
