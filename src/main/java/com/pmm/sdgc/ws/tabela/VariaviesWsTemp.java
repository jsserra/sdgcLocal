/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.VariaveisDao;
import com.pmm.sdgc.dao.VariaveisDescDao;
import com.pmm.sdgc.dao.VariaveisLotacaoDao;
import com.pmm.sdgc.dao.VariaveisLotacaoSubDao;
import com.pmm.sdgc.dao.VariaveisPossivelFuncionalDao;
import com.pmm.sdgc.dao.VariaveisPossivelLotacaoSubDao;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Variaveis;
import com.pmm.sdgc.model.VariaveisDesc;
import com.pmm.sdgc.model.VariaveisLotacao;
import com.pmm.sdgc.model.VariaveisLotacaoSub;
import com.pmm.sdgc.model.VariaveisPossivelFuncional;
import com.pmm.sdgc.model.VariaveisPossivelLotacaoSub;
import com.pmm.sdgc.ws.model.ModelVariaveisFuncionalWs;
import com.pmm.sdgc.ws.model.ModelVariaveisLotacaoSubWs;
import com.pmm.sdgc.ws.model.ModelVariaveisLotacaoWs;
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
/*
Para acessar o caminho é: http://localhost:8080/SpvWs/rest/webservice/******
 */
