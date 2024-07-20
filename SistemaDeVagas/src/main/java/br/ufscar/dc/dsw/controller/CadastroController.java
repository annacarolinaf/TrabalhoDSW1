package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Profissional;

import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Cadastro", urlPatterns = { "/indexCadastro.jsp"})
public class CadastroController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Erro erros = new Erro();
                
		if (request.getParameter("bProfissional") != null) {

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
            String nome = request.getParameter("senha");
            String cpf = request.getParameter("cpf");
            String data_nasc = request.getParameter("data_nasc");
            String sexo = request.getParameter("sexo");
            String telefone = request.getParameter("telefone");

			if (email == null || email.isEmpty()) {
				erros.add("Email não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
            if (nome == null || nome.isEmpty()) {
				erros.add("Nome não informado!");
			}
            if (cpf == null || cpf.isEmpty()) {
				erros.add("CPF não informado!");
			}
            if (data_nasc == null || data_nasc.isEmpty()) {
				erros.add("Data de nascimento não informada!");
			}
            if (sexo == null || sexo.isEmpty()) {
				erros.add("Sexo não informado!");
			}
            if (telefone == null || telefone.isEmpty()) {
				erros.add("Telefone não informado!");
			}

            
			if (!erros.isExisteErros()) {
				UsuarioDAO dao_usuario = new UsuarioDAO();

                Usuario usuario = new Usuario(nome, email, senha, "Profissional");
                dao_usuario.insert(usuario);

                ProfissionalDAO dao_profissional = new ProfissionalDAO();
                Profissional profissional = new Profissional (cpf, data_nasc, sexo, telefone, usuario);
                dao_profissional.insert(profissional);

                request.getSession().setAttribute("usuarioLogado", usuario);
                response.sendRedirect("profissional/");
			}
            
		}

		else if (request.getParameter("bCadastro") != null) {
			response.sendRedirect("cadastro.jsp");
			return;
		}
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
}