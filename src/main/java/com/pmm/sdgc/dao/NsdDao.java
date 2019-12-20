package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Nsd;
import com.pmm.sdgc.ws.model.ModelBuscaNSDWs;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ajuliano
 */
@Stateless
public class NsdDao {

    @PersistenceContext
    EntityManager em;


    public List<ModelBuscaNSDWs> getListNsd(String mes, String ano, String numeroNota, String secretaria, String chave) throws Exception {
        String strSql = "select distinct n.codigo, n.referencia, n.nomeOrigem, n.nomeDestino, n.data  from Nsd n ";
        
        
        Boolean where = false;
        Boolean ref = false;
        Boolean nr = false;
        Boolean secr = false;
        
        if (!(mes==null || mes.isEmpty())) {
            if (ano==null || ano.isEmpty()) {
                throw new Exception("Ano obrigatório quando o mês é enviado");
            }
            
            if (!where) {
                strSql+=" where ";
                where = true;
            }
            strSql += " n.referencia = :ref ";
            ref = true;
        }
        
        if (!(numeroNota==null || numeroNota.isEmpty())) {
            
            if (!where) {
                strSql+=" where ";
                where = true;
            } else {
                strSql+= " and ";
            }
            strSql += " n.codigo = :nr";
            nr = true;
        }

        if (!(secretaria==null || secretaria.isEmpty())) {
            
            if (!where) {
                strSql+=" where ";
                where = true;
            } else {
                strSql+= " and ";
            }
            strSql += " n.codDestino = :sec";
            secr = true;
        }
        
        Query q = em.createQuery(strSql);
        if (ref){ 
                String dataBusca = ano+"-"+mes+"-"+"01";
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dataBusca.trim(), format);
                q.setParameter("ref", localDate);
        }
        if (nr) q.setParameter("nr", numeroNota);
        //if (secr) q.setParameter("sec", "%"+secretaria.replaceAll(" ", "%")+"%");
        if (secr) q.setParameter("sec", secretaria);
        
        List<Nsd> listaNsd = q.getResultList();
        //return ModelBuscaNSDWs.toModelBuscaNSDWs(listaNsd);
        return q.getResultList();
        
    }
    
    public List<Nsd> getListReferenciaNsd (String d1, String d2){
        String data1 = "01/"+d1;
        String data2 = "01/"+d2;
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   
        LocalDate localDate1 = LocalDate.parse(data1.trim(), format);
        LocalDate localDate2 = LocalDate.parse(data2.trim(), format);
        Query q = em.createQuery("select distinct n.referencia from Nsd n where n.data between :d1 and :d2 order by n.referencia");
      
        q.setParameter("d1", localDate1);
        q.setParameter("d2", localDate2);
        
        return q.getResultList();
    }
}
