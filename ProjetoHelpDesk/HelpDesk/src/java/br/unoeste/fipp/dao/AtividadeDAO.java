package br.unoeste.fipp.dao;

import br.unoeste.fipp.entidade.Atividade;
import br.unoeste.fipp.sql.Conexao;
import br.unoeste.fipp.sql.DAOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtividadeDAO {
        public void insere(Atividade sta) throws DAOException {
            java.sql.Date dataSql = new java.sql.Date(sta.getAti_dtinicio().getTime());
            String sql;
            if(sta.getAti_dtfim() != null)
            {
                sql = "insert into Atividade (ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email) values (" + "'" + sta.getAti_descricao() 
                    + "','" + dataSql + "','" + sta.getAti_dtfim() + "'," + sta.getFun_codigo() + "," + sta.getSta_codigo() + ",'" + sta.getSol_email() + "');";
            }
            else
            {
                sql = "insert into Atividade (ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email) values (" + "'" + sta.getAti_descricao() 
                    + "','" + dataSql + "'," + sta.getAti_dtfim() + "," + sta.getFun_codigo() + "," + sta.getSta_codigo() + ",'" + sta.getSol_email() + "');";
            }
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
                Logger.getLogger(AtividadeDAO.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
    }
        
    public int pegaultimo()
    {
        String sql = "select max(ati_codigo) from atividade";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return rs.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public Atividade busca(int cod) {
        String sql = "select ati_codigo, ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email from Atividade where ati_codigo = " + cod + ";";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Atividade(rs.getInt("ati_codigo"),
                                    rs.getString("cla_nome"),
                                    rs.getDate("ati_dtinicio"),
                                    rs.getDate("ati_dtfim"),
                                    rs.getInt("fun_codigo"),
                                    rs.getInt("sta_codigo"),
                                    rs.getString("sol_email"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Atividade> listar() {
        List<Atividade> resp = new ArrayList<>();
        int x = 0;
        String sql = "select ati_codigo, ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email from Atividade order by ati_codigo;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                                x = rs.getInt("sta_codigo");
                                if(x == 1)
                                {
                                    java.util.Date dataString = rs.getDate("ati_dtinicio");
                                    resp.add(
                                            new Atividade(rs.getInt("ati_codigo"),
                                                    rs.getString("ati_descricao"),
                                                    dataString,
                                                    rs.getInt("fun_codigo"),
                                                    x,
                                                    rs.getString("sol_email"))
                                    );
                                }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    public void altera(Atividade sta){
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada  = formataData.format(sta.getAti_dtfim());
        String sql = "update Atividade set ati_dtfim = '"+ dataFormatada +"', sta_codigo = "+ sta.getSta_codigo() +" where ati_codigo = " + sta.getAti_codigo() + ";";
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
            Logger.getLogger(AtividadeDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Atividade sta){
        String sql = "delete from Atividade where ati_codigo = " + sta.getAti_codigo() + ";";
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
            Logger.getLogger(AtividadeDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
