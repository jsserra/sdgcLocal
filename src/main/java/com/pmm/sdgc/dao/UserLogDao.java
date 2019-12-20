/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.AppVersao;
import com.pmm.sdgc.model.UserLog;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.ws.model.ModelUserLogWs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
public class UserLogDao {

    @EJB
    UserLoginDao daoUserLogin;
    
    @EJB
    AppVersaoDao daoAppVersao;
    
    @PersistenceContext
    EntityManager em;

    public void criarLog(String descricao, String operacao, String chave, String parametros, String aplicativo, String ipOrigem) throws Exception {
        UserLogin usuario = daoUserLogin.getUserLoginPorChave(chave);

        if (usuario == null) {
            throw new Exception("Usuário com a chave enviada não encontrado");
        }

        AppVersao appv = daoAppVersao.getListaAppChave(aplicativo);

        if (appv == null) {
            throw new Exception("Chave de Aplicativo enviado não encontrado");
        }

        UserLog ul = new UserLog();

        ul.setDataHora(LocalDateTime.now());
        ul.setDescricao(descricao);
        ul.setOperacao(operacao);
        ul.setUserlogin(usuario);
        ul.setParametros(parametros);
        ul.setAplicativo(appv.getNome() + " - " + appv.getVersao());
        ul.setIpOrigem(ipOrigem);

        em.persist(ul);

    }

    public List<UserLog> getListaLog(String cpf, String dI, String dF) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        LocalDate ld1;
        LocalDate ld2;
        LocalDateTime d1 = null;
        LocalDateTime d2 = null;
        if (!(dI==null || dI.isEmpty()) ) {
            ld1 = LocalDate.parse(dI, formatter);
            d1 = LocalDateTime.of(ld1, LocalTime.of(0, 0));
        }
        if (!(dF==null || dF.isEmpty()) ) {
            ld2 = LocalDate.parse(dF, formatter);
            d2 = LocalDateTime.of(ld2, LocalTime.of(23, 59));
        }



        boolean where = false;
        String strSql = "select u from UserLog u ";

        if (!(cpf == null || cpf.isEmpty())) {
            where = true;
            strSql += " where u.userlogin.cpf = :cpf ";
        }

        if(!(dI == null || dI.isEmpty())) {
            if (!(where)) {
                strSql += " where ";
                where = true;
            } else {
                strSql += " and ";
            }
            strSql += " u.dataHora >= :dI ";
        }
        if (!(dF == null || dF.isEmpty())) {
            if (!(where)) {
                strSql += " where ";
                where = true;
            } else {
                strSql += " and ";
            }
            strSql += " u.dataHora <= :dF ";
        }

        //System.out.println(strSql);
        //System.out.println("Di: " + dI);
        //System.out.println("Df: " + dF);
        
        Query q = em.createQuery(strSql);

        if (!(cpf == null || cpf.isEmpty())) {
            q.setParameter("cpf", cpf);
        }
        if (!(dI == null || dI.isEmpty())) {
            q.setParameter("dI", d1);
        }
        if (!(dF == null || dF.isEmpty())) {
            q.setParameter("dF", d2);
        }
        return q.getResultList();
    }
    
    public List<UserLog> getDataHoraUltimoLog(Integer idUsuario){
        Query q = em.createQuery("SELECT ul.dataHora FROM UserLog ul WHERE ul.userlogin.id =:idUsuario ORDER BY ul.dataHora DESC").setMaxResults(1);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }

}
