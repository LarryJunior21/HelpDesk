package br.unoeste.fipp.entidade;

import java.util.Date;

public class Funcionario {
    private int fun_codigo;
    private String fun_nome;
    private Date fun_dtcontratacao;
    private Date fun_dtdemissao;
    private String fun_ativo;
    private String fun_senha;
    private String fun_tipo;

    public Funcionario() {
    }

    public Funcionario(int fun_codigo, String fun_nome, Date fun_dtcontratacao, Date fun_dtdemissao, String fun_ativo, String fun_senha, String fun_tipo) {
        this.fun_codigo = fun_codigo;
        this.fun_nome = fun_nome;
        this.fun_dtcontratacao = fun_dtcontratacao;
        this.fun_dtdemissao = fun_dtdemissao;
        this.fun_ativo = fun_ativo;
        this.fun_senha = fun_senha;
        this.fun_tipo = fun_tipo;
    }

    public int getFun_codigo() {
        return fun_codigo;
    }

    public void setFun_codigo(int fun_codigo) {
        this.fun_codigo = fun_codigo;
    }

    public String getFun_nome() {
        return fun_nome;
    }

    public void setFun_nome(String fun_nome) {
        this.fun_nome = fun_nome;
    }

    public Date getFun_dtcontratacao() {
        return fun_dtcontratacao;
    }

    public void setFun_dtcontratacao(Date fun_dtcontratacao) {
        this.fun_dtcontratacao = fun_dtcontratacao;
    }

    public Date getFun_dtdemissao() {
        return fun_dtdemissao;
    }

    public void setFun_dtdemissao(Date fun_dtdemissao) {
        this.fun_dtdemissao = fun_dtdemissao;
    }

    public String getFun_ativo() {
        return fun_ativo;
    }

    public void setFun_ativo(String fun_ativo) {
        this.fun_ativo = fun_ativo;
    }

    public String getFun_senha() {
        return fun_senha;
    }

    public void setFun_senha(String fun_senha) {
        this.fun_senha = fun_senha;
    }

    public String getFun_tipo() {
        return fun_tipo;
    }

    public void setFun_tipo(String fun_tipo) {
        this.fun_tipo = fun_tipo;
    }
    
    
}
