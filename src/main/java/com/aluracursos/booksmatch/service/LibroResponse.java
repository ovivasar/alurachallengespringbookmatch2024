package com.aluracursos.booksmatch.service;

import com.aluracursos.booksmatch.model.DatosLibro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// Clase que representa el JSON completo
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroResponse {

    @JsonAlias("count")
    private Integer count;

    @JsonAlias("results")
    private List<DatosLibro> results;

    // Getters y Setters
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DatosLibro> getResults() {
        return results;
    }

    public void setResults(List<DatosLibro> results) {
        this.results = results;
    }
}
