/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.PlanejamentoAuxiliar;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jrdutra
 */
@Stateless
public class PlanejamentoAuxiliarDao {
    @PersistenceContext
    EntityManager em;
    
    @EJB
    LotacaoSubDao daoLotacaosub;
    
    @EJB
    PlanejamentoDao daoPlanejamento;
    
    
    public void revalidarPlanejamentoAuxiliar(String idLotacaoSub) {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-yyyy");
        LocalDateTime hoje = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        String mes_ano = hoje.format(formatter);
        Query q = em.createQuery("SELECT plaux FROM PlanejamentoAuxiliar plaux WHERE plaux.planejamento.lotacaoSub.id = :idLotacaoSub ");
        q.setParameter("idLotacaoSub", idLotacaoSub);
        List<PlanejamentoAuxiliar> listPlanejamentoAuxiliar = q.getResultList();
        for(PlanejamentoAuxiliar p : listPlanejamentoAuxiliar){
           p.setMesAno(mes_ano);  
           em.merge(p); 
        }
    }
    
}
