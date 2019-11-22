<%@page import="br.unoeste.fipp.sql.DAOException"%>
<%@page import="br.unoeste.fipp.dao.SolicitanteDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="funcionarioLogado" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>
<jsp:useBean id="Solicitante" class="br.unoeste.fipp.entidade.Solicitante"
             scope="session"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD/SOL</title>
    </head>
    <body>
        <h1>CRUD Solicitante</h1>
        <form method="post" action="solicitante.do">                        
            <div class="campos">
                <p>Email: <input type="text" name="campoemail" size="30" value="${Solicitante.sol_email}"/> </p> 
                <p>Nome: <input type="text" name="camponome" size="30" value="${Solicitante.sol_nome}"/> </p> 
                <p>Telefone: <input type="text" name="campotelefone" size="20" value="${Solicitante.sol_telefone}"/> </p> 
                <p>Observacao: <textarea rows="4" cols="50" name="campobs">${Solicitante.sol_observacao}</textarea></p>      
            <%--BOTOES--%>
            <input type="submit" name="bInsere" value="Inserir" ${alterando ? "disabled=\"disabled\"" : ""}> 
            <input type="submit" id="bAltera" name="bAltera" value="Alterar" ${alterando ? "" : "disabled=\"disabled\""}> 
            <input type="submit" id="bLimpar" name="bLimpar" value="Limpar" ${alterando ? "" : "disabled=\"disabled\""}>
            
            </div>
            
            <h3> Solicitantes </h3>
            <input type="text" name="textobusca" size="30" ${alterando ? "disabled=\"disabled\"" : ""}>
            <input type="submit" id="bBusca" name="bBusca" value="Buscar" ${alterando ? "disabled=\"disabled\"" : ""}>
            
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Telefone</th>
                    <th>Observação</th>
                    <th colspan="2">-</th>
                </tr>
                <c:forEach var="f" items="${solico}">
                    <tr>
                        <td>${f.sol_nome}</td>
                        <td>${f.sol_email}</td>
                        <td>${f.sol_telefone}</td>
                        <td>${f.sol_observacao}</td>
                        <td>
                            <input type="submit" name="${f.sol_telefone}selec" value="Selecionar" ${alterando ? "disabled=\"disabled\"" : ""}>
                        </td>
                        <td>
                            <input type="submit" name="${f.sol_telefone}exclui" value="Excluir" ${alterando ? "" : "disabled=\"disabled\""}>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <p><a href="crud.do">Voltar</a></p>
        </form>
    </body>
</html>
