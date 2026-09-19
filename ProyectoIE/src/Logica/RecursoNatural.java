package Logica;

import Logica.Excepciones.DatoInvalidoException;
import Utilidades.*;

public class RecursoNatural extends PuntoInteres {
    private final String[] categoriaVal = {
        "Cascada", 
        "Laguna", 
        "Bosque", 
        "Formacion rocosa"
    };
    
    private int categoria;
    
    public RecursoNatural(){
        super();
        categoria = 0;
    }
    
    
    // ============================================= Metodos Publicos ============================================= //
    

    @Override
    public void mostrarInformacion() {
        Consola.emitirTitulo(3, 40, "-", "Recurso natural");
        super.mostrarDatoComunes();
        Consola.emitirMensajeLN("| Tipo de servicio: "+categoriaVal[categoria]+" |");
        
        Consola.emitirBordeLN(40, "-");
    }

    @Override
    public String obtenerTipo() {
        return "Recurso natural";
    }
    
    
    // ============================================= Getter ============================================= //
    

    public String[] getCategoriaVal() {
        return categoriaVal;
    }

    public int getCategoria() {
        return categoria;
    }
    
    
    // ============================================= Setter ============================================= //
    

    public void setCategoria(int categoria) throws DatoInvalidoException{
        boolean opcValido = Validador.esNroValido(categoria, 1, categoriaVal.length);
        
        if(!opcValido){
            throw new DatoInvalidoException("Opcion de categoria no valida.");
        }
        
        this.categoria = categoria;
    }
}
