package br.ufscar.dc.dsw.domain;

public class Inscricao {
    private Profissional profissional;
    private Vaga vaga;
    private Integer resultado;
    private String qualificacao;

    public Inscricao(Profissional profissional, Vaga vaga, Integer resultado, String qualificacao) {
        this.profissional = profissional;
        this.vaga = vaga;
        this.resultado = resultado;
        this.qualificacao = qualificacao;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }
}
