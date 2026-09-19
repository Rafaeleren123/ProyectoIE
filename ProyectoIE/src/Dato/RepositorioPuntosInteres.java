package Dato;

import Logica.PuntoInteres;

public interface RepositorioPuntosInteres {
    public void agregar(PuntoInteres punto);
    public PuntoInteres obtener(int posicion);
    public PuntoInteres buscarPorCodigo(int codigo);
    public int cantidad();
    public boolean estaLleno();
    public boolean estaVacio();
    public boolean existeCodigo(int codigo);
}
