package com.vitamatica.ppp.app.repository.sala;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitamatica.ppp.app.entities.SalaCine;

public interface ISalaRepository extends JpaRepository<SalaCine, Long> {

}
