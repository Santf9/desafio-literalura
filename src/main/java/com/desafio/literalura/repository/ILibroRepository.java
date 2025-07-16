package com.desafio.literalura.repository;
import com.desafio.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

    // Metodo para buscar libros por título
    Libro findByTitulo(String titulo);

    // Metodo para encontrar libros por lenguaje
    List<Libro> findByLenguajeContaining(String lenguaje);
}
