package bancario.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Investimento implements Serializable {
    private String tipo;
    private double valor;
    private double rendimentoPercentual;
    private LocalDateTime data;

    public Investimento(String tipo, double valor, double rendimentoPercentual) {
        this.tipo = tipo;
        this.valor = valor;
        this.rendimentoPercentual = rendimentoPercentual;
        this.data = LocalDateTime.now();
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public double getRendimentoPercentual() { return rendimentoPercentual; }
    public LocalDateTime getData() { return data; }

    public double getValorProjetado() {
        return valor + valor * rendimentoPercentual / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%s | Investido: R$ %.2f | Rendimento: %.2f%% | Projetado: R$ %.2f",
                tipo, valor, rendimentoPercentual, getValorProjetado());
    }
}
