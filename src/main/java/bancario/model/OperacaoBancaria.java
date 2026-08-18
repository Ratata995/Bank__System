package bancario.model;

public interface OperacaoBancaria {
    boolean depositar(double valor);
    boolean sacar(double valor);
    boolean transferir(Conta destino, double valor);
}
