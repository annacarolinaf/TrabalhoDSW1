package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import org.hibernate.mapping.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
public class Profissional extends AbstractEntity<Long> {

	// O sistema deve possuir um cadastro de profissionais, com os seguintes dados:
	// e-mail, senha, CPF, nome, telefone, sexo e data de nascimento

	@UniqueCPF(message = "{Unique.profissional.CPF}")
	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.editora.CPF}")
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotNull(message = "{NotNull.profissional.telefone}")
	@Column(nullable = false, length = 10)
	private String telefone;

	@NotNull(message = "{NotNull.profissional.sexo}")
	@Column(nullable = false, length = 150)
	private String sexo;

	@NotNull(message = "{NotNull.profissional.nasc}")
	@Column(nullable = false, length = 10)
	private String nasc;

	@NotNull(message = "{NotNull.profissional.usuario}")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	// @NotNull(message = "{NotNull.profissional.inscricao}")
	// @ManyToOne
	// @JoinColumn(name = "inscricao_id")
	// private Inscricao inscricao;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNasc() {
		return nasc;
	}

	public void setNasc(String nasc) {
		this.nasc = nasc;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario ususario) {
		this.usuario = usuario;
	}

	// public Inscricao getInscricao() {
	// return inscricao;
	// }

	// public void setInscricao(Inscricao inscricao) {
	// this.inscricao = inscricao;
	// }
}
