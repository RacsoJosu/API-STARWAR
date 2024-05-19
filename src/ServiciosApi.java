import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ServiciosApi {
    private Gson gson;
    private List<Pelicula> peliculas;
    public ServiciosApi(){
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        this.peliculas = new ArrayList<Pelicula>();
        leeArchivos();

    }

    Pelicula buscarPelicula(int numeroPelicula) throws IOException, InterruptedException {
        Pelicula pelicula;
        try{

            String url = String.format("https://swapi.dev/api/films/%s", numeroPelicula);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 404 ){
                return null;


            }

            pelicula = new Gson().fromJson(response.body(), Pelicula.class);
            this.guardarPelicula(pelicula);



        }catch (InterruptedException | IOException e) {
            throw  new RuntimeException("No encontre esa pelicula");
        }


        return pelicula;


    }

    void leeArchivos(){
        File archivo = new File("D:\\nuevos_proyectos\\StarWarsConsultaApi\\peliculas.json");
        try (FileReader reader = new FileReader(archivo)) {
            // Define the type of the list we expect to get
            List<Pelicula> peliculasTem = new ArrayList<Pelicula>();
            List<Pelicula> peliculas = gson.fromJson(reader, peliculasTem.getClass());

            this.peliculas.addAll(peliculas);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void guardarPelicula(Pelicula pelicula) throws IOException{
        this.peliculas.add(pelicula);

        FileWriter escritura = new FileWriter("peliculas.json");

        escritura.write(this.gson.toJson(peliculas));
        escritura.close();


    }

    List<Pelicula> obtenerPeliculas(){
        return this.peliculas;
    }

    

}
