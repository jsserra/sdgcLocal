/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.DataFrequencia;
import com.pmm.sdgc.model.Mensagem;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.UsermsnPreDefinida;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.Date;
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
public class DataFrequenciaDao {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    UserMsnPreDefinidaDao daoUserMsnPreDefinida;
    
    @EJB
    UserLoginDao daoUserLogin;
    
    @EJB
    MensagemDao daoMensagem;

    public List<DataFrequencia> getDataFrequencia() {
        Query q = em.createQuery("Select df from DataFrequencia df");// order by trim(df.id)");
        return q.getResultList();
    }

    public List<DataFrequencia> getDataFrequenciaData() {
        Query q = em.createQuery("Select df.dataFrequencia from DataFrequencia df order by trim(df.id)");
        return q.getResultList();
    }

    public void alterarPeriodoOcorrencia(String dataNova, String dataAgenda, String chave) throws Exception {
        
        //Adiciona Mensagem automaticamente
        UsermsnPreDefinida usmsnpd = daoUserMsnPreDefinida.getUmaUsermsnPreDefinidaReferencia("lancamentoFrequencia").get(0);
        UserLogin userLogin = daoUserLogin.getUserLoginPorChave(chave);
        usmsnpd.setUserLogin(userLogin);
        Mensagem msn = new Mensagem(); 
        msn.setTipo(usmsnpd.getTipo());  
        msn.setTitulo(usmsnpd.getTitulo()); 
        msn.setTexto(usmsnpd.getTexto() + " " + dataAgenda.split("-")[2]+"/"+dataAgenda.split("-")[1]+"/"+dataAgenda.split("-")[0] + " às 23:59");   
        msn.setAtivo(Boolean.TRUE);   
        msn.setDataHora(LocalDateTime.now());    
        msn.setUserLogin(userLogin);    
        daoMensagem.incluirMensagem(msn);
        
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d = LocalDate.parse(dataNova, formatter);
        LocalDate d1 = LocalDate.parse(dataAgenda, formatter);
       // LocalDate d1 = sdf.parse(dataAgenda);
        for (DataFrequencia data : getDataFrequencia()) {
            if (data.getId() == 0) {
                data.setDataFrequencia(d);
                em.merge(data);
            } else if (data.getId() == 90) {
                data.setDataFrequencia(d1);
                em.merge(data);

            }
        }
    }

    public void alterarPeriodoImpressao(String dataNova, String dataAgenda) throws Exception {

        List<DataFrequencia> data = getDataFrequencia();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d = LocalDate.parse(dataNova, formatter);
        LocalDate d1 = LocalDate.parse(dataAgenda, formatter);

        data.get(1).setDataFrequencia(d);
        data.get(3).setDataFrequencia(d1);
        em.merge(data.get(1));
        em.merge(data.get(3));
    }

    public void alterarPeriodoPlanejamento(String periodoPlan) throws Exception {

        List<DataFrequencia> data = getDataFrequencia();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d = LocalDate.parse(periodoPlan, formatter);

        data.get(2).setDataFrequencia(d);

        em.merge(data.get(2));
    }

    
    public DataFrequencia getDataFrequenciaAtual() {
        Query q = em.createQuery("select d from DataFrequencia d where d.id = 100");
        List<DataFrequencia> datas = q.getResultList();
        
        if (datas.isEmpty()) return null;
        
        return datas.get(0);
    }
    /* public void alterarPeriodoOcorrencia(String ) throws Exception {

        Calendar c = Calendar.getInstance();
        for (DataFrequencia data : getDataFrequencia()) {
            c.setTime(data.getDataFrequencia());
            int month = c.get(Calendar.MONTH);
            if(month == 12){
                c.add(Calendar.YEAR, 1);
                c.set(Calendar.MONTH, 1);               
               
            }else{
                c.add(Calendar.MONTH, 1);
            }
            data.setDataFrequencia((java.util.Date) c.getTime());
                
            em.merge(data);
            
        }

    }*/
}
