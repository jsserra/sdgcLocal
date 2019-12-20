/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Requerimento;
import com.pmm.sdgc.model.RequerimentoFuncional;
import com.pmm.sdgc.model.RequerimentoHistorico;
import com.pmm.sdgc.model.RequerimentoSolicitacao;
import com.pmm.sdgc.model.RequerimentoStatus;
import com.pmm.sdgc.model.UserLogin;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.ws.rs.PathParam;

/**
 *
 * @author ajuliano
 */
@Stateless
public class RequerimentoDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    RequerimentoStatusDao daoReqStatus;

    @EJB
    RequerimentoSolicitacaoDao daoReqSolicitacao;

    @EJB
    FuncionalDao daoFuncional;

    public List<Requerimento> getRequerimento() {
        Query q = em.createQuery("select r from Requerimento r");
        //q.setParameter("idfunc", idfunc);
        return q.getResultList();
    }

    public List<Requerimento> getRequerimentoPorId(Integer id) throws Exception {
        Query q = em.createQuery("select r from Requerimento r where r.id = :id");
        q.setParameter("id", id);
        List<Requerimento> r = q.getResultList();
        if (r.isEmpty()) {
            throw new Exception("Protocolo Inexistente.");
        } else {
            return r;
        }
    }

    public List<Requerimento> getRequerimentoPorProtocolo(String protocolo) {
        Query q = em.createQuery("select r from Requerimento r where r.protocolo = :protocolo");
        q.setParameter("protocolo", protocolo);
        return q.getResultList();
    }

    public List<RequerimentoHistorico> getRequerimentoHistorico() {
        Query q = em.createQuery("select rh from RequerimentoHistorico rh");
        return q.getResultList();
    }

    public List<Requerimento> getRequerimentoPorFuncional(String idFuncional) throws Exception {
        List<RequerimentoFuncional> reqFuncional = new ArrayList<>();
        Query q = em.createQuery("select rf from RequerimentoFuncional rf where rf.funcional.id like :idFuncional");
        q.setParameter("idFuncional", idFuncional);

        reqFuncional = q.getResultList();

        if (reqFuncional.isEmpty()) {
            throw new Exception("Requerimento para matricula " + idFuncional + " não encontrada.");
        }

        List<Requerimento> req = new ArrayList<>();

        for (Requerimento r : getRequerimento()) {
            for (RequerimentoFuncional rf : reqFuncional) {
                if (r.equals(rf.getRequerimento())) {
                    req.add(r);
                }
            }
        }

        List<Requerimento> reqq = new ArrayList<>();
        for (Requerimento r : req) {
            List<Requerimento> rprotocol = new ArrayList<>();
            for (RequerimentoHistorico rh : getRequerimentoHistorico()) {
                if (rh.getRequerimento().getProtocolo().equals(r.getProtocolo())) {

                    rprotocol.add(r);
                }
            }
            if (rprotocol.size() == 1) {
                reqq.add(r);
            }
            if (rprotocol.size() == 2) {
                reqq.add(r);
            }
        }

        return reqq;
    }

    public List<RequerimentoHistorico> getRequerimentoPorProtocoloStatus(String protocolo) throws Exception {

        List<Requerimento> req = getRequerimentoPorProtocolo(protocolo);
        if (req.isEmpty()) {
            throw new Exception("Protocolo inexistente!");
        }

        List<RequerimentoHistorico> reqh = getRequerimentoHistorico();
        List<RequerimentoHistorico> reqhh = new ArrayList<>();

        for (RequerimentoHistorico rh : reqh) {
            for (Requerimento r : req) {
                if (rh.getRequerimento().equals(r)) {
                    reqhh.add(rh);
                }

            }
        }

        return reqhh;

    }

    /*public void alterar(Solicitacao solicitacao){// throws Exception {
        em.merge(solicitacao);
    }*/
    public void incluirRequerimento(String idFuncional, String protocolo, Integer reqStatus, Integer ReqSolicitacao, String login) throws Exception {

        Funcional f = daoFuncional.getUmFuncionalPorIdFuncional(idFuncional);
        if (idFuncional == null) {
            throw new Exception("Funcional com o id " + idFuncional + " não encontrado");
        }

        UserLogin ul = daoUserLogin.getUserLoginPorChave(login);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        RequerimentoStatus rstatus = daoReqStatus.getRequerimentoStatus().get(0);

        RequerimentoSolicitacao rsolicitacao = daoReqSolicitacao.getRequerimentoSolicitacaoPorId(ReqSolicitacao);
        /*if (rsolicitacao == null) {
            throw new Exception("Selecione um tipo de solicitação para o requerimento!");
        }*/

        List<Requerimento> requerimentos = getRequerimento();
        for (Requerimento req : requerimentos) {
            if (req.getProtocolo().equals(protocolo)) {
                throw new Exception("Protocolo já existe!");
            }
        }

        Requerimento r = new Requerimento();

        r.setProtocolo(protocolo);
        r.setReqStatus(rstatus);
        r.setReqSolicitacao(rsolicitacao);
        r.setData(LocalDateTime.now());
        r.setLogin(ul);

        RequerimentoHistorico rh = new RequerimentoHistorico();
        rh.setRequerimento(r);
        rh.setReqStatus(rstatus);
        rh.setData(LocalDateTime.now());

        RequerimentoFuncional rf = new RequerimentoFuncional();
        rf.setRequerimento(r);
        rf.setFuncional(f);

        em.persist(r);
        em.persist(rh);
        em.persist(rf);

    }

    public void incluirRequerimentoHistorico(Integer req, Integer reqStatus) throws Exception {
        RequerimentoHistorico rh = new RequerimentoHistorico();

        if (reqStatus == null) {
            throw new Exception("Status vazio");
        }

        if (reqStatus != 1 && reqStatus != 2 && reqStatus != 4) {
            throw new Exception("Insira status válido");
        }

        RequerimentoStatus rstatus = daoReqStatus.getRequerimentoStatusPorId(reqStatus);

        Requerimento requerimento = getRequerimentoPorId(req).get(0);

        for (RequerimentoHistorico rhs : getRequerimentoHistorico()) {
            if ((rhs.getRequerimento().equals(requerimento)) && rhs.getReqStatus().equals(rstatus)) {
                throw new Exception("Status selecionado" + rhs.getReqStatus().getNome() + "já existe!");
            }
        }

        rh.setReqStatus(rstatus);
        rh.setRequerimento(requerimento);
        rh.setData(LocalDateTime.now());

        em.persist(rh);

    }

}
