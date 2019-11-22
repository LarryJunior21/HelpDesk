
package br.unoeste.fipp.dao;

import br.unoeste.fipp.entidade.Classificacao;
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

public class ClassificacaoDAO {
       public void insere(Classificacao sta) throws DAOException {
        String sql = "insert into classificacao (cla_nome, cla_ativa) values ('" + sta.getCla_nome() + "'," + sta.getCla_ativa() + ");";
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
            Logger.getLogger(ClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public Classificacao busca(int cod) {
        String sql = "select cla_codigo, cla_nome, cla_ativa from Classificacao where cla_codigo = " + cod + ";";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Classificacao(rs.getInt("cla_codigo"),
                                    rs.getString("cla_nome"),
                                    rs.getBoolean("cla_ativa"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Classificacao> listarNomes(String nome) {
        List<Classificacao> resp = new ArrayList<>();
        String sql = "select cla_codigo, cla_nome, cla_ativa from classificacao where cla_nome like '%" + nome + "%';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Classificacao(rs.getInt("cla_codigo"),
                                    rs.getString("cla_nome"),
                                    rs.getBoolean("cla_ativa"))
                            );
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public  List<Classificacao> listar() {
        List<Classificacao> resp = new ArrayList<>();
        String sql = "select cla_codigo, cla_nome, cla_ativa from Classificacao order by cla_codigo;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Classificacao(rs.getInt("cla_codigo"),
                                    rs.getString("cla_nome"),
                                    rs.getBoolean("cla_ativa"))
                            );
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    public void altera(Classificacao sta){
        
        String sql = "update classificacao set cla_nome = '"+ sta.getCla_nome()+"', cla_ativa = "+ sta.getCla_ativa()+" where cla_codigo = " + sta.getCla_codigo() + ";";
        
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
    
    public void delete(Classificacao sta){
        String sql = "delete from Classificacao where cla_codigo = " + sta.getCla_codigo() + ";";
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
            Logger.getLogger(ClassificacaoDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
