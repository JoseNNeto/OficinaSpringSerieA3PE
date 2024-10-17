package seriea3.oficina.controllers;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seriea3.oficina.models.Partida;
import seriea3.oficina.models.Tabela;
import seriea3.oficina.services.PartidaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService partidaService;

    //Terminar de testar os controllers

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Partida> buscarPartida(@PathVariable Long id){
        Partida partida = partidaService.buscarPartida(id);
        return ResponseEntity.ok(partida);
    }

    @GetMapping("/time/{id}")
    public ResponseEntity<List<Partida>> buscarPartidaTime(@PathVariable Long id){
        List<Partida> partidas = partidaService.buscarPartidaTime(id);
        return ResponseEntity.status(200).body(partidas);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<Partida>> buscarPartidaData(@PathVariable LocalDate data){
        List<Partida> partidas = partidaService.buscarPartidaData(data);
        return ResponseEntity.ok(partidas);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Partida>> listarPartidas(){
        List<Partida> partidas = partidaService.listarPartidas();
        return ResponseEntity.ok(partidas);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Partida> salvarPartida(@RequestBody Partida partida){
        Partida novaPartida = partidaService.salvarPartida(partida);
        return ResponseEntity.status(201).body(novaPartida);
    }

    //Colocar return ResponseEntity.status(xxx).body(x);

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Partida> atualizarPartida(@PathVariable Long id, @RequestBody Partida partida){
        Partida partidaAtualizada = partidaService.atualizarPartida(id, partida);
        return ResponseEntity.ok(partidaAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPartida(@PathVariable Long id){
        partidaService.deletarPartida(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tabela")
    public List<Tabela> gerarTabela(){
        return partidaService.gerarTabela();
    }

    @GetMapping("/tabela/print")
    public ResponseEntity<String> gerarTabelaFormatada() {
        List<Tabela> tabela = partidaService.gerarTabela();
        
        // Ordena a tabela pelos pontos
        tabela.sort(Comparator.comparingInt(Tabela::getPontos).reversed());

        // StringBuilder para gerar a tabela formatada
        StringBuilder tabelaFormatada = new StringBuilder();
        tabelaFormatada.append(String.format("%-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Time", "PTS", "PJ", "VIT", "E", "DER", "GM", "GS", "SG"));
        tabelaFormatada.append("------------------------------------------------------------------------------------------------------\n");

        for (Tabela tc : tabela) {
            tabelaFormatada.append(String.format("%-20s %-10d %-10d %-10d %-10d %-10s %-10s %-10s %-10s\n", 
                    tc.getTime().getNome(), 
                    tc.getPontos(), 
                    tc.getJogos(),
                    tc.getVitorias(), 
                    tc.getEmpates(), 
                    tc.getDerrotas(),
                    tc.getGolsPro(),
                    tc.getGolsContra(),
                    tc.getSaldoGols()));
        }

        return ResponseEntity.ok(tabelaFormatada.toString()); // Retorna a tabela formatada como string
    }

}
