<%@page import="br.unoeste.fipp.dao.AtividadeClassificacaoDAO"%>
<%@page import="br.unoeste.fipp.dao.ClassificacaoDAO"%>
<%@page import="br.unoeste.fipp.dao.FuncionarioDAO"%>
<%@page import="br.unoeste.fipp.dao.AtividadeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.unoeste.fipp.entidade.Atividade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="funcionarioLogado" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>


<% 
    request.setAttribute("atividades", new AtividadeDAO().listar());
    request.setAttribute("classificacoes", new AtividadeClassificacaoDAO().listar());
    request.setAttribute("classifica", new ClassificacaoDAO().listar());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Encerrar Chamadas</title>
    </head>
    <body>
        <h1>Encerrar chamadas abertas abaixo</h1>
        <form method="post" action="encerrarchamadas.do">
            
                <h2>Chamadas abertas</h2>
                <c:choose>
                    <c:when test="${not empty atividades}">
                        <table>
                            <tr>
                                <th>Fechar |</th>
                                <th>Código |</th>
                                <th>Código do Funcionario |</th>
                                <th>Status |</th>
                                <th>Classificação |</th>
                                <th>Email Solicitante </th>
                                <th>| Descrição |</th>
                                <th>Data Abertura</th>
                                <th colspan="2">-</th>
                            </tr>
                            <c:forEach var="atv" items="${atividades}">
                                <tr>
                                    <td><input type=checkbox name="${atv.ati_codigo}"></td>
                                    <td>${atv.ati_codigo}</td>
                                    <td>${atv.fun_codigo}</td>
                                    <td>Ativo</td>
                                    <c:forEach var="kk" items="${classificacoes}">
                                        <c:if test="${kk.ati_codigo == atv.ati_codigo}">
                                            <c:forEach var="k" items="${classifica}">
                                            <c:if test="${kk.cla_codigo == k.cla_codigo}">
                                                <td>${k.cla_nome}</td>
                                            </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                    <td>${atv.sol_email}</td>
                                    <td>${atv.ati_descricao}</td>
                                    <td>${atv.ati_dtinicio}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p><input type="submit" name="bAplicar" value="Aplicar"/></p> 
                    </c:when>    
                    <c:otherwise>
                        <h3>Não existem chamadas abertas</h3>
                        
                    </c:otherwise>
                </c:choose>
                        
                <p><a href="principal.do">Voltar</a></p> 
        </form>
    </body>
</html>
