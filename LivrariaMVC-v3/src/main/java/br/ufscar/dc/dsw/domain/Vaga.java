package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;

@SuppressWarnings("serial")
@Entity
@Table(name = "Vaga")
public class Vaga extends AbstractEntity<Long> {

	@NotBlank(message = "{NotBlank.vaga.descricao}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String descricao;

	@NotBlank(message = "{NotBlank.vaga.dataLimite}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String dataLimite;

	@NotNull(message = "{NotNull.vaga.remunaracao}")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal remuneracao;
    
	@NotNull(message = "{NotNull.vaga.empresa}")
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@NotNull(message = "{NotNull.vaga.inscricao}")
	@OneToMany
	@JoinColumn(name = "inscricao_id")
	private Inscricao inscricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLimite() {
		return dataLimite;
	}

	public void setLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}

	public BigDecimal getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(BigDecimal remuneracao) {
		this.remuneracao = remuneracao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}
}
