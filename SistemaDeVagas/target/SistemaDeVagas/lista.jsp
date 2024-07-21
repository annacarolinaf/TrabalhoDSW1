<!-- Lista de vagas sem estar logado-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <html>
<head>
<title>Candidate-se agora!</title>
</head>
<body>

<div align="center">
    <h1>Lista de vagas</h1>
    <h2>
        <a href="/${requestScope.contextPath}">Ir para log-in</a> &nbsp;&nbsp;&nbsp; 
    </h2> 
    <h2>Pesquisar por cidade:</h2>
    <input type="text" name="filtro" id="filtro" placeholder="Procure uma cidade" >
    <input type="button" value="Buscar">
</div>

 <div align="center">
    <table border="1">
        <caption>Vagas em aberto</caption>
        <tr>
            <th>Empresa</th>
            <th>Descrição de Vaga</th>
            <th>Salário</th>
            <th>Data limite</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="vaga" items="${requestScope.listaVagas}">
            <tr>
                <td>${vaga.descricao}</td>
                <td>${vaga.salario}</td>
                <td>${vaga.data_limite}</td>
                
                    <td><a href="/">Se inscrever</a></td>
                    &nbsp;&nbsp;&nbsp;&nbsp; </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html> 