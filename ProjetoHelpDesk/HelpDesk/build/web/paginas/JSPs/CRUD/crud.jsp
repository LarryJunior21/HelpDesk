<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="funcionarioLogado" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Centro CRUD</title>
    </head>
    <body>
        <h1>CRUD's</h1>
        <form method="post" action="crud.do">
            <li>
                <a href="classificacao.do">Classificacao</a>
            </li>
            <li>
                <a href="funcionarios.do">Funcionarios</a>
            </li>
            <li>
                <a href="solicitante.do">Solicitante</a>
            </li>
            <li>
                <a href="status.do">Status</a>
            </li>
            <p><a href="principal.do">Voltar</a></p>
        </form>
    </body>
</html>
