package bancario.model;

import java.io.Serializable;
import java.util.List;

public class Extrato implements Serializable {
    private List<Transacao> transacoes;

    public Extrato(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public void exibir() {
        System.out.println("\n========== EXTRATO ==========");
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (Transacao t : transacoes) {
                System.out.println(t);
            }
        }
        System.out.println("=============================");
    }
}
