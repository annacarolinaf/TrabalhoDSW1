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

	//O sistema deve possuir um cadastro de empresas, com os seguintes dados: e-mail, senha, CNPJ,
	//nome, descrição e cidade.
 
	@UniqueCNPJ (message = "{Unique.empresa.CNPJ}")
	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.empresa.CNPJ}")
	@Column(nullable = false, unique = true, length = 60)
	private String CNPJ;
	
	@NotBlank
	@Size(min = 3, max = 100)
	@Column(nullable = false, unique = true, length = 100)
	private String descricao;

	@NotBlank
	@Size(min = 3, max = 30)
	@Column(nullable = false, unique = true, length = 30) //ver dif
	private String cidade;

	@OneToMany(mappedBy = "empresa")
	private List<Vaga> vagas;
	
	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCidade() {
		return descricao;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}
}
