package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.dao.VagaDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = {"/empresa", "/empresa/index.jsp"})
public class EmpresaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
    	if (usuario == null) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getPapel().equals("Empresa")) {
			List<Vaga> listaVagas = new VagaDAO().getAllVagasEmpresa(usuario);
       		request.setAttribute("listaVagas", listaVagas);
	   
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/index.jsp");
            dispatcher.forward(request, response);

    	} else {
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [Empresa] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}
    }
}

