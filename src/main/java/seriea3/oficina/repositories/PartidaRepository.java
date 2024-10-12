package seriea3.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import seriea3.oficina.models.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    
}
