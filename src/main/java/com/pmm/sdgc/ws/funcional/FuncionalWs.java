/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.funcional;

import com.pmm.sdgc.dao.BiometriaCadastradaDao;
import com.pmm.sdgc.dao.EscalaDao;
import com.pmm.sdgc.dao.EscalaTipoDao;
import com.pmm.sdgc.dao.EspecialidadeDao;
import com.pmm.sdgc.dao.EspecialidadeTipoDao;
import com.pmm.sdgc.dao.FuncionalDao;
import com.pmm.sdgc.dao.LotacaoDao;
import com.pmm.sdgc.dao.LotacaoSubDao;
import com.pmm.sdgc.dao.MensagemDao;
import com.pmm.sdgc.dao.OcorrenciaDescDao;
import com.pmm.sdgc.dao.SolicitacaoDao;
import com.pmm.sdgc.dao.SolicitacaoSubDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.UserLoginDao;
import com.pmm.sdgc.dao.UserPermissaoAcessoDao;
import com.pmm.sdgc.model.BiometriaCadastrada;
import com.pmm.sdgc.model.EscalaTipo;
import com.pmm.sdgc.model.EspecialidadeTipo;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.SolicitacaoSub;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.UserPermissaoAcesso;
import com.pmm.sdgc.ws.model.ModelBiometriaCadastradaWs;
import com.pmm.sdgc.ws.model.ModelBuscaServidorWs;
import com.pmm.sdgc.ws.model.ModelEscalaWs;
import com.pmm.sdgc.ws.model.ModelEspecialidadeWs;
import com.pmm.sdgc.ws.model.ModelFuncionalWs;
import com.pmm.sdgc.ws.model.ModelSetorWs;
import com.pmm.sdgc.ws.model.ModelLotacaoWs;
import com.pmm.sdgc.ws.model.ModelOcorrenciaDescWs;
import com.pmm.sdgc.ws.model.ModelPodeAlterarRegime;
import com.pmm.sdgc.ws.model.ModelRegimeWs;
import com.pmm.sdgc.ws.model.ModelSolicitacaoSubWs;
import com.pmm.sdgc.ws.model.ModelUserTemplatePermissaoAcessoWs;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author acg
 */
@Named
@Path("funcionalws")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionalWs {

    @EJB
    FuncionalDao daoFuncional;
    @EJB
    EspecialidadeTipoDao daoEspecialidadeTipo;
    @EJB
    EspecialidadeDao daoEspecialidade;
    @EJB
    LotacaoDao daoLotacao;
    @EJB
    LotacaoSubDao daoLotacaoSub;
    @EJB
    EscalaDao daoEscala;
    @EJB
    EscalaTipoDao daoEscalaTipo;
    @EJB
    SolicitacaoDao daoSolicitacao;
    @EJB
    SolicitacaoSubDao daoSolicitacaoSub;
    @EJB
    OcorrenciaDescDao daoOcoDesc;
    @EJB
    MensagemDao daoMensagem;
    @EJB
    UserLogDao daoUserLog;
    @EJB
    UserPermissaoAcessoDao daoUserPermissaoAcesso;
    @EJB
    BiometriaCadastradaDao daoBiometriaCadastrada;

    @GET
    @Path("getListaFuncional")
    public List<Funcional> getListaFuncional(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaFuncional", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoFuncional.getFuncionais();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getUmFuncionalPorMatricula/{matricula}")
    public Funcional getUmFuncionalPorMatricula(@PathParam("matricula") String matricula, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("UmFuncionalPorMatricula", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            Funcional funcional = daoFuncional.getUmFuncionalPorMatricula(matricula);

            funcional.setPodeCriarUsuario(daoUserPermissaoAcesso.getUsuarioPodeCriarUsuario(headers.getRequestHeader("chave").get(0)));
            return funcional;
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }
    }

    @GET
    @Path("getUmFuncionalPorId/{id}")
    public ModelFuncionalWs getUmFuncionalPorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("UmFuncionalPorId", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());

            ModelFuncionalWs mfws = new ModelFuncionalWs();

            mfws.setId(id);

            mfws.setEspecialidades(getListaEspecialidadePorId(id, headers, request));

            mfws.setLotacoes(getListaLotacaoPorId(id, headers, request));

            mfws.setLotacoesSub(getListaSetorPorId(id, headers, request));

            mfws.setRegimes(getListaRegimePorId(id, headers, request));

            mfws.setOcorrenciaDesc(getListaOcorrenciaDesc(headers, request));

            mfws.setSetoresAtivos(getListaSetorAtivoPorId(id, headers, request));

            mfws.setTemUsuario(getListaFuncionalTemUsuarioPorId(id, headers, request));

            mfws.setPermissoes(getListaPermissaoAcesso(id, headers, request));
            
            //mfws.setBiometria(getBiometriaCadastrada(id, headers,request));
          

            return mfws;
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());

        }
    }
    
