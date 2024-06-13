package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepsion.ErrorEnConversionDeDuracionExcepcion;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true){
            System.out.println("Escriba el nombre de la pelicula");
            var busqueda = lectura.nextLine();

            if(busqueda.equalsIgnoreCase("salir")){
                break;
            }
            String direccion = "http://www.omdbapi.com/?t=" +
                    busqueda.replace(" ", "+")
                    + "&apikey=d4d0bf92";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> res = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = res.body();


                TituloOMDB miTituloOMDB = gson.fromJson(json, TituloOMDB.class);

                Titulo miTitulo = new Titulo(miTituloOMDB);
                System.out.println("Titulo ya combertido: " + miTitulo);

                //FileWriter escritura = new FileWriter("Peliculas.txt");
                //escritura.write(miTitulo.toString());
                //escritura.close();

                titulos.add(miTitulo);

            }catch (NumberFormatException e){
                System.out.println("Error: " + e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Error, verifique la url " + e.getMessage());
            }catch (ErrorEnConversionDeDuracionExcepcion e){
                System.out.println("Ocurrio un error inesperado =( " + e.getMessage());
            }
            /*finally {
                System.out.println("Finalizo la ejecucion del programa");
            }*/
        }

        System.out.println("Lista de titulos" + titulos);
        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("Finalizo la ejecucion del programa");

    }
}
