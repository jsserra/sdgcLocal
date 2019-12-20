/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.CargoDao;
import com.pmm.sdgc.dao.CargoGeralDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.Cargo;
import com.pmm.sdgc.model.CargoGeral;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
@Path("cargo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CargoWs {

    @EJB
    CargoDao daoCargo;
    @EJB
    CargoGeralDao daoCargoGeral;
    @EJB
    UserLogDao daoUserLog;


    @GET
    @Path("getListaCargo")
    public List<Cargo> getListaCargo(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista de Cargos", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoCargo.getCargos();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaCargoGeral")
    public List<CargoGeral> getListaCargoGeral(@Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista de Cargos", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoCargoGeral.getCargosGeral();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaCargoGeralUsuario")
    public List<CargoGeral> getListaLotacaoUsuario(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaCargoGeralUsuario", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoCargoGeral.getListCargosGeralUsuario(headers.getRequestHeader("chave").get(0));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }  

    @GET
    @Path("getListaCargoMult/{nome: [^/]*?}/{horaSemanal: [^/]*?}")
    public List<Cargo> getListaCargoMult(@PathParam("nome") String nome, @PathParam("horaSemanal") Integer horaSemanal, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista de Cargos Multi", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoCargo.getListaCargoMult(nome, horaSemanal);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @POST
    @Path("postIncluirCargo")
    public Response postIncluirCargo(Cargo cargo, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir Cargo", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoCargo.incluir(cargo);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postAlterarCargo")
    public Response postAlterarCargo(Cargo cargo, @PathParam("nome") String nome, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            String param = "";
            if (!(nome == null)) {
                param += nome;
            }
            daoCargo.alterar(cargo);
            daoUserLog.criarLog("Alterar Cargo", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postRemoverCargo")
    public Response postRemoverCargo(Cargo cargo, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoCargo.remover(cargo);
            daoUserLog.criarLog("Remover Cargo", "Remover", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

}
