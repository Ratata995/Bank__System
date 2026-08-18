package bancario.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conta implements Serializable {
    private String numero;
    private double saldo;
    private double limiteCredito;
    private double creditoUtilizado;
    private List<Transacao> transacoes;

    public Conta(String numero) {
        this.numero = numero;
        this.saldo = 0.0;
        this.limiteCredito = 1000.0;
        this.creditoUtilizado = 0.0;
        this.transacoes = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public double getCreditoUtilizado() {
        return creditoUtilizado;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void adicionarSaldo(double valor) {
        saldo += valor;
    }

    public boolean retirarSaldo(double valor) {
        if (valor <= 0 || valor > saldo) return false;
        saldo -= valor;
        return true;
    }

    public void registrarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void definirLimiteCredito(double limite) {
        if (limite >= 0) limiteCredito = limite;
    }

    public double getCreditoDisponivel() {
        return limiteCredito - creditoUtilizado;
    }

    public boolean utilizarCredito(double valor) {
        if (valor <= 0 || valor > getCreditoDisponivel()) return false;
        creditoUtilizado += valor;
        return true;
    }

    public boolean pagarCredito(double valor) {
        if (valor <= 0 || valor > creditoUtilizado || valor > saldo) return false;
        saldo -= valor;
        creditoUtilizado -= valor;
        return true;
    }
}
