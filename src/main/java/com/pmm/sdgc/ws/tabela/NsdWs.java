/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.NsdDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.Nsd;
import com.pmm.sdgc.ws.model.ModelBuscaNSDWs;
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
@Path("nsdWs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NsdWs {

    @EJB
    NsdDao daoNsd;
    
    @EJB
    UserLogDao daoUserLog;

    @GET
    @Path("getListaNsd/{mes:[^/]*?}/{ano:[^/]*?}/{numeroNota:[^/]*?}/{secretaria:[^/]*?}")
    public List<ModelBuscaNSDWs> getListaNsd(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("mes") String mes, @PathParam("ano") String ano, @PathParam("numeroNota") String numeroNota, @PathParam("secretaria") String secretaria) {
        try {
            
            daoUserLog.criarLog("Buscar NSD", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            
            return daoNsd.getListNsd(mes, ano, numeroNota, secretaria, headers.getRequestHeader("chave").get(0));
            
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaReferenciaNsd/{d1: [^/]*?}/{d2: [^/]*?}")
    public List<Nsd> getListaReferenciaNsd(@PathParam("d1") String d1, @PathParam("d2") String d2, @Context HttpHeaders headers, @Context HttpServletRequest request){
        //System.out.println("d1: " + d1 + " d2: " + d2);
        try {
            daoUserLog.criarLog("ListaReferenciaNsd", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoNsd.getListReferenciaNsd(d1, d2);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }

    }
}