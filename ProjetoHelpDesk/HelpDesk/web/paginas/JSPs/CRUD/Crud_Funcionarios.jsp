<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unoeste.fipp.dao.FuncionarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="funcionarioLogado" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>
<jsp:useBean id="Funcionario" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>
<!DOCTYPE html>

<%
    List<String> tipo = new ArrayList();
    tipo.add("A");
    tipo.add("N");
    tipo.add("T");
    request.setAttribute("tipos", tipo);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD/Fun</title>
    </head>
    <body>
        <h1>CRUD Funcionarios</h1>
        <form method="post" action="funcionarios.do">
            <div class="campos">
            <p>Código: <input type="text" name="camponome" size="3" value="${Funcionario.fun_codigo}" readonly="readonly" disabled="disabled"/> </p> 
            <p>Nome: <input type="text" name="camponome" size="30" value="${Funcionario.fun_nome}"/> </p> 
            <p>Data contratacao: <input type="date" name="campodata" value="${Funcionario.fun_dtcontratacao}" ${alterando ? "readonly=\"readonly\"":""}/> </p> 
            <p>Senha:<input type="password" name="camposenha" size="20" value="${Funcionario.fun_senha}"/> </p> 
            <p>Tipo: 
            <select name="selectipo">
                <c:forEach var="fu" items="${tipos}">
                    <option value="${fu}" ${Funcionario.fun_tipo == fu ? 'selected="selected"' : ''} >
                        ${fu == 'A' ? 'Administrador' : (fu == 'T' ? 'Funcionario TI' : 'Funcionario Normal')}
                    </option>
                </c:forEach>
            </select></p>            
            <%--BOTOES--%>
            <input type="submit" name="bInsere" value="Inserir" ${alterando ? "disabled=\"disabled\"" : ""}> 
            <input type="submit" id="bAltera" name="bAltera" value="Alterar" ${alterando ? "" : "disabled=\"disabled\""}> 
            <input type="submit" id="bLimpar" name="bLimpar" value="Limpar" ${alterando ? "" : "disabled=\"disabled\""}>
            
            </div>
            
            <h3> Funcionarios </h3>
            <input type="text" name="textobusca" size="30" ${alterando ? "disabled=\"disabled\"" : ""}>
            <input type="submit" id="bBusca" name="bBusca" value="Buscar" ${alterando ? "disabled=\"disabled\"" : ""}>
            
            <table>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Data Contratação</th>
                    <th>Tipo</th>
                    <th>Ativo</th>
                    <th colspan="2">-</th>
                </tr>
                <c:forEach var="f" items="${funcio}">
                    <tr>
                        <td>${f.fun_codigo}</td>
                        <td>${f.fun_nome}</td>
                        <td>${f.fun_dtcontratacao}</td>
                        <td>${f.fun_tipo == 'A' ? 'Administrador' : (f.fun_tipo == 'T' ? 'Funcionario TI' : 'Funcionario Normal')}</td>
                        <td>${f.fun_ativo == 't'? 'Sim' : 'Não'}</td>
                        <td>
                            <input type="submit" name="${f.fun_codigo}selec" value="Selecionar" ${alterando ? "disabled=\"disabled\"" : ""}>
                        </td>
                        <td>
                            <input type="submit" name="${f.fun_codigo}desativa" value="Ativa/Desativa" ${alterando ? "" : "disabled=\"disabled\""}>
                        </td>
                        <td>
                            <input type="submit" name="${f.fun_codigo}exclui" value="Excluir" ${alterando ? "" : "disabled=\"disabled\""}>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <p><a href="crud.do">Voltar</a></p>
        </form>
    </body>
</html>
