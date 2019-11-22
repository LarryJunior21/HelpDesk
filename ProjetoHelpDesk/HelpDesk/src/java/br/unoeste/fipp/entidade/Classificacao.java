package br.unoeste.fipp.entidade;

public class Classificacao {
    private int cla_codigo;
    private String cla_nome;
    private boolean cla_ativa;

    public Classificacao() {
    }

    public Classificacao(int cla_codigo, String cla_nome, boolean cla_ativa) {
        this.cla_codigo = cla_codigo;
        this.cla_nome = cla_nome;
        this.cla_ativa = cla_ativa;
    }

    public int getCla_codigo() {
        return cla_codigo;
    }

    public void setCla_codigo(int cla_codigo) {
        this.cla_codigo = cla_codigo;
    }

    public String getCla_nome() {
        return cla_nome;
    }

    public void setCla_nome(String cla_nome) {
        this.cla_nome = cla_nome;
    }

    public boolean getCla_ativa() {
        return cla_ativa;
    }

    public void setCla_ativa (boolean cla_ativa) {
        this.cla_ativa = cla_ativa;
    }
    
    
}
