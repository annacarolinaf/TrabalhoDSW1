package br.ufscar.dc.dsw.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Empresa")
public class Empresa extends AbstractEntity<Long> {

    @UniqueCNPJ(message = "{Unique.e.CNPJ}")
    @NotBlank
    @Size(min = 18, max = 18, message = "{Size.empresa.CNPJ}")
    @Column(nullable = false, unique = true, length = 18)
    private String CNPJ;
    
    @NotBlank
    @Size(min = 3, max = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String nome;
    
    @NotBlank
    @Size(min = 10, max = 255)
    @Column(nullable = false, length = 255)
    private String descricao;
    
    @NotBlank
    @Size(min = 3, max = 60)
    @Column(nullable = false, length = 60)
    private String cidade;

    @OneToMany(mappedBy = "empresa")
    private List<Livro> livros;

    // Getters e Setters

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
