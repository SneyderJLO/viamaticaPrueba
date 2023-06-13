package com.vitamatica.ppp.app.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @DateTimeFormat(pattern = "dd-MM-YYYY")

    private Date fechaPublicacion;
    @Column(name = "fecha_fin")
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private Date fechaFin;
    @ManyToOne
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sala_cine")
    private SalaCine salaCine;

}
