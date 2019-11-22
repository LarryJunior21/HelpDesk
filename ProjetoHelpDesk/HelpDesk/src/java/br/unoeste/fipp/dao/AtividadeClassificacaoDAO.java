package br.unoeste.fipp.dao;

import br.unoeste.fipp.entidade.AtividadeClassificacao;
import br.unoeste.fipp.sql.Conexao;
import br.unoeste.fipp.sql.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtividadeClassificacaoDAO {
       public void insere(AtividadeClassificacao sta) throws DAOException {
        String sql = "insert into AtividadeClassificacao (ati_codigo, cla_codigo) values (" + sta.getAti_codigo()+ "," + sta.getCla_codigo() + ");";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                conn.setAutoCommit(false);
                try (Statement ps = conn.createStatement()) {
                    /*try (ResultSet rs = ps.executeQuery(
                            "select * from AtividadeClassificacao where ati_codigo = " + sta.getAti_codigo() + " and cla_codigo = " + sta.getCla_codigo())) {
                        if (rs.next()) {
                            conn.rollback();
                            throw new DAOException("Código duplicado");
                        }
                    }*/
                    ps.executeUpdate(sql);
                    conn.commit();
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public AtividadeClassificacao busca(int cod) {
        String sql = "select ati_codigo, cla_codigo from AtividadeClassificacao where ati_codigo = " + cod + ";";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new AtividadeClassificacao(rs.getInt("ati_codigo"),
                                    rs.getInt("cla_codigo"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<AtividadeClassificacao> listar() {
        List<AtividadeClassificacao> resp = new ArrayList<>();
        String sql = "select ati_codigo, cla_codigo from AtividadeClassificacao order by ati_codigo;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new AtividadeClassificacao(rs.getInt("ati_codigo"),
                                    rs.getInt("cla_codigo"))
                            );
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    public void delete(AtividadeClassificacao sta){
        String sql = "delete from AtividadeClassificacao where ati_codigo = " + sta.getAti_codigo() + " and cla_codigo = " + sta.getCla_codigo() + ";";
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
            Logger.getLogger(AtividadeClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }   
}
