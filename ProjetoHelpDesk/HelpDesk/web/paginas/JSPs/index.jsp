<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Funcionario</title>
    </head>
    <body>
        <h1>Login</h1>
        <c:if test="${not empty mensagens}">
            <div>
                <h2>Erros</h2>
                <ul>
                    <c:forEach var="msg" items="${mensagens}">
                        <li>
                            <c:out value="${msg}"/>                            
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="index.do">
            <p>Login: 
                <input type="text" name="Login" value="${param.Login}" size="10"/>
            </p>
            <p>Senha:
                <input type="password" name="txtSenha" size="10"/>
            </p>
            
            <p>
                LOGIN DE ADMIN:2
                SENHA:1337
            </p> 
            <p>
                LOGIN DE USUARIO:1
                SENHA:1337
            </p> 
            <p><input type="submit" name="bEntrar" value="Entrar"/></p>
        </form>    </body>
</html>