@Named
@Path("variaveisTemp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VariaviesWsTemp {

    @EJB
    UserLogDao daoUserLog;

    @EJB
    VariaveisDao daoVariaveis;

    @EJB
    VariaveisDescDao daoVariaveisDesc;

    @EJB
    VariaveisLotacaoDao daoVariaveisLot;

    @EJB
    VariaveisLotacaoSubDao daoVariaveisLotSub;

    @EJB
    VariaveisPossivelFuncionalDao daoVariaveisPossivel;

    @EJB
    VariaveisPossivelLotacaoSubDao daoVariaveisPossivelLotacaoSub;

    @GET
    @Path("getListaVariaveisAtivas")
    public List<Variaveis> getListaVariaveisAtivas(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Descrição das Variaveis", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveis.getListaVariaveisAtivas();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaVariaveisDesc")
    public List<VariaveisDesc> getListaVariaveisDesc(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Descrição das Variaveis", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveisDesc.getListVariaveisDesc();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaVariaveisLotacao")
    public List<VariaveisLotacao> getListaVariaveisLotacao(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Descrição das Variaveis", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveisLot.getListVariaveisLotacao();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaVariaveisLotacaoSub")
    public List<VariaveisLotacaoSub> getListaVariaveisLotacaoSub(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Descrição das Variaveis", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveisLotSub.getListVariaveisLotacaoSub();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaVariaveisFuncional")
    public List<ModelVariaveisFuncionalWs> getListaVariaveisFuncional(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Descrição das Variaveis", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelVariaveisFuncionalWs.toModelVariaviesFuncional(daoVariaveisPossivel.getListVariaveisPossivelFuncional());
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

   /* @GET
    @Path("getListaVariaveisFuncionalPorId/{idFuncional}")
    public List<ModelVariaveisFuncionalWs> getListaVariaveisPossivelFuncionalPorId(@PathParam("idFuncional") String idFuncional, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Variáveis Funcional", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelVariaveisFuncionalWs.toModelVariaviesFuncional(daoVariaveisPossivel.getListaVariaveisPossivelFuncionalPorId(idFuncional));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }*/
    
    @GET
    @Path("getListaVariaveisFuncionalPorVariavelDesc/{idVarDesc}")
    public List<Funcional> getListaVariaveisFuncionalPorVariavelDesc(@PathParam("idVarDesc") Integer idVarDesc, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Variáveis por Tipo", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveis.getFuncionaisDeVariaveis(idVarDesc);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }


    @GET
    @Path("getListaVariaveisLotacaoPorLotacao/{idLotacao}")
    public List<ModelVariaveisLotacaoWs> getListaVariaveisLotacaoPorLotacao(@PathParam("idLotacao") String idLotacao, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista Variaveis Lotação", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelVariaveisLotacaoWs.toModelLotacaoWs(daoVariaveisLot.getListaVariaveisLotacaoPorLotacao(idLotacao));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    //Solicitado, Trello: getVariaveisListaSecretaria
    @GET
    @Path("getListaVariaveisSecretaria/{idLotacao}")
    public List<ModelVariaveisLotacaoWs> getListaVariaveisSecretaria(@PathParam("idLotacao") String idLotacao, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista Variaveis Lotação", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelVariaveisLotacaoWs.toModelLotacaoWs(daoVariaveisLot.getListaVariaveisSecretaria(idLotacao));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaVariaveisLotacaoSubPorLotacaoSub/{idLotacaoSub}")

    public List<ModelVariaveisLotacaoSubWs> getListaVariaveisLotacaoSubPorLotacaoSub(@PathParam("idLotacaoSub") String idLotacaoSub, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista Variaveis do Seotr", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelVariaveisLotacaoSubWs.toModelLotacaoSubWs(daoVariaveisLotSub.getListaVariaveisLotacaoSubPorId(idLotacaoSub));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaVariaveisPossivelLotacaoSuba")
    public List<VariaveisPossivelLotacaoSub> getListaVariaveisPossivelLotacaoSuba(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista Variaveis do Seotr", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveisPossivelLotacaoSub.getListVariaveisPossivelLotacaoSub();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    //Solicitado, Trello: getVariaveisListaSetor
    @GET
    @Path("getListaVariaveisSetor/{idLotacaoSub}")
    public List<ModelVariaveisLotacaoSubWs> getListaVariaveisSetor(@PathParam("idLotacaoSub") String idLotacaoSub, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista Variaveis do Seotr", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelVariaveisLotacaoSubWs.toModelLotacaoSubWs(daoVariaveisLotSub.getListaVariaveisSetor(idLotacaoSub));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    //Solicitado, Trello: getVariaveisListaDispHistFunc
    @GET
    @Path("getVariaveisListaDispHistFunc/{idFuncional}")
    public List<VariaveisDesc> getVariaveisListaDispHistFunc(@PathParam("idFuncional") String idLotacaoSub, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista Variaveis do Seotr", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveis.getVariaveisListaDispHistFunc(idLotacaoSub);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @POST
    @Path("postIncluirVariaveisDesc/{codigo}/{nome}/{qtd}/{valor}/{max}/{min}")
    public Response postIncluirCargo(@PathParam("codigo") String codigo, @PathParam("nome") String nome, @PathParam("qtd") Integer qtd, @PathParam("valor") Integer valor, @PathParam("max") Double max, @PathParam("min") Double min, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir VariaveisDesc", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoVariaveisDesc.incluirVariaveisDesc(codigo, nome, qtd, valor, max, min, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postVariaveisFecharLotacaoSub/{idLotacaoSub}/{idVarDesc}")
    public Response postVariaveisFecharLotacaoSub(@PathParam("idLotacaoSub") String idLotacaoSub, @PathParam("idVarDesc") Integer idVarDesc, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Fechar LotaçãoSub VariaveisDesc", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoVariaveisLotSub.postVariaveisFecharLotacaoSub(idLotacaoSub, idVarDesc, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postVariaveisFecharLotacao/{idLotacao}/{idVarDesc}")
    public Response postVariaveisFecharLotacao(@PathParam("idLotacao") String idLotacao, @PathParam("idVarDesc") Integer idVarDesc, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Fechar Lotação por Variaveis", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoVariaveisLot.postVariaveisFecharLotacao(idLotacao, idVarDesc, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postReprovarVariaveisHistFuncId/{idFuncional}")
    public Response postReprovarVariaveisHistFuncId(@PathParam("idFuncional") String idFuncional, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Fechar Lotação por Variaveis", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoVariaveis.postReprovarVariaveisHistFuncId(idFuncional, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postAprovarVariaveisHistFuncId/{idFuncional}")
    public Response postAprovarVariaveisHistFuncId(@PathParam("idFuncional") String idFuncional, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Fechar Lotação por Variaveis", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoVariaveis.postAprovarVariaveisHistFuncId(idFuncional, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postReprovarVariaveisSetorId/{idSetor}")
    public Response postReprovarVariaveisSetorId(@PathParam("idSetor") String idSetor, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Fechar Lotação por Variaveis", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoVariaveis.postReprovarVariaveisSetorId(idSetor, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }  
    
    @POST
    @Path("postAprovarVariaveisSetorId/{idSetor}")
    public Response postAprovarVariaveisSetorId(@PathParam("idSetor") String idSetor, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Fechar Lotação por Variaveis", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoVariaveis.postAprovarVariaveisSetorId(idSetor, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }  
}
