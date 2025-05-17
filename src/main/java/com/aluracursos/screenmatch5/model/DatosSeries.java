package com.aluracursos.screenmatch5.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosSeries(
        @JsonAlias("name") String titulo,
        @JsonAlias("description") String description,
        @JsonAlias("start_date") String start,
        @JsonAlias("rating") String rating
) {
}
