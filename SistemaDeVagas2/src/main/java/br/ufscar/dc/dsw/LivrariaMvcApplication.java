package br.ufscar.dc.dsw;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.dao.IInscricaoDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Inscricao;

@SpringBootApplication
public class LivrariaMvcApplication {

	private static final Logger log = LoggerFactory.getLogger(LivrariaMvcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LivrariaMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IEmpresaDAO empresaDAO, IProfissionalDAO profissionalDAO, IVagaDAO vagaDAO, IInscricaoDAO inscricaoDAO) {
		return (args) -> {

			Usuario u1 = new Usuario();
			u1.setEmail("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			log.info("Salvando admin");

			Usuario u2 = new Usuario();
			u2.setEmail("empresa");
			u2.setPassword(encoder.encode("empresa"));
			u2.setName("Empresa 1");
			u2.setRole("ROLE_EMPRESA");
			u2.setEnabled(true);
			usuarioDAO.save(u2);
			log.info("Salvando usuário - Empresa1");

			Empresa e1 = new Empresa();
			e1.setCnpj("45.990.181/0001-89");
			e1.setCidade("São Paulo");
			e1.setDescricao("Empresa de Tecnologia");
			e1.setUsuario(u2);
			empresaDAO.save(e1);
			log.info("Salvando dados da Empresa1");

			Usuario u3 = new Usuario();
			u3.setEmail("prof");
			u3.setPassword(encoder.encode("prof"));
			u3.setName("Profissional1");
			u3.setRole("ROLE_USER");
			u3.setEnabled(true);
			usuarioDAO.save(u3);
			log.info("Salvando usuário - Profissional1");

			Profissional p1 = new Profissional();
			p1.setCpf("062.408.045-50");
			p1.setNasc("03/03/2001");
			p1.setSexo("Feminino");
			p1.setTelefone("(11) 94528-4695");
			p1.setUsuario(u3);
			p1.setUsuario(u3);
			profissionalDAO.save(p1);
			log.info("Salvando dados do Profissional1");

			Vaga v1 = new Vaga();
			v1.setDescricao("Vaga de Desenvolvedor");
			v1.setDataLimite("21/04/2024");
			v1.setRemuneracao(BigDecimal.valueOf(2000.0));
			v1.setEmpresa(e1);
			vagaDAO.save(v1);
			log.info("Salvando dados da Vaga1");

			Inscricao i1 = new Inscricao();
			i1.setVaga(v1);
			i1.setProfissional(p1);
			i1.setData_inscricao("20/04/2024");
			inscricaoDAO.save(i1);
			log.info("Salvando dados da Inscricao1");

			List<Usuario> usuarios = usuarioDAO.findAll();
			for (Usuario u : usuarios){
				log.info(u.toString());
			}

			log.info(empresaDAO.findById(1L).toString());
			log.info(profissionalDAO.findById(1L).toString());
			log.info(vagaDAO.findById(1L).toString());
			log.info(inscricaoDAO.findById(1L).toString());
		};
	}
}
