package com.aluracursos.booksmatch.repository;

import com.aluracursos.booksmatch.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    List<Libro> findTop5ByOrderByTotalDescargasDesc();
    List<Libro> findByGenero(Categoria categoria);

    @Query("SELECT s FROM Libro s WHERE s.totalDescargas <= :totalDescargas")
    List<Libro> librosPorDescargas(int totalDescargas);

    @Query("SELECT e FROM Libro s JOIN s.autores e WHERE e.nombre ILIKE %:nombreAutor%")
    List<Autor> autoresPorNombre(String nombreAutor);

    //@Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5 ")
    //List<Episodio> top5Episodios(Serie serie);
}
