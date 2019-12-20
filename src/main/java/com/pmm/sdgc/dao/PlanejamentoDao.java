package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.Planejamento;
import com.pmm.sdgc.model.PlanejamentoAuxiliar;
import com.pmm.sdgc.model.PlanejamentoDescricao;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.ws.model.ModelPlanejamentoWs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dreges
 */
@Stateless
public class PlanejamentoDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    FuncionalDao daoFuncional;

    @EJB
    LotacaoSubDao daoLotacaosub;

    @EJB
    UserLoginDao daoUserLogin;
    
    @EJB
    UserPermissaoAcessoDao daoUserPermissaoAcesso;

    public List<PlanejamentoDescricao> buscarListaPlanejamentoDescricao() {
        Query q = em.createQuery("select p from PlanejamentoDescricao p order by p.nome");
        return q.getResultList();
    }

    public PlanejamentoDescricao buscarPlanejamentoDescricaoPorId(Long idTipoPlanejamento) {
        Query q = em.createQuery("select p from PlanejamentoDescricao p where p.id = :id");
        q.setParameter("id", idTipoPlanejamento);
        List<PlanejamentoDescricao> plans = q.getResultList();
        if (plans.isEmpty()) {
            return null;
        }
        return plans.get(0);
    }

    public Planejamento buscarPlanejamentoFuncional(Funcional funcional, LotacaoSub lotacaoSub) {
        Query q = em.createQuery("select p from Planejamento p where p.funcional.id = :idFunc and p.lotacaoSub.id = :idLotacaoSub");
        q.setParameter("idFunc", funcional.getId());
        q.setParameter("idLotacaoSub", lotacaoSub.getId());

        List<Planejamento> plans = q.getResultList();
        if (plans.isEmpty()) {
            return null;
        }
        return plans.get(0);

    }

    public PlanejamentoAuxiliar buscarPlanejamentoAuxiliar(Planejamento planejamento, Integer diaSemana) {
        Query q = em.createQuery("select p from PlanejamentoAuxiliar p where p.planejamento.id = :idPlan and p.diaSemana = :dia");
        q.setParameter("idPlan", planejamento.getId());
        q.setParameter("dia", diaSemana);

        List<PlanejamentoAuxiliar> plans = q.getResultList();
        if (plans.isEmpty()) {
            return null;
        }
        return plans.get(0);
    }

    public Planejamento criarPlanejamento(Funcional funcional, LotacaoSub lotacaoSub, UserLogin userLogin) throws Exception {
        Planejamento planejamento = new Planejamento();
        planejamento.setDataHora(LocalDateTime.now());
        planejamento.setFuncional(funcional);
        planejamento.setLotacaoSub(lotacaoSub);
        planejamento.setUserLogin(userLogin);
        em.persist(planejamento);

        return planejamento;
    }

    public void revalidarPlanejamentoAuxiliar(PlanejamentoAuxiliar planejamentoAuxiliar) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-yyyy");
        LocalDate hoje = LocalDate.now();

        planejamentoAuxiliar.setMesAno(hoje.format(dtf));

        em.merge(planejamentoAuxiliar);
    }

    public void criarPlanejamentoAuxiliar(Long idTipoPlanejamento, String idFuncional, Integer diaSemana, String horaInicial, String horaFinal, String setor, Boolean feriado, Boolean pontoFacultativo, String chaveUsuario) throws Exception {
        UserLogin userLogin = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (userLogin == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }

        PlanejamentoDescricao planDesc = buscarPlanejamentoDescricaoPorId(idTipoPlanejamento);
        if (planDesc == null) {
            throw new Exception("Tipo de planejamento " + idTipoPlanejamento.toString() + " não encontrado");
        }

        Funcional funcional = daoFuncional.getUmFuncionalPorIdFuncional(idFuncional);
        if (funcional == null) {
            throw new Exception("Funcionário " + idFuncional + " não encontrado");
        }

        LotacaoSub lotacaoSub = daoLotacaosub.getLotacaoSubPorId(setor);
        if (lotacaoSub == null) {
            throw new Exception("Lotação Sub " + setor + " não encontrado");
        }

        Planejamento planejamento = buscarPlanejamentoFuncional(funcional, lotacaoSub);
        if (planejamento == null) {
            planejamento = criarPlanejamento(funcional, lotacaoSub, userLogin);
        }

        PlanejamentoAuxiliar planejamentoAuxiliar = buscarPlanejamentoAuxiliar(planejamento, diaSemana);
        Boolean alterando = true;

        if (planejamentoAuxiliar == null) {
            alterando = false;
            planejamentoAuxiliar = new PlanejamentoAuxiliar();
        }

        planejamentoAuxiliar.setDataHora(LocalDateTime.now());
        planejamentoAuxiliar.setDiaSemana(diaSemana);
        planejamentoAuxiliar.setFacultativo(pontoFacultativo);
        planejamentoAuxiliar.setFeriado(feriado);
        planejamentoAuxiliar.setPlanejamento(planejamento);
        planejamentoAuxiliar.setPlanejamentoDescricao(planDesc);
        planejamentoAuxiliar.setUserLogin(userLogin);

        if (horaInicial.length() < 5) {
            horaInicial = "0" + horaInicial;
        }
        if (horaFinal.length() < 5) {
            horaFinal = "0" + horaFinal;
        }
        DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");
        planejamentoAuxiliar.setEntrada(LocalTime.parse(horaInicial, dtfHora));
        planejamentoAuxiliar.setSaida(LocalTime.parse(horaFinal, dtfHora));

        if (planejamentoAuxiliar.getEntrada().equals(planejamentoAuxiliar.getSaida())) {
            throw new Exception("Hora de Entrada não pode ser igual a hora de Saída");
        }

        if (planejamentoAuxiliar.getSaida().isBefore(planejamentoAuxiliar.getEntrada())) {
            throw new Exception("Hora de Entrada está após a hora de saída");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-yyyy");
        LocalDate hoje = LocalDate.now();
        planejamentoAuxiliar.setMesAno(hoje.format(dtf));

        if (alterando == false) {
            em.persist(planejamentoAuxiliar);
        } else {
            em.merge(planejamentoAuxiliar);
        }
    }

    public List<ModelPlanejamentoWs> buscarPlanejamentos(String idFuncional,String chave) {

        LocalDate hoje = LocalDate.now();
        String mesAno = hoje.format(DateTimeFormatter.ofPattern("MM-yyyy"));
        
        Query q = em.createQuery("select pa from PlanejamentoAuxiliar pa where pa.planejamento.funcional.id = :idFunc and pa.mesAno = :mes order by pa.diaSemana");
        q.setParameter("idFunc", idFuncional);
        q.setParameter("mes", mesAno);
        //List<PlanejamentoAuxiliar> planejamentos = q.getResultList();

        DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");

        //Busca as Lotacoes que o usuário pode acessar
        List<Lotacao> lotacoes = daoUserPermissaoAcesso.getListLotacaoUsuarioSemAdicao(chave);
        
        //Busca s Setores que o usuário pode acessar
        List<LotacaoSub> lotacoesSub = daoUserPermissaoAcesso.getListLotacaoSubUsuario(chave);
        
        
        List<PlanejamentoAuxiliar> planejamentoAuxiliar = q.getResultList();
        
        List<PlanejamentoAuxiliar> planejamentoAuxiliarFinal = new ArrayList<PlanejamentoAuxiliar>();
        
        if ((lotacoes.isEmpty()) && lotacoesSub.isEmpty()) {
            
            planejamentoAuxiliarFinal = planejamentoAuxiliar;
        }else{
            
            if (!(lotacoes.isEmpty()) && lotacoesSub.isEmpty()) {
                
                for (Lotacao l : lotacoes) {
                    for (PlanejamentoAuxiliar pa : planejamentoAuxiliar) {
                        if(l.getId().equals(pa.getPlanejamento().getLotacaoSub().getLotacao().getId().trim())){
                            planejamentoAuxiliarFinal.add(pa);
                        }
                    }
                }
            }

            if (!(lotacoesSub.isEmpty())) {
                
                for (LotacaoSub s : lotacoesSub) {
                    for (PlanejamentoAuxiliar pa : planejamentoAuxiliar) {
                        if(s.getId().equals(pa.getPlanejamento().getLotacaoSub().getId().trim())){
                            planejamentoAuxiliarFinal.add(pa);
                        }
                    }
                }
            }
         }
        List<ModelPlanejamentoWs> plans = new ArrayList();
        for (PlanejamentoAuxiliar pa : planejamentoAuxiliarFinal) {
            ModelPlanejamentoWs mpa = new ModelPlanejamentoWs();
            mpa.setDiaSemana(pa.getDiaSemana());
            mpa.setFeriado(pa.getFeriado());
            mpa.setIdFunc(pa.getPlanejamento().getFuncional().getId());
            mpa.setIdTpPlan(pa.getPlanejamentoDescricao().getId());
            mpa.setPlanejamentoTipo(pa.getPlanejamentoDescricao().getNome());
            mpa.setPontoFacult(pa.getFacultativo());
            mpa.setSetorPlan(pa.getPlanejamento().getLotacaoSub().getId());
            mpa.setNomeSetor(pa.getPlanejamento().getLotacaoSub().getNome());
            mpa.sethFinal(pa.getSaida().format(dtfHora));
            mpa.sethInicial(pa.getEntrada().format(dtfHora));
            mpa.setIdPlanejamento(pa.getPlanejamento().getId());
            mpa.setIdPlanejamentoAuxiliar(pa.getId());
            plans.add(mpa);
        }
        return plans;

    }

    public void removerPlanejamento(Long idPlanejamento) throws Exception {
        Query q = em.createQuery("select p from Planejamento p where p.id = :id");
        q.setParameter("id", idPlanejamento);

        List<Planejamento> planejamentos = q.getResultList();

        if (planejamentos.isEmpty()) {
            throw new Exception("Planejamento não encontrado");
        }

        removerPlanejamentosAuxiliaresUmPlanejamento(idPlanejamento);
        em.remove(planejamentos.get(0));
    }

    public void removerPlanejamentoAuxiliar(Long idPlanejamentoAuxiliar) throws Exception {
        Query q = em.createQuery("select p from PlanejamentoAuxiliar p where p.id = :id");
        q.setParameter("id", idPlanejamentoAuxiliar);

        List<Planejamento> planejamentos = q.getResultList();

        if (planejamentos.isEmpty()) {
            throw new Exception("Planejamento Auxiliar não encontrado");
        }

        em.remove(planejamentos.get(0));
    }

    public void removerPlanejamentosAuxiliaresUmPlanejamento(Long idPlanejamento) {
        Query q = em.createQuery("select p from PlanejamentoAuxiliar p where p.planejamento.id = :id");
        q.setParameter("id", idPlanejamento);
        List<PlanejamentoAuxiliar> pa = q.getResultList();

        for (PlanejamentoAuxiliar p : pa) {
            em.remove(p);
        }

    }
}
