package com.vitamatica.ppp.app.services.pelicula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitamatica.ppp.app.entities.Pelicula;
import com.vitamatica.ppp.app.repository.pelicula.IPeliculaRepository;

@Service
public class PeliculaServiceImplements implements IPeliculaService {

    @Autowired
    private IPeliculaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> listarPeliculas() {
        return (List<Pelicula>) repository.findAll();

    }

    @Override
    @Transactional
    public Pelicula guardar(Pelicula pelicula) {
        return repository.save(pelicula);
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula encontPelicula(Pelicula pelicula) {
        return repository.findById(pelicula.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula buscarPeliculaPorId(Long id) {
        return repository.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public void eliminar(Pelicula pelicula) {
        repository.delete(pelicula);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

}
