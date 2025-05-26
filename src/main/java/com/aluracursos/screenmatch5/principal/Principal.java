package com.aluracursos.screenmatch5.principal;

import com.aluracursos.screenmatch5.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String APY_KEY = "&apikey=812f93ca";

    public void muestraElMenu(){
        System.out.println("Ingres el nombre de la serie: ");
        var nombreSerie = teclado.nextLine();
        var json = consumoAPI.obtenerDatos( URL_BASE + nombreSerie.replace(" ", "+") + APY_KEY);
        System.out.println(json);

    }
}
