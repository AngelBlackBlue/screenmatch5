package com.aluracursos.screenmatch5.principal;

import com.aluracursos.screenmatch5.model.DatosEpisodio;
import com.aluracursos.screenmatch5.model.DatosSeries;
import com.aluracursos.screenmatch5.model.DatosTemporada;
import com.aluracursos.screenmatch5.model.Episodios;
import com.aluracursos.screenmatch5.service.ConsumoAPI;
import com.aluracursos.screenmatch5.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String APY_KEY = "&apikey=812f93ca";
    private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraElMenu(){

        //Busca los datos de la serie
        System.out.println("Ingres el nombre de la serie: ");
        var nombreSerie = teclado.nextLine();
        var json = consumoAPI.obtenerDatos( URL_BASE + nombreSerie.replace(" ", "+") + APY_KEY);
        var datos = conversor.obtenerDatos(json, DatosSeries.class);
        System.out.println(datos);
        //http://www.omdbapi.com/?t=Game of Thrones&Season=1&Episode=1
        // Busca los datos de todas las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= datos.totalTemporadas() ; i++) {
            json = consumoAPI.obtenerDatos( URL_BASE + nombreSerie.replace(" ", "+") +"&Season=" + i + APY_KEY );
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);

        //Mostrar solo el titulo de los episodios para las temporadas
//        for (int i = 0; i < datos.totalTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodeos();
//            System.out.println("Episodios: ");
//            for (int j = 0; j < episodiosTemporadas.size() ; j++) {
//                System.out.println(episodiosTemporadas.get(j).titulo());
//            }
//        }

        //funicones lambdas
        temporadas.forEach(t -> t.episodeos().forEach(e -> System.out.println(e.titulo())));

        //convertir todas las informaciones a una lista del tipo DatoEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodeos().stream())
                .collect(Collectors.toList());

        //Top 5 episodios
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);


        //convirtiendo los datos a una lista de tipo espisodio
        List<Episodios> episodios = temporadas.stream()
                .flatMap(t -> t.episodeos().stream()
                        .map(d -> new Episodios(t.numero(),d)))
                .collect(Collectors.toList());
        episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de x año
        System.out.println("Indique a partir de que fecha desea ver los episodios");
        var fecha = teclado.nextInt();
        teclado.nextLine();

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getFechaDeLanzamineto() != null && e.getFechaDeLanzamineto().isAfter(fechaBusqueda))
                .forEach( e -> {
                    System.out.println(
                            "Temporada " + e.getTemporada() +
                                    "Episodio " + e.getTitulo() +
                                    "Fecha de lanzamiento " + e.getFechaDeLanzamineto()
                    );
                });
    }
}
