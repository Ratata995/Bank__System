package bancario.view;

import bancario.controller.*;
import bancario.model.Cliente;
import bancario.model.Emprestimo;
import bancario.model.Investimento;

import java.util.Scanner;

public class MenuConsole {
    private final Scanner scanner = new Scanner(System.in);
    private final BancoController banco;
    private final ContaController conta;
    private final EmprestimoController emprestimo;
    private final CreditoController credito;
    private final InvestimentoController investimento;
    private final RelatorioController relatorio;

    public MenuConsole(BancoController banco, ContaController conta,
                       EmprestimoController emprestimo, CreditoController credito,
                       InvestimentoController investimento, RelatorioController relatorio) {
        this.banco = banco;
        this.conta = conta;
        this.emprestimo = emprestimo;
        this.credito = credito;
        this.investimento = investimento;
        this.relatorio = relatorio;
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n===== BANCO JAVA =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Login");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: login(); break;
                case 3: System.out.println("Encerrando..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 3);
    }

    private void cadastrar() {
        System.out.println("\n--- CADASTRO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = banco.cadastrar(nome, cpf, usuario, senha);

        if (cliente == null) {
            System.out.println("Cadastro recusado. Verifique CPF, usuário ou dados informados.");
        } else {
            System.out.println("Cadastro realizado com sucesso!");
            System.out.println("Número da conta: " + cliente.getConta().getNumero());
        }
    }

    private void login() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = banco.login(usuario, senha);

        if (cliente == null) {
            System.out.println("Usuário ou senha incorretos.");
            return;
        }

        System.out.println("Login realizado. Bem-vindo(a), " + cliente.getNome() + "!");
        menuConta(cliente);
    }

    private void menuConta(Cliente cliente) {
        int opcao;
        do {
            System.out.println("\n===== CONTA " + cliente.getConta().getNumero() + " =====");
            System.out.printf("Saldo: R$ %.2f%n", cliente.getConta().getSaldo());
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Empréstimo");
            System.out.println("5 - Crédito");
            System.out.println("6 - Investimento");
            System.out.println("7 - Extrato");
            System.out.println("8 - Meus dados");
            System.out.println("9 - Gerar relatório");
            System.out.println("0 - Logout");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1: depositar(cliente); break;
                case 2: sacar(cliente); break;
                case 3: transferir(cliente); break;
                case 4: menuEmprestimo(cliente); break;
                case 5: menuCredito(cliente); break;
                case 6: menuInvestimento(cliente); break;
                case 7: conta.exibirExtrato(cliente); break;
                case 8: mostrarDados(cliente); break;
                case 9: relatorio.gerar(banco.getClientes()); break;
                case 0: System.out.println("Logout realizado."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void depositar(Cliente cliente) {
        System.out.print("Valor do depósito: R$ ");
        double valor = lerDouble();
        System.out.println(conta.depositar(cliente, valor) ? "Depósito realizado." : "Valor inválido.");
    }

    private void sacar(Cliente cliente) {
        System.out.print("Valor do saque: R$ ");
        double valor = lerDouble();
        System.out.println(conta.sacar(cliente, valor)
                ? "Saque realizado." : "Saque recusado. Verifique o saldo e o valor.");
    }

    private void transferir(Cliente cliente) {
        System.out.print("Conta destino: ");
        String destino = scanner.nextLine();
        System.out.print("Valor: R$ ");
        double valor = lerDouble();

        System.out.println(conta.transferir(cliente, destino, valor)
                ? "Transferência realizada." : "Transferência recusada.");
    }

    private void menuEmprestimo(Cliente cliente) {
        System.out.println("\n--- EMPRÉSTIMO ---");
        System.out.print("Valor: R$ ");
        double valor = lerDouble();
        System.out.print("Parcelas: ");
        int parcelas = lerInt();

        Emprestimo e = emprestimo.solicitar(cliente, valor, parcelas);
        System.out.println(e == null ? "Empréstimo recusado." : "Empréstimo aprovado: " + e);
        if (e != null) {
            System.out.println("\nSeus empréstimos:");
            for (Emprestimo item : emprestimo.listar(cliente)) System.out.println(item);
        }
    }

    private void menuCredito(Cliente cliente) {
        System.out.println("\n--- CRÉDITO ---");
        System.out.printf("Limite: R$ %.2f%n", cliente.getConta().getLimiteCredito());
        System.out.printf("Disponível: R$ %.2f%n", credito.consultarDisponivel(cliente));
        System.out.println("1 - Utilizar crédito");
        System.out.println("2 - Pagar crédito");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int opcao = lerInt();

        if (opcao == 1) {
            System.out.print("Valor: R$ ");
            System.out.println(credito.utilizar(cliente, lerDouble())
                    ? "Crédito utilizado." : "Operação recusada.");
        } else if (opcao == 2) {
            System.out.print("Valor: R$ ");
            System.out.println(credito.pagar(cliente, lerDouble())
                    ? "Crédito pago." : "Pagamento recusado.");
        }
    }

    private void menuInvestimento(Cliente cliente) {
        System.out.println("\n--- INVESTIMENTOS ---");
        System.out.println("Tipos: Poupanca, CDB ou Acoes");
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Valor: R$ ");
        double valor = lerDouble();

        Investimento i = investimento.investir(cliente, tipo, valor);
        System.out.println(i == null ? "Investimento recusado." : "Investimento criado: " + i);

        if (i != null) {
            System.out.println("\nSeus investimentos:");
            for (Investimento item : investimento.listar(cliente)) System.out.println(item);
        }
    }

    private void mostrarDados(Cliente cliente) {
        System.out.println("\n--- MEUS DADOS ---");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Usuário: " + cliente.getUsuario());
        System.out.println("Conta: " + cliente.getConta().getNumero());
        System.out.printf("Saldo: R$ %.2f%n", cliente.getConta().getSaldo());
        System.out.printf("Limite de crédito: R$ %.2f%n", cliente.getConta().getLimiteCredito());
    }

    private int lerInt() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }

    private double lerDouble() {
        while (true) {
            try {
                String entrada = scanner.nextLine().replace(",", ".");
                double valor = Double.parseDouble(entrada);
                if (valor >= 0) return valor;
            } catch (NumberFormatException ignored) {}
            System.out.print("Digite um valor válido: ");
        }
    }
}
