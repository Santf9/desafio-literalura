package com.desafio.literalura.model;

public class Libro {

    private Long id;
    private String titulo;
    private String nombreAutor;
    private String lenguaje;
    private double totalDescargas;

    private Autor autor;

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        this.nombreAutor = autor.getNombre();
        this.lenguaje = String.valueOf(datosLibro.lenguajes());
        this.totalDescargas = Double.valueOf(datosLibro.totalDescargas());
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public double getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(double totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    @Override
    public String toString() {
        return "--------------- LIBRO 📖 ---------------" + "\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + nombreAutor + "\n" +
                "Idioma: " + lenguaje + "\n" +
                "Número de descargas: " + totalDescargas + "\n" +
                "------------------------------------" + "\n";
    }
}
