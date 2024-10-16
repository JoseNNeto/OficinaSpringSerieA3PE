package seriea3.oficina.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seriea3.oficina.models.Partida;
import seriea3.oficina.models.Tabela;
import seriea3.oficina.services.PartidaService;

@RestController
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }
    

    @GetMapping("/listar")
    public List<Partida> listarPartidas(){
        return partidaService.listarPartidas();
    }

    @PostMapping("/salvar")
    public Partida salvarPartida(@RequestBody Partida partida){
        return partidaService.salvarPartida(partida);
    }

    @GetMapping("/tabela")
    public List<Tabela> gerarTabela(){
        return partidaService.gerarTabela();
    }
}
