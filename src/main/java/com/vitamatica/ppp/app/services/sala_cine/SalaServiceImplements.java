package com.vitamatica.ppp.app.services.sala_cine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitamatica.ppp.app.entities.SalaCine;
import com.vitamatica.ppp.app.repository.sala.ISalaRepository;

@Service
public class SalaServiceImplements implements ISalaCineService {
    @Autowired
    private ISalaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<SalaCine> listarSalacine() {
        return (List<SalaCine>) repository.findAll();
    }

    @Override
    @Transactional
    public SalaCine guardar(SalaCine salaCine) {
        return repository.save(salaCine);
    }

    @Override
    @Transactional(readOnly = true)
    public SalaCine encontrarSalaCine(SalaCine salaCine) {
        return repository.findById(salaCine.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public SalaCine buscarSalaCineById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(SalaCine salaCine) {
        repository.delete(salaCine);
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

}
