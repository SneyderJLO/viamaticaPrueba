package com.vitamatica.ppp.app.repository.pelicula_sala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import com.vitamatica.ppp.app.entities.PeliculaSalaCine;

public interface IPeliculaSalaCineRepository extends JpaRepository<PeliculaSalaCine, Long> {

    @Query("FROM PeliculaSalaCine psc, Pelicula p where psc.fechaPublicacion = ?1 and psc.id = p.id")
    List<PeliculaSalaCine> contarPeliculasPorFechaPublicacion(Date date);

    @Query("FROM PeliculaSalaCine psc, Pelicula p, SalaCine sc  where p.nombre = ?1 and psc.id = p.id and sc.id = ?2")
    List<PeliculaSalaCine> buscarPeliculasPorSala(String nombre, int id);

    @Query("FROM PeliculaSalaCine psc, Pelicula p, SalaCine sc where sc.nombre = ?1 and psc.id = p.id and psc.id = sc.id")
    // @Query("FROM PeliculaSalaCine psc JOIN psc.Pelicula p JOIN p.SalaCine sc
    // where sc.id = psc.id and psc.id = p.id and sc.nombre = ?1 ")
    List<PeliculaSalaCine> buscarPorSalas(String nombre);

}
