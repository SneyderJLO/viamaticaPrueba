package com.vitamatica.ppp.app.services.sala_cine;

import java.util.List;

import com.vitamatica.ppp.app.entities.SalaCine;

public interface ISalaCineService {
    public List<SalaCine> listarSalacine();

    public SalaCine guardar(SalaCine salaCine);

    public SalaCine encontrarSalaCine(SalaCine salaCine);

    public SalaCine buscarSalaCineById(Long id);

    public void eliminar(SalaCine salaCine);

    public void eliminarPorId(Long id);
}
