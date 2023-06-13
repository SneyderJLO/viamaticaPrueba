package com.vitamatica.ppp.app.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitamatica.ppp.app.entities.Pelicula;
import com.vitamatica.ppp.app.entities.PeliculaSalaCine;
import com.vitamatica.ppp.app.entities.SalaCine;
import com.vitamatica.ppp.app.services.pelicula.IPeliculaService;
import com.vitamatica.ppp.app.services.pelicula_sala_cine.IPeliculaSalaCineService;
import com.vitamatica.ppp.app.services.sala_cine.ISalaCineService;

//mapear una ruta alterna en caso que el puerto por defecto 8080 esté ocupado.
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class PeliculaSalacineRestController {
    @Autowired
    private ISalaCineService salaCineService;
    @Autowired
    private IPeliculaSalaCineService peliculaSalaCineService;
    @Autowired
    private IPeliculaService peliculaService;

    /* listar todas la PeliculaSalacine */
    @GetMapping("/ListarPeliculasSalacine")
    public List<PeliculaSalaCine> listarPeliculaSalacine() {
        return peliculaSalaCineService.listarPeliculasSalaCine();

    }

    /* Crear nueva PeliculasalaCine */
    @PostMapping("/crearPeliculaSalacine")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody PeliculaSalaCine peliculaSalaCine, Pelicula pelicula,
            SalaCine salaCine) {
        Map<String, Object> response = new HashMap<>();
        PeliculaSalaCine peliculaSalaCineNuevo = null;
        try {
            pelicula = peliculaService.buscarPeliculaPorId(peliculaSalaCine.getPelicula().getId());
            salaCine = salaCineService.buscarSalaCineById(peliculaSalaCine.getSalaCine().getId());
            peliculaSalaCine.setPelicula(pelicula);
            peliculaSalaCine.setSalaCine(salaCine);
            peliculaSalaCineNuevo = peliculaSalaCineService.guardar(peliculaSalaCine);
        } catch (Exception e) {
            response.put("Error", "No se pudo crear la pelicula con la sala");
            response.put("Detalles", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Pelicula con sala creado exitosamente");
        response.put("Pelicula-Sala", peliculaSalaCineNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /* Update de PeliculaSalaCine */
    @PutMapping("/actualizarPeliculaSalacine/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PeliculaSalaCine peliculaSala,
            Pelicula pelicula, SalaCine salaCine) {
        Map<String, Object> response = new HashMap<>();

        PeliculaSalaCine peliculaSalaActual = peliculaSalaCineService.buscarPeliculaSalaCinePorId(id);
        PeliculaSalaCine peliculaSalaModificado = null;

        Pelicula peliculaNueva = peliculaService.buscarPeliculaPorId(peliculaSala.getPelicula().getId());
        SalaCine salacineNueva = salaCineService.buscarSalaCineById(peliculaSala.getSalaCine().getId());
        if (peliculaSalaActual == null) {
            response.put("error", "no se entonctró la pelicula con su sala seleccionada.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            peliculaSalaActual.setFechaFin(peliculaSala.getFechaFin());
            peliculaSalaActual.setFechaPublicacion(peliculaSala.getFechaPublicacion());
            peliculaSalaActual.setPelicula(peliculaNueva);
            peliculaSalaActual.setSalaCine(salacineNueva);
            peliculaSalaModificado = peliculaSalaCineService.guardar(peliculaSalaActual);

        } catch (Exception e) {
            response.put("Error", "No es posible actualizar el videojuego");
            response.put("Detalles", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Pelicula con su sala y cine actualizada");
        response.put("Pelicula: ", peliculaSalaModificado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarPeliculaSala/{id}")
    public ResponseEntity<?> delete(PeliculaSalaCine peliculaSala) {
        Map<String, Object> response = new HashMap<>();
        try {
            PeliculaSalaCine peliculaSalaActual = peliculaSalaCineService
                    .buscarPeliculaSalaCinePorId(peliculaSala.getId());
            if (peliculaSalaActual == null) {
                response.put("error", "no se encontro la pelicula con su sala.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            peliculaSalaCineService.eliminar(peliculaSala);
        } catch (Exception e) {
            response.put("Error", "No se pudo eliminar la pelicula con su sala.");
            response.put("Detalles", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "la pelicula con su respectiva sala fue eliminada.");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/listarFecha/{date}")
    public ResponseEntity<?> listarFechas(@PathVariable String date) {
        Map<String, Object> response = new HashMap<>();
        List<PeliculaSalaCine> peliculas = peliculaSalaCineService.listarPorFechas(convertirFechaDate(date));
        response.put("La cantidad de peliculas son:", peliculas.size());
        response.put("Peliculas: ", peliculas);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @GetMapping("/listarPeliculasSalas/{nombre}/{id}")
    public ResponseEntity<?> listarPeliculasSalas(@PathVariable String nombre, @PathVariable int id) {

        Map<String, Object> response = new HashMap<>();
        List<PeliculaSalaCine> peliculas = peliculaSalaCineService.buscarPeliculasPorSala(nombre, id);
        System.out.println(peliculas.get(0).getPelicula().getNombre());
        response.put("mensaje", "Pelicula con sala.");
        response.put("Pelicula-Sala", peliculas);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/listarPorSalas/{nombre}")
    public ResponseEntity<?> listarPorSalas(@PathVariable String nombre) {
        Map<String, Object> response = new HashMap<>();
        List<PeliculaSalaCine> peliculas = peliculaSalaCineService.buscarPorSalas(nombre);
        int contadorPeliculas = 0;
        for (int i = 0; i < peliculas.size(); i++) {
            if (peliculas.get(i).getPelicula().getNombre() != null) {
                contadorPeliculas++;

            }
        }
        if (contadorPeliculas < 3) {
            response.put("mensaje", "Sala casi Vacía");
            response.put("Pelicula-Sala", peliculas);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if (contadorPeliculas >= 3 && contadorPeliculas <= 5) {
            response.put("mensaje", "Sala casi llena");
            response.put("Pelicula-Sala", peliculas);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if (contadorPeliculas > 5) {
            response.put("mensaje", "Sala llena");
            response.put("Pelicula-Sala", peliculas);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        response.put("Pelicula-Sala", peliculas);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    public Date convertirFechaDate(String fechaStr) {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return formatoFecha.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
