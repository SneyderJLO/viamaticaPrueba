package com.vitamatica.ppp.app.services.pelicula_sala_cine;

import java.util.Date;
import java.util.List;

import com.vitamatica.ppp.app.entities.PeliculaSalaCine;

public interface IPeliculaSalaCineService {

    public List<PeliculaSalaCine> listarPeliculasSalaCine();

    public PeliculaSalaCine guardar(PeliculaSalaCine peliculaSalaCine);

    public PeliculaSalaCine encontrarSalaCine(PeliculaSalaCine peliculaSalaCine);

    public PeliculaSalaCine buscarPeliculaSalaCinePorId(Long id);

    public void eliminar(PeliculaSalaCine peliculaSalaCine);

    public void eliminarPorId(Long id);

    public List<PeliculaSalaCine> listarPorFechas(Date date);

    public List<PeliculaSalaCine> buscarPeliculasPorSala(String nombre, int id);

    public List<PeliculaSalaCine> buscarPorSalas(String nombre);

}
