package com.aluracursos.screenmatch5;

import com.aluracursos.screenmatch5.model.DatosEpisodio;
import com.aluracursos.screenmatch5.model.DatosSeries;
import com.aluracursos.screenmatch5.model.DatosTemporada;
import com.aluracursos.screenmatch5.service.ConsumoAPI;
import com.aluracursos.screenmatch5.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Screenmatch5Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obtenerDatos("http://www.omdbapi.com/?t=Stranger+Things&&apikey=812f93ca");

		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosSeries.class);
		System.out.println(datos);

		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Stranger+Things&Season=1&episode=1&&apikey=812f93ca");
		DatosEpisodio episodio = conversor.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(episodio);

		List<DatosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i < datos.totalTemporadas() ; i++) {
			json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Stranger+Things&Season="+i+"&&apikey=812f93ca");
			var datosTemporadas = conversor.obtenerDatos(json, DatosTemporada.class);
			temporadas.add(datosTemporadas);
		}

		temporadas.forEach(System.out::println);



	}
}
