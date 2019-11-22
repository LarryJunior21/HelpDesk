<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="funcionarioLogado" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>
<jsp:useBean id="Status" class="br.unoeste.fipp.entidade.Status"
             scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD/Sta</title>
    </head>
    <body>
        <h1>CRUD Status</h1>
        <form method="post" action="status.do">
            <div class="campos">
                <p>Código: <input type="text" name="campocod" size="3" value="${Status.sta_codigo}" readonly="readonly" disabled="disabled"/> </p> 
                <p>Nome: <input type="text" name="camponome" size="30" value="${Status.sta_status}"/> </p>         
            <%--BOTOES--%>
            <input type="submit" name="bInsere" value="Inserir" ${alterando ? "disabled=\"disabled\"" : ""}> 
            <input type="submit" id="bAltera" name="bAltera" value="Alterar" ${alterando ? "" : "disabled=\"disabled\""}> 
            <input type="submit" id="bLimpar" name="bLimpar" value="Limpar" ${alterando ? "" : "disabled=\"disabled\""}>
            
            </div>
            
            <h3> Status </h3>
            <input type="text" name="textobusca" size="30" ${alterando ? "disabled=\"disabled\"" : ""}>
            <input type="submit" id="bBusca" name="bBusca" value="Buscar" ${alterando ? "disabled=\"disabled\"" : ""}>
            
            <table>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Ativo</th>
                    <th colspan="2">-</th>
                </tr>
                <c:forEach var="f" items="${statio}">
                    <tr>
                        <td>${f.sta_codigo}</td>
                        <td>${f.sta_status}</td>
                        <td>${f.sta_ativo == true? 'Sim' : 'Não'}</td>
                        <td>
                            <input type="submit" name="${f.sta_codigo}selec" value="Selecionar" ${alterando ? "disabled=\"disabled\"" : ""}>
                        </td>
                        <td>
                            <input type="submit" name="${f.sta_codigo}desativa" value="Ativa/Desativa" ${alterando ? "" : "disabled=\"disabled\""}>
                        </td>
                        <td>
                            <input type="submit" name="${f.sta_codigo}exclui" value="Excluir" ${alterando ? "" : "disabled=\"disabled\""}>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <p><a href="crud.do">Voltar</a></p>
        </form>
    </body>
</html>
