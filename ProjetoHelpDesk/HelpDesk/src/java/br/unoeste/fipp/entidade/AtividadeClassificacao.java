package br.unoeste.fipp.entidade;

public class AtividadeClassificacao {
    private int ati_codigo;
    private int cla_codigo;

    public AtividadeClassificacao() {
    }

    public AtividadeClassificacao(int ati_codigo, int cla_codigo) {
        this.ati_codigo = ati_codigo;
        this.cla_codigo = cla_codigo;
    }

    public int getAti_codigo() {
        return ati_codigo;
    }

    public void setAti_codigo(int ati_codigo) {
        this.ati_codigo = ati_codigo;
    }

    public int getCla_codigo() {
        return cla_codigo;
    }

    public void setCla_codigo(int cla_codigo) {
        this.cla_codigo = cla_codigo;
    }
    
    
}
