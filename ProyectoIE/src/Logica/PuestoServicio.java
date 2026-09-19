package Logica;

import Utilidades.*;

public class PuestoServicio extends PuntoInteres{
    private final String[] categoriaVal = {"Cascada", "Laguna", "Bosque", "Formacion rocosa"};
    
    private int categoria;
    
    public PuestoServicio(){
        categoria = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    
    
    @Override
    public void cargarDato(int codigo) throws DatoInvalidoException{
        Consola.emitirTitulo(3, 40, "=", "Puesto de servicio");
        super.cargarDato(codigo);
        leerCategoria();
        
        Consola.emitirBordeLN(40,"=");
    }

    @Override
    public void mostrarInformacion() {
        Consola.emitirTitulo(3, 40, "-", "Puesto de servicio");
        super.mostrarDatoComunes();
        Consola.emitirMensajeLN("| categoria: "+categoriaVal[categoria]+" |");
        
        Consola.emitirBordeLN(40, "-");
    }

    @Override
    public String obtenerTipo() {
        return "Puesto servicio";
    }
    
    
    // ============================================= Metodos Privado ============================================= //
    

    private void leerCategoria() throws DatoInvalidoException{
        int opc;
        boolean opcValido;
        
        Consola.emitirMensajeLN("Tipo de categoria:");
        Consola.emitirLista(categoriaVal);
        
        Consola.emitirMensaje("Respuesta: ");
        opc = Lector.leerInt();
        
        opcValido = Validador.esNroValido(opc, 1, categoriaVal.length);
        
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de categoria no valida.");
        }
        
        setCategoria(opc-1);
    }
    
    
    // ============================================= Getter ============================================= //

    
    public String[] getCategoriaVal() {
        return categoriaVal;
    }
    
    public int getCategoria() {
        return categoria;
    }

    
    // ============================================= Setter ============================================= //
    
    
    private void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
