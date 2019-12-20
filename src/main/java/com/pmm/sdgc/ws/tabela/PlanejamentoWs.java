/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.PlanejamentoAuxiliarDao;
import com.pmm.sdgc.dao.PlanejamentoConsolidadoDao;
import com.pmm.sdgc.dao.PlanejamentoDao;
import com.pmm.sdgc.dao.PlanejamentoDataDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.PlanejamentoData;
import com.pmm.sdgc.model.PlanejamentoDescricao;
import com.pmm.sdgc.ws.model.ModelPlanejamentoConsolidadoWs;
import com.pmm.sdgc.ws.model.ModelPlanejamentoDataWs;
import com.pmm.sdgc.ws.model.ModelPlanejamentoWs;
import com.pmm.sdgc.ws.model.ModelRevalidarPlanejamentoAuxiliar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 *
 * @author ajuliano
 */
/*
Para acessar o caminho é: http://localhost:8080/SpvWs/rest/webservice/******
 */
@Named
@Path("planejamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanejamentoWs {

    @EJB
    PlanejamentoDao daoPlanejamento;
    
    @EJB
    PlanejamentoAuxiliarDao daoPlanejamentoAuxiliar;
 
    @EJB
    PlanejamentoConsolidadoDao daoPlanejamentoConsolidado;

    @EJB
    UserLogDao daoUserLog;
    
    @EJB
    PlanejamentoDataDao daoPlanejamentoData;
    
    @GET
    @Path("getListaPlanejamentoData")
    public List<PlanejamentoData> getListaPlanejamentoData(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaPlanejamentoData", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoPlanejamentoData.getListaPlanejamentoData();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @POST
    @Path("postIncluirPlanejamentoData")
    public Response postIncluirPlanejamentoData(@Context HttpHeaders headers, @Context HttpServletRequest request, List<ModelPlanejamentoDataWs> modelsPlanejamentoDatasWs) {
        try {
            daoUserLog.criarLog("Incluir PlanejamentoData", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoPlanejamentoData.limparTabela();
            for (ModelPlanejamentoDataWs modelPlanejamentoDataWs : modelsPlanejamentoDatasWs) {
                daoPlanejamentoData.incluirPlanejamentoData(modelPlanejamentoDataWs.getDia(), headers.getRequestHeader("chave").get(0));
            }
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @GET
    @Path("getListaPlanejamentoConsolidado/{cpf}")
    public List<ModelPlanejamentoConsolidadoWs> getPlanejamentoConsolidados(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("cpf") String cpf) {
        try {
            daoUserLog.criarLog("ListaPlanejamento", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<ModelPlanejamentoConsolidadoWs> pc = ModelPlanejamentoConsolidadoWs.toModelPlanejamentoConsolidadoWs(daoPlanejamentoConsolidado.getPlanejamentoConsolidadoPorId(cpf));
            return pc;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaPlanejamento/{idFunc}")
    public List<ModelPlanejamentoWs> getListaPlanejamento(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idFunc") String idFunc) {
        try {
            daoUserLog.criarLog("ListaPlanejamento", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoPlanejamento.buscarPlanejamentos(idFunc , headers.getRequestHeader("chave").get(0));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaPlanejamentoDescricao")
    public List<PlanejamentoDescricao> getListaPlanejamento(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaPlanejamentoDescricao", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoPlanejamento.buscarListaPlanejamentoDescricao();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @POST
    @Path("postIncluirPlanejamentoConsolidado")
    public Response postIncluirPlanejamentoConsolidado(@Context HttpHeaders headers, List<ModelPlanejamentoConsolidadoWs> planejamentoConsolidados, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir PlanejamentoConsolidado", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelPlanejamentoConsolidadoWs planejamentoConsolidado : planejamentoConsolidados) {
                daoPlanejamentoConsolidado.incluirPlanejamentoConsolidado(planejamentoConsolidado.getIdLotacaoSub(),planejamentoConsolidado.getCpf());
            }
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("postIncluirPlanejamento")
    public Response postIncluirPlanejamento(@Context HttpHeaders headers, List<ModelPlanejamentoWs> planejamentos, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir Planejamento", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelPlanejamentoWs planejamento : planejamentos) {
                daoPlanejamento.criarPlanejamentoAuxiliar(planejamento.getIdTpPlan(), planejamento.getIdFunc(), planejamento.getDiaSemana(), planejamento.gethInicial(), planejamento.gethFinal(), planejamento.getSetorPlan(), planejamento.getFeriado(), planejamento.getPontoFacult(), headers.getRequestHeader("chave").get(0));
            }
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postRemoverPlanejamento")
    public Response postRemoverPlanejamento(@Context HttpHeaders headers, List<ModelPlanejamentoWs> planejamentos, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Remover Planejamento", "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelPlanejamentoWs planejamento : planejamentos) {
                daoPlanejamento.removerPlanejamento(planejamento.getIdPlanejamento());
            }
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postRemoverPlanejamentoAuxiliar")
    public Response postRemoverPlanejamentoAuxiliar(@Context HttpHeaders headers, List<ModelPlanejamentoWs> planejamentos, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Remover Planejamento Auxiliar", "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelPlanejamentoWs planejamento : planejamentos) {
                daoPlanejamento.removerPlanejamentoAuxiliar(planejamento.getIdPlanejamentoAuxiliar());
            }
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("postRevalidarPlanejamentoAuxiliar")
    public Response postRevalidarPlanejamentoAuxiliar(@Context HttpHeaders headers, ModelRevalidarPlanejamentoAuxiliar modelplaux, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Revalidar Planejamento Auxiliar", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoPlanejamentoAuxiliar.revalidarPlanejamentoAuxiliar(modelplaux.getIdLotacaoSub());
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

}
