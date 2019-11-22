package br.unoeste.fipp.servlet.CRUD;

import br.unoeste.fipp.dao.ClassificacaoDAO;
import br.unoeste.fipp.entidade.Classificacao;
import br.unoeste.fipp.entidade.Funcionario;
import br.unoeste.fipp.sql.DAOException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ClassificacaoCRUD", urlPatterns = {"/classificacao.do"})
public class ClassificacaoCRUD extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario user = (Funcionario) session.getAttribute("funcionarioLogado");
        Classificacao fun = (Classificacao)session.getAttribute("Classificacao");
        ClassificacaoDAO f = new ClassificacaoDAO();
        String flag = "";
        request.setAttribute("alterando", false);
        
        if (user == null) {
            session.invalidate();
            response.sendRedirect("index.do");
            return;
        }
        List<Classificacao> lista = f.listar();
        for(Classificacao ff : lista)
        {
            String aux = ff.getCla_codigo()+ "selec";
            String aux2 = ff.getCla_codigo() + "exclui";
            String aux3 = ff.getCla_codigo() + "desativa";
            if(request.getParameter(aux) != null)
            {
                fun = f.busca(ff.getCla_codigo()); 
                request.setAttribute("alterando", true);
                flag = fun.getCla_nome();
            }
            else if(request.getParameter(aux2) != null)
            {
                f.delete(ff);
                fun = new Classificacao();
            }
            else if(request.getParameter(aux3) != null)
            {
                if(ff.getCla_ativa())
                    ff.setCla_ativa(false);
                else
                   ff.setCla_ativa(true);
                f.altera(ff);
                fun = new Classificacao();
            }
        }
        if(request.getParameter("bInsere") != null)
        {
            fun.setCla_ativa(true);
            fun.setCla_nome(request.getParameter("camponome"));
            try {
                f.insere(fun);
            } catch (DAOException ex) {
                Logger.getLogger(ClassificacaoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            fun = new Classificacao();
        }
        if(request.getParameter("bAltera") != null)
        {
            fun.setCla_nome(request.getParameter("camponome"));
            f.altera(fun);
            fun = new Classificacao();
        }
        if(request.getParameter("bLimpar") != null)
        {
            fun = new Classificacao();
        }
        if(!flag.isEmpty())
            request.setAttribute("classio", f.listarNomes(flag));
        else
            request.setAttribute("classio", new ClassificacaoDAO().listar());
        
        
        if(request.getParameter("bBusca") != null)
        {
            if(!request.getParameter("textobusca").isEmpty())
            {
                request.setAttribute("classio", f.listarNomes(request.getParameter("textobusca")));
            }
            fun = new Classificacao();
        }
        
        session.setAttribute("Classificacao", fun);
        request.getRequestDispatcher("paginas/JSPs/CRUD/Crud_Classificacao.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
