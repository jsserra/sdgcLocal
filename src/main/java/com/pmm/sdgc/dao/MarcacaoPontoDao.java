/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.MarcacaoPonto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author setinf
 */
@Stateless
public class MarcacaoPontoDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    PessoaDao daoPessoa;

    public void atualizar(String cpf, Integer mes, Integer ano) {
        Statement con = getConexaoServidorPonto();

        if (con == null) {
            return;
        }

        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = LocalDate.of(ano, mes, inicio.lengthOfMonth());
        List<MarcacaoPonto> marcacoes = getMarcacoesPorCpfMes(cpf, mes, ano);

        LocalDate inicioReal = inicio;
        if (!(marcacoes.isEmpty())) {
            MarcacaoPonto ultimaMarcacao = marcacoes.get(marcacoes.size() - 1);
            inicioReal = ultimaMarcacao.getDataPonto();
        }

        //LocalDateTime inicio = LocalDateTime.parse(mesAno+"-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //LocalDateTime fim = inicio.withDayOfMonth(inicio.toLocalDate().lengthOfMonth());
        String strSql = "select dt_registro from pto_pessoa where cpf = '" + cpf
                + "' and dt_registro between '" + inicioReal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + "' and '" + fim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        try {
            ResultSet rs = con.executeQuery(strSql);

            while (rs.next()) {
                Date dataLida = rs.getTimestamp("dt_registro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcacaoPontoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarOld() {

        Statement con = getConexaoServidorPonto();

        if (con == null) {
            return;
        }

        Long maiorId = getMaioridPonto();
        if (maiorId == null) {
            maiorId = new Long(0);
        }

        String sql = "SELECT \n"
                + "    pto.id_ponto_pessoa as idpto, \n"
                + "    gp.nr_matricula AS matricula, gp.nr_cpf_pessoa AS cpf, \n"
                + "    gp.nm_pessoa AS nome, gp.id_empresa AS orgao, pto.dt_ponto AS data_ponto, \n"
                + "    pto.hr_entrada1 AS entrada1, \n"
                + "    pto.ds_justificativa_entrada1 AS justificativa_entrada1, \n"
                + "    pto.hr_saida1 AS saida1, \n"
                + "    pto.ds_justificativa_saida1 AS justificativa_saida1, \n"
                + "    pto.hr_entrada2 AS entrada2, \n"
                + "    pto.ds_justificativa_entrada2 AS justificativa_entrada2, \n"
                + "    pto.hr_saida2 AS saida2, \n"
                + "    pto.ds_justificativa_saida2 AS justificativa_saida2, \n"
                + "    pto.hr_entrada3 AS entrada3, \n"
                + "    pto.ds_justificativa_entrada3 AS justificativa_entrada3, \n"
                + "    pto.hr_saida3 AS saida3, \n"
                + "    pto.ds_justificativa_saida3 AS justificativa_saida3, \n"
                + "    pto.hr_entrada4 AS entrada4, \n"
                + "    pto.ds_justificativa_entrada4 AS justificativa_entrada4, \n"
                + "    pto.hr_saida4 AS saida4, \n"
                + "    pto.ds_justificativa_saida4 AS justificativa_saida4, \n"
                + "    pto.nr_hr_trabalhada_ts AS hs_trabalhadas\n"
                + "   FROM gen_pessoa gp\n"
                + "   LEFT JOIN com_pessoa_grupo_rep cpgr ON gp.id_pessoa = cpgr.id_pessoa\n"
                + "   LEFT JOIN pto_ponto_pessoa pto ON gp.id_pessoa = pto.id_pessoa\n"
                + "   WHERE pto.id_ponto_pessoa > " + maiorId.toString();

        try {
            ResultSet rs = con.executeQuery(sql);

            while (rs.next()) {
                MarcacaoPonto m = new MarcacaoPonto();
                m.setCpf(rs.getString("cpf"));
                m.setIdPonto(rs.getLong("idpto"));

                m.setDataPonto(rs.getDate("data_ponto").toLocalDate());

                if (!(rs.getTime("entrada1") == null)) {
                    m.setEntrada1(rs.getTime("entrada1").toLocalTime());
                }
                m.setJustificativaEntrada1(rs.getString("justificativa_entrada1"));
                if (!(rs.getTime("saida1") == null)) {
                    m.setEntrada1(rs.getTime("saida1").toLocalTime());
                }
                m.setJustificativaSaida1(rs.getString("justificativa_saida1"));

                if (!(rs.getTime("entrada2") == null)) {
                    m.setEntrada2(rs.getTime("entrada2").toLocalTime());
                }
                m.setJustificativaEntrada2(rs.getString("justificativa_entrada2"));
                if (!(rs.getTime("saida2") == null)) {
                    m.setEntrada2(rs.getTime("saida2").toLocalTime());
                }
                m.setJustificativaSaida2(rs.getString("justificativa_saida2"));

                if (!(rs.getTime("entrada3") == null)) {
                    m.setEntrada3(rs.getTime("entrada3").toLocalTime());
                }
                m.setJustificativaEntrada3(rs.getString("justificativa_entrada3"));
                if (!(rs.getTime("saida3") == null)) {
                    m.setEntrada3(rs.getTime("saida3").toLocalTime());
                }
                m.setJustificativaSaida3(rs.getString("justificativa_saida3"));

                if (!(rs.getTime("entrada4") == null)) {
                    m.setEntrada4(rs.getTime("entrada4").toLocalTime());
                }
                m.setJustificativaEntrada4(rs.getString("justificativa_entrada4"));
                if (!(rs.getTime("saida4") == null)) {
                    m.setEntrada4(rs.getTime("saida4").toLocalTime());
                }
                m.setJustificativaSaida4(rs.getString("justificativa_saida4"));

                //System.out.println(rs.getTimestamp("hs_trabalhadas"));
                if (!(rs.getTimestamp("hs_trabalhadas") == null)) {
                    m.setHsTrabalhadas(rs.getTimestamp("hs_trabalhadas").toLocalDateTime());
                }
                m.setPessoa(daoPessoa.getPessoaPorCpf(m.getCpf()));
                em.persist(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MarcacaoPontoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Statement getConexaoServidorPonto() {
        String ip = "201.23.192.151";
        String porta = "5433";
        String bd = "neweasy";
        String usuario = "postgres";
        String senha = "velti@123";

        String connectionString = "jdbc:postgresql://" + ip + ":" + porta + "/" + bd;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(connectionString, usuario, senha);
            Statement statement = connection.createStatement();
            return statement;
        } catch (SQLException e) {
            //e.printStackTrace();
            //System.out.println("Conexão com o servidor de ponto está fora");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MarcacaoPontoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Long getMaioridPonto() {
        Query q = em.createQuery("select max(m.idPonto) from MarcacaoPonto m ");
        List<Long> resp = q.getResultList();

        if (resp == null) {
            return new Long(0);
        }

        if (resp.isEmpty()) {
            return new Long(0);
        }

        return resp.get(0);

    }

    public List<MarcacaoPonto> getMarcacoesPorCpfMes(String cpf, Integer mes, Integer ano) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = LocalDate.of(ano, mes, dataInicio.lengthOfMonth());

        Query q = em.createQuery("select m from MarcacaoPonto m where m.cpf = :cpf and m.dataPonto between :dI and :dF order by m.dataPonto");
        q.setParameter("cpf", cpf);
        q.setParameter("dI", dataInicio);
        q.setParameter("dF", dataFim);

        return q.getResultList();

    }
}
