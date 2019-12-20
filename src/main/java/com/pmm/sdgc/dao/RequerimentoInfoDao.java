/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Pessoa;
import com.pmm.sdgc.model.RequerimentoInfo;
import com.pmm.sdgc.model.UserLogin;
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
public class RequerimentoInfoDao {

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

    @EJB
    PessoaDao daoPessoa;

    public List<RequerimentoInfo> getRequerimentoInfo() {
        Query q = em.createQuery("select r from RequerimentoInfo r");
        return q.getResultList();
    }
  
    public void incluirRequerimentoInfo(String idFuncional, String cep, Integer estado, Integer bairro, String end, String complemento, Integer celular, Integer telefone, String email, String login) throws Exception {

        UserLogin ul = daoUserLogin.getUserLoginPorChave(login);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        Funcional f = daoFuncional.getUmFuncionalPorIdFuncional(idFuncional);
        if (idFuncional == null) {
            throw new Exception("Funcional com o id " + idFuncional + " não encontrado");
        }

        List<Pessoa> pessoas = daoPessoa.getPessoa();
        Pessoa pessoaReq = new Pessoa();
        for (Pessoa p : pessoas) {
            if (p.getId().equals(f.getPessoa().getId())) {
                pessoaReq = p;
            }                            
        }             

        RequerimentoInfo ri = new RequerimentoInfo();

        ri.setPessoal(pessoaReq);
        ri.setCep(cep);
        ri.setEstado(estado);
        ri.setBairro(bairro);
        ri.setEndereco(end);
        ri.setComplemento(complemento);
        ri.setCelular(celular);
        ri.setTelefone(telefone);
        ri.setEmail(email);

        em.persist(ri);

    }

}
