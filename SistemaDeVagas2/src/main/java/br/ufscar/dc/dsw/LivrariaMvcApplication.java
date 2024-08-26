package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IInscricaoDAO;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;


@SpringBootApplication
public class LivrariaMvcApplication {

	private static final Logger log = LoggerFactory.getLogger(LivrariaMvcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LivrariaMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IEmpresaDAO empresaDAO,
			IProfissionalDAO profissionalDAO, IVagaDAO vagaDAO, IInscricaoDAO inscricaoDAO) {

		return (args) -> {

			Usuario u1 = new Usuario();
			u1.setEmail("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);

			Usuario u2 = new Usuario();
			u2.setEmail("amazon");
			u2.setPassword(encoder.encode("empresa"));
			u2.setName("Amazon");
			u2.setRole("ROLE_EMPRESA");
			u2.setEnabled(true);
			usuarioDAO.save(u2);

			Usuario u3 = new Usuario();
			u3.setEmail("profissional");
			u3.setPassword(encoder.encode("profissional"));
			u3.setName("Profissional");
			u3.setRole("ROLE_PROFISSIONAL");
			u3.setEnabled(true);
			usuarioDAO.save(u3);

			Usuario u4 = new Usuario();
			u4.setEmail("visagio");
			u4.setPassword(encoder.encode("empresa"));
			u4.setName("Visagio");
			u4.setRole("ROLE_EMPRESA");
			u4.setEnabled(true);
			usuarioDAO.save(u4);

			Usuario u5 = new Usuario();
			u5.setEmail("marcos");
			u5.setPassword(encoder.encode("profissional"));
			u5.setName("Marcos");
			u5.setRole("ROLE_PROFISSIONAL");
			u5.setEnabled(true);
			usuarioDAO.save(u5);

			Usuario u6 = new Usuario();
			u6.setEmail("google");
			u6.setPassword(encoder.encode("empresa"));
			u6.setName("Google");
			u6.setRole("ROLE_EMPRESA");
			u6.setEnabled(true);
			usuarioDAO.save(u6);

			Empresa e1 = new Empresa();
			e1.setCnpj("45.990.181/0001-89");
			e1.setCidade("São Paulo");
			e1.setDescricao("Empresa de Tecnologia");
			e1.setUsuario(u2);
			empresaDAO.save(e1);
			log.info("Salvando dados da Empresa1");

			Empresa e2 = new Empresa();
			e2.setCnpj("13.741.181/0001-89");
			e2.setCidade("Campinas");
			e2.setDescricao("Empresa de Tecnologia");
			e2.setUsuario(u4);
			empresaDAO.save(e2);

			Empresa e3 = new Empresa();
			e3.setCnpj("20.212.181/2512-89");
			e3.setCidade("Belo Horizonte");
			e3.setDescricao("Empresa de Tecnologia");
			e3.setUsuario(u6);
			empresaDAO.save(e3);

			Profissional p1 = new Profissional();
			p1.setCpf("062.408.045-50");
			p1.setNasc("03/03/2001");
			p1.setSexo("Feminino");
			p1.setTelefone("(11) 94528-4695");
			p1.setUsuario(u3);
			p1.setUsuario(u3);
			profissionalDAO.save(p1);

			Profissional p2 = new Profissional();
			p2.setCpf("837.424.045-50");
			p2.setNasc("20/07/1998");
			p2.setSexo("Masculino");
			p2.setTelefone("(11) 94528-4695");
			p2.setUsuario(u5);
			p2.setUsuario(u5);
			profissionalDAO.save(p2);


			Vaga v1 = new Vaga();
			v1.setDescricao("Desenvolvedor Java Estágio");
			v1.setDataLimite("10/11/2024"); 
			v1.setRemuneracao(new BigDecimal("1500.00")); 
			v1.setEmpresa(e1); 
			vagaDAO.save(v1);

			Vaga v2 = new Vaga();
			v2.setDescricao("Desenvolvedor Python Estágio");
			v2.setDataLimite("10/11/2024"); 
			v2.setRemuneracao(new BigDecimal("1500.00")); 
			v2.setEmpresa(e1); 
			vagaDAO.save(v2);

			Vaga v3 = new Vaga();
			v3.setDescricao("Cientista de Dados");
			v3.setDataLimite("10/12/2024"); 
			v3.setRemuneracao(new BigDecimal("2000.00")); 
			v3.setEmpresa(e2); 
			vagaDAO.save(v3);

			Inscricao i1 = new Inscricao();
            i1.setData_inscricao("09/12/2024");
            i1.setVaga(v1);
            i1.setProfissional(p1);
            i1.setResultado("ABERTO"); 
            i1.setQualificacao("Exemplo de qualificação".getBytes()); // Exemplo de arquivo em bytes
			inscricaoDAO.save(i1);

		};
	}
}
