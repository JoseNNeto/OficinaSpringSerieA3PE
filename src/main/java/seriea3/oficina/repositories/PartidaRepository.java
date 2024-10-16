package seriea3.oficina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import seriea3.oficina.models.Partida;
import seriea3.oficina.models.Time;
import java.time.LocalDate;


public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByTimeCasaOrTimeVisitante(Time timeCasa, Time timeVisitante);
    List<Partida> findByData(LocalDate data);
}
