package logica.excepciones;

public class DatoInvalidoException extends IllegalArgumentException{
    public DatoInvalidoException(String msg) {
        super(msg);
    }
}
