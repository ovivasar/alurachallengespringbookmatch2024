package com.aluracursos.booksmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Integer totalDescargas,
        @JsonAlias("bookshelves") List<String> genero, // Cambiado a List<String>
        @JsonAlias("copyright") Boolean copyright)
{
}
