package br.ufscar.dc.dsw.domain;

public class Empresa {

    create table Empresa( 
    id_empresa bigint not null auto_increment, 
    cnpj varchar(18) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    cidade varchar(256) not null,
    descrição varchar(500) not null,
    primary key (id_empresa)
);

    private Long id_empresa;
    private String cnpj;
    private String nome;

    public Empresa(Long id) {
        this.id = id;
    }

    public Empresa(String CNPJ, String nome) {
        this.CNPJ = CNPJ;
        this.nome = nome;
    }

    public Empresa(Long id, String CNPJ, String nome) {
        this(CNPJ, nome);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}