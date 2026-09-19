package Logica.Excepciones;

public class DatoInvalidoException extends IllegalArgumentException{
    public DatoInvalidoException(String msg) {
        super(msg);
    }
}
