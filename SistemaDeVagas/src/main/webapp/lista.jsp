<!-- Lista de vagas sem estar logado-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>

            <head>
                <title>Sistema De Vagas</title>
            </head>

            <body>
                <h1>Candidate-se agora!</h1>

                <div align="center">
                    <h1>Lista de vagas</h1>
                    <h2>
                        <a href="${pageContext.request.contextPath}/login.jsp">Login</a> &nbsp;&nbsp;&nbsp;
                    </h2>
                    <form method="post" action="pesquisaCidade">
                        <h2>Pesquisar por cidade:</h2>
                        <input type="text" name="filtro" id="filtro" placeholder="Procure uma cidade">
                        <button type="submit"> Buscar </button>
                    </form>
                </div>

                <div align="center">
                    <table border="1">
                        <caption>Vagas em aberto</caption>
                        <tr>
                            <th>Empresa</th>
                            <th>Descrição de Vaga</th>
                            <th>Salário</th>
                            <th>Data limite</th>
                            <th>Cidade</th>
                            <th>Ação</th>
                        </tr>
                        <c:forEach var="vaga" items="${requestScope.listaVagas}">
                            <tr>
                                <td>${vaga.empresa.usuario.nome}</td>
                                <td>${vaga.descricao}</td>
                                <td>${vaga.salario}</td>
                                <td>${vaga.data_limite}</td>
                                <td>${vaga.empresa.cidade}</td>

                                <td><a href="/">Se inscrever</a></td>
                                &nbsp;&nbsp;&nbsp;&nbsp; </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <form method="post" action="showAll">
                        <button type="submit"> Mostrar todos </button>
                    </form>
                </div>
            </body>

            </html>