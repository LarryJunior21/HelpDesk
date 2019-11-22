package br.unoeste.fipp.dao;

import br.unoeste.fipp.entidade.Solicitante;
import br.unoeste.fipp.sql.Conexao;
import br.unoeste.fipp.sql.DAOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitanteDAO {
        
    public void insere(Solicitante sta) throws DAOException {
        String sql = "insert into solicitante (sol_email, sol_nome, sol_telefone, sol_observacao) values ('" + sta.getSol_email() + "','" + sta.getSol_nome() + "','" + sta.getSol_telefone() + "','" + sta.getSol_observacao() + "');";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                conn.setAutoCommit(false);
                try (Statement ps = conn.createStatement()) {
                    ps.executeUpdate(sql);
                    conn.commit();
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitanteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public Solicitante busca(String email) {
        String sql = "select sol_email, sol_nome, sol_telefone, sol_observacao from Solicitante where sol_email = '" + email + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Solicitante(rs.getString("sol_email"),
                                    rs.getString("sol_nome"),
                                    rs.getString("sol_telefone"),
                                    rs.getString("sol_observacao"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitanteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
    public List<Solicitante> listarNomes(String nome) {
        List<Solicitante> resp = new ArrayList<>();
        String sql = "select sol_email, sol_nome, sol_telefone, sol_observacao from solicitante where sol_nome like '%" + nome + "%';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Solicitante(rs.getString("sol_email"),
                                    rs.getString("sol_nome"),
                                    rs.getString("sol_telefone"),
                                    rs.getString("sol_observacao"))
                            );
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitanteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public List<Solicitante> listar() {
        List<Solicitante> resp = new ArrayList<>();
        String sql = "select sol_email, sol_nome, sol_telefone, sol_observacao from Solicitante order by sol_nome;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Solicitante(rs.getString("sol_email"),
                                    rs.getString("sol_nome"),
                                    rs.getString("sol_telefone"),
                                    rs.getString("sol_observacao"))
                            );
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitanteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    public void altera(Solicitante sta){
        String sql = "update solicitante set sol_email = '"+ sta.getSol_email()+"', sol_nome = '"+ sta.getSol_nome()+"', "
                     + "sol_telefone = '"+ sta.getSol_telefone() +"', sol_observacao = '"+ sta.getSol_observacao() +"';";
        
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                conn.setAutoCommit(false);
                try (Statement ps = conn.createStatement()) {
                    ps.executeUpdate(sql);
                    conn.commit();
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Solicitante sta){
        String sql = "delete from solicitante where sol_email like '" + sta.getSol_email() + "' and sol_nome like '" + sta.getSol_nome() + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                conn.setAutoCommit(false);
                try (Statement ps = conn.createStatement()) {
                    ps.executeUpdate(sql);
                    conn.commit();
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitanteDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
