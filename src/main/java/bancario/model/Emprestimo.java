package bancario.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Emprestimo implements Serializable {
    private double valor;
    private double juros;
    private int parcelas;
    private LocalDateTime data;
    private boolean ativo;

    public Emprestimo(double valor, double juros, int parcelas) {
        this.valor = valor;
        this.juros = juros;
        this.parcelas = parcelas;
        this.data = LocalDateTime.now();
        this.ativo = true;
    }

    public double getValor() { return valor; }
    public double getJuros() { return juros; }
    public int getParcelas() { return parcelas; }
    public LocalDateTime getData() { return data; }
    public boolean isAtivo() { return ativo; }

    public double getValorTotal() {
        return valor + (valor * juros / 100.0);
    }

    public void finalizar() {
        ativo = false;
    }

    @Override
    public String toString() {
        return String.format("R$ %.2f | %.2f%% juros | %d parcelas | Total: R$ %.2f | %s",
                valor, juros, parcelas, getValorTotal(), ativo ? "Ativo" : "Finalizado");
    }
}
