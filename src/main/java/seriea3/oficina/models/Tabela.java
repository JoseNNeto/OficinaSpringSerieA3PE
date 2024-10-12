package seriea3.oficina.models;

import lombok.Data;

@Data
public class Tabela {
    private Time time;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsPro;
    private int golsContra;
    private int saldoGols;

    public Tabela(Time time) {
        this.time = time;
    }

    public void atualizar(int golsPro, int golsContra) {
        this.golsPro += golsPro;
        this.golsContra += golsContra;
        this.saldoGols = this.golsPro - this.golsContra;
        if (golsPro > golsContra) {
            this.pontos += 3;
            this.vitorias++;
        } else if (golsPro == golsContra) {
            this.pontos++;
            this.empates++;
        } else {
            this.derrotas++;
        }
    }
}
