package com.pmm.sdgc.dao;

import com.pmm.sdgc.enumeration.VariaveisStatus;
import com.pmm.sdgc.model.DataFrequencia;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.SolicitacaoSub;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.Variaveis;
import com.pmm.sdgc.model.VariaveisDesc;
import com.pmm.sdgc.model.VariaveisLotacaoSub;
import com.pmm.sdgc.model.VariaveisPossivelFuncional;
import com.pmm.sdgc.model.VariaveisPossivelLotacaoSub;
import com.pmm.sdgc.ws.model.ModelIdFuncionalWs;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.mapping.Collection;

/**
 *
 * @author dreges
 */
@Stateless
public class VariaveisDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    FuncionalDao daoFuncional;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    DataFrequenciaDao daoDataFreq;

    @EJB
    LotacaoSubDao daoLotacaoSubDao;

    @EJB
    SolicitacaoSubDao daoSolicitacaoSub;

    @EJB
    VariaveisLotacaoSubDao daoVariaveisLotacaoSub;

    @EJB
    VariaveisPossivelFuncionalDao daoVariaveisPossivelFuncional;

    @EJB
    VariaveisPossivelLotacaoSubDao daoVariaveisPossivelLotacaoSubDao;

    @EJB
    VariaveisDescDao daoVariaveisDes;

    public List<Variaveis> getListaVariaveisAtivas() {

        Query q = em.createQuery("select v from Variaveis v where v.ativo = true");
        return q.getResultList();
    }

    public Variaveis getVariaveisPorId(Integer id) {

        Query q = em.createQuery("select v from Variaveis v where v.id = :id and v.ativo = true");
        q.setParameter("id", id);

        List<Variaveis> variaveis = q.getResultList();
        if (variaveis.isEmpty()) {
            return null;
        }

        return variaveis.get(0);
    }

    public List<Variaveis> getVariaveisListaLancamentos(String idHistFunc) throws ParseException {
        //falta pegar a mes/ano da folha 10/2019

        //codigo temporario
        DateFormat f = DateFormat.getDateInstance();
        Date dataFolha = f.parse("2019-10-01");
        //fim codigo temporario

        Query q = em.createQuery("select v from Variaveis v where v.funcional.id = :idHistFunc and v.periodoFolha = :dataFolha");
        q.setParameter("idHistFunc", idHistFunc);
        q.setParameter("dataFolha", dataFolha);
        return q.getResultList();
    }

    public List<Funcional> getFuncionaisDeVariaveis(Integer idVariavelDesc) {

        VariaveisDesc vd = em.find(VariaveisDesc.class, daoVariaveisDes.getVariavelDesc(idVariavelDesc).getId());
        System.out.println("variaves:" + vd.getNome());
        Query q = em.createQuery("select v from Variaveis v where v.fechado = 0 and v.variaveisDesc like :vDesc");
        q.setParameter("vDesc", vd);

        List<Variaveis> variaveis = q.getResultList();

        List<Funcional> funcional = new ArrayList<>();

        for (Variaveis v : variaveis) {
            funcional.add(v.getFuncional());
        }

        return funcional;
    }

    public List<VariaveisDesc> getVariaveisListaDispHistFunc(String idHistFunc) throws Exception {

        List<VariaveisDesc> vd = new ArrayList<>();

        List<VariaveisPossivelFuncional> variaveisPossivel = daoVariaveisPossivelFuncional.getListaVariaveisPossivelFuncionalPorId(idHistFunc);
        if(variaveisPossivel.isEmpty()){
            throw new Exception("Não existe varável para Funcional" + idHistFunc);
        }
        for (VariaveisPossivelFuncional vpf : variaveisPossivel) {
            vd.add(vpf.getVariaveisDesc());
        }               

        List<LotacaoSub> lotacaoSub = daoSolicitacaoSub.getLotacaoSubPorId(idHistFunc);
        List<VariaveisPossivelLotacaoSub> variaveisPossivelLotacaoSub = daoVariaveisPossivelLotacaoSubDao.getListVariaveisPossivelLotacaoSub(lotacaoSub.get(0).getId());

        List<VariaveisDesc> vdesc = new ArrayList<>();
        for (VariaveisPossivelLotacaoSub vps : variaveisPossivelLotacaoSub) {
            vdesc.add(vps.getVariaveisDesc());
        }

        vd.retainAll(vdesc);
        List<VariaveisDesc> varPossivel = new ArrayList<>();
        for (VariaveisLotacaoSub varLotSub : daoVariaveisLotacaoSub.getListaVariaveisLotacaoSubPorId(lotacaoSub.get(0).getId())) {
            if ((varLotSub.getPeriodoFolha().equals(daoDataFreq.getDataFrequencia().get(0).getDataFrequencia())) && (varLotSub.getFechado() != Boolean.TRUE)) {
                varPossivel.add(varLotSub.getVariaveisDesc());
            }

        }
        varPossivel.retainAll(vd);
        //interseção
        return varPossivel;
    }

    public boolean getVariaveisVerficarDublicada(String idHistFunc, Integer idVariaveisDesc) throws Exception {

        //codigo temporario
        DateFormat f = DateFormat.getDateInstance();
        Date dataFolha = f.parse("2019-10-01");
        //fim codigo temporario        

        Query q = em.createQuery("select v from Variaveis v where v.funcional.id = :idHistFunc and v.periodoFolha = :dataFolha and v.variaveisDesc.id = :idVariaveisDesc");
        q.setParameter("idHistFunc", idHistFunc);
        q.setParameter("dataFolha", dataFolha);
        q.setParameter("idVariaveisDesc", idVariaveisDesc);
        List<Variaveis> variaveis = q.getResultList();

        if (!variaveis.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void postVariaveisExcluir(Integer idVariavel) throws Exception {
        Query q = em.createQuery("select o from Variaveis v where v.id = :idVariavel and v.ativo = true");
        q.setParameter("idVariavel", idVariavel);
        List<Variaveis> variaveis = q.getResultList();

        if (variaveis.isEmpty()) {
            throw new Exception("Lançamento não encontrada");
        }

        Variaveis variavel = variaveis.get(0);
        //em.remove(ocorrencias.get(0));
        variavel.setAtivo(Boolean.FALSE);
        em.merge(variavel);
    }

    public void postAprovarVariaveisHistFuncId(String idFuncional, String chaveUsuario) throws Exception {

        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        Funcional funcional = daoFuncional.getUmFuncionalPorIdFuncional(idFuncional);

        List<Variaveis> v = getListaVariaveisAtivas();

        List<Variaveis> variaveis = v.stream().filter(x -> x.getFuncional().equals(funcional)).collect(Collectors.toList());

        if (!(variaveis.isEmpty())) {
            for (Variaveis var : variaveis) {
                if (var.getStatus() == (VariaveisStatus.Analizando)) {
                    var.setStatus(VariaveisStatus.Aprovado);
                    em.merge(var);
                }
            }
        } else {
            throw new Exception("Não existe váriavel lançada para esse servidor!");

        }
    }

    public void postReprovarVariaveisHistFuncId(String idFuncional, String chaveUsuario) throws Exception {

        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        Funcional funcional = daoFuncional.getUmFuncionalPorIdFuncional(idFuncional);

        List<Variaveis> v = getListaVariaveisAtivas();

        List<Variaveis> variaveis = v.stream().filter(x -> x.getFuncional().equals(funcional)).collect(Collectors.toList());
        System.out.println("variaveis 0078840000 = " + variaveis.size());
        if (variaveis.size() > 0) {
            for (Variaveis var : variaveis) {
                if (var.getStatus() == (VariaveisStatus.Analizando)) {
                    var.setStatus(VariaveisStatus.Negado);
                    em.merge(var);
                }
            }
        } else {
            throw new Exception("Não existe váriavel lançada para esse servidor!");

        }
    }

    public void postAprovarVariaveisSetorId(String idSetor, String chaveUsuario) throws Exception {

        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        LotacaoSub setor = daoLotacaoSubDao.getLotacaoSubPorId(idSetor);

        List<Variaveis> v = getListaVariaveisAtivas();

        List<Variaveis> variaveis = v.stream().filter(x -> x.getLotacaoSub().equals(setor)).collect(Collectors.toList());

        if (!(variaveis.isEmpty())) {
            for (Variaveis var : variaveis) {
                if (var.getStatus() == (VariaveisStatus.Analizando)) {
                    var.setStatus(VariaveisStatus.Aprovado);
                    em.merge(var);
                }
            }
        } else {
            throw new Exception("Não existe váriavel lançada para esse servidor!");

        }
    }

    public void postReprovarVariaveisSetorId(String idSetor, String chaveUsuario) throws Exception {

        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        LotacaoSub setor = daoLotacaoSubDao.getLotacaoSubPorId(idSetor);

        List<Variaveis> v = getListaVariaveisAtivas();

        List<Variaveis> variaveis = v.stream().filter(x -> x.getLotacaoSub().equals(setor)).collect(Collectors.toList());

        if (!(variaveis.isEmpty())) {
            for (Variaveis var : variaveis) {
                if (var.getStatus() == (VariaveisStatus.Analizando)) {
                    var.setStatus(VariaveisStatus.Negado);
                    em.merge(var);
                }
            }
        } else {
            throw new Exception("Não existe váriavel lançada para esse servidor!");

        }
    }

    //incluir ocorrencia
    //Entrada:  id_hist_func, id_variaveis_desc, id_lotacao_sub_, quantidade, valor, userlogin
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void postVariaveisIncluirEmLote(List<ModelIdFuncionalWs> idFuncional, Integer idVariaveisDesc, String idLotacaoSub, Double quantidade, Double valor, String chaveUsuario) throws Exception {

        //codigo temporario
        DateFormat f = DateFormat.getDateInstance();
        Date dataFolha = f.parse("2019-10-01");
        //fim codigo temporario

        LotacaoSub lotacaoSub = daoSolicitacaoSub.getLotacaoSubPorUmId(idLotacaoSub);
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        for (ModelIdFuncionalWs idFunc : idFuncional) {
            Funcional funcional = daoFuncional.getUmFuncionalPorIdFuncional(idFunc.getIdFunc());
            if (funcional == null) {
                throw new Exception("Funcional com o id " + idFunc + " não encontrado");
            }

            Variaveis variaveis = new Variaveis();

            variaveis.setAtivo(Boolean.TRUE);
            variaveis.setDataHora(LocalDateTime.now());
            variaveis.setFechado(Boolean.FALSE);
            variaveis.setFuncional(funcional);
            variaveis.setLogin(ul);
            variaveis.setLotacaoSub(lotacaoSub);
            //variaveis.setPeriodoFolha(dataFolha);
            variaveis.setQuantidade(quantidade);
            variaveis.setValor(valor);
            //variaveis.setStatus('Lancado');
            //variaveis.setVariaveisDesc();
            em.persist(variaveis);

        }
    }
}
