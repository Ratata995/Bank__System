package bancario.controller;

import bancario.model.Cliente;
import bancario.model.Investimento;
import bancario.model.Transacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvestimentoController {
    private Map<String, List<Investimento>> investimentos = new HashMap<>();

    public Investimento investir(Cliente cliente, String tipo, double valor) {
        if (cliente == null || valor <= 0 || !cliente.getConta().retirarSaldo(valor)) return null;

        double rendimento;
        if (tipo.equalsIgnoreCase("Poupanca")) {
            rendimento = 6.0;
        } else if (tipo.equalsIgnoreCase("CDB")) {
            rendimento = 10.0;
        } else {
            rendimento = 12.0;
        }

        Investimento investimento = new Investimento(tipo, valor, rendimento);
        investimentos.computeIfAbsent(cliente.getCpf(), k -> new ArrayList<>()).add(investimento);

        cliente.getConta().registrarTransacao(
                new Transacao("INVESTIMENTO", valor, "Investimento em " + tipo));

        return investimento;
    }

    public List<Investimento> listar(Cliente cliente) {
        return investimentos.getOrDefault(cliente.getCpf(), new ArrayList<>());
    }
}
