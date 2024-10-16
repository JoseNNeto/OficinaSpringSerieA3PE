package seriea3.oficina.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import seriea3.oficina.models.Partida;
import seriea3.oficina.models.Tabela;
import seriea3.oficina.models.Time;
import seriea3.oficina.repositories.PartidaRepository;
import seriea3.oficina.repositories.TimeRepository;

@Service
public class PartidaService {
    private final PartidaRepository partidaRepository;

    private final TimeService timeService;

    public PartidaService(PartidaRepository partidaRepository, TimeService timeService, TimeRepository timeRepository) {
        this.partidaRepository = partidaRepository;
        this.timeService = timeService;
    }

    public Partida buscarPartida(Long id) {
        return partidaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partida não encontrada"));
    }

    public List<Partida> buscarPartidaTime(Long id) {
        Time time = timeService.buscarTime(id);
        return partidaRepository.findByTimeCasaOrTimeVisitante(time, time);
    }

    public List<Partida> buscarPartidaData(LocalDate data) {
        return partidaRepository.findByData(data);
    }

    public List<Partida> listarPartidas() {
        return partidaRepository.findAll();
    }

    public Partida salvarPartida(Partida partida) {
        Time timeCasa = timeService.buscarTime(partida.getTimeCasa().getId());
        Time timeVisitante = timeService.buscarTime(partida.getTimeVisitante().getId());

        partida.setTimeCasa(timeCasa);
        partida.setTimeVisitante(timeVisitante);

        return partidaRepository.save(partida);
    }

    public Partida atualizarPartida(Long id, Partida partida) {
        Partida partidaAtual = buscarPartida(id);

        Time timeCasa = timeService.buscarTime(partida.getTimeCasa().getId());
        Time timeFora = timeService.buscarTime(partida.getTimeVisitante().getId());

        partidaAtual.setTimeCasa(timeCasa);
        partidaAtual.setTimeVisitante(timeFora);
        partidaAtual.setPlacarCasa(partida.getPlacarCasa());
        partidaAtual.setPlacarVisitante(partida.getPlacarVisitante());
        partidaAtual.setData(partida.getData());

        return partidaRepository.save(partidaAtual);
    }

    public void deletarPartida(Long id) {
        if (partidaRepository.existsById(id)){
            partidaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Partida não encontrada");
        }

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
