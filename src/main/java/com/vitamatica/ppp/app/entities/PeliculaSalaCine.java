package com.vitamatica.ppp.app.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pelicula_salacine")
public class PeliculaSalaCine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula_sala")
    private Long id;
    @Column(name = "fecha_publicacion")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date fechaPublicacion;
    @Column(name = "fecha_fin")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date fechaFin;
    @ManyToOne
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sala_cine")
    private SalaCine salaCine;
    private static final String patron = "yyyy/dd/MM";

    public static Date convertir(String fecha) throws ParseException {
        // Date miFecha = new Date();
        SimpleDateFormat dateFecha = new SimpleDateFormat(patron);
        Date miFecha = dateFecha.parse(fecha);
        // System.out.println(miFecha);
        return miFecha;

    }
}
