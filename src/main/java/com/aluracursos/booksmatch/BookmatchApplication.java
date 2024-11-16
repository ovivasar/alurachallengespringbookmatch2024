package com.aluracursos.booksmatch;

import com.aluracursos.booksmatch.principal.Menu;
import com.aluracursos.booksmatch.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmatchApplication implements CommandLineRunner {

	@Autowired
	//private SerieRepository repository;
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BookmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Principal principal = new Principal(repository);
		//principal.muestraElMenu();

		Menu menu = new Menu(repository);
		menu.muestraElMenu();
	}
}