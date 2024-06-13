package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("El señor de los anillos", 2001);
        peliculaDeBruno.evalua(10);

        Serie lost = new Serie("Lost", 2000);

        Pelicula p1 = peliculaDeBruno;

        List<Titulo> lista = new ArrayList<>();
        lista.add(peliculaDeBruno);
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(lost);

        for (Titulo item: lista){
            System.out.println(item.getNombre());
            if(item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2){
                //Pelicula pelicula = (Pelicula) item;
                System.out.println(((Pelicula) item).getClasificacion());
            }

        }

        ArrayList<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penelope Cruz");
        listaDeArtistas.add("Antonio Bandera");
        listaDeArtistas.add("Ricardo Darin");

        Collections.sort(listaDeArtistas);

        System.out.println("=============================================================");
        System.out.println("Lista de Artidtas ordenada: " + listaDeArtistas);

        System.out.println("=============================================================");
        Collections.sort(lista);
        System.out.println("Lista ordenada de titulos: " + lista);

        System.out.println("=============================================================");
        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista Ordenada por fecha: " + lista);
    }
}
