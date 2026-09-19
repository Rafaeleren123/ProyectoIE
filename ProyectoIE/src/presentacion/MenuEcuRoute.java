package presentacion;

import Logica.Menu;

public class MenuEcuRoute {
    private Menu menu;
    
    public MenuEcuRoute(){
        menu = new Menu(7);
    }
    
    public int ejecutar(){
        return menu.ejecutar();
    }
    
    public void cargar(){
        String[] opciones = {"Cargar","Mostrar","Buscar","Contar","Mayor altitud", "Promedio altitud", "Cantidad de accesibilidad alta", "Salir"};
        
        menu.cargarDato("Menu Ecu Route", opciones);
    }
}
