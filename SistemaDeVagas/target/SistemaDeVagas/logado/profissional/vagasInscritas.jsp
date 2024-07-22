<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>

            <head>
                <title>Página do Profissional</title>
            </head>

            <body>
                <h1>Olá ${sessionScope.usuarioLogado.nome}</h1>

					<div align="center">
						<h1>Lista de vagas inscritas</h1>
					</div>
	
					<div align="center">
						<table border="1">
							<caption>Vagas inscritas</caption>
							<tr>
								<th>ID</th>
								<th>Empresa</th>
								<th>Descrição de Vaga</th>
								<th>Salário</th>
								<th>Data limite</th>
								<th>Cidade</th>
								<th>Status</th>
                                <th>Resultado</th>
							</tr>
							<c:forEach var="vaga" items="${requestScope.listaVagasInscritas}">
								<tr>
									<td>${vaga.id_vaga}</td> 
									<td>${vaga.empresa.usuario.nome}</td>
									<td>${vaga.descricao}</td>
									<td>${vaga.salario}</td>
									<td>${vaga.data_limite}</td>
									<td>${vaga.empresa.cidade}</td>
								</tr>
							</c:forEach>
						</table>

					</div>

                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/profissional">Voltar</a>
                        </li>
                    </ul>
            </body>