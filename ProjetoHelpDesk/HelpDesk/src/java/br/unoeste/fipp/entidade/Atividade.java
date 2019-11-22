package br.unoeste.fipp.entidade;

import java.util.Date;

public class Atividade {
    private int ati_codigo;
    private String ati_descricao;
    private Date ati_dtinicio;
    private Date ati_dtfim;
    private int fun_codigo;
    private int sta_codigo;
    private String sol_email;

    public Atividade() {
    }

    public Atividade(int ati_codigo, String ati_descricao, Date ati_dtinicio, Date ati_dtfim, int fun_codigo, int sta_codigo, String sol_email) {
        this.ati_codigo = ati_codigo;
        this.ati_descricao = ati_descricao;
        this.ati_dtinicio = ati_dtinicio;
        this.ati_dtfim = ati_dtfim;
        this.fun_codigo = fun_codigo;
        this.sta_codigo = sta_codigo;
        this.sol_email = sol_email;
    }

    public Atividade(int ati_codigo, String ati_descricao, Date ati_dtinicio, int fun_codigo, int sta_codigo, String sol_email) {
        this.ati_codigo = ati_codigo;
        this.ati_descricao = ati_descricao;
        this.ati_dtinicio = ati_dtinicio;
        this.fun_codigo = fun_codigo;
        this.sta_codigo = sta_codigo;
        this.sol_email = sol_email;
    }
    
    

    public int getAti_codigo() {
        return ati_codigo;
    }

    public void setAti_codigo(int ati_codigo) {
        this.ati_codigo = ati_codigo;
    }

    public String getAti_descricao() {
        return ati_descricao;
    }

    public void setAti_descricao(String ati_descricao) {
        this.ati_descricao = ati_descricao;
    }

    public Date getAti_dtinicio() {
        return ati_dtinicio;
    }

    public void setAti_dtinicio(Date ati_dtinicio) {
        this.ati_dtinicio = ati_dtinicio;
    }

    public Date getAti_dtfim() {
        return ati_dtfim;
    }

    public void setAti_dtfim(Date ati_dtfim) {
        this.ati_dtfim = ati_dtfim;
    }

    public int getFun_codigo() {
        return fun_codigo;
    }

    public void setFun_codigo(int fun_codigo) {
        this.fun_codigo = fun_codigo;
    }

    public int getSta_codigo() {
        return sta_codigo;
    }

    public void setSta_codigo(int sta_codigo) {
        this.sta_codigo = sta_codigo;
    }

    public String getSol_email() {
        return sol_email;
    }

    public void setSol_email(String sol_email) {
        this.sol_email = sol_email;
    }
    
    
}
