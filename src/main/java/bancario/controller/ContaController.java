package bancario.controller;

import bancario.model.Cliente;
import bancario.model.Conta;
import bancario.model.Extrato;
import bancario.model.Transacao;
import bancario.util.ArquivoBinario;

public class ContaController {
    public boolean depositar(Cliente cliente, double valor) {
        return cliente != null && cliente.getConta().depositar(valor);
    }

    public boolean sacar(Cliente cliente, double valor) {
        return cliente != null && cliente.getConta().sacar(valor);
    }

    public boolean transferir(Cliente origem, String numeroDestino, double valor) {
        if (origem == null) return false;

        Cliente destino = buscarPorConta(numeroDestino);
        if (destino == null) return false;

        return origem.getConta().transferir(destino.getConta(), valor);
    }

    public void exibirExtrato(Cliente cliente) {
        if (cliente != null) {
            new Extrato(cliente.getConta().getTransacoes()).exibir();
        }
    }

    private Cliente buscarPorConta(String numero) {
        for (Cliente cliente : ArquivoBinario.clientes.values()) {
            if (cliente.getConta().getNumero().equals(numero)) return cliente;
        }
        return null;
    }
}
