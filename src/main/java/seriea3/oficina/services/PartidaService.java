package seriea3.oficina.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import seriea3.oficina.models.Partida;
import seriea3.oficina.models.Tabela;
import seriea3.oficina.models.Time;
import seriea3.oficina.repositories.PartidaRepository;

@Service
public class PartidaService {
    private final PartidaRepository partidaRepository;

    private final TimeService timeService;

    public PartidaService(PartidaRepository partidaRepository, TimeService timeService) {
        this.partidaRepository = partidaRepository;
        this.timeService = timeService;
    }

    public Partida salvarPartida(Partida partida) {
        // partida.setTimeCasa(timeService.salvarTime(partida.getTimeCasa()));
        // partida.setTimeVisitante(timeService.salvarTime(partida.getTimeVisitante()));
        return partidaRepository.save(partida);
    }

    public List<Partida> listarPartidas() {
        return partidaRepository.findAll();
    }

    public List<Tabela> gerarTabela(){
        List<Time> times = timeService.listarTimes();
        List<Partida> partidas = listarPartidas();

        Map<Long, Tabela> tabela = new HashMap<>();
        for (Time time : times) {
            tabela.put(time.getId(), new Tabela(time));
        }
        
        for(Partida partida : partidas) {
            Tabela timeCasa = tabela.get(partida.getTimeCasa().getId());
            Tabela timeVisitante = tabela.get(partida.getTimeVisitante().getId());

            timeCasa.atualizar(partida.getPlacarCasa(), partida.getPlacarVisitante());
            timeVisitante.atualizar(partida.getPlacarVisitante(), partida.getPlacarCasa());
        }

        return new ArrayList<>(tabela.values());
    }
}
