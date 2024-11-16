package com.aluracursos.booksmatch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;
    @ManyToOne
    private Libro libro;

    public Autor(){}

    public Autor(Integer numero, DatosAutor d) {
        this.nombre = d.nombre();
        this.anoNacimiento = d.anoNacimiento();
        this.anoFallecimiento = d.anoFallecimiento();

    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    @Override
    public String toString() {
        return
                "autor=" +
                        ", nombre='" + nombre + '\'' +
                        ", añoNacimiento=" + anoNacimiento +
                        ", añoFallecimiento=" + anoFallecimiento;
    }
}
