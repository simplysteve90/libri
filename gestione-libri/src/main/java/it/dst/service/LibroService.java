package it.dst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.dst.model.Libro;
import it.dst.repository.LibroRepository;

@Service
@Transactional
public class LibroService {
	@Autowired
	LibroRepository libroRepository;
	
	public void save(Libro libro) {
		libroRepository.save(libro);
	}

	public List<Libro> listaLibri() {
		return (List<Libro>) libroRepository.findAll();
	}

	public Libro get(Long id) {
		return libroRepository.findById(id).get();
	}

	public void delete(Long id) {
		libroRepository.deleteById(id);
	}

}
