package br.unoeste.fipp.dao;

import br.unoeste.fipp.entidade.Funcionario;
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

public class FuncionarioDAO {
        
    public void insere(Funcionario sta) throws DAOException {
        String sql;
        if(sta.getFun_ativo().equals("t"))
            sql = "insert into Funcionario (fun_nome, fun_dtcontratacao, fun_ativo, fun_senha, fun_tipo) values ('" + sta.getFun_nome() + "','" + sta.getFun_dtcontratacao() + "',true,'" + sta.getFun_senha() + "','" + sta.getFun_tipo() + "');";
        else
            sql = "insert into Funcionario (fun_nome, fun_dtcontratacao, fun_ativo, fun_senha, fun_tipo) values ('" + sta.getFun_nome() + "','" + sta.getFun_dtcontratacao() + "',false,'" + sta.getFun_senha() + "','" + sta.getFun_tipo() + "');";
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
    
    public List<Funcionario> listarNomes(String nome) {
        List<Funcionario> resp = new ArrayList<>();
        String sql = "select fun_codigo, fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo from Funcionario where fun_nome like '%" + nome + "%';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Funcionario(rs.getInt("fun_codigo"),
                                    rs.getString("fun_nome"),
                                    rs.getDate("fun_dtcontratacao"),
                                    rs.getDate("fun_dtdemissao"),
                                    rs.getString("fun_ativo"),
                                    rs.getString("fun_senha"),
                                    rs.getString("fun_tipo"))
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
    
    public Funcionario busca(int cod) {
        String sql = "select fun_codigo, fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo from Funcionario where fun_codigo = " + cod + ";";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Funcionario(rs.getInt("fun_codigo"),
                                    rs.getString("fun_nome"),
                                    rs.getDate("fun_dtcontratacao"),
                                    rs.getDate("fun_dtdemissao"),
                                    rs.getString("fun_ativo"),
                                    rs.getString("fun_senha"),
                                    rs.getString("fun_tipo"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Funcionario buscas(int cod) {
        String sql = "select fun_codigo, fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo from Funcionario where fun_codigo = " + cod + ";";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Funcionario(rs.getInt("fun_codigo"),
                                    rs.getString("fun_nome"),
                                    rs.getDate("fun_dtcontratacao"),
                                    rs.getDate("fun_dtdemissao"),
                                    rs.getString("fun_ativo"),
                                    rs.getString("fun_senha"),
                                    rs.getString("fun_tipo"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Funcionario> listar() {
        List<Funcionario> resp = new ArrayList<>();
        String sql = "select fun_codigo, fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo from Funcionario order by fun_codigo;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            resp.add(
                                    new Funcionario(rs.getInt("fun_codigo"),
                                    rs.getString("fun_nome"),
                                    rs.getDate("fun_dtcontratacao"),
                                    rs.getDate("fun_dtdemissao"),
                                    rs.getString("fun_ativo"),
                                    rs.getString("fun_senha"),
                                    rs.getString("fun_tipo"))
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
    
    public void altera(Funcionario sta){
        String sql;
        if(sta.getFun_ativo().equals("t"))
            sql = "update funcionario set fun_nome = '"+ sta.getFun_nome()+"', fun_dtcontratacao = '"+ sta.getFun_dtcontratacao()+"', "
                     + "fun_senha = '"+ sta.getFun_senha() +"', fun_tipo = '"+ sta.getFun_tipo() +"', fun_ativo = true where fun_codigo = " + sta.getFun_codigo() + ";";
        else
            sql = "update funcionario set fun_nome = '"+ sta.getFun_nome()+"', fun_dtcontratacao = '"+ sta.getFun_dtcontratacao()+"', "
                     + "fun_senha = '"+ sta.getFun_senha() +"', fun_tipo = '"+ sta.getFun_tipo() +"', fun_ativo = false where fun_codigo = " + sta.getFun_codigo() + ";";
        
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
    
    public void delete(Funcionario sta){
        String sql = "delete from Funcionario where fun_codigo = " + sta.getFun_codigo() + ";";
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
}
