package bancario.model;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {
    private String usuario;
    private String senhaHash;
    private ContaBancaria conta;

    public Cliente(String nome, String cpf, String usuario, String senhaHash) {
        super(nome, cpf);
        this.usuario = usuario;
        this.senhaHash = senhaHash;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }
}
