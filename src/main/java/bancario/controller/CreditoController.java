package bancario.controller;

import bancario.model.Cliente;
import bancario.model.Transacao;

public class CreditoController {
    public boolean utilizar(Cliente cliente, double valor) {
        if (cliente == null || valor <= 0) return false;

        boolean sucesso = cliente.getConta().utilizarCredito(valor);
        if (sucesso) {
            cliente.getConta().registrarTransacao(
                    new Transacao("CRÉDITO", valor, "Utilização do limite de crédito"));
        }
        return sucesso;
    }

    public boolean pagar(Cliente cliente, double valor) {
        if (cliente == null || valor <= 0) return false;

        boolean sucesso = cliente.getConta().pagarCredito(valor);
        if (sucesso) {
            cliente.getConta().registrarTransacao(
                    new Transacao("PAGAMENTO CRÉDITO", valor, "Pagamento do crédito"));
        }
        return sucesso;
    }

    public double consultarDisponivel(Cliente cliente) {
        return cliente.getConta().getCreditoDisponivel();
    }
}
