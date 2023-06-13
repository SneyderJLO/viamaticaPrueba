package com.vitamatica.ppp.app.repository.pelicula;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitamatica.ppp.app.entities.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {

}
