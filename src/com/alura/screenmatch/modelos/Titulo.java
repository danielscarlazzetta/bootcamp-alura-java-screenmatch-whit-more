package com.alura.screenmatch.modelos;

import com.alura.screenmatch.excepsion.ErrorEnConversionDeDuracionExcepcion;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo>{

    //@SerializedName("Title")
    private String nombre;
    //@SerializedName("Year")
    private int fechaDeLanzamiento;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    private int duracionEnMinutos;

    public Titulo(TituloOMDB miTituloOMDB) {
        this.nombre = miTituloOMDB.title();
        this.fechaDeLanzamiento = Integer
                .valueOf(miTituloOMDB.year());
        if(miTituloOMDB.runtime().contains("N/A")){
            throw new ErrorEnConversionDeDuracionExcepcion("No se puede convertir la duracion" +
                    "porque tiene un N/A");
        }
        this.duracionEnMinutos = Integer
                .valueOf(miTituloOMDB.runtime()
                        .substring(0, 3)
                        .replace(" ", ""));//en la api aca estaba como 60_min
                                          // con este substring solo ocupa de donde inicia y donde termina
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public int getTotalDeEvaluaciones() {
        return totalDeEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }


    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void muestraFichaTecnica(){
        System.out.println("Nombre de la película: " + nombre);
        System.out.println("Año de lanzamiento: " + fechaDeLanzamiento);
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeEvaluaciones++;
    }

    public double calculaMediaEvaluaciones(){
        return sumaDeLasEvaluaciones / totalDeEvaluaciones;
    }


    //Esto funciona para comprar los titulos, en este caso el nombre del titulo
    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return "Titulo: (nombre = " + nombre +
                ", fechaDeLanzamiento = " + fechaDeLanzamiento +
                ", Duracion en minutos: " + duracionEnMinutos + ")";
    }
}
