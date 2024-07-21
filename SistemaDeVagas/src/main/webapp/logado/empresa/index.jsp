<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu da Empresa</title>
    </head>
    <body>
        <h1>Página da Empresa</h1>
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>

        <div align="center">
            <table border="1">
                <caption>Lista de Vagas</caption>
                <tr>
                    <th>Descrição</th>
                    <th>Salário</th>
                    <th>Data limite</th>
                    <th>Status</th>
                    <th>ID</th>
                </tr>

                <c:forEach var="vaga" items="${requestScope.listaVagas}">
                    <tr>
                        <td><c:out value="${vaga.descricao}" /></td>
						<td><c:out value="${vaga.salario}" /></td>
						<td><c:out value="${vaga.data_limite}" /></td>
						<td><c:out value="${vaga.id}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
            </li>
        </ul>
    </body>
</html>