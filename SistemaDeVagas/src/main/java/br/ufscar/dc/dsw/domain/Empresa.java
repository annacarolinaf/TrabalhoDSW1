package br.ufscar.dc.dsw.domain;

public class Empresa {

    private Long id_empresa;
    private String cnpj;
    private String nome;
    private String email;
    private String senha;
    private String cidade;
    private String descricao;

    public Empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public Empresa(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Empresa(Long id_empresa, String cnpj, String nome) {
        this(cnpj, nome);
        this.id_empresa = id_empresa;
    }

    public Long getId() {
        return id_empresa;
    }

    public void setId(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}