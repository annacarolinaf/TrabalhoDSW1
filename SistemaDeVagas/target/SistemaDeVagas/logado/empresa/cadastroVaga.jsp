<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Vagas</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Cadastro Vagas</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="cadastroVaga">
            <table>
                <tr>
                    <th>Descricao: </th>
                    <td><input type="text" name="salario"
                               value="${param.salario}"/></td>
                </tr>
                <tr>
                    <th>Salario: </th>
                    <td><input type="text" name="salario_vaga" /></td>
                </tr>
                <tr>
                    <th>Data Limite: </th>
                    <td><input type="text" name="data_limite" /></td>
                </tr>
                <tr>
                    <th>CNPJ: </th>
                    <td><input type="text" name="cnpj" value="${sessionScope.usuarioLogado.nome}"/></td>
                </tr>
                    <td colspan="2"> 
                        <input type="submit" name="criacaoVaga" value="Enviar"/>
                    </td>
                </tr>
                
            </table>
        </form>
    </body>
</html>
