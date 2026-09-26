package Utilidades;

import java.util.Scanner;

public class Lector {
    private final static Scanner leer = new Scanner(System.in);
    
    public static String leerString(){
        return leer.nextLine();
    }
    
    public static double leerDouble(){
        while(true){
            try{
                return Double.parseDouble(leerString());
            }catch(NumberFormatException e){
                Consola.emitirError("no es un numero decimal.");
            }
        }
    }
    
    public static int leerInt(){
        while(true){
            try{
                return Integer.parseInt(leerString());
            }catch(NumberFormatException e){
                Consola.emitirError("no es un numero decimal.");
            }
        }
    }
}
