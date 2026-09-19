package Utilidades;

public class Validador {
    public static boolean esStringVacio(String dato){
        return dato == null || dato.trim().isEmpty();
    }
    
    public static boolean esDecimalValido(double d, double min, double max){
        return d >= min && d <= max;
    }
    
    public static boolean esDecimalPositivo(double d){
        return d >= 0;
    }
    
    public static boolean esNroValido(int nro, int min, int max){
        return nro >= min && nro <= max;
    }
    
    public static boolean esNroPositivo(int nro){
        return nro >= 0;
    }
    
    public static boolean esUnNro(String nro){
        return nro.matches("//d+");
    }
}
