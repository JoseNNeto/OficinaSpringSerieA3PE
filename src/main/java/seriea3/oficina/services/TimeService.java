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

    public List<Time> listarTimes(){
        return timeRepository.findAll();
    }

    public Time salvarTime(Time time){
        return timeRepository.save(time);
    }
}
