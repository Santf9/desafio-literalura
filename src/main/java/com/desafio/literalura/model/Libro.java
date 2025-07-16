package com.desafio.literalura.model;

import com.desafio.literalura.dto.DatosLibro;

public class Libro {

    private String titulo;
    private String autor;
    private String lenguaje;
    private Integer totalDescargas;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = String.valueOf(datosLibro.autores());
        this.lenguaje = String.valueOf(datosLibro.lenguajes());
        this.totalDescargas = datosLibro.totalDescargas();
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    @Override
    public String toString() {
        return "Libro= " +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", lenguaje='" + lenguaje + '\'' +
                ", totalDescargas=" + totalDescargas;
    }
}
