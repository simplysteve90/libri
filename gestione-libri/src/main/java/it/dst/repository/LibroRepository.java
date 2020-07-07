package it.dst.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dst.model.Libro;

public interface LibroRepository  extends JpaRepository<Libro, Long> {

}
