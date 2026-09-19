package Logica.Excepciones;

public class RepositorioVacioException extends IllegalArgumentException{
    public RepositorioVacioException(String msg) {
        super(msg);
    }
}
