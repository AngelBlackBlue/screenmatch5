package com.aluracursos.screenmatch5.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
