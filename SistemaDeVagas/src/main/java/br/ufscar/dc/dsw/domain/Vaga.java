package br.ufscar.dc.dsw.domain;

public class Vaga {

    private Long id_vaga;
    private float salario;
    private String descricao;
    private String data_limite;
    private Empresa empresa;

    public Vaga(Long id_vaga) {
        this.id_vaga = id_vaga;
    }


    public Vaga( float salario, String descricao, String data_limite, Empresa empresa) {
        this.salario = salario;
        this.descricao = descricao;
        this.data_limite = data_limite;
        this.empresa = empresa;
    }
    
    public Vaga( Long id_vaga,  float salario, String descricao, String data_limite, Empresa empresa) {
        this(salario, descricao, data_limite, empresa);
        this.id_vaga = id_vaga;
    }

    public Long getId_vaga() {
        return id_vaga;
    }

    public void setId_vaga(Long id_vaga) {
        this.id_vaga = id_vaga;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_limite() {
        return data_limite;
    }

    public void setData_limite(String data_limite) {
        this.data_limite = data_limite;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
