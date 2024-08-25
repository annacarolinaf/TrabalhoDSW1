package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;

@SpringBootApplication
public class LivrariaMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IEmpresaDAO empresaDAO,
			IProfissionalDAO profissionalDAO, IVagaDAO vagaDAO) {
		return (args) -> {

			Usuario u1 = new Usuario();
			u1.setEmail("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);

			Usuario u2 = new Usuario();
			u2.setEmail("empresa");
			u2.setPassword(encoder.encode("empresa"));
			u2.setName("Empresa 1");
			u2.setRole("ROLE_USER");
			u2.setEnabled(true);
			usuarioDAO.save(u2);

			Empresa e1 = new Empresa();
			e1.setCnpj("45.990.181/0001-89");
			e1.setCidade("São Paulo");
			e1.setDescricao("Empresa de Tecnologia");
			e1.setUsuario(u2);
			empresaDAO.save(e1);

			Profissional p1 = new Profissional();
			p1.setCpf("062.408.045-50");
			p1.setNasc("03/03/2001");
			p1.setSexo("Feminino");
			p1.setTelefone("(11) 94528-4695");
			p1.setUsuario(u1);
			profissionalDAO.save(p1);

			Vaga v1 = new Vaga();
			v1.setDescricao("Desenvolvedor Java Estágio");
			v1.setDatalimite("10/11/2024"); // Usando o formato padrão de data como string
			v1.setRemuneracao(new BigDecimal("1500.00")); // Remuneração em decimal
			v1.setEmpresa(e1); // Relacionando a vaga com uma empresa existente
			vagaDAO.save(v1);
		};
	}
}
