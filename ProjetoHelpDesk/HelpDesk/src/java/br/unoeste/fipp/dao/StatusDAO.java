package br.unoeste.fipp.dao;

import br.unoeste.fipp.entidade.Status;
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

public class StatusDAO {
    
    public void insere(Status sta) throws DAOException {
        String sql = "insert into status (sta_status, sta_ativo) values ('" + sta.getSta_status() + "'," + sta.getSta_ativo() + ");";
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
            Logger.getLogger(StatusDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        throw new DAOException("Erro inserindo o registro.");
    }
    
        public Status busca(int codigo) {
        String sql = "select sta_codigo, sta_status, sta_ativo from Status where sta_codigo = " + codigo + ";";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Status(rs.getInt("sta_codigo"),
                                    rs.getString("sta_status"),
                                    rs.getBoolean("sta_ativo"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    public List<Status> listarNomes(String aux) {
        List<Status> resp = new ArrayList<>();
        String sql = "select sta_codigo, sta_status, sta_ativo from status where sta_status like '%" + aux + "%';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Status(rs.getInt("sta_codigo"),
                                    rs.getString("sta_status"),
                                    rs.getBoolean("sta_ativo"))
                            );
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }    

    public List<Status> listar() {
        List<Status> resp = new ArrayList<>();
        String sql = "select sta_codigo, sta_status, sta_ativo from Status order by sta_codigo;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Status(rs.getInt("sta_codigo"),
                                    rs.getString("sta_status"),
                                    rs.getBoolean("sta_ativo"))
                            );
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    public void altera(Status sta){
        
        String sql = "update status set sta_status = '"+ sta.getSta_status()+"', sta_ativo = "+ sta.getSta_ativo()+" where sta_codigo = " + sta.getSta_codigo() + ";";
        
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
    
    public void delete(Status sta){
        String sql = "delete from status where sta_codigo = " + sta.getSta_codigo() + ";";
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
            Logger.getLogger(StatusDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
