package seriea3.oficina.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import seriea3.oficina.models.Time;
import seriea3.oficina.services.TimeService;

@RestController
@RequestMapping("/times")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/listar")
    public List<Time> listarTimes(){
        return timeService.listarTimes();
    }

    @PostMapping("/salvar")
    public Time salvarTime(@RequestBody Time time){
        return timeService.salvarTime(time);
    }
}
