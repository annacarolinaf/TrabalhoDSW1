package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.dao.InscricaoDAO;

import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissional/*")
public class ProfissionalController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
		} else if (usuario.getPapel().equals("Profissional")) {

			String action = request.getPathInfo();

			if (action == null)
				action = "";

			try {
				switch (action) {
					case "/edicao":
						apresentaFormEdicao(request, response);
						break;
					case "/atualiza":
						atualiza(request, response);
						break;
					case "/remocao":
						remove(request, response);
						break;
					case "/pesquisaCidade":
						pesquisaCidade(request, response);
						break;
					case "/inscricaoForm":
						apresentaFormInscricao(request, response);
						break;
					case "/inscricao":
						inscricao(request, response);
						break;
					default:
						listaVagas(request, response, usuario);
						break;
				}
			} catch (RuntimeException | IOException | ServletException e) {
				throw new ServletException(e);
			}

		} else {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [Profissional] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
		}
	}

	private void listaVagas(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/index.jsp");
		VagaDAO dao = new VagaDAO();
		List<Vaga> listaVagas = dao.getAll();
		request.setAttribute("listaVagas", listaVagas);
		dispatcher.forward(request, response);
	}

	private void pesquisaCidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filtro = request.getParameter("filtro");

		VagaDAO dao = new VagaDAO();
		List<Vaga> listaVagas = dao.getPorCidade(filtro);
		request.setAttribute("listaVagas", listaVagas);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/index.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario usuario = new UsuarioDAO().getbyID(id);
		Profissional profissional = new ProfissionalDAO().getByIdUsuario(usuario);
		request.setAttribute("profissional", profissional);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void atualiza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String cpf = request.getParameter("cpf");
		String data_nasc = request.getParameter("data_nasc");
		String sexo = request.getParameter("sexo");
		String telefone = request.getParameter("telefone");

		Usuario usuario = new Usuario(id, nome, email, senha, "Profissional");
		Profissional profissional = new Profissional(cpf, data_nasc, sexo, telefone, usuario);

		new UsuarioDAO().update(usuario);
		new ProfissionalDAO().update(profissional);

		response.sendRedirect(request.getContextPath() + "/profissional");
	}

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario usuario = new UsuarioDAO().getbyID(id);
		Profissional profissional = new ProfissionalDAO().getByIdUsuario(usuario);
		new ProfissionalDAO().delete(profissional);
		response.sendRedirect(request.getContextPath());
	}

	private void apresentaFormInscricao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Long id_vaga = Long.parseLong(request.getParameter("id_vaga"));

		Usuario usuario = new UsuarioDAO().getbyID(id);
		Profissional profissional = new ProfissionalDAO().getByIdUsuario(usuario);
		Vaga vaga = new VagaDAO().get(id_vaga);

		request.setAttribute("profissional", profissional);
		request.setAttribute("vaga", vaga);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formularioInscricao.jsp");
		dispatcher.forward(request, response);
	}

	private void inscricao(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Long id = Long.parseLong(request.getParameter("id"));
		Long id_vaga = Long.parseLong(request.getParameter("id_vaga"));

		String cpf = request.getParameter("cpf");

		// String qualificaco = request.getParameter("qualificaco");

		Usuario usuario = new UsuarioDAO().getbyID(id);
		Profissional profissional = new ProfissionalDAO().getByIdUsuario(usuario);
		Vaga vaga = new VagaDAO().get(id_vaga);

		// Verifica se já existe uma inscrição para este profissional e vaga
		Inscricao inscricaoExistente = new InscricaoDAO().getbyIDVagaIDCpf(cpf, id_vaga);

		if (inscricaoExistente != null) {
			// Inscrição já existe, redireciona para uma página de erro ou exibe mensagem
			response.sendRedirect(request.getContextPath() + "/profissional");
		} else {
			// Inscrição não existe, prossegue com a inserção
			Inscricao inscricao = new Inscricao(profissional, vaga, 0, "Sem");

			new InscricaoDAO().insert(inscricao);

			response.sendRedirect(request.getContextPath() + "/profissional");
		}
	}

}