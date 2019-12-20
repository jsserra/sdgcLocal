/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.DataFrequenciaDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.DataFrequencia;
import com.pmm.sdgc.ws.model.ModelDataFrequenciaIncluirWs;
import com.pmm.sdgc.ws.model.ModelDataFrequenciaWs;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ajuliano
 */
@Named
@Path("dataFrequenciaWs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataFrequenciaWs {

    @EJB
    DataFrequenciaDao daoDataFrequencia;

    
    @EJB
    UserLogDao daoUserLog;
    
    
    @GET
    @Path("getListaDataFrequencia")

    
    public List<ModelDataFrequenciaWs> getListaDataFrequencia(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("ListaDataFrequencia", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return new ModelDataFrequenciaWs().converter(daoDataFrequencia.getDataFrequencia());
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaDataFrequenciaFirst")
    public Optional<DataFrequencia> getListaDataFrequenciaLast(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaDataFrequenciaLast", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoDataFrequencia.getDataFrequencia().stream().findFirst();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @POST
    @Path("postAlterarPeriodoOcorrencia")
    public Response postAlterarPeriodoOcorrencia(@Context HttpHeaders headers, @Context HttpServletRequest request, List<ModelDataFrequenciaIncluirWs> data) {
        try {
            daoUserLog.criarLog("AlterarPeriodoOcorrencia", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoDataFrequencia.alterarPeriodoOcorrencia(data.get(0).getData(), data.get(0).getDataAgenda(), headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postAlterarPeriodoImpressao")
    public Response postAlterarPeriodoImpressao(@Context HttpHeaders headers, @Context HttpServletRequest request, List<ModelDataFrequenciaIncluirWs> data) {
        try {
            daoUserLog.criarLog("AlterarPeriodImpressao", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoDataFrequencia.alterarPeriodoImpressao(data.get(0).getData(), data.get(0).getDataAgenda());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postAlterarPeriodoPlanejamento")
    public Response postAlterarPeriodoPlanejamento(@Context HttpHeaders headers, @Context HttpServletRequest request, ModelDataFrequenciaIncluirWs data) {
        try {
            daoUserLog.criarLog("AlterarPeriodoPlanejamento", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoDataFrequencia.alterarPeriodoPlanejamento(data.getData());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

}
