package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.DataFrequencia;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.Ocorrencia;
import com.pmm.sdgc.model.OcorrenciaDesc;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.ws.model.ModelIdFuncionalWs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ajuliano
 */
@Stateless
public class OcorrenciaDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    FuncionalDao daoFuncional;

    @EJB
    DataFrequenciaDao daoDataFrequencia;

    @EJB
    SolicitacaoSubDao daoSolicitacaoSub;

    @EJB
    OcorrenciaDescDao daoOcorrenciaDesc;

    @EJB
    UserPermissaoAcessoDao daoUserPermissaoAcesso;

    public List<Ocorrencia> getOcorrenciaPorId(String id) {

        DataFrequencia dataAtual = daoDataFrequencia.getDataFrequenciaAtual();
        String mesAno = dataAtual.getDataFrequencia().format(DateTimeFormatter.ofPattern("MM-yyyy"));

        //System.out.println("idFuncional: " + id);
        //System.out.println("mesAno: " + mesAno);
        Query q = em.createQuery("select o from Ocorrencia o where o.funcional.id = :id and o.mesAno = :mes and o.ativo = true");
        q.setParameter("id", id);
        q.setParameter("mes", mesAno);
        return q.getResultList();
    }

    public Ocorrencia getOcorrenciaPorIdOcorrencia(Integer id) {

        Query q = em.createQuery("select o from Ocorrencia o where o.id = :id and o.ativo = true");
        q.setParameter("id", id);

        List<Ocorrencia> ocorrencias = q.getResultList();
        if (ocorrencias.isEmpty()) {
            return null;
        }

        return ocorrencias.get(0);
    }

    public List<Ocorrencia> getOcorrenciaPorIdData(String id, String data1, String data2, String chave) {
        LocalDate ld1 = LocalDate.parse(data1);
        LocalDate ld2 = LocalDate.parse(data2);
        LocalDateTime d1 = LocalDateTime.of(ld1, LocalTime.of(0, 0));
        LocalDateTime d2 = LocalDateTime.of(ld2, LocalTime.of(23, 59));
        Query q = em.createQuery("select o from Ocorrencia o where o.funcional.id = :id and o.entrada between :d1 and :d2 and o.ativo = true");
        // Query q = em.createQuery("select o from Ocorrencia o where o.funcional.id = :id and o.dataHora between :d1 and :d2");
        q.setParameter("id", id);
        q.setParameter("d1", d1);
        q.setParameter("d2", d2);
        
        //Busca as Lotacoes que o usuário pode acessar
        List<Lotacao> lotacoes = daoUserPermissaoAcesso.getListLotacaoUsuarioSemAdicao(chave);

        //Busca s Setores que o usuário pode acessar
        List<LotacaoSub> lotacoesSub = daoUserPermissaoAcesso.getListLotacaoSubUsuario(chave);
        
        List<Ocorrencia> ocorrencias = q.getResultList();
        
        List<Ocorrencia> ocorrenciasFinal = new ArrayList<Ocorrencia>();
        
        if ((lotacoes.isEmpty()) && lotacoesSub.isEmpty()) {
            ocorrenciasFinal = ocorrencias;
        }else{
            if (!(lotacoes.isEmpty()) && lotacoesSub.isEmpty()) {
                for (Lotacao l : lotacoes) {
                    for (Ocorrencia o : ocorrencias) {
                        if(l.getId().equals(o.getLotacaoSub().getLotacao().getId())){
                            //System.out.println("l.getId(): " + l.getId() + "==" + o.getLotacaoSub().getLotacao().getId() + "o.getLotacaoSub().getLotacao().getId()");
                            ocorrenciasFinal.add(o);
                        }
                    }
                }
            }

            if (!(lotacoesSub.isEmpty())) {
                for (LotacaoSub s : lotacoesSub) {
                    for (Ocorrencia o : ocorrencias) {
                        if(s.getId().equals(o.getLotacaoSub().getId())){
                            //System.out.println("l.getId(): " + s.getId() + "==" + o.getLotacaoSub().getLotacao().getId() + "o.getLotacaoSub().getLotacao().getId()");
                            ocorrenciasFinal.add(o);
                        }
                    }
                }

            }
        }
        return ocorrenciasFinal;
    }

     public List<Ocorrencia> getOcorrenciaPorIdDataOco(String id, String idOcoDesc, String data1, String data2, String chave) {
        
        LocalDate ld1 = LocalDate.parse(data1);
        LocalDate ld2 = LocalDate.parse(data2);
        LocalDateTime d1 = LocalDateTime.of(ld1, LocalTime.of(0, 0));
        LocalDateTime d2 = LocalDateTime.of(ld2, LocalTime.of(23, 59));
        //Query q = em.createQuery("select o from Ocorrencia o where o.funcional.id = :id and o.ocorrenciaDesc.idOcorrencia = :idOcoDesc and o.dataHora between :d1 and :d2");
       
        Query q = em.createQuery("select o from Ocorrencia o where o.ativo = true and o.funcional.id = :id and o.ocorrenciaDesc.idOcorrencia = :idOcoDesc and o.entrada between :d1 and :d2");
        q.setParameter("id", id);
        q.setParameter("idOcoDesc", idOcoDesc);
        q.setParameter("d1", d1);
        q.setParameter("d2", d2);

        //Busca as Lotacoes que o usuário pode acessar
        List<Lotacao> lotacoes = daoUserPermissaoAcesso.getListLotacaoUsuarioSemAdicao(chave);

        //Busca s Setores que o usuário pode acessar
        List<LotacaoSub> lotacoesSub = daoUserPermissaoAcesso.getListLotacaoSubUsuario(chave);
        
        List<Ocorrencia> ocorrencias = q.getResultList();
        
        List<Ocorrencia> ocorrenciasFinal = new ArrayList<Ocorrencia>();
        
        if ((lotacoes.isEmpty()) && lotacoesSub.isEmpty()) {
            ocorrenciasFinal = ocorrencias;
        }else{
            if (!(lotacoes.isEmpty()) && lotacoesSub.isEmpty()) {
                for (Lotacao l : lotacoes) {
                    for (Ocorrencia o : ocorrencias) {
                        if(l.getId().equals(o.getLotacaoSub().getLotacao().getId())){
                            //System.out.println("l.getId(): " + l.getId() + "==" + o.getLotacaoSub().getLotacao().getId() + "o.getLotacaoSub().getLotacao().getId()");
                            ocorrenciasFinal.add(o);
                        }
                    }
                }
            }
            if (!(lotacoesSub.isEmpty())) {
                for (LotacaoSub s : lotacoesSub) {
                    for (Ocorrencia o : ocorrencias) {
                        if(s.getId().equals(o.getLotacaoSub().getId())){
                            //System.out.println("l.getId(): " + s.getId() + "==" + o.getLotacaoSub().getLotacao().getId() + "o.getLotacaoSub().getLotacao().getId()");
                            ocorrenciasFinal.add(o);
                        }
                    }
                }
            }
        }
        return ocorrenciasFinal;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void incluirOcorrencia(List<ModelIdFuncionalWs> idFuncional, String dataHoraEntrada, String dataHoraSaida, String idLotacaoSub, Integer numeroDias, String observacao, String idOcorrenciaDesc, String chaveUsuario) throws Exception {
        
        boolean umaData = false;
        

        
        
        if (dataHoraSaida==null) {
            umaData=true;
            dataHoraSaida = dataHoraEntrada;
        }
        
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        LocalDateTime entrada = LocalDateTime.parse(dataHoraEntrada.replaceAll(" ", ""), formater);
        LocalDateTime saida = LocalDateTime.parse(dataHoraSaida.replaceAll(" ", ""), formater);

        OcorrenciaDesc ocorDesc = daoOcorrenciaDesc.getOcorrenciaDescPorId(idOcorrenciaDesc);
        if (ocorDesc == null) {
            throw new Exception("Tipo de ocorrência não encontrado");
        }
        

        
        if (observacao.equals("")) {
            throw new Exception("A observação não pode estar em branco");
        }
        
        if (entrada.equals(saida) && umaData==false) {
            throw new Exception("Entrada igual a Saida");
        }

        if (saida.isBefore(entrada)) {
            throw new Exception("Entrada tem que ser anterior Saida");
        }

        Long horas = entrada.until(saida, ChronoUnit.HOURS);

        if (horas > 24) {
            throw new Exception("No máximo 24 horas de intervalo");
        }

        LotacaoSub lotacaoSub = daoSolicitacaoSub.getLotacaoSubPorUmId(idLotacaoSub);

        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }
        

        List<DataFrequencia> datasFrequencias = daoDataFrequencia.getDataFrequencia();
        if (datasFrequencias.isEmpty()) {
            throw new Exception("Data Frequencia não cadastrada");
        }
        DataFrequencia dataFrequencia = datasFrequencias.get(0);
        DateTimeFormatter formatoDataFrequencia = DateTimeFormatter.ofPattern("MM-yyyy");

        for (ModelIdFuncionalWs idFunc : idFuncional) {
            Funcional funcional = daoFuncional.getUmFuncionalPorIdFuncional(idFunc.getIdFunc());
            if (funcional == null) {
                throw new Exception("Funcional com o id " + idFunc + " não encontrado");
            }

            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setAtivo(Boolean.TRUE);
            ocorrencia.setControle("2");
            ocorrencia.setDataHora(LocalDateTime.now());

            if (ocorDesc.getDefinirDias() != 1) {
                ocorrencia.setNumeroDias(0);
            } else {
                    if (numeroDias == 0) {
                        throw new Exception("Número de dias deve ser definido");
          }
                        ocorrencia.setNumeroDias(numeroDias);
              
            }

            ocorrencia.setObs(observacao);
            ocorrencia.setEntrada(entrada);
            ocorrencia.setSaida(saida);
            ocorrencia.setLotacaoSub(lotacaoSub);
            ocorrencia.setOcorrenciaDesc(ocorDesc);
            ocorrencia.setUserLogin(ul);

            DataFrequencia dataAtual = daoDataFrequencia.getDataFrequenciaAtual();
            String mesAno = dataAtual.getDataFrequencia().format(DateTimeFormatter.ofPattern("MM-yyyy"));

            ocorrencia.setMesAno(mesAno);

            ocorrencia.setFuncional(funcional);
            em.persist(ocorrencia);

        }
    }

    public void excluirOcorrencia(Long idOcorrencia) throws Exception {
        Query q = em.createQuery("select o from Ocorrencia o where o.id = :id and o.ativo = true");
        q.setParameter("id", idOcorrencia.intValue());
        List<Ocorrencia> ocorrencias = q.getResultList();
        
        if (ocorrencias.isEmpty()) {
            throw new Exception("Ocorrencia não encontrada");
        }

        //em.remove(ocorrencias.get(0));
        Ocorrencia ocorrencia = ocorrencias.get(0);

        //ocorrencia.setAtivo(Boolean.FALSE);
        //em.merge(ocorrencia);
        em.remove(ocorrencia);
    }

}
