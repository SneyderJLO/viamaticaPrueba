package com.vitamatica.ppp.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sala_cine")
public class SalaCine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Long id;
    @NotNull
    @NotEmpty
    private String nombre;
    @NotNull
    @NotEmpty
    private int estado;
}
