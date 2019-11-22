<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="funcionarioLogado" class="br.unoeste.fipp.entidade.Funcionario"
             scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página principal</title>
    </head>
    <body>
        <h1>Ola ${funcionarioLogado.fun_nome} ${funcionarioLogado.fun_tipo == 'A' ? " (Administrador)" : ""}</h1>
        <form method="post" action="principal.do">
            <li ${funcionarioLogado.fun_tipo == 'A' ? "" : "hidden=\"hidden\""}>
                <a href="crud.do" ${funcionarioLogado.fun_tipo == 'A' ? "" : "hidden=\"hidden\""}>Cadastros, Consultas, Alterações e Exclusões</a>
            </li>
            <li>
                <a href="abrirchamadas.do">Abrir Chamado</a>
            </li>
            <li>
                <a href="encerrarchamadas.do">Encerrar Chamado</a>
            </li>
            <p><a href="logout.do">Sair</a></p>
        </form>
    </body>
</html>