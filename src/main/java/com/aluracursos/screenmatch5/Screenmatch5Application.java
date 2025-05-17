package com.aluracursos.screenmatch5;

import com.aluracursos.screenmatch5.model.DatosSeries;
import com.aluracursos.screenmatch5.service.ConsumoAPI;
import com.aluracursos.screenmatch5.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatch5Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obtenerDatos("https://www.episodate.com/api/show-details?q=46778");
		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosSeries.class);
		System.out.println(datos);

	}
}
