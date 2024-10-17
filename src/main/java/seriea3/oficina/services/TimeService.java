package seriea3.oficina.services;

import java.util.List;

import org.springframework.stereotype.Service;

import seriea3.oficina.models.Time;
import seriea3.oficina.repositories.TimeRepository;

@Service
public class TimeService {
    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public Time buscarTime(Long id){
        return timeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Time não encontrado"));
    }

    public Time buscarTimeNome(String nome){
        return timeRepository.findByNome(nome);
    }

    public List<Time> listarTimes(){
        return timeRepository.findAll();
    }

    public Time salvarTime(Time time){
        return timeRepository.save(time);
    }

    public Time atualizarTime(Long id, Time time){
        Time timeAtual = buscarTime(id);

        timeAtual.setNome(time.getNome());
        timeAtual.setCidade(time.getCidade());

        timeRepository.save(timeAtual);

        return timeAtual;
    }

    public void deletarTime(Long id){
        if (timeRepository.existsById(id)){
            timeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Time não encontrado");
        }
    }
}
