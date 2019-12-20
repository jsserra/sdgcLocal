/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.VariaveisDesc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ajuliano
 */
@Stateless
public class VariaveisDescDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    public List<VariaveisDesc> getListVariaveisDesc() {
        Query q = em.createQuery("from VariaveisDesc");
        return q.getResultList();

    }
    
    public VariaveisDesc getVariavelDesc(Integer idDesc){
        Query q = em.createQuery("select vd from VariaveisDesc vd where vd.id like :idDesc ");
        q.setParameter("idDesc", idDesc);
  
        List<VariaveisDesc> vd = q.getResultList();
        
        return vd.get(0);
    }

    public void incluirVariaveisDesc(String codigo, String nome, Integer qtd, Integer valor, Double max, Double min, String login) throws Exception {

        VariaveisDesc vd = new VariaveisDesc();
        
        UserLogin ul = daoUserLogin.getUserLoginPorChave(login);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }
        
        if(codigo == null){
            throw new Exception("Insira o código");
        }
        
        if(nome == null){
            throw new Exception("Digite o nome da váriavel");
        }
        
     /*   if((qtd == null && valor == null) || (qtd != null && valor != null)){
            throw new Exception("Insira a quantide ou valor");
        }*/
        
        vd.setCodigo(codigo);
        vd.setNome(nome);
        vd.setQuatidade(qtd);
        vd.setValor(valor);
        vd.setMaximo(max);
        vd.setMinimo(min);
        vd.setDataHora(LocalDateTime.now());
        vd.setAtivo(Boolean.TRUE);
        
        em.persist(vd);               
    }

}
