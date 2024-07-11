package com.example.ApiRestSB.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.ApiRestSB.modelos.Pelicula;
import com.example.ApiRestSB.repositorios.PeliculaRepository;

@RestController
public class PeliculaController {

	PeliculaRepository repositorio;

	public PeliculaController(PeliculaRepository repositorio) {
		this.repositorio = repositorio;
	}

	
	
//	@Configuration
//	public class WebConfig implements WebMvcConfigurer {
//
//	    @Override
//	    public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/api/**")
//	                .allowedOrigins("http://127.0.0.1:5500")
//	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//	    }
//	}
//	@GetMapping("/api/crearPeliculas")
//	public void crearPeliculas() {
//
//		Pelicula pelicula1 = new Pelicula("Titanic", "James Cameron", "drama");
//		Pelicula pelicula2 = new Pelicula("Forest Gump", "Robert Zemekis", "drama");
//		Pelicula pelicula3 = new Pelicula("Star Wars", "George Lucas", "Ficción");
//
//		repositorio.save(pelicula1);
//		repositorio.save(pelicula2);
//		repositorio.save(pelicula3);
//
//	}
	
	@CrossOrigin("http://127.0.0.1:5500")
	@GetMapping("/api/peliculas")
	public List<Pelicula> obtenerPeliculas() {

		return repositorio.findAll();

	}
	
	@CrossOrigin("http://127.0.0.1:5500")
	@GetMapping("/api/pelicula/{id}")
	public ResponseEntity<Pelicula> obtenerPelicula(@PathVariable Long id) {
		Optional<Pelicula> opt = repositorio.findById(id);

		if (opt.isEmpty()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(opt.get());
		}
	}

	@CrossOrigin("http://127.0.0.1:5500")
	@PostMapping("/api/Guardarpelicula")
	public ResponseEntity<Pelicula> guardarPelicula(@RequestBody Pelicula pelicula) {

		if (pelicula.getId() != null) {
			return ResponseEntity.badRequest().build();
		} 
		
		repositorio.save(pelicula);
		return ResponseEntity.ok(pelicula);
		
	}
	
	
	@CrossOrigin("http://127.0.0.1:5500")
	@PutMapping("/api/Guardarpelicula")
	public ResponseEntity<Pelicula> actualizarPelicula(@RequestBody Pelicula pelicula) {

		if (pelicula.getId() == null || !repositorio.existsById(pelicula.getId())) {
			
			
			return ResponseEntity.badRequest().build();
		} 
		
		repositorio.save(pelicula);
		return ResponseEntity.ok(pelicula);
		
	}
	
	
	 @CrossOrigin(origins = "http://127.0.0.1:5500")
	@DeleteMapping("/api/pelicula/{id}") 	 
	public ResponseEntity<Pelicula> borrarPelicula(@PathVariable Long id) {

		if (id == null || !repositorio.existsById(id)) {
			
			return ResponseEntity.badRequest().build();
		} 
		
		repositorio.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	

}
