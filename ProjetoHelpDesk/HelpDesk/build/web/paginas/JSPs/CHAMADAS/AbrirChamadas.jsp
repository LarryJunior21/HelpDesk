<%@page import="br.unoeste.fipp.dao.ClassificacaoDAO"%>
<%@page import="br.unoeste.fipp.dao.StatusDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.unoeste.fipp.entidade.Status"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="funcionarioLogado" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>

<% 
    request.setAttribute("statusCB", new StatusDAO().listar());
    request.setAttribute("classiCB", new ClassificacaoDAO().listar());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chamadas/Abrir</title>
    </head>
    <body>
        <h1>Abrir Chamada</h1>
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
        
        <form method="post" action="abrirchamadas.do">
            
            <p>Funcionario
                <input type="text" name="camponome" value="${funcionarioLogado.fun_nome}" readonly/>
            </p>
            
            <p>Status
                <select name="statuscaixa" id="statuscaixa" onchange="myFunction()">
                        <option value="">Selecione um status..</option>
                        <c:forEach var="item" items="${statusCB}">
                            <option value="${item.sta_codigo}" name="campostacodigo">
                                ${item.sta_status}
                            </option>
                        </c:forEach>
                </select>
            </p>
            
            <p>Classificação
                <select name="classificacaocaixa" id="classificacaocaixa">
                        <option value="">Selecione uma classificação..</option>
                        <c:forEach var="item" items="${classiCB}">
                            <option value="${item.cla_codigo}" name="campostacodigo">
                                ${item.cla_nome}
                            </option>
                        </c:forEach>
                </select>
            </p>
            
            <p>Data
                <input type="date" name="campodata" maxlength="10" onkeypress="formataData(this, event)"/>
            </p>
            
            <p id="demo"></p>
            <script>
            function myFunction() {
              var x = document.getElementById("statuscaixa").value;
              if(x === '2')
                document.getElementById("demo").innerHTML = "Data Fechamento <input type=\"date\" name=\"campodataf\" onkeypress=\"formataData(this, event)\"/>";
              else
                document.getElementById("demo").innerHTML = "";
            }
            </script>
            
            <p>Email do solicitante
                <input type="email" name="campoemail"/>
            </p>
            
            <p>Descrição:
            </p>
            <textarea rows="4" cols="50" name="campodescricao"></textarea>
            
            <p><input type="submit" name="bEnviar" value="Abrir Chamado"/></p> 
            
            <a href="principal.do">Voltar</a>
        </form>
    </body>
</html>
