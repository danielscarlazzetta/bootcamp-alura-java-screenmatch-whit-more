package startwars.api;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaPeliculaSW {


    public Pelicula buscaPelicula (int numeroDePeliculas){

        URI direccion = URI.create("https://swapi.py4e.com/api/films/" + numeroDePeliculas + "/");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> res = null;
            res = client
                    .send(req, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(res.body(), Pelicula.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("NO se encontro al Pelicula: " + e);
        }

    }
}
