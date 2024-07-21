<!-- Lista de vagas sem estar logado-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>

            <head>
                <title>Candidate-se agora!</title>
            </head>

            <body>


                <h1>Página da Empresa</h1>
                <p>Olá ${sessionScope.usuarioLogado.nome}</p>


                <div align="center">
                    <table border="1">
                        <h1>Lista de Vagas</h1>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>
                            <th>Salário</th>
                            <th>Data limite</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach var="vaga" items="${requestScope.listaVagas}">
                            <tr>
                                <td>${vaga.id_vaga}</td>
                                <td>${vaga.descricao}</td>
                                <td>${vaga.salario}</td>
                                <td>${vaga.data_limite}</td>
                                <td></td>
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