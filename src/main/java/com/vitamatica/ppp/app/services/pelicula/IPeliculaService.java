package com.vitamatica.ppp.app.services.pelicula;

import java.util.List;
import com.vitamatica.ppp.app.entities.Pelicula;

public interface IPeliculaService {
    public List<Pelicula> listarPeliculas();

    public Pelicula guardar(Pelicula Pelicula);

    public Pelicula encontPelicula(Pelicula Pelicula);

    public Pelicula buscarPeliculaPorId(Long id);

    public void eliminar(Pelicula Pelicula);

    public void eliminarPorId(Long id);

}
