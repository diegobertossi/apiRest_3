package com.example.ApiRestSB.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestSB.modelos.Pelicula;
import com.example.ApiRestSB.repositorios.PeliculaRepository;

@RestController
public class PeliculaController {
	
	PeliculaRepository repositorio;

	public PeliculaController(PeliculaRepository repositorio) {
		this.repositorio = repositorio;
		}
	
	@GetMapping("/api/crearPeliculas")
	public void crearPeliculas() {
		
		Pelicula pelicula1 = new Pelicula("Titanic", "James Cameron", "drama");
		Pelicula pelicula2 = new Pelicula("Forest Gump", "Robert Zemekis", "drama");
		Pelicula pelicula3 = new Pelicula("Star Wars", "George Lucas", "Ficción");
		
		repositorio.save(pelicula1);
		repositorio.save(pelicula2);
		repositorio.save(pelicula3);
		
		
	}
	
	@GetMapping("api/peliculas")
	public List<Pelicula> obtenerPeliculas(){
		
		return repositorio.findAll();
		
	}
	
	
}
