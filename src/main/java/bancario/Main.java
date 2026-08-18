package bancario;

import bancario.controller.BancoController;
import bancario.controller.ContaController;
import bancario.controller.CreditoController;
import bancario.controller.EmprestimoController;
import bancario.controller.InvestimentoController;
import bancario.controller.RelatorioController;
import bancario.util.ArquivoBinario;
import bancario.view.MenuConsole;

public class Main {
    public static void main(String[] args) {
        ArquivoBinario.carregarDados();

        BancoController banco = new BancoController();
        ContaController conta = new ContaController();
        EmprestimoController emprestimo = new EmprestimoController();
        CreditoController credito = new CreditoController();
        InvestimentoController investimento = new InvestimentoController();
        RelatorioController relatorio = new RelatorioController();

        MenuConsole menu = new MenuConsole(
                banco, conta, emprestimo, credito, investimento, relatorio
        );

        menu.iniciar();
        ArquivoBinario.salvarDados();
    }
}
