package br.unoeste.fipp.servlet.Chamadas;

import br.unoeste.fipp.dao.AtividadeClassificacaoDAO;
import br.unoeste.fipp.dao.AtividadeDAO;
import br.unoeste.fipp.dao.SolicitanteDAO;
import br.unoeste.fipp.entidade.Atividade;
import br.unoeste.fipp.entidade.AtividadeClassificacao;
import br.unoeste.fipp.entidade.Funcionario;
import br.unoeste.fipp.sql.DAOException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AbrirChamadas", urlPatterns = {"/abrirchamadas.do"})
public class AbrirChamadas extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario user = (Funcionario) session.getAttribute("funcionarioLogado");
        if (user == null) {
            session.invalidate();
            response.sendRedirect("index.do");
            return;
        }
        List<String> erros = new ArrayList<>();
        if (request.getParameter("bEnviar") != null) {
            	Date data = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada  = formataData.format(data);
                try {
                    String aux = request.getParameter("campodata");
                    Date datas = formataData.parse(aux);
                    aux = formataData.format(datas);
                    int inteiro = 0;
                    if(!request.getParameter("statuscaixa").equalsIgnoreCase(""))
                        inteiro = Integer.parseInt(request.getParameter("statuscaixa"));
                    if (!dataFormatada.equals(aux) && inteiro == 1) {
                        erros.add("Data para a abertura deve ser a data de hoje");
                    }
                    if (!dataFormatada.equals(aux) && inteiro == 3) {
                        erros.add("Data para a abertura deve ser a data de hoje");
                    }
                    if (inteiro == 2 && datas.after(data)) {
                        erros.add("Data para a abertura não pode ser depois da data de hoje");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(AbrirChamadas.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(request.getParameter("campodescricao").isEmpty())
                {
                    erros.add("Adicione uma descrição");
                }
                if(request.getParameter("campoemail").isEmpty())
                {
                    erros.add("Adicione um email");
                }
                if(!request.getParameter("campoemail").isEmpty())
                {
                    SolicitanteDAO s = new SolicitanteDAO();
                    if(s.busca(request.getParameter("campoemail")) == null)
                        erros.add("Email não cadastrado na base de dados");
                }
                if(request.getParameter("campodataf") != null)
                {
                    if(request.getParameter("campodataf").isEmpty())
                        erros.add("Adicione uma data de fechamento");
                    else
                    {
                        try {
                            String aux = request.getParameter("campodataf");
                            Date datas = formataData.parse(aux);
                            aux = formataData.format(datas);
                            if (!dataFormatada.equals(aux)) {
                                erros.add("Data para o fechamento deve ser hoje");
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(AbrirChamadas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if(request.getParameter("statuscaixa").equalsIgnoreCase(""))
                    erros.add("Selecione um status");
                if(request.getParameter("classificacaocaixa").equalsIgnoreCase(""))
                    erros.add("Selecione uma classificacao");
                if(erros.isEmpty())
                {
                    Atividade a = new Atividade();
                    a.setAti_descricao(request.getParameter("campodescricao"));
                    String aux = request.getParameter("campodata");
                    Date datas;
                    try {
                        datas = formataData.parse(aux);
                        a.setAti_dtinicio(datas);
                        if(request.getParameter("campodataf") != null)
                        {
                            aux = request.getParameter("campodataf");
                            datas = formataData.parse(aux);
                            a.setAti_dtfim(datas);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(AbrirChamadas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Funcionario f = (Funcionario) session.getAttribute("funcionarioLogado");
                    a.setFun_codigo(f.getFun_codigo());
                    a.setSol_email(request.getParameter("campoemail"));
                    aux = request.getParameter("statuscaixa");
                    a.setSta_codigo(Integer.parseInt(aux));
                    AtividadeDAO ad = new AtividadeDAO();
                        try {
                            ad.insere(a);
                        } catch (DAOException ex) {
                            Logger.getLogger(AbrirChamadas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    AtividadeClassificacao novo = new AtividadeClassificacao();
                    novo.setAti_codigo(ad.pegaultimo());
                    novo.setCla_codigo(Integer.parseInt(request.getParameter("classificacaocaixa")));
                    AtividadeClassificacaoDAO novoa = new AtividadeClassificacaoDAO();
                        try {
                            novoa.insere(novo);
                        } catch (DAOException ex) {
                            Logger.getLogger(AbrirChamadas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    response.sendRedirect("principal.do");
                    return;
                }
        }
        request.setAttribute("mensagens", erros);
        RequestDispatcher rd = request.getRequestDispatcher("paginas/JSPs/CHAMADAS/AbrirChamadas.jsp");
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
        return "Short description";
    }// </editor-fold>

}
