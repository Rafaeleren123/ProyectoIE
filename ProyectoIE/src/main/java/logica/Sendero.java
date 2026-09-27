package logica;

import logica.excepciones.DatoInvalidoException;

public class Sendero {
    private double distancia;
    private int dificultad;
    private int tiempoEstimado;
    private boolean habilitado;

    public Sendero(double distancia, int dificultad, int tiempoEstimado, boolean habilitado) {
        this.distancia = distancia;
        this.dificultad = dificultad;
        this.tiempoEstimado = tiempoEstimado;
        this.habilitado = habilitado;
    }
    
    public Sendero() {
        this.distancia = 0;
        this.dificultad = 0;
        this.tiempoEstimado = 0;
        this.habilitado = false;
    }    

    public double getDistancia() {
        return distancia;
    }

    public int getDificultad() {
        return dificultad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setDistancia(double distancia) throws DatoInvalidoException{
        if(distancia <= 0){
            throw new DatoInvalidoException("La distancia debe ser mayor a cero.");
        }
        
        this.distancia = distancia;
    }

    public void setDificultad(int dificultad) throws DatoInvalidoException{
        if(dificultad < 1 || dificultad > 5){
            throw new DatoInvalidoException("La dificultad solo puede ser de 1 a 5.");
        }
        
        this.dificultad = dificultad;
    }

    public void setTiempoEstimado(int tiempoEstimado) throws DatoInvalidoException{
        if(tiempoEstimado <= 0){
            throw new DatoInvalidoException("El tiempo estimado debe ser mayor a cero.");
        }
        
        this.tiempoEstimado = tiempoEstimado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}

