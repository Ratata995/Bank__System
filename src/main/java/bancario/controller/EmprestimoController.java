package bancario.controller;

import bancario.model.Cliente;
import bancario.model.Emprestimo;
import bancario.model.Transacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmprestimoController {
    private Map<String, List<Emprestimo>> emprestimos = new HashMap<>();

    public Emprestimo solicitar(Cliente cliente, double valor, int parcelas) {
        if (cliente == null || valor <= 0 || parcelas <= 0) return null;

        double juros = parcelas <= 6 ? 5.0 : 10.0;
        Emprestimo emprestimo = new Emprestimo(valor, juros, parcelas);

        emprestimos.computeIfAbsent(cliente.getCpf(), k -> new ArrayList<>()).add(emprestimo);
        cliente.getConta().adicionarSaldo(valor);
        cliente.getConta().registrarTransacao(
                new Transacao("EMPRÉSTIMO", valor, "Empréstimo aprovado"));

        return emprestimo;
    }

    public List<Emprestimo> listar(Cliente cliente) {
        return emprestimos.getOrDefault(cliente.getCpf(), new ArrayList<>());
    }
}
