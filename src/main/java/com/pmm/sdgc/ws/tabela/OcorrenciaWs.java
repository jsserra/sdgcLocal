/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.OcorrenciaDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.Ocorrencia;
import com.pmm.sdgc.ws.model.ModelOcorrenciaInclusaoWs;
import com.pmm.sdgc.ws.model.ModelOcorrenciaWs;
import java.time.format.DateTimeFormatter;
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
@Path("OcorrenciaWs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciaWs {

    @EJB
    OcorrenciaDao daoOcorrencia;

    @EJB
    UserLogDao daoUserLog;

    @GET
    @Path("getListaOcorrenciaPorId/{id}")
    public List<Ocorrencia> getListaOcorrenciaPorId(@PathParam("id") String id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista Ocorrencia Por Id", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<Ocorrencia> oc = daoOcorrencia.getOcorrenciaPorId(id);
            return oc;
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }
    }

    @GET
    @Path("getListaOcorrenciaPorIdData/{id}/{data1}/{data2}/{idOcoDesc: [^/]*?}")
    public List<ModelOcorrenciaWs> getListaOcorrenciaPorIdData(@PathParam("id") String id, @PathParam("idOcoDesc") String idOcoDesc, @PathParam("data1") String data1, @PathParam("data2") String data2, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista Ocorrencia por Id Data Teste", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<ModelOcorrenciaWs> mow;
            if (idOcoDesc.isEmpty()) {
                mow = ModelOcorrenciaWs.toModelOcorrenciaWs(daoOcorrencia.getOcorrenciaPorIdData(id, data1, data2,headers.getRequestHeader("chave").get(0)));
            } else {
                mow = ModelOcorrenciaWs.toModelOcorrenciaWs(daoOcorrencia.getOcorrenciaPorIdDataOco(id, idOcoDesc, data1, data2, headers.getRequestHeader("chave").get(0)));
            }
            return mow;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }
    }

    @POST
    @Path("postIncluirOcorrencia")
    public Response postIncluirOcorrencia(@Context HttpHeaders headers, List<ModelOcorrenciaInclusaoWs> ocorrencias, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir Ocorrencia", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelOcorrenciaInclusaoWs ocorrencia : ocorrencias) {
                if (ocorrencia.getDias().equals("")){                 
                }
                daoOcorrencia.incluirOcorrencia(ocorrencia.getIdFuncs(), ocorrencia.getEntrada(), ocorrencia.getSaida(), ocorrencia.getSetorOco(), ocorrencia.getDias(), ocorrencia.getObs(), ocorrencia.getIdTipoOco(), headers.getRequestHeader("chave").get(0));
            }
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postExcluirOcorrencia")
    public Response postExcluirOcorrencia(@Context HttpHeaders headers, List<ModelOcorrenciaInclusaoWs> ocorrencias, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Excluir Ocorrencia", "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelOcorrenciaInclusaoWs ocorrencia : ocorrencias) {
                Ocorrencia o = daoOcorrencia.getOcorrenciaPorIdOcorrencia(ocorrencia.getIdOcorrencia().intValue());
                String log = "Excluir Ocorrencia ";
                log+= " Id OcoDesc: " + o.getOcorrenciaDesc().getIdOcorrencia() ;
                log+= " Mes/Ano: " + o.getMesAno() ;
                log+= " Entrada: " + o.getEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                log+= " Saida: " + o.getSaida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                log+= " Matricula: " + o.getFuncional().getMatricula();
                daoUserLog.criarLog(log, "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
                
                daoOcorrencia.excluirOcorrencia(ocorrencia.getIdOcorrencia());
            }
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
            //Teste de exclusão
        }
    }

}
