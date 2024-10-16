package seriea3.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import seriea3.oficina.models.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {
    Time findByNome(String nome);
    Time findByCidade(String cidade);
}
