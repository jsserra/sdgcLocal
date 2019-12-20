package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.CargoGeral;
import com.pmm.sdgc.model.Escala;
import com.pmm.sdgc.model.EscalaTipo;
import com.pmm.sdgc.model.Especialidade;
import com.pmm.sdgc.model.EspecialidadeTipo;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.Solicitacao;
import com.pmm.sdgc.model.SolicitacaoSub;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.UserMenu;
import com.pmm.sdgc.model.UsuarioAcesso;
import com.pmm.sdgc.ws.model.ModelBuscaServidorWs;
import com.pmm.sdgc.ws.model.ModelEscalaWs;
import com.pmm.sdgc.ws.model.ModelEspecialidadeWs;
import com.pmm.sdgc.ws.model.ModelLotacaoWs;
import com.pmm.sdgc.ws.model.ModelPodeAlterarRegime;
import com.pmm.sdgc.ws.model.ModelSetorWs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class FuncionalDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserPermissaoAcessoDao daoUserPermissaoAcesso;

    public List<Funcional> getFuncionais() {
        Query q = em.createQuery("select f from Funcional f order by trim(f.matricula)");
        return q.setMaxResults(10).getResultList();
    }

    public Funcional getUmFuncionalPorMatricula(String matricula) throws Exception {
        Query q = em.createQuery("select f from Funcional f where f.matricula like :matricula");
        q.setParameter("matricula", "%" + matricula);
        List<Funcional> l = q.getResultList();

        if (l.isEmpty()) {
            throw new Exception("Funcionario com a matricula " + matricula + " não encontrado");
        }

        if (l.size() > 1) {
            return null;
        }

        return l.get(0);
    }

    public Funcional getUmFuncionalPorIdFuncional(String id) throws Exception {
        Query q = em.createQuery("select f from Funcional f where f.id like :id");
        q.setParameter("id", id);
        List<Funcional> l = q.getResultList();

        if (l.isEmpty()) {
            throw new Exception("Funcionario com a id " + id + " não encontrado");
        }

        if (l.size() > 1) {
            return null;
        }

        return l.get(0);
    }

    @EJB
    UserMenuDao daoUserMenu;
    
    public List<ModelBuscaServidorWs> getFuncionalMult(String nome, String matricula, String cpf, String chave) {

        //Declaracoes para nivl de acesso de busca de inativos
        List<UserMenu> listaUsermenu = daoUserMenu.getUserInativo(chave);
        
        
        
        String strSQL = "select f from Funcional f where ";
        boolean tAnd = false;
        if (!(nome.isEmpty())) {
            strSQL += "f.pessoa.nome like :nome";
            tAnd = true;
        }
        if (!(matricula.isEmpty())) {
            if (tAnd == true) {
                strSQL += " and ";
            }
            strSQL += " f.matricula like :matricula";
            tAnd = true;
        }
        if (!(cpf.isEmpty())) {
            if (tAnd == true) {
                strSQL += " and ";
            }
            strSQL += " f.pessoa.cpf like :cpf";
            tAnd = true;
        }
        
        if ((listaUsermenu.isEmpty())) {
            
            if (tAnd == true) {
                strSQL += " and ";
            }
            strSQL += " f.situacao.nome like 'ATIVO'";
            
        }

        Query q = em.createQuery(strSQL);

        if (!(nome.isEmpty())) {
            q.setParameter("nome", "%" + nome.toUpperCase().replaceAll(" ", "%") + "%");
        }
        if (!(matricula.isEmpty())) {
            q.setParameter("matricula", "%" + matricula);
        }
        if (!(cpf.isEmpty())) {
            q.setParameter("cpf", cpf);
        }
        List<Funcional> funcionais = q.getResultList();

        //Busca as Lotacoes que o usuário pode acessar
        List<Lotacao> lotacoes = daoUserPermissaoAcesso.getListLotacaoUsuarioSemAdicao(chave);
        
        //Busca s Setores que o usuário pode acessar
        List<LotacaoSub> lotacoesSub = daoUserPermissaoAcesso.getListLotacaoSubUsuario(chave);
        
        //Busca os Cargo Gerais que o Usuario tem Acesso
        List<CargoGeral> cargosGeral = daoUserPermissaoAcesso.getListCargoGeralUsuario(chave);
        
        

        
        //se o cara tem acesso a toda prefeitura e todos os cargos
        if (lotacoes.isEmpty() && lotacoesSub.isEmpty() && cargosGeral.isEmpty()) {
            return ModelBuscaServidorWs.toModelBuscaServidorWs(funcionais);
        }
        
        Set<Funcional> funcionaisFinal = new HashSet();
        //se o cara tem acesso a toda prefeitura e somente um cargo
        if (lotacoes.isEmpty() && lotacoesSub.isEmpty() && !(cargosGeral.isEmpty())) {
            for (Funcional f : funcionais) {
                for (CargoGeral c : cargosGeral) {
                    Query q1 = em.createQuery("select f from Funcional f where f.id = :if and f.cargo.cargoGeral.id = :ic");
                    q1.setParameter("if", f.getId());
                    q1.setParameter("ic", c.getId());
                    if (!(q1.getResultList().isEmpty())) {
                        funcionaisFinal.add(f);
                    }
                }
            }
        }

        //se o cara tem acesso a toda secretaria e todos os cargos
        if (!(lotacoes.isEmpty()) && lotacoesSub.isEmpty()) {
            for (Funcional f : funcionais) {
                for (Lotacao l : lotacoes) {
                    Query q2 = em.createQuery("select s from Solicitacao s where s.funcional.id = :if and s.lotacao.id =:il and s.ativo = true");
                    q2.setParameter("if", f.getId());
                    q2.setParameter("il", l.getId());
                    if (!(q2.getResultList().isEmpty())) {
                        funcionaisFinal.add(f);
                    }
                }
            }
        }
        
        //se o cara tem acesso a toda secretaria e somente a alguns cargos
        if (!(lotacoes.isEmpty()) && lotacoesSub.isEmpty() && !(cargosGeral.isEmpty())) {
            Set<Funcional> funcionaisFinal2 = new HashSet();
            funcionaisFinal2 = funcionaisFinal;
            funcionaisFinal = null;
            funcionaisFinal = new HashSet();
            for (Funcional f : funcionaisFinal2) {
                for (CargoGeral c : cargosGeral) {
                    Query q1 = em.createQuery("select f from Funcional f where f.id = :if and f.cargo.cargoGeral.id = :ic");
                    q1.setParameter("if", f.getId());
                    q1.setParameter("ic", c.getId());
                    if (!(q1.getResultList().isEmpty())) {
                        funcionaisFinal.add(f);
                    }
                }
            }
        }
        
        //se o cara tem acesso somente ao setor e todos os cargos
        if (!(lotacoesSub.isEmpty())) {
            for (Funcional f : funcionais) {
                for (LotacaoSub s : lotacoesSub) {
                    Query q3 = em.createQuery("select ss from SolicitacaoSub ss where ss.funcional.id = :if and ss.lotacaoSub.id =:ils and ss.ativo = true");
                    q3.setParameter("if", f.getId());
                    q3.setParameter("ils", s.getId());
                    if (!(q3.getResultList().isEmpty())) {
                        funcionaisFinal.add(f);
                    }
                }
            }
        }
        
        //se o cara tem acesso a alguns setores e somente alguns cargos
        if (!(lotacoesSub.isEmpty()) && !(cargosGeral.isEmpty())) {
            Set<Funcional> funcionaisFinal2 = new HashSet();
            funcionaisFinal2 = funcionaisFinal;
            funcionaisFinal = null;
            funcionaisFinal = new HashSet();
            for (Funcional f : funcionaisFinal2) {
                for (CargoGeral c : cargosGeral) {
                    Query q1 = em.createQuery("select f from Funcional f where f.id = :if and f.cargo.cargoGeral.id = :ic");
                    q1.setParameter("if", f.getId());
                    q1.setParameter("ic", c.getId());
                    if (!(q1.getResultList().isEmpty())) {
                        funcionaisFinal.add(f);
                    }
                }
            }
        }

        return new ArrayList(ModelBuscaServidorWs.toModelBuscaServidorWs(funcionaisFinal));
    }

    @EJB
    LotacaoDao daoLotacao;

    @EJB
    SolicitacaoDao daoSolicitacao;

    @EJB
    UserLoginDao daoUserLogin;

    public void alterarLotacao(List<ModelLotacaoWs> lotacoesWs, String chaveUsuario) throws Exception {
        Funcional funcional = null;

        //Busca o usuário que está realizando essa operação
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);

        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }

        //O problema se resolvera em dois loops. No primeiro ira buscar entre os ativos. Se nao encontrar, cria um novo registro em solicitacao
        //No segundo, busca do banco os ativos e torna false os que nao vieram na lista pelo webservice
        //Loop 1 - Vendo quais lotacoes devem ser incluidas
        for (ModelLotacaoWs lot : lotacoesWs) {
            //Busca o funcional. Como o id do funcional se repetira por todos os valores vindos, basta buscar uma vez
            //Por isso coloquei esse teste de null
            if (funcional == null) {
                funcional = getUmFuncionalPorIdFuncional(lot.getIdFuncional());
            }

            //Busc a solicitaco com ativo = true da lotacao e do funcional em questao
            Query q = em.createQuery("select s from Solicitacao s where s.funcional.id = :idFunc and s.lotacao.id = :idLot and s.ativo = true");
            q.setParameter("idFunc", lot.getIdFuncional());
            q.setParameter("idLot", lot.getIdLotacao());

            List<Solicitacao> solicitacoes = q.getResultList();

            //Caso nao encontre, é nova então deve se realizar a inclusão
            if (solicitacoes.isEmpty()) {
                Lotacao lotacao = daoLotacao.getLotacaoPorId(lot.getIdLotacao());

                daoSolicitacao.incluirSolicitacao(funcional, lotacao, ul);

            }

        }

        //Loop 2 - Transformar em false quem não está na lista
        List<Solicitacao> solicitacoes = daoSolicitacao.getSolicitacaoAtiva(funcional.getId());

        for (Solicitacao s : solicitacoes) {
            boolean encontrou = false;
            for (ModelLotacaoWs l : lotacoesWs) {
                if (s.getLotacao().getId().equals(l.getIdLotacao())) {
                    encontrou = true;
                }
            }
            if (!encontrou) {
                s.setAtivo(Boolean.FALSE);
                daoSolicitacao.alterar(s);
            }
        }

    }

    @EJB
    LotacaoSubDao daoLotacaoSub;
    @EJB
    SolicitacaoSubDao daoSolicitacaoSub;

    public void alterarSetor(List<ModelSetorWs> setoresWs, String chaveUsuario) throws Exception {
        Funcional funcional = null;

        //Busca o usuário que está realizando essa operação
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);

        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }

        //O problema se resolvera em dois loops. No primeiro ira buscar entre os ativos. Se nao encontrar, cria um novo registro em solicitacaosub
        //No segundo, busca do banco os ativos e torna false os que nao vieram na lista pelo webservice
        //Loop 1 - Vendo quais lotacoes devem ser incluidas
        for (ModelSetorWs set : setoresWs) {
            //Busca o funcional. Como o id do funcional se repetira por todos os valores vindos, basta buscar uma vez
            //Por isso coloquei esse teste de null
            if (funcional == null) {
                funcional = getUmFuncionalPorIdFuncional(set.getIdFuncional());

                if (funcional == null) {
                    throw new Exception("Funcional com o id " + set.getIdFuncional() + " não encontrado");
                }
            }

            //Busc a solicitacosub com ativo = true da lotacao e do funcional em questao
            Query q = em.createQuery("select s from SolicitacaoSub s where s.funcional.id = :idFunc and s.lotacaoSub.id = :idSet and s.ativo = true");
            q.setParameter("idFunc", set.getIdFuncional());
            q.setParameter("idSet", set.getIdSetor());

            List<SolicitacaoSub> solicitacoesSub = q.getResultList();

            //Caso nao encontre, é nova então deve se realizar a inclusão
            if (solicitacoesSub.isEmpty()) {
                LotacaoSub lotacaoSub = daoLotacaoSub.getLotacaoSubPorId(set.getIdSetor());

                if (lotacaoSub == null) {
                    throw new Exception("Setor de id " + set.getIdSetor() + " não encontrada");
                }
                daoLotacaoSub.incluirLotacaoSub(funcional, lotacaoSub, ul);

            }

        }

        //Loop 2 - Transformar em false quem não está na lista
        List<SolicitacaoSub> solicitacoesSub = daoSolicitacaoSub.getSolicitacaoSubPorIdAtivo(funcional.getId());

        for (SolicitacaoSub s : solicitacoesSub) {
            boolean encontrou = false;
            for (ModelSetorWs sw : setoresWs) {
                if (s.getLotacaoSub().getId().equals(sw.getIdSetor())) {
                    encontrou = true;
                }
            }
            if (!encontrou) {
                s.setAtivo(Boolean.FALSE);
                daoSolicitacaoSub.alterar(s);
            }
        }

    }

    @EJB
    EspecialidadeTipoDao daoEspecialidadeTipo;

    @EJB
    EspecialidadeDao daoEspecialidade;

    public void alterarEspecialidade(List<ModelEspecialidadeWs> especialidadesWs, String chaveUsuario) throws Exception {
        Funcional funcional = null;

        //Busca o usuário que está realizando essa operação
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);

        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }

        //O problema se resolvera em dois loops. No primeiro ira buscar entre os ativos. Se nao encontrar, cria um novo registro em solicitacaosub
        //No segundo, busca do banco os ativos e torna false os que nao vieram na lista pelo webservice
        //Loop 1 - Vendo quais lotacoes devem ser incluidas
        for (ModelEspecialidadeWs mesp : especialidadesWs) {
            //Busca o funcional. Como o id do funcional se repetira por todos os valores vindos, basta buscar uma vez
            //Por isso coloquei esse teste de null
            if (funcional == null) {
                funcional = getUmFuncionalPorIdFuncional(mesp.getIdFuncional());

                if (funcional == null) {
                    throw new Exception("Funcional com o id " + mesp.getIdFuncional() + " não encontrado");
                }
            }

            //Busc a solicitacosub com ativo = true da lotacao e do funcional em questao
            Query q = em.createQuery("select e from Especialidade e where e.funcional.id = :idFunc and e.especialidadeTipo.id = :idEsp and e.ativo = true");
            q.setParameter("idFunc", mesp.getIdFuncional());
            q.setParameter("idEsp", mesp.getIdEspecialidade());

            List<Especialidade> especialidades = q.getResultList();

            //Caso nao encontre, é nova então deve se realizar a inclusão
            if (especialidades.isEmpty()) {

                if (!(mesp.getIdEspecialidade().equals(0))) {
                    EspecialidadeTipo especialidadeTipo = daoEspecialidadeTipo.getEspecialidadeTipoPorId(mesp.getIdEspecialidade());

                    if (especialidadeTipo == null) {
                        throw new Exception("Especialidade de id " + mesp.getIdEspecialidade() + " não encontrada");
                    }
                    daoEspecialidade.incluirEspecialidade(funcional, especialidadeTipo, ul);
                }
            }

        }

        //Loop 2 - Transformar em false quem não está na lista
        List<Especialidade> especialidades = daoEspecialidade.getEspecialidadePorIdFuncional(funcional.getId());

        for (Especialidade e : especialidades) {
            boolean encontrou = false;
            for (ModelEspecialidadeWs mw : especialidadesWs) {
                if (e.getEspecialidadeTipo().getId().equals(mw.getIdEspecialidade())) {
                    encontrou = true;
                }
            }
            if (!encontrou) {
                e.setAtivo(Boolean.FALSE);
                daoEspecialidade.alterar(e);
            }
        }

    }

    @EJB
    EscalaTipoDao daoEscalaTipo;
    @EJB
    EscalaDao daoEscala;
    @EJB
    EscalaIncompativelDao daoEscalaIncompativel;

    public void alterarRegime(List<ModelEscalaWs> escalasWs, String chaveUsuario) throws Exception {
        Funcional funcional = null;

        //Busca o usuário que está realizando essa operação
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);

        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }

        //O problema se resolvera em dois loops. No primeiro ira buscar entre os ativos. Se nao encontrar, cria um novo registro em solicitacaosub
        //No segundo, busca do banco os ativos e torna false os que nao vieram na lista pelo webservice
        //Loop 1 - Vendo quais lotacoes devem ser incluidas
        for (ModelEscalaWs mesc : escalasWs) {
            //Busca o funcional. Como o id do funcional se repetira por todos os valores vindos, basta buscar uma vez
            //Por isso coloquei esse teste de null
            if (funcional == null) {
                funcional = getUmFuncionalPorIdFuncional(mesc.getIdFuncional());

                if (funcional == null) {
                    throw new Exception("Funcional com o id " + mesc.getIdFuncional() + " não encontrado");
                }
            }

            //Busc a escalatipo com ativo = true da escala e do funcional em questao
            Query q = em.createQuery("select e from Escala e where e.funcional.id = :idFunc and e.escalaTipo.id = :idEsc and e.ativo = true");
            q.setParameter("idFunc", mesc.getIdFuncional());
            q.setParameter("idEsc", mesc.getIdRegime());

            List<Escala> escalas = q.getResultList();

            //Caso nao encontre, é nova então deve se realizar a inclusão
            //System.out.println("ID do Regime: " + mesc.getIdRegime());
            if (escalas.isEmpty()) {

                if (!(mesc.getIdRegime().equals("0"))) {
                    EscalaTipo escalaTipo = daoEscalaTipo.getEscalaTipoPorIdEscala(mesc.getIdRegime());

                    //EscalaTipo escalaTipo = daoEscalaTipo.getEscalaTipoPorId(mesc.getIdEscala());
                    //EspecialidadeTipo especialidadeTipo = daoEspecialidadeTipo.getEspecialidadeTipoPorId(mesp.getIdEspecialidade());
                    if (escalaTipo == null) {
                        throw new Exception("Escala de id " + mesc.getIdRegime() + " não encontrada");
                    }
                    daoEscala.incluirEscala(funcional, escalaTipo, ul);
                }
            }

        }

        //Loop 2 - Transformar em false quem não está na lista
        List<Escala> escalas = daoEscala.getEscalaPorIdFuncional(funcional.getId());

        for (Escala e : escalas) {
            boolean encontrou = false;
            for (ModelEscalaWs mw : escalasWs) {
                if (e.getEscalaTipo().getId().equals(mw.getIdRegime())) {
                    encontrou = true;
                }
            }
            if (!encontrou) {
                e.setAtivo(Boolean.FALSE);
                daoEscala.alterar(e);
            }
        }

    }
    
    private List<Funcional> getFuncionalPorCpf(String cpf){
        Query q = em.createQuery("select f from Funcional f where f.pessoa.cpf = :cpf");
        q.setParameter("cpf", cpf);
        
        return q.getResultList();
        
    }
    
    public ModelPodeAlterarRegime podeAlterarRegime(String idEscalaTipo, String idFuncional){
        
        Query q = em.createQuery("select f from Funcional f where f.id = :idFuncional");
        q.setParameter("idFuncional", idFuncional);
        
        List<Funcional> f =  q.getResultList();
        
        List<Funcional> listFuncional = getFuncionalPorCpf(f.get(0).getPessoa().getCpf());
        
        
        if(listFuncional.size()<=1){
            return new ModelPodeAlterarRegime(Boolean.TRUE);
        }
        
        Funcional fAux = new Funcional();
        
        for(Funcional func : listFuncional){
            if(f.get(0).getId()!=func.getId()){
                fAux = func;
            }
        } 
        
        //System.out.println("Funcional passado: " + f.get(0));
        //System.out.println("Outro funcional encontrado: " + fAux);
        
        Query q2 = em.createQuery("SELECT e FROM Escala e WHERE e.funcional.id = :id AND e.ativo = true");
        String id = fAux.getId();
        q2.setParameter("id", id);
        
        //System.out.println(q2.getResultList());
        
        List<Escala> er = q2.getResultList();
        
        String escalaTipo_1 = er.get(0).getEscalaTipo().getId();
        
        String escalaTipo_2 = idEscalaTipo;
        
        
        Boolean podeIncluir;
        
        podeIncluir = daoEscalaIncompativel.getComparaEscalaIncompativelPorIdEscalaTipo(escalaTipo_1, escalaTipo_2);
        
        return new ModelPodeAlterarRegime(podeIncluir);
    }

}
