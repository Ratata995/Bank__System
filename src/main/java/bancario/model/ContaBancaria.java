package bancario.model;

public class ContaBancaria extends Conta implements OperacaoBancaria {
    public ContaBancaria(String numero) {
        super(numero);
    }

    @Override
    public boolean depositar(double valor) {
        if (valor <= 0) return false;
        adicionarSaldo(valor);
        registrarTransacao(new Transacao("DEPÓSITO", valor, "Depósito realizado"));
        return true;
    }

    @Override
    public boolean sacar(double valor) {
        if (!retirarSaldo(valor)) return false;
        registrarTransacao(new Transacao("SAQUE", valor, "Saque realizado"));
        return true;
    }

    @Override
    public boolean transferir(Conta destino, double valor) {
        if (destino == null || destino == this || !retirarSaldo(valor)) return false;
        destino.adicionarSaldo(valor);
        registrarTransacao(new Transacao("TRANSFERÊNCIA", valor,
                "Transferência para conta " + destino.getNumero()));
        destino.registrarTransacao(new Transacao("TRANSFERÊNCIA", valor,
                "Recebimento da conta " + getNumero()));
        return true;
    }
}
