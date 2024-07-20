<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Usuário</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Cadastro de Usuário</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="Cadastro.jsp">
            <table>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bEmpresa" value="Cadastro de Empresa"/>
                    </td>
                    <td colspan="2"> 
                        <input type="submit" name="bProfissional" value="Cadastro de Profissional"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>