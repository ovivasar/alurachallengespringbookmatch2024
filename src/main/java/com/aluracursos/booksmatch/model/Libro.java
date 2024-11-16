package com.aluracursos.booksmatch.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(unique = true)
    private String titulo;
    private Integer totalDescargas;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.id = datosLibro.id();
        this.titulo = datosLibro.titulo();
        this.totalDescargas = datosLibro.totalDescargas();
        //this.genero = Categoria.fromBookshelves(datosLibro.genero().split(",")[0].trim());
        this.genero = Categoria.fromBookshelves(datosLibro.genero());
    }

    @Override
    public String toString() {
        return  "genero=" + genero +
                "titulo='" + titulo + '\'' +
                ", totalDescargas=" + totalDescargas +
                ", autores='" + autores + '\'';

    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(e -> e.setLibro(this));
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    /*public void setId(Long id) {
        id = id;
    }*/

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

}
