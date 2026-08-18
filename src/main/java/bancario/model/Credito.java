package bancario.model;

import java.io.Serializable;

public class Credito implements Serializable {
    private double limite;
    private double utilizado;

    public Credito(double limite) {
        this.limite = limite;
    }

    public double getLimite() { return limite; }
    public double getUtilizado() { return utilizado; }
    public double getDisponivel() { return limite - utilizado; }

    public boolean utilizar(double valor) {
        if (valor <= 0 || valor > getDisponivel()) return false;
        utilizado += valor;
        return true;
    }

    public boolean pagar(double valor) {
        if (valor <= 0 || valor > utilizado) return false;
        utilizado -= valor;
        return true;
    }
}
