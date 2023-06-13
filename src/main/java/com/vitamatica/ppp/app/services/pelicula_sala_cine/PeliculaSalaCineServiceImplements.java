package com.vitamatica.ppp.app.services.pelicula_sala_cine;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitamatica.ppp.app.entities.PeliculaSalaCine;
import com.vitamatica.ppp.app.repository.pelicula_sala.IPeliculaSalaCineRepository;

@Service
public class PeliculaSalaCineServiceImplements implements IPeliculaSalaCineService {

    @Autowired
    private IPeliculaSalaCineRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSalaCine> listarPeliculasSalaCine() {
        return (List<PeliculaSalaCine>) repository.findAll();

    }

    @Override
    @Transactional

    public PeliculaSalaCine guardar(PeliculaSalaCine peliculaSalaCine) {
        return repository.save(peliculaSalaCine);
    }

    @Override
    @Transactional(readOnly = true)
    public PeliculaSalaCine encontrarSalaCine(PeliculaSalaCine peliculaSalaCine) {
        return repository.findById(peliculaSalaCine.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public PeliculaSalaCine buscarPeliculaSalaCinePorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(PeliculaSalaCine peliculaSalaCine) {
        repository.delete(peliculaSalaCine);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSalaCine> listarPorFechas(Date date) {
        return (List<PeliculaSalaCine>) repository.contarPeliculasPorFechaPublicacion(date);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSalaCine> buscarPeliculasPorSala(String nombre, int id) {
        return (List<PeliculaSalaCine>) repository.buscarPeliculasPorSala(nombre, id);

    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSalaCine> buscarPorSalas(String nombre) {
        return (List<PeliculaSalaCine>) repository.buscarPorSalas(nombre);

    }

}
