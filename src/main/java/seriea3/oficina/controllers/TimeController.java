package seriea3.oficina.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Time> buscarTime(@PathVariable Long id){
        Time time = timeService.buscarTime(id);
        return ResponseEntity.ok(time);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Time> buscarTimePorNome(@PathVariable String nome){
        Time time = timeService.buscarTimeNome(nome);
        return ResponseEntity.ok(time);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Time>> listarTimes(){
        List<Time> times = timeService.listarTimes();
        return ResponseEntity.ok(times);
    }

    @PostMapping("/salvar")
    public Time salvarTime(@RequestBody Time time){
        return timeService.salvarTime(time);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Time> atualizarTime(@PathVariable Long id, @RequestBody Time time){
        Time timeAtualizado = timeService.atualizarTime(id, time);
        return ResponseEntity.ok(timeAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTime(@PathVariable Long id) {
        timeService.deletarTime(id);
        return ResponseEntity.noContent().build();
    }
}
