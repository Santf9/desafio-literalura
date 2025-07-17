package com.desafio.literalura.model;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(name = "nombre_autor")
    private String nombreAutor;
    @Column(name = "lenguajes")
    private String lenguaje;
    private double totalDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    // Constructor vacío requerido por JPA
    public Libro() {
    }

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
