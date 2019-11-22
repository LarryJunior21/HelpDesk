package br.unoeste.fipp.servlet;

import br.unoeste.fipp.dao.FuncionarioDAO;
import br.unoeste.fipp.entidade.Funcionario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Index", urlPatterns = {"/index.do"})
public class Index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new ArrayList<>();
        if (request.getParameter("bEntrar") != null) {
            String login = request.getParameter("Login");
            String senha = request.getParameter("txtSenha");
            if (login == null || login.isEmpty()) {
                    erros.add("Login não informado corretamente.");
            }
            if (senha == null || senha.isEmpty()
                    || senha.length() > 8) {
                erros.add("Senha não informada corretamente.");
            }
            if (erros.isEmpty()) {
                Funcionario f = FuncionarioDAO.buscas(Integer.parseInt(login));
                if (f == null) {
                    erros.add("Funcionario não existe");
                } else {
                    if (f.getFun_senha().equalsIgnoreCase(senha)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("funcionarioLogado", f);
                        response.sendRedirect("principal.do");
                        return;
                    } else {
                        erros.add("Senha inválida");
                    }
                }
            }
        }
        request.setAttribute("mensagens", erros);
        request.getSession().invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("paginas/JSPs/index.jsp");
        rd.forward(request, response);
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
        return "Controlador para o Login";
    }// </editor-fold>

}
