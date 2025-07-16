package com.desafio.literalura.repository;

import com.desafio.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

    // Metodo para buscar un autor por nombre
    Autor findByNombreIgnoreCase(String nombre);

    // Metodo para encontrar autores por fecha de nacimiento y fecha de fallecimiento
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(int fechaNacimiento, int fechaFallecimiento);
}
