package bancario.controller;

import bancario.model.Cliente;
import bancario.model.Transacao;
import bancario.util.ArquivoTexto;

import java.util.Map;

public class RelatorioController {
    public void gerar(Map<String, Cliente> clientes) {
        StringBuilder sb = new StringBuilder();

        sb.append("========== RELATÓRIO FINAL ==========\n");
        sb.append("Sistema Bancário - Projeto Educacional\n\n");
        sb.append("Total de clientes: ").append(clientes.size()).append("\n\n");

        for (Cliente cliente : clientes.values()) {
            sb.append("Cliente: ").append(cliente.getNome()).append("\n");
            sb.append("CPF: ").append(cliente.getCpf()).append("\n");
            sb.append("Conta: ").append(cliente.getConta().getNumero()).append("\n");
            sb.append(String.format("Saldo: R$ %.2f\n", cliente.getConta().getSaldo()));
            sb.append("Movimentações:\n");

            for (Transacao t : cliente.getConta().getTransacoes()) {
                sb.append("  ").append(t).append("\n");
            }
            sb.append("-------------------------------------\n");
        }

        ArquivoTexto.escrever("relatorio_bancario.txt", sb.toString());
        System.out.println("Relatório gerado em relatorio_bancario.txt");
    }
}
