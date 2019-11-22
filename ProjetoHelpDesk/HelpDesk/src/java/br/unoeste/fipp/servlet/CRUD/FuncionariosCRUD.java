package br.unoeste.fipp.servlet.CRUD;

import br.unoeste.fipp.dao.FuncionarioDAO;
import br.unoeste.fipp.entidade.Funcionario;
import br.unoeste.fipp.sql.DAOException;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "FuncionariosCRUD", urlPatterns = {"/funcionarios.do"})
public class FuncionariosCRUD extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario user = (Funcionario) session.getAttribute("funcionarioLogado");
        Funcionario fun = (Funcionario)session.getAttribute("Funcionario");
        FuncionarioDAO f = new FuncionarioDAO();
        String flag = "";
        request.setAttribute("alterando", false);
        
        if (user == null) {
            session.invalidate();
            response.sendRedirect("index.do");
            return;
        }
        List<Funcionario> lista = f.listar();
        for(Funcionario ff : lista)
        {
            String aux = ff.getFun_codigo() + "selec";
            String aux2 = ff.getFun_codigo() + "exclui";
            String aux3 = ff.getFun_codigo() + "desativa";
            if(request.getParameter(aux) != null)
            {
                fun = f.busca(ff.getFun_codigo()); 
                request.setAttribute("alterando", true);
                flag = fun.getFun_nome();
            }
            else if(request.getParameter(aux2) != null)
            {
                f.delete(ff);
                fun = new Funcionario();
            }
            else if(request.getParameter(aux3) != null)
            {
                if(ff.getFun_ativo().equals("t"))
                    ff.setFun_ativo("f");
                else
                   ff.setFun_ativo("t");
                f.altera(ff);
                fun = new Funcionario();
            }
        }
        if(request.getParameter("bInsere") != null)
        {
            fun.setFun_ativo("t");
            fun.setFun_nome(request.getParameter("camponome"));
            fun.setFun_senha(request.getParameter("camposenha"));
            fun.setFun_tipo(request.getParameter("selectipo"));
            
            String aux = request.getParameter("campodata");
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            Date datas;
            try {
                datas = formataData.parse(aux);
                //aux = formataData.format(datas);
                fun.setFun_dtcontratacao(datas);
                f.insere(fun);
            } catch (ParseException | DAOException ex) {
                Logger.getLogger(FuncionariosCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            fun = new Funcionario();
        }
        if(request.getParameter("bAltera") != null)
        {
            fun.setFun_nome(request.getParameter("camponome"));
            fun.setFun_senha(request.getParameter("camposenha"));
            fun.setFun_tipo(request.getParameter("selectipo"));
            
            String aux = request.getParameter("campodata");
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            Date datas;
            try {
                datas = formataData.parse(aux);
                //aux = formataData.format(datas);
                fun.setFun_dtcontratacao(datas);
                f.altera(fun);
            } catch (ParseException ex) {
                Logger.getLogger(FuncionariosCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            fun = new Funcionario();
        }
        if(request.getParameter("bLimpar") != null)
        {
            fun = new Funcionario();
        }
        if(!flag.isEmpty())
            request.setAttribute("funcio", f.listarNomes(flag));
        else
            request.setAttribute("funcio", new FuncionarioDAO().listar());
        
        
        if(request.getParameter("bBusca") != null)
        {
            if(!request.getParameter("textobusca").isEmpty())
            {
                request.setAttribute("funcio", f.listarNomes(request.getParameter("textobusca")));
            }
            fun = new Funcionario();
        }
        
        session.setAttribute("Funcionario", fun);
        request.getRequestDispatcher("paginas/JSPs/CRUD/Crud_Funcionarios.jsp").forward(request, response);
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
