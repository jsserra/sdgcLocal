/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.PortariaDao;
import com.pmm.sdgc.dao.PortariaRelacaoDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.Portaria;
import com.pmm.sdgc.model.PortariaRelacao;
import com.pmm.sdgc.ws.model.ModelPortariaRelacao;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("portariaWs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortariaWs {

    @EJB
    PortariaDao daoPortaria;
    @EJB
    UserLogDao daoUserLog;
    @EJB
    PortariaRelacaoDao daoPortariaRelacao;
    
    @GET
    @Path("getListaPortaria/{nome:[^/]*?}/{dataPublicacao:[^/]*?}")
    public List<Portaria> getListaPortaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("nome") String nome, @PathParam("dataPublicacao") String dataPublicacao) {
        try {
            daoUserLog.criarLog("Buscar portaria", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoPortaria.getListaPortaria(nome, dataPublicacao);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
   
    @GET
    @Path("getListaPortariaRelacao/{idPortaria}")
    public List<ModelPortariaRelacao> getListaPortariaRelacao(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idPortaria") String id_portaria) {
        try {
            daoUserLog.criarLog("Buscar portaria", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return ModelPortariaRelacao.toModelPortariaRelacao(daoPortariaRelacao.getListaPortariaRelacao(id_portaria));
        } catch (Exception exception) 
        {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

}
