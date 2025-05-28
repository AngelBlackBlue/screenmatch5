package com.aluracursos.screenmatch5.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Brenda", "Luis", "Maria Fernanda", "Eric", "Genesys");

        nombres.stream()
                .sorted()
                .forEach(System.out::println);

    }
}
