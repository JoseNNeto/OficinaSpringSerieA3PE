package seriea3.oficina.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_casa_id")
    private Time timeCasa;

    @ManyToOne
    @JoinColumn(name = "time_visitante_id")
    private Time timeVisitante;

    private int placarCasa;

    private int placarVisitante;

    private LocalDate data;
}
