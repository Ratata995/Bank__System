package bancario.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao implements Serializable {
    private String tipo;
    private double valor;
    private String descricao;
    private LocalDateTime data;

    public Transacao(String tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.data = LocalDateTime.now();
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getData() { return data; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%s | %-15s | R$ %10.2f | %s",
                data.format(f), tipo, valor, descricao);
    }
}
