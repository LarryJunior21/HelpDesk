package br.unoeste.fipp.entidade;

public class Solicitante {
    private String sol_email;
    private String sol_nome;
    private String sol_telefone;
    private String sol_observacao;

    public Solicitante() {
    }

    public Solicitante(String sol_email, String sol_nome, String sol_telefone, String sol_observacao) {
        this.sol_email = sol_email;
        this.sol_nome = sol_nome;
        this.sol_telefone = sol_telefone;
        this.sol_observacao = sol_observacao;
    }

    public String getSol_email() {
        return sol_email;
    }

    public void setSol_email(String sol_email) {
        this.sol_email = sol_email;
    }

    public String getSol_nome() {
        return sol_nome;
    }

    public void setSol_nome(String sol_nome) {
        this.sol_nome = sol_nome;
    }

    public String getSol_telefone() {
        return sol_telefone;
    }

    public void setSol_telefone(String sol_telefone) {
        this.sol_telefone = sol_telefone;
    }

    public String getSol_observacao() {
        return sol_observacao;
    }

    public void setSol_observacao(String sol_observacao) {
        this.sol_observacao = sol_observacao;
    }
    
    
}
