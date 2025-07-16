package com.desafio.literalura;

import com.desafio.literalura.main.Main;
import com.desafio.literalura.repository.IAutorRepository;
import com.desafio.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	// Inyección de dependencias para los repositorios
	@Autowired
	private ILibroRepository libroRepository;
	private IAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Main main = new Main(autorRepository, libroRepository);
		main.muestraMenu();
	}
}
