/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.relatorio;

import com.pmm.sdgc.dao.RelatorioDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.ws.model.ModelRelatorioWs;
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
VERSAO 2
 */
@Named
@Path("relatorio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RelatorioWS {

    @EJB
    UserLogDao daoUserLog;

    @EJB
    RelatorioDao daoRelatorio;

    @GET
    @Path("getRelServidoresPorSetor/{idlotacaosub}/{ordem}/{tiporelatorio}")
    public ModelRelatorioWs getRelServidoresPorSetor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacaosub") String idLotacaoSub, @PathParam("ordem") String ordem, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelServidoresPorSetor", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioServidoresPorSetor(headers.getRequestHeader("chave").get(0), idLotacaoSub, ordem, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelServidoresComissionadosPorSetor/{idlotacaosub}/{ordem}/{tiporelatorio}")
    public ModelRelatorioWs getRelServidoresComissionadosPorSetor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacaosub") String idLotacaoSub, @PathParam("ordem") String ordem, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelServidoresComissionadosPorSetor", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioServidoresComissionadosPorSetor(headers.getRequestHeader("chave").get(0), idLotacaoSub, ordem, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelBiometriaCadastradaSetor/{idlotacaosub}/{ordem}/{tiporelatorio}")
    public ModelRelatorioWs getRelBiometriaCadastradaSetor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacaosub") String idLotacaoSub, @PathParam("ordem") String ordem, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("BiometriaCadastradaSetor", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioBiometriaCadastradaSetor(headers.getRequestHeader("chave").get(0), idLotacaoSub, ordem, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelBiometriaCadastradaSecretaria/{idlotacao}/{ordem}/{tiporelatorio}")
    public ModelRelatorioWs getRelBiometriaCadastradaSecretaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacao") String idLotacao, @PathParam("ordem") String ordem, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelBiometriaCadastradaSecretaria", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioBiometriaCadastradaSecretaria(headers.getRequestHeader("chave").get(0), idLotacao, ordem, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelServidoresPorCargo/{idcargogeral}/{tiporelatorio}")
    public ModelRelatorioWs getRelServidoresPorSecretaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idcargogeral") String idCargoGeral, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelServidoresPorCargo", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioServidoresPorCargo(headers.getRequestHeader("chave").get(0), idCargoGeral, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelServidoresPorSecretaria/{idlotacao}/{ordem}/{tiporelatorio}")
    public ModelRelatorioWs getRelServidoresPorSecretaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacao") String idLotacao, @PathParam("ordem") String ordem, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelServidoresPorSecretaria", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioServidoresPorSecretaria(headers.getRequestHeader("chave").get(0), idLotacao, ordem, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelServidoresComissionadosPorSecretaria/{idlotacao}/{ordem}/{tiporelatorio}")
    public ModelRelatorioWs getRelServidoresComissionadosPorSecretaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacao") String idLotacao, @PathParam("ordem") String ordem, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelServidoresComissionadosPorSecretaria", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelServidoresComissionadosPorSecretaria(headers.getRequestHeader("chave").get(0), idLotacao, ordem, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelSetoresComTotalServidores/{idlotacao}/{tiporelatorio}")
    public ModelRelatorioWs getRelSetoresComTotalServidores(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacao") String idLotacao, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelSetoresComTotalServidores", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelSetoresComTotalServidores(headers.getRequestHeader("chave").get(0), idLotacao, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelSecretariasComTotalServidores/{tiporelatorio}")
    public ModelRelatorioWs getRelSecretariasComTotalServidores(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            
            daoUserLog.criarLog("RelSecretariasComTotalServidores", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelSecretariasComTotalServidores(headers.getRequestHeader("chave").get(0), tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelOcorrenciasPorPeriodo/{matricula}/{data1}/{data2}/{idOcoDesc: [^/]*?}/{tiporelatorio}")
    public ModelRelatorioWs getRelOcorrenciasPorPeriodo(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("matricula")  String matricula, @PathParam("data1")  String data1, @PathParam("data2")  String data2, @PathParam("idOcoDesc")  String idOcoDesc) {
        try {
            daoUserLog.criarLog("RelOcorrenciasPorPeriodo", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelOcorrenciasPorPeriodo(headers.getRequestHeader("chave").get(0), matricula, data1, data2, idOcoDesc,tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelOcorrenciasPorSetor/{idlotacaosub}/{tiporelatorio}")
    public ModelRelatorioWs getRelOcorrenciasPorSetor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idlotacaosub")  String idLotacaoSub) {
        try {
            daoUserLog.criarLog("RelOcorrenciasPorSetor", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelOcorrenciasPorSetor(headers.getRequestHeader("chave").get(0), idLotacaoSub, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelOcorrenciasPorSecretaria/{idlotacao}/{tiporelatorio}")
    public ModelRelatorioWs getRelOcorrenciasPorSecretaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idlotacao") String idLotacao) {
        try {
            daoUserLog.criarLog("RelOcorrenciasPorSecretaria", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelOcorrenciasPorSecretaria(headers.getRequestHeader("chave").get(0), idLotacao, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelLancamentoIndividualPorPeriodo/{anoMes}/{cpf}/{tiporelatorio}")
    public ModelRelatorioWs getRelLancamentoIndividualPorPeriodo(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("anoMes") String anoMes, @PathParam("cpf") String cpf) {
        try {
            daoUserLog.criarLog("getRelLancamentoIndividualPorPeriodo", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelLancamentoIndividualPorPeriodo(headers.getRequestHeader("chave").get(0), anoMes, cpf, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelFichaFuncional/{idpessoal}/{tiporelatorio}")
    public ModelRelatorioWs getRelFichaFuncional(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idpessoal") String idPessoal) {
        try {
            daoUserLog.criarLog("RelLancamentoIndividualPorPeriodo", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelFichaFuncional(headers.getRequestHeader("chave").get(0), idPessoal, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelPortarias/{idfuncional}/{tiporelatorio}")
    public ModelRelatorioWs getRelPortarias(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idfuncional") String idFuncional) {
        try {
            daoUserLog.criarLog("RelPortarias", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelPortarias(headers.getRequestHeader("chave").get(0), idFuncional, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelContraCheque/{matricula}/{referenciaNsda}/{tiporelatorio}")
    public ModelRelatorioWs getRelContraCheque(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("matricula") String matricula, @PathParam("referenciaNsda") String referenciaNsda) {
        try {
            daoUserLog.criarLog("RelContraCheque", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelContraCheque(headers.getRequestHeader("chave").get(0), referenciaNsda, matricula, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelFolhaPonto/{matricula}/{mesAno}/{idLotacaoSub}/{tiporelatorio}")
    public ModelRelatorioWs getRelFolhaPonto(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("matricula") String matricula, @PathParam("mesAno") String mesAno, @PathParam("idLotacaoSub") String idLotacaoSub) {
        try {
            daoUserLog.criarLog("RelFolhaPonto", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelFolhaPonto(headers.getRequestHeader("chave").get(0), matricula, mesAno, tipoRelatorio, idLotacaoSub));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelFolhaPontoEmLote/{idLotacaoSub}/{mesAno}/{tiporelatorio}")
    public ModelRelatorioWs getRelFolhaPontoEmLote(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idLotacaoSub") String idLotacaoSub, @PathParam("mesAno") String mesAno) {
        try {
            daoUserLog.criarLog("RelFolhaPontoEmLote", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelFolhaPontoEmLote(headers.getRequestHeader("chave").get(0), idLotacaoSub, mesAno, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelFolhaPontoEmLoteDoServidor/{matricula}/{idLotacaoSub}/{mesAnoInicial}/{mesAnoFinal}/{tiporelatorio}")
    public ModelRelatorioWs getRelFolhaPontoEmLoteDoServidor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("matricula") String matricula, @PathParam("idLotacaoSub") String idLotacaoSub, @PathParam("mesAnoInicial") String mesAnoInicial, @PathParam("mesAnoFinal") String mesAnoFinal, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("RelFolhaPontoEmLote", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelFolhaPontoEmLoteDoServidor(headers.getRequestHeader("chave").get(0), matricula, idLotacaoSub, mesAnoInicial, mesAnoFinal, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelNsd/{codNsd}/{tiporelatorio}")
    public ModelRelatorioWs getRelNsd(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("codNsd") String codNsd) {
        try {
            daoUserLog.criarLog("RelNsd", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelNsd(headers.getRequestHeader("chave").get(0), codNsd, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelNsds/{codNsd}/{tiporelatorio}")
    public ModelRelatorioWs getRelNsds(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("codNsd") String codNsd) {
        try {
            daoUserLog.criarLog("RelNsds", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelNsds(headers.getRequestHeader("chave").get(0), codNsd, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelNsda/{codNsd}/{tiporelatorio}")
    public ModelRelatorioWs getRelNsda(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("codNsd") String codNsd) {
        try {
            daoUserLog.criarLog("RelNsda", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelNsda(headers.getRequestHeader("chave").get(0), codNsd, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelEmpenhoAnaliticoMacprev/{referencia}/{codganNsda}/{tiporelatorio}")
    public ModelRelatorioWs getRelEmpenhoAnaliticoMacprev(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("referencia") String referencia, @PathParam("codganNsda") String codganNsda ) {
        try {
            daoUserLog.criarLog("RelEmpenhoAnaliticoMacprev", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelEmpenhoAnaliticoMacprev(headers.getRequestHeader("chave").get(0), referencia, codganNsda, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelEmpenhoSinteticoMacprev/{referencia}/{codganNsda}/{tiporelatorio}")
    public ModelRelatorioWs getRelEmpenhoSinteticoMacprev(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("referencia") String referencia, @PathParam("codganNsda") String codganNsda ) {
        try {
            daoUserLog.criarLog("RelEmpenhoSinteticoMacprev", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelEmpenhoSinteticoMacprev(headers.getRequestHeader("chave").get(0), referencia, codganNsda, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelDdoSinteticoMacprev/{referencia}/{codganNsda}/{tiporelatorio}")
    public ModelRelatorioWs getRelDdoSinteticoMacprev(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("referencia") String referencia, @PathParam("codganNsda") String codganNsda ) {
        try {
            daoUserLog.criarLog("RelDdoSinteticoMacprev", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelDdoSinteticoMacprev(headers.getRequestHeader("chave").get(0), referencia, codganNsda, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelDdoAnaliticoMacprev/{referencia}/{codganNsda}/{tiporelatorio}")
    public ModelRelatorioWs getRelDdoAnaliticoMacprev(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("referencia") String referencia, @PathParam("codganNsda") String codganNsda ) {
        try {
            daoUserLog.criarLog("RelDdoAnaliticoMacprev", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelDdoAnaliticoMacprev(headers.getRequestHeader("chave").get(0), referencia, codganNsda, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelExportarFundebNsd/{referenciaNsd}/{tiporelatorio}")
    public ModelRelatorioWs getRelExportarFundebNsd(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("referenciaNsd") String referenciaNsd) {
        try {
            daoUserLog.criarLog("RelExportarFundebNsd", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelExportarFundebNsd(headers.getRequestHeader("chave").get(0), referenciaNsd, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelExportarMacprevNsd/{referenciaNsd}/{tiporelatorio}")
    public ModelRelatorioWs getRelExportarMacprevNsd(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("referenciaNsd") String referenciaNsd) {
        try {
            daoUserLog.criarLog("RelExportarMacprevNsd", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelExportarMacprevNsd(headers.getRequestHeader("chave").get(0), referenciaNsd, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelBancoDeHoras/{idFuncional}/{tiporelatorio}")
    public ModelRelatorioWs getRelBancoDeHoras(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idFuncional") String idFuncional) {
        try {
            daoUserLog.criarLog("RelBancoDeHoras", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelBancoDeHoras(headers.getRequestHeader("chave").get(0), idFuncional, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelMarcacao/{mesAno}/{matricula}/{tiporelatorio}")
    public ModelRelatorioWs getRelMarcacao(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("mesAno") String mesAno, @PathParam("matricula") String matricula) {
        try {
            daoUserLog.criarLog("getRelMarcacao", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelMarcacao(headers.getRequestHeader("chave").get(0), mesAno, matricula, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelMarcacaoServidorEmLote/{mesAnoInicial}/{mesAnoFinal}/{matricula}/{tiporelatorio}")
    public ModelRelatorioWs getRelMarcacaoServidorEmLote(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("mesAnoInicial") String mesAnoInicial, @PathParam("mesAnoFinal") String mesAnoFinal, @PathParam("matricula") String matricula) {
        try {
            daoUserLog.criarLog("getRelMarcacao", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelMarcacaoServidorEmLote(headers.getRequestHeader("chave").get(0), mesAnoInicial, mesAnoFinal, matricula, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelMarcacaoServidorEmLotePorSetor/{mesAnoInicial}/{mesAnoFinal}/{idLotacaoSub}/{tiporelatorio}")
    public ModelRelatorioWs getRelMarcacaoServidorEmLotePorSetor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("mesAnoInicial") String mesAnoInicial, @PathParam("mesAnoFinal") String mesAnoFinal, @PathParam("idLotacaoSub") String idLotacaoSub) {
        try {
            daoUserLog.criarLog("getRelMarcacao", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelMarcacaoServidorEmLotePorSetor(headers.getRequestHeader("chave").get(0), mesAnoInicial, mesAnoFinal, idLotacaoSub, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getRelUsuariosAcesso/{idSecretaria}/{orby}/{tiporelatorio}")
    public ModelRelatorioWs getRelUsuariosAcesso(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idSecretaria") String idSecretaria, @PathParam("orby") String orby) {
        try {
            daoUserLog.criarLog("getRelUsuariosAcesso", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelUsuariosAcesso(headers.getRequestHeader("chave").get(0), idSecretaria, orby, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelInfoSec/{idFuncional}/{tiporelatorio}")
    public ModelRelatorioWs getRelInfoSec(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idFuncional") String idFuncional) {
        try {
            daoUserLog.criarLog("getRelInfoSec", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelInfoSec(headers.getRequestHeader("chave").get(0), idFuncional, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelInfoSecSetor/{idFuncional}/{idSecretaria}/{tiporelatorio}")
    public ModelRelatorioWs getRelInfoSecSetor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idFuncional") String idFuncional, @PathParam("idSecretaria") String idSecretaria) {
        try {
            daoUserLog.criarLog("getRelInfoSecSetor", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelInfoSecSetor(headers.getRequestHeader("chave").get(0), idFuncional, idSecretaria, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelInfoEspecialidades/{idFuncional}/{tiporelatorio}")
    public ModelRelatorioWs getRelInfoEspecialidades(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idFuncional") String idFuncional) {
        try {
            daoUserLog.criarLog("getRelInfoEspecialidades", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelInfoEspecialidades(headers.getRequestHeader("chave").get(0), idFuncional, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelInfoRegimeTrabalho/{idFuncional}/{tiporelatorio}")
    public ModelRelatorioWs getRelInfoRegimeTrabalho(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("tiporelatorio") String tipoRelatorio, @PathParam("idFuncional") String idFuncional) {
        try {
            daoUserLog.criarLog("getRelInfoRegimeTrabalho", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();            
            mrw.setUrl(daoRelatorio.getRelInfoRegimeTrabalho(headers.getRequestHeader("chave").get(0), idFuncional, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelDetalhamentoPlanejamento/{idlotacao}/{ordem}/{tiporelatorio}")
    public ModelRelatorioWs getRelDetalhamentoPlanejamento(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacao") String idLotacao, @PathParam("ordem") String ordem, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("getRelDetalhamentoPlanejamento", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioDetalhamentoPlanejamento(headers.getRequestHeader("chave").get(0), idLotacao, ordem, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelDetalhamentoPlanejamentoIndividual/{matricula}/{tiporelatorio}")
    public ModelRelatorioWs getRelDetalhamentoPlanejamentoIndividual(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("matricula") String matricula,  @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("getRelDetalhamentoPlanejamentoIndividual", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelatorioDetalhamentoPlanejamentoIndividual(headers.getRequestHeader("chave").get(0), matricula, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelPlanejamentoSetor/{idlotacaosub}/{tiporelatorio}")
    public ModelRelatorioWs getRelPlanejamentoSetor(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacaosub") String idLotacaoSub, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("getRelPlanejamentoSetor", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelPlanejamentoSetor(headers.getRequestHeader("chave").get(0), idLotacaoSub, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelPlanejamentoSecretaria/{idlotacao}/{tiporelatorio}")
    public ModelRelatorioWs getRelPlanejamentoSecretaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idlotacao") String idLotacao, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("getRelPlanejamentoSecretaria", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelPlanejamentoSecretaria(headers.getRequestHeader("chave").get(0), idLotacao, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelFolhaSintetica/{mesAnoInicial}/{mesAnoFinal}/{refNsds}/{tiporelatorio}")
    public ModelRelatorioWs getRelFolhaSintetica(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("mesAnoInicial") String mesAnoInicial, @PathParam("mesAnoFinal") String mesAnoFinal, @PathParam("refNsds") String refNsds, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("getRelFolhaSintetica", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelFolhaSintetica(headers.getRequestHeader("chave").get(0), mesAnoInicial, mesAnoFinal, refNsds, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getRelExibitPortaria/{nomePortaria}/{tiporelatorio}")
    public ModelRelatorioWs getRelExibitPortaria(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("nomePortaria") String nomePortaria, @PathParam("tiporelatorio") String tipoRelatorio) {
        try {
            daoUserLog.criarLog("getRelExibitPortaria", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelExibitPortaria(headers.getRequestHeader("chave").get(0), nomePortaria, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelLogAcessos/{dataIni}/{dataFim}/{idUser}/{tiporelatorio}")
    public ModelRelatorioWs getRelLogAcessos(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("dataIni") String dataIni, @PathParam("dataFim") String dataFim, @PathParam("idUser") String idUser, @PathParam("tiporelatorio") String tipoRelatorio ) {
        try {
            daoUserLog.criarLog("getRelLogAcessos", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelLogAcessos(headers.getRequestHeader("chave").get(0), dataIni, dataFim, idUser, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelConsignadoCritica/{referenciaNsd}/{tiporelatorio}")
    public ModelRelatorioWs getRelConsignadoCritica(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("referenciaNsd") String referenciaNsd, @PathParam("tiporelatorio") String tipoRelatorio ) {
        try {
            daoUserLog.criarLog("getRelConsignadoCritica", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelConsignadoCritica(headers.getRequestHeader("chave").get(0), referenciaNsd, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getRelConsignadoLiquidacao/{referenciaNsd}/{tiporelatorio}")
    public ModelRelatorioWs getRelConsignadoLiquidacao(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("referenciaNsd") String referenciaNsd, @PathParam("tiporelatorio") String tipoRelatorio ) {
        try {
            daoUserLog.criarLog("getRelConsignadoLiquidacao", "Relatorio", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            ModelRelatorioWs mrw = new ModelRelatorioWs();
            mrw.setUrl(daoRelatorio.getRelConsignadoLiquidacao(headers.getRequestHeader("chave").get(0), referenciaNsd, tipoRelatorio));
            //mrw.setStrHtml(daoRelatorio.getSrtHtml(mrw.getUrl()));
            return mrw;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
}
