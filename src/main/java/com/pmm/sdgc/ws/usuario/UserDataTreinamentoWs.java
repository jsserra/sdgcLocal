package com.pmm.sdgc.ws.usuario;

import com.pmm.sdgc.dao.UserDataTreinamentoDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.UserPermissaoAcessoDao;
import com.pmm.sdgc.model.Ocorrencia;
import com.pmm.sdgc.model.UserDataTreinamento;
import com.pmm.sdgc.model.UserPermissaoAcesso;
import com.pmm.sdgc.ws.model.ModelUserDataTreinamentoWs;
import com.pmm.sdgc.ws.model.ModelUserPermissaoAcessoWs;
import java.time.LocalDate;
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
 * @author djoao
 */
@Named
@Path("userDataTreinamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserDataTreinamentoWs {
    
    @EJB
    UserLogDao daoUserLog;
    @EJB
    UserDataTreinamentoDao daoUserDataTreinamento;

    @POST
    @Path("postIncluirUserDataTreinamento")
    public Response postIncluirUserDataTreinamento(@Context HttpHeaders headers, List<ModelUserDataTreinamentoWs> musts, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir Data de Treinamento Do Usuario (UserDatareinamento) ", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for(ModelUserDataTreinamentoWs must : musts){
                daoUserDataTreinamento.incluir(must.getIdUserLogin(), must.getData());
            }
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @GET
    @Path("getListaUserDataTreinamentoPorUserLogin/{id}")
    public List<UserDataTreinamento> getListaUserDataTreinamentoPorUserLogin(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("id") Integer id){
        try {
            daoUserLog.criarLog("getListaUserDataTreinamentoPorUserLogin", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<UserDataTreinamento> listaUserDataTreinamento;
            listaUserDataTreinamento = daoUserDataTreinamento.getListaUserDataTreinamento(id);
            return listaUserDataTreinamento;
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }
    }
}