//    @GET
//    @Path("getBiometriaCadastrada/{id}")
//    public BiometriaCadastrada getBiometriaCadastrada(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request){
//        try {
//            daoUserLog.criarLog("GetBiometriaCadastrada", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
//            return daoBiometriaCadastrada.getBiometriaCadastrada(id);
//        } catch (Exception ex) {
//            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
//        }
//    }

    @EJB
    UserLoginDao daoUserLogin;

    @GET
    @Path("getListaFuncionalTemUsuarioPorId/{id}")
    public Boolean getListaFuncionalTemUsuarioPorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        Funcional funcional;
        try {
            daoUserLog.criarLog("ListaFuncionalTemUsuarioPorId", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            funcional = daoFuncional.getUmFuncionalPorIdFuncional(id);
            UserLogin ul = daoUserLogin.getUserLoginPorCPf(funcional.getPessoa().getCpf());
            if (ul == null) {
                return false;
            }
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }

        return true;

    }

    @GET
    @Path("getListaSetorAtivoPorIdLotacao/{id}")
    public List<ModelSetorWs> getListaSetorAtivoPorIdLotacao(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("ListaSetorAtivoPorIdLotacao", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<LotacaoSub> ss = daoLotacaoSub.getSetores(id);
            if (ss == null) {
                return null;
            }

            List<ModelSetorWs> lotacoesSub = ModelSetorWs.toModelLotacaoSubAtivoWs(ss);

            return lotacoesSub;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

    }

    @GET
    @Path("getListaFuncionalPorIdSetor/{id}")
    public List<ModelSolicitacaoSubWs> getListaFuncionalPorIdSetor(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {

            daoUserLog.criarLog("Lista Um Funcional", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<SolicitacaoSub> ss = daoSolicitacaoSub.getFuncionalPorIdSetor(id);
            if (ss == null) {
                return null;
            }

            List<ModelSolicitacaoSubWs> lotacoesSub = ModelSolicitacaoSubWs.toModelSolicitacaoSubFuncionalWs(ss);

            return lotacoesSub;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

    }

    @GET
    @Path("getListaEspecialidadePorId/{id}")
    public List<ModelEspecialidadeWs> getListaEspecialidadePorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        //Parte 1 - buscando as especialidades
        try {
            daoUserLog.criarLog("Lista Um Funcional", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<ModelEspecialidadeWs> especialidades = ModelEspecialidadeWs.toModelEspecialidadeWs(daoEspecialidadeTipo.getEspecialidadeTipo(id));
            List<EspecialidadeTipo> especialidadeAtivas = daoEspecialidade.getEspecialidadePorId(id);

            for (EspecialidadeTipo et : especialidadeAtivas) {
                for (ModelEspecialidadeWs mw : especialidades) {
                    if (mw.getIdEspecialidade().equals(et.getId())) {
                        mw.setAtivo(true);
                        break;
                    }
                }
            }

            return especialidades;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Path("getListaLotacaoPorId/{id}")
    public List<ModelLotacaoWs> getListaLotacaoPorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("ListaLotacaoPorId", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            Funcional func = daoFuncional.getUmFuncionalPorIdFuncional(id);
            if (func == null) {
                return null;
            }

            //Parte 2 - Lotacao
            //List<ModelLotacaoWs> lotacoes = ModelLotacaoWs.toModelLotacaoWs(daoLotacao.getLotacao());
            List<ModelLotacaoWs> lotacoes = ModelLotacaoWs.toModelLotacaoWs(daoUserPermissaoAcesso.getListLotacaoUsuario(headers.getRequestHeader("chave").get(0)));
            List<Lotacao> lotacaoAtivas = daoSolicitacao.getLotacaoPorId(id);

            for (Lotacao l : lotacaoAtivas) {
                for (ModelLotacaoWs mw : lotacoes) {
                    if (mw.getIdLotacao().equals(l.getId())) {
                        mw.setAtivo(true);
                        break;
                    }
                }
            }

            return lotacoes;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Path("getListaSetorAtivoPorId/{id}")
    public List<ModelSetorWs> getListaSetorAtivoPorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista Setor Ativo Por Id", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<SolicitacaoSub> ss = daoSolicitacaoSub.getSolicitacaoSubPorIdAtivo(id);
            if (ss == null) {
                return null;
            }

            List<ModelSetorWs> lotacoesSub = ModelSetorWs.toModelSetorAtivoSemAtivoWs(ss);

            return lotacoesSub;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

    }

    @GET
    @Path("getListaPermissaoAcesso/{id}")
    public List<ModelUserTemplatePermissaoAcessoWs> getListaPermissaoAcesso(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista de Permissao Acesso", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<UserPermissaoAcesso> permissoes = daoUserPermissaoAcesso.getPermissaoAcessoDeUmFuncional(id, headers.getRequestHeader("chave").get(0));

            List<ModelUserTemplatePermissaoAcessoWs> pws = new ArrayList();
            for (UserPermissaoAcesso permissao : permissoes) {
                ModelUserTemplatePermissaoAcessoWs mu = new ModelUserTemplatePermissaoAcessoWs();
                mu.setAlterar(permissao.getAlterar());
                mu.setArquivo(permissao.getUserMenu().getArquivo());
                mu.setBuscar(permissao.getBuscar());
                mu.setDirecao(permissao.getUserMenu().getDirecao());
                mu.setExcluir(permissao.getExcluir());
                mu.setIcon(permissao.getUserMenu().getIcon());
                mu.setIdUserMenu(permissao.getUserMenu().getId());
                mu.setIdUserTemplate(permissao.getUserTemplate().getId());
                mu.setIncluir(permissao.getIncluir());
                mu.setLink(permissao.getUserMenu().getLink());
                mu.setListar(permissao.getListar());
                mu.setMenuN1(permissao.getUserMenu().getMenuN1());
                mu.setMenuN2(permissao.getUserMenu().getMenuN2());
                mu.setMenuN3(permissao.getUserMenu().getMenuN3());
                mu.setOrdenar(permissao.getUserMenu().getOrdenar());
                mu.setPasta(permissao.getUserMenu().getPasta());
                mu.setUserMenuativo(permissao.getUserMenu().getAtivo());
                pws.add(mu);

            }

            return pws;

        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

    }

    @GET
    @Path("getListaSetorPorId/{id}")
    public List<ModelSetorWs> getListaSetorPorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaSetorPorId", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            Funcional func = daoFuncional.getUmFuncionalPorIdFuncional(id);
            if (func == null) {
                return null;
            }

            List<LotacaoSub> lsupa = daoUserPermissaoAcesso.getListLotacaoSubUsuario(headers.getRequestHeader("chave").get(0));

            //Parte 3 - Setor (SolicitacaoSub)
            List<LotacaoSub> lotacoes = daoLotacaoSub.getLotacaoSub(func);

            List<LotacaoSub> lsfinal = new ArrayList();

            if (lsupa.isEmpty()) {
                lsfinal = lotacoes;
            } else {
                for (LotacaoSub lsf : lotacoes) {
                    for (LotacaoSub lsp : lsupa) {
                        if (lsf.getId().equals(lsp.getId())) {
                            lsfinal.add(lsf);
                        }
                    }
                }
            }

            List<ModelSetorWs> lotacoesSub = new ArrayList();

            if (!(lotacoes.isEmpty())) {
                //lotacoesSub= ModelSetorWs.toModelLotacaoSubWs(lotacoes);
                lotacoesSub = ModelSetorWs.toModelLotacaoSubWs(lsfinal);
            }

            List<LotacaoSub> lotacaoSubAtivas = daoSolicitacaoSub.getLotacaoSubPorId(id);

            for (LotacaoSub ls : lotacaoSubAtivas) {
                for (ModelSetorWs mw : lotacoesSub) {
                    if (mw.getIdSetor().equals(ls.getId())) {
                        mw.setAtivo(true);
                        break;
                    }
                }
            }
            return lotacoesSub;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Path("getListaRegimePorId/{id}")
    public List<ModelRegimeWs> getListaRegimePorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        Boolean naoDeuCerto = true;
        try {
            daoUserLog.criarLog("ListaRegimePorId", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            Funcional func = daoFuncional.getUmFuncionalPorIdFuncional(id);
            if (func == null) {
                return null;
            }
            //Parte 4 - Regime
            List<ModelRegimeWs> regimes = ModelRegimeWs.toModelRegimeWs(daoEscalaTipo.getEscalaTipo(id));
            List<EscalaTipo> escalaAtivas = daoEscala.getEscalaPorId(id);
            for (EscalaTipo e : escalaAtivas) {
                for (ModelRegimeWs mrw : regimes) {
                    if (mrw.getIdRegime().equals(e.getId())) {
                        mrw.setAtivo(true);
                        naoDeuCerto = false;
                        break;
                    }
                }
            }
            //TEIIIII
            if(!(escalaAtivas.isEmpty())){
                if(naoDeuCerto){
                    regimes.add(new ModelRegimeWs(
                          escalaAtivas.get(0).getNomeEscalaTipo(),
                          "NAOPODE",
                          true
                    ));
                }  
            }

            return regimes;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

    }

    //Lista todos tipos de Ocorrências Ativas    
    @GET
    @Path("getListaOcorrenciaDesc")

    //Parte 5 - OcorrenciaDesc
    public List<ModelOcorrenciaDescWs> getListaOcorrenciaDesc(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        List<ModelOcorrenciaDescWs> ocorrenciasDesc = ModelOcorrenciaDescWs.toModelOcorrenciaDescWs(daoOcoDesc.getOcorrenciaDescAtivo());
        try {
            daoUserLog.criarLog("ListaOcorrenciaDesc", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ocorrenciasDesc;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    /**
     * Esse webservice altera as Lotacoes de um funcionário removendo as não
     * listadas e adicionando as que nao estao no banco de dados
     *
     *
     * @param headers
     * @param lotacoes
     * @return Response
     * @autor Raphael
     */
    @POST
    @Path("postAlterarLotacao")
    public Response postAlterarLotacao(@Context HttpHeaders headers, @Context HttpServletRequest request, List<ModelLotacaoWs> lotacoes) {

        try {
            daoUserLog.criarLog("AlterarLotacao", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoFuncional.alterarLotacao(lotacoes, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    /**
     * Esse webservice altera as especialidades de um funcionário removendo as
     * não listadas e adicionando as que nao estao no banco de dados
     *
     *
     * @param headers
     * @param especialidades
     * @return Response
     * @autor Alan
     */
    @POST
    @Path("postAlterarEspecialidade")
    public Response postAlterarEspecialidade(@Context HttpHeaders headers, @Context HttpServletRequest request, List<ModelEspecialidadeWs> especialidades) {
        try {
            daoUserLog.criarLog("AlterarEspecialidade", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoFuncional.alterarEspecialidade(especialidades, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    /**
     * Esse webservice altera as SolicitacoesSub de um funcionário removendo as
     * não listadas e adicionando as que nao estao no banco de dados
     *
     *
     * @param headers
     * @param setores
     * @return Response
     * @autor Alan
     */
    @POST
    @Path("postAlterarSetor")
    public Response postAlterarSetor(@Context HttpHeaders headers, List<ModelSetorWs> setores, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("AlterarSetor", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoFuncional.alterarSetor(setores, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    /**
     * Esse webservice altera as Escalas de um funcionário removendo as não
     * listadas e adicionando as que nao estao no banco de dados
     *
     *
     * @param headers
     * @param escalas
     * @return Response
     * @autor Raphael
     */
    @POST
    @Path("postAlterarRegime")
    public Response postAlterarRegime(@Context HttpHeaders headers, List<ModelEscalaWs> escalas, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("AlterarRegime", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoFuncional.alterarRegime(escalas, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @GET
    @Path("getPodeAlterarRegime/{idEscala}/{idFuncional}")
    public ModelPodeAlterarRegime getPodeAlterarRegime(@PathParam("idEscala") String idEscala, @PathParam("idFuncional") String idFuncional, @Context HttpHeaders headers, @Context HttpServletRequest request){
        try {
            daoUserLog.criarLog("getPodeAlterarRegime", "Consultar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoFuncional.podeAlterarRegime(idEscala, idFuncional);
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }
    }

    @GET
    @Path("getListarFuncionalPorNomeMatriculaCpf/{nome: [^/]*?}/{matricula: [^/]*?}/{cpf: [^/]*?}")
    public List<ModelBuscaServidorWs> getListarFuncionalPorNomeMatriculaCpf(@PathParam("nome") String nome, @PathParam("matricula") String matricula, @PathParam("cpf") String cpf, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("ListarFuncionalPorNomeMatriculaCpf", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<ModelBuscaServidorWs> bf = daoFuncional.getFuncionalMult(nome, matricula, cpf, headers.getRequestHeader("chave").get(0));

            return bf;

        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }

    }

    @GET
    @Path("getBiometriaCadastrada/{cpf}")
    public ModelBiometriaCadastradaWs getBiometriaCadastrada(@PathParam("cpf") String cpf, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaMarcacaoPonto", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelBiometriaCadastradaWs.toModelBiometriaCadastradaWs(daoBiometriaCadastrada.getBiometriaCadastrada(cpf));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
}
