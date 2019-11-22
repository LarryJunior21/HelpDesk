package br.unoeste.fipp.servlet.CRUD;

import br.unoeste.fipp.dao.SolicitanteDAO;
import br.unoeste.fipp.entidade.Funcionario;
import br.unoeste.fipp.entidade.Solicitante;
import br.unoeste.fipp.sql.DAOException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "SolicitanteCRUD", urlPatterns = {"/solicitante.do"})
public class SolicitanteCRUD extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario user = (Funcionario) session.getAttribute("funcionarioLogado");
        Solicitante fun = (Solicitante)session.getAttribute("Solicitante");
        SolicitanteDAO f = new SolicitanteDAO();
        String flag = "";
        request.setAttribute("alterando", false);
        
        if (user == null) {
            session.invalidate();
            response.sendRedirect("index.do");
            return;
        }
        List<Solicitante> lista = f.listar();
        for(Solicitante ff : lista)
        {
            String aux = ff.getSol_telefone() + "selec";
            String aux2 = ff.getSol_telefone() + "exclui";
            if(request.getParameter(aux) != null)
            {
                fun = f.busca(ff.getSol_email()); 
                request.setAttribute("alterando", true);
                flag = fun.getSol_nome();
            }
            else if(request.getParameter(aux2) != null)
            {
                f.delete(ff);
                fun = new Solicitante();
            }
        }
        if(request.getParameter("bInsere") != null)
        {
            fun.setSol_nome(request.getParameter("camponome"));
            fun.setSol_email(request.getParameter("campoemail"));
            fun.setSol_observacao(request.getParameter("campobs"));
            fun.setSol_telefone(request.getParameter("campotelefone"));
            try {
                f.insere(fun);
            } catch (DAOException ex) {
                Logger.getLogger(SolicitanteCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            fun = new Solicitante();
        }
        if(request.getParameter("bAltera") != null)
        {
            fun.setSol_nome(request.getParameter("camponome"));
            fun.setSol_email(request.getParameter("campoemail"));
            fun.setSol_observacao(request.getParameter("campobs"));
            fun.setSol_telefone(request.getParameter("campotelefone"));
            f.altera(fun);
            fun = new Solicitante();
        }
        if(request.getParameter("bLimpar") != null)
        {
            fun = new Solicitante();
        }
        if(!flag.isEmpty())
            request.setAttribute("solico", f.listarNomes(flag));
        else
            request.setAttribute("solico", new SolicitanteDAO().listar());
        
        
        if(request.getParameter("bBusca") != null)
        {
            if(!request.getParameter("textobusca").isEmpty())
            {
                request.setAttribute("solico", f.listarNomes(request.getParameter("textobusca")));
            }
            fun = new Solicitante();
        }
        
        session.setAttribute("Solicitante", fun);
        request.getRequestDispatcher("paginas/JSPs/CRUD/Crud_Solicitante.jsp").forward(request, response);
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
