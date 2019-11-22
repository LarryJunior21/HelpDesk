package br.unoeste.fipp.servlet.Chamadas;

import br.unoeste.fipp.dao.AtividadeDAO;
import br.unoeste.fipp.entidade.Atividade;
import br.unoeste.fipp.entidade.Funcionario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@WebServlet(name = "EncerrarChamadas", urlPatterns = {"/encerrarchamadas.do"})
public class EncerrarChamadas extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario user = (Funcionario) session.getAttribute("funcionarioLogado");
        if (user == null) {
            session.invalidate();
            response.sendRedirect("index.do");
            return;
        }
        if (request.getParameter("bAplicar") != null) {
            List<Atividade> lista = new ArrayList();
            AtividadeDAO a = new AtividadeDAO();
            lista = a.listar();
            
            for(Atividade asss : lista)
            {
                if(request.getParameter(""+asss.getAti_codigo()) != null)
                {                                     
                    Date data = new Date();
                    asss.setAti_dtfim(data);
                    asss.setSta_codigo(2);
                    a.altera(asss);
                }
            }
            response.sendRedirect("principal.do");
            return;
        }
        request.getRequestDispatcher("paginas/JSPs/CHAMADAS/EncerrarChamadas.jsp").forward(request, response);
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
