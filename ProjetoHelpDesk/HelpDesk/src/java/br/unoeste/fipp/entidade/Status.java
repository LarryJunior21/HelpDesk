package br.unoeste.fipp.entidade;

public class Status {
    private int sta_codigo;
    private String sta_status;
    private Boolean sta_ativo;

    public Status() {
    }

    public Status(int sta_codigo, String sta_status, Boolean sta_ativo) {
        this.sta_codigo = sta_codigo;
        this.sta_status = sta_status;
        this.sta_ativo = sta_ativo;
    }

    public int getSta_codigo() {
        return sta_codigo;
    }

    public void setSta_codigo(int sta_codigo) {
        this.sta_codigo = sta_codigo;
    }

    public String getSta_status() {
        return sta_status;
    }

    public void setSta_status(String sta_status) {
        this.sta_status = sta_status;
    }

    public Boolean getSta_ativo() {
        return sta_ativo;
    }

    public void setSta_ativo(Boolean sta_ativo) {
        this.sta_ativo = sta_ativo;
    }
    
    
}
