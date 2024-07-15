package br.ufscar.dc.dsw.domain;

public class Profissional {

    private String cpf;
    private String data_nasc;
    private String sexo;
    private String telefone;
    private Usuario usuario;

    public Profissional(String cpf){
        this.cpf = cpf;
    }

    public Profissional(String cpf, String data_nasc, String sexo, String telefone, Usuario usuario)
    {
        this(cpf);
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.telefone = telefone;
        this.usuario = usuario;
    }
}
