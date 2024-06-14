package startwars.api;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner lectura = new Scanner(System.in);
        System.out.println("Escribe el numero de la pelicula de Start wars que quiere consultar");


        ConsultaPeliculaSW consulta = new ConsultaPeliculaSW();
        try{
            var numeroDePelicula = Integer.valueOf(lectura.nextLine());
            Pelicula pelicula = consulta.buscaPelicula(numeroDePelicula);
            System.out.println(pelicula);
            GeneradorDeArchivo generador = new GeneradorDeArchivo();
            generador.guardarJson(pelicula);
        }catch (NumberFormatException e){
            System.out.println("Numero de pelicula no encontrado {" + e.getMessage() + "}") ;
        }
        catch (RuntimeException | IOException e){
            System.out.println("Error con la nbusqueda de pelicula: " + e);
        }finally {
            System.out.println("========================================");
            System.out.println("Finalizando la aplicacion.");
        }

    }
}
