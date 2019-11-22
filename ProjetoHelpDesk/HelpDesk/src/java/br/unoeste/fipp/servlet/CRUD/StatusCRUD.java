package br.unoeste.fipp.servlet.CRUD;

import br.unoeste.fipp.dao.StatusDAO;
import br.unoeste.fipp.entidade.Status;
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

@WebServlet(name = "StatusCRUD", urlPatterns = {"/status.do"})
public class StatusCRUD extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario user = (Funcionario) session.getAttribute("funcionarioLogado");
        Status fun = (Status)session.getAttribute("Status");
        StatusDAO f = new StatusDAO();
        String flag = "";
        request.setAttribute("alterando", false);
        
        if (user == null) {
            session.invalidate();
            response.sendRedirect("index.do");
            return;
        }
        List<Status> lista = f.listar();
        for(Status ff : lista)
        {
            String aux = ff.getSta_codigo()+ "selec";
            String aux2 = ff.getSta_codigo() + "exclui";
            String aux3 = ff.getSta_codigo() + "desativa";
            if(request.getParameter(aux) != null)
            {
                fun = f.busca(ff.getSta_codigo()); 
                request.setAttribute("alterando", true);
                flag = fun.getSta_status();
            }
            else if(request.getParameter(aux2) != null)
            {
                f.delete(ff);
                fun = new Status();
            }
            else if(request.getParameter(aux3) != null)
            {
                if(ff.getSta_ativo())
                    ff.setSta_ativo(false);
                else
                   ff.setSta_ativo(true);
                f.altera(ff);
                fun = new Status();
            }
        }
        if(request.getParameter("bInsere") != null)
        {
            fun.setSta_ativo(true);
            fun.setSta_status(request.getParameter("camponome"));
            try {
                f.insere(fun);
            } catch (DAOException ex) {
                Logger.getLogger(StatusCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            fun = new Status();
        }
        if(request.getParameter("bAltera") != null)
        {
            fun.setSta_status(request.getParameter("camponome"));
            f.altera(fun);
            fun = new Status();
        }
        if(request.getParameter("bLimpar") != null)
        {
            fun = new Status();
        }
        if(!flag.isEmpty())
            request.setAttribute("statio", f.listarNomes(flag));
        else
            request.setAttribute("statio", new StatusDAO().listar());
        
        
        if(request.getParameter("bBusca") != null)
        {
            if(!request.getParameter("textobusca").isEmpty())
            {
                request.setAttribute("statio", f.listarNomes(request.getParameter("textobusca")));
            }
            fun = new Status();
        }
        
        session.setAttribute("Status", fun);
        request.getRequestDispatcher("paginas/JSPs/CRUD/Crud_Status.jsp").forward(request, response);
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
