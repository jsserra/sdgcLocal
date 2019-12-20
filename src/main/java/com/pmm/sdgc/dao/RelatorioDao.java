package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Configuracao;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.Solicitacao;
import com.pmm.sdgc.model.UserLogin;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alan
 */
@Stateless
public class RelatorioDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    FuncionalDao daoFuncional;

    @EJB
    SolicitacaoDao daoSolicitacao;

    @EJB
    LotacaoSubDao daoLotacaoSub;

    public Configuracao getParametro() {
        Query q = em.createQuery("select c from Configuracao c");
        List<Configuracao> parametros = q.getResultList();
        if (parametros.isEmpty()) {
            return null;
        }

        return parametros.get(0);
    }

    public String getUrlBasica(String nomeRelatorio, String tipoRelatorio, String chave) throws Exception {
        Configuracao p = getParametro();

        if (p == null) {
            throw new Exception("O sistema não está com os parâmetros de relatórios definidos");
        }

        UserLogin usuario = daoUserLogin.getUserLoginPorChave(chave);

        if (!(tipoRelatorio.equals("html") || tipoRelatorio.equals("csv") || tipoRelatorio.equals("pdf") || tipoRelatorio.equals("xls"))) {
            throw new Exception("Tipo de relatório inválido. Só é permitido html, pdf, csv ou xls");
        }

        String url = "http://" + p.getIpServidorRelatorio() + "/jasperserver/rest_v2/reports/SDGC/" + nomeRelatorio + "." + tipoRelatorio;
        url += "?j_username=" + p.getUsuarioRelatorio();
        url += "&j_password=" + p.getSenhaUsuarioRelatorio();
        url += "&nomeusuario=" + usuario.getNome().replaceAll(" ", "%20");
        url += "&urlBrasao=" + p.getUrlBrasao();
        url += "&urlAssinatura=" + p.getUrlAssinatura();
        url += "&urlFoto=" + p.getUrlFoto();
        url += "&urlPortaria=" + p.getUrlPortaria();
        url += "&userTimezone=America%2FSao_Paulo";

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelatorioServidoresPorSetor(String chave, String idLotacaoSub, String ordem, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("ServidoresPorSetor", tipoRelatorio, chave);

        url += "&idlotacaosub=" + idLotacaoSub;
        url += "&ordem=" + ordem;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelatorioServidoresComissionadosPorSetor(String chave, String idLotacaoSub, String ordem, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("ServidoresComissionadosPorSetor", tipoRelatorio, chave);

        url += "&idlotacaosub=" + idLotacaoSub;
        url += "&ordem=" + ordem;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }
    
    public String getRelatorioBiometriaCadastradaSetor(String chave, String idLotacaoSub, String ordem, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("BiometriaCadastradaSetor", tipoRelatorio, chave);

        url += "&idlotacaosub=" + idLotacaoSub;
        url += "&ordem=" + ordem;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }
    
    public String getRelatorioBiometriaCadastradaSecretaria(String chave, String idLotacao, String ordem, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("BiometriaCadastradaSecretaria", tipoRelatorio, chave);

        url += "&idlotacao=" + idLotacao;
        url += "&ordem=" + ordem;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelatorioDetalhamentoPlanejamento(String chave, String idLotacaoSub, String ordem, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("DetalhamentoPlanejamento", tipoRelatorio, chave);

        url += "&idlotacaosub=" + idLotacaoSub;
        url += "&ordem=" + ordem;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }
    public String getRelatorioDetalhamentoPlanejamentoIndividual(String chave, String matricula, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("DetalhamentoPlanejamentoIndividual", tipoRelatorio, chave);

        url += "&matricula=" + matricula;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelatorioServidoresPorSecretaria(String chave, String idLotacao, String ordem, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ServidoresPorSecretaria", tipoRelatorio, chave);

        url += "&idlotacao=" + idLotacao;
        url += "&ordem=" + ordem;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }
    public String getRelatorioServidoresPorCargo(String chave, String idCargoGeral, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ServidoresPorCargo", tipoRelatorio, chave);

        url += "&idcargogeral=" + idCargoGeral;
    

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelServidoresComissionadosPorSecretaria(String chave, String idLotacao, String ordem, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ServidoresComissionadosPorSecretaria", tipoRelatorio, chave);

        url += "&idlotacao=" + idLotacao;
        url += "&ordem=" + ordem;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelSetoresComTotalServidores(String chave, String idLotacao, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("SetoresComTotalServidores", tipoRelatorio, chave);

        url += "&idlotacao=" + idLotacao;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelSecretariasComTotalServidores(String chave, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("SecretariasComTotalServidores", tipoRelatorio, chave);

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelOcorrenciasPorSetor(String chave, String idLotacaoSub, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("OcorrenciasPorSetor", tipoRelatorio, chave);
        url += "&idlotacaosub=" + idLotacaoSub;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }
    public String getRelOcorrenciasPorPeriodo(String chave, String matricula, String data1, String data2, String idOcoDesc,String tipoRelatorio) throws Exception {
        System.out.println("idOcoDesc: " + idOcoDesc.isEmpty());
        
        String url;
        
        if(idOcoDesc.isEmpty()){
           url = getUrlBasica("OcorrenciasPorPeriodo", tipoRelatorio, chave); 
        }else{
           url = getUrlBasica("OcorrenciasPorPeriodoPorTipo", tipoRelatorio, chave);
           url += "&idOcoDesc=" + idOcoDesc;
        }
        
        url += "&matricula=" + matricula;
        url += "&data1=" + data1;
        url += "&data2=" + data2;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelOcorrenciasPorSecretaria(String chave, String idLotacao, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("OcorrenciasPorSecretaria", tipoRelatorio, chave);
        url += "&idlotacao=" + idLotacao;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelLancamentoIndividualPorPeriodo(String chave, String anoMes, String cpf, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("LancamentoIndividualPorPeriodo", tipoRelatorio, chave);
        //pega o usuario por CPF
        UserLogin usuario = daoUserLogin.getUserLoginPorCPf(cpf);
        //Separa data
        String mes = anoMes.split("-")[1];
        String ano = anoMes.split("-")[0];
        //Constroi URL
        url += "&iduserlogin=" + usuario.getId();
        url += "&mesAno=" + mes + "-" + ano;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelFichaFuncional(String chave, String idPessoal, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("FichaFuncional", tipoRelatorio, chave);
        url += "&idpessoal=" + idPessoal;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelPortarias(String chave, String idFuncional, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("Portarias", tipoRelatorio, chave);
        url += "&idfuncional=" + idFuncional;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelNsd(String chave, String codNsd, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("NSD", tipoRelatorio, chave);
        url += "&codNsd=" + codNsd;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelNsds(String chave, String codNsd, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("NSDS", tipoRelatorio, chave);
        url += "&codNsd=" + codNsd;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelNsda(String chave, String codNsd, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("NSDA", tipoRelatorio, chave);
        url += "&codNsd=" + codNsd;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelEmpenhoAnaliticoMacprev(String chave, String referencia, String codganNsda, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("EmpenhoAnaliticoMacprev", tipoRelatorio, chave);
        url += "&referencia=" + referencia;
        url += "&codgan=" + codganNsda;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelEmpenhoSinteticoMacprev(String chave, String referencia, String codganNsda, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("EmpenhoSinteticoMacprev", tipoRelatorio, chave);
        url += "&dataNsd=" + referencia;
        url += "&codgan_nsda=" + codganNsda;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelDdoSinteticoMacprev(String chave, String referencia, String codganNsda, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("DdoSinteticoMacprev", tipoRelatorio, chave);
        url += "&dataNsd=" + referencia;
        url += "&codgan=" + codganNsda;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelDdoAnaliticoMacprev(String chave, String referencia, String codganNsda, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("DdoAnaliticoMacprev", tipoRelatorio, chave);
        url += "&dataNsd=" + referencia;
        url += "&codgan=" + codganNsda;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelExportarFundebNsd(String chave, String referenciaNsd, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ExportarFundebNSD", tipoRelatorio, chave);
        url += "&referencia_nsd=" + referenciaNsd;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelExportarMacprevNsd(String chave, String referenciaNsd, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ExportarMacprevNSD", tipoRelatorio, chave);
        url += "&referenciaNsd=" + referenciaNsd;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelBancoDeHoras(String chave, String idFuncional, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("BancoDeHoras", tipoRelatorio, chave);
        url += "&idFuncional=" + idFuncional;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelContraCheque(String chave, String referenciaNsda, String matricula, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("Contracheque", tipoRelatorio, chave);

        String[] nsda = referenciaNsda.split("-");
        String nsda_Formatada = nsda[1] + "%2F" + nsda[0];

        Funcional funcional = daoFuncional.getUmFuncionalPorMatricula(matricula);

        url += "&cpf=" + funcional.getPessoa().getCpf();
        url += "&nome=" + funcional.getPessoa().getNome().replaceAll(" ", "%20");
        url += "&nome_curso_grau_max=" + funcional.getPessoa().getCursoMax().getNome().replaceAll(" ", "%20");
        if (funcional.getPessoa().getNascimento() == null) {
            url += "&nascimento=00%2F00/0000";
        } else {
            url += "&nascimento=" + funcional.getPessoa().getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).replaceAll("-", "%2F");
        }
        //url += "&nascimento=" + funcional.getPessoa().getNascimento().format(DateTimeFormatter.ofPattern("dd%2FMM%2Fyyyy"));
        url += "&matricula=" + funcional.getMatricula();
        if (funcional.getDataAdmissao() == null) {
            url += "&data_admissao=00%2F00%2F0000";
        } else {
            url += "&data_admissao=" + funcional.getDataAdmissao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replaceAll("-", "%2F");
            //System.out.println(url);
        }
        //url += "&data_admissao=" + funcional.getDataAdmissao().format(DateTimeFormatter.ofPattern("dd%2FMM%2Fyyyy"));
        url += "&nome_vinculo=" + funcional.getVinculo().getNome().replaceAll(" ", "%20");
        if (funcional.getPermutado().isEmpty()) {
            url += "&permutado=NAO";
        } else {
            url += "&permutado=" + funcional.getPermutado();
        }
        url += "&nome_regime=" + funcional.getRegime().getNome().replaceAll(" ", "%20");
        url += "&situacao=" + funcional.getSituacao().getNome();
        url += "&hora_semanal_cargo=" + funcional.getCargo().getHoraSemanal();
        url += "&referencia_nsda=" + nsda_Formatada;

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelFolhaPonto(String chave, String matricula, String mesAno, String tipoRelatorio, String idLotacaoSub) throws Exception {
        String url = getUrlBasica("FolhaPonto", tipoRelatorio, chave);

        Funcional funcional = daoFuncional.getUmFuncionalPorMatricula(matricula);

        LocalDate dInicio = LocalDate.parse(mesAno + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dFim = dInicio.plusMonths(1).minusDays(1);

        url += "&cpf=" + funcional.getPessoa().getCpf();
        url += "&nome=" + funcional.getPessoa().getNome().replaceAll(" ", "%20");
        url += "&matricula=" + funcional.getMatricula();
        url += "&cargofuncao=" + funcional.getCargo().getNome().replaceAll(" ", "%20");
        if (!(funcional.getCargoCom() == null)) {
            url += "&funcaogratificada=" + funcional.getCargoCom().getNome().replaceAll(" ", "%20");
            //System.out.println(funcional.getCargoCom().getNome().replaceAll(" ", "%20"));
        }
        if ((funcional.getCargoCom().getNome()).equals("NAO")) {
            url += "&horasemanal=" + funcional.getCargo().getHoraSemanal();
        } else {
            url += "&horasemanal=" + funcional.getCargoCom().getHoraSemanal();
        }
        url += "&data_inicio=" + dInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&data_fim=" + dFim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&nome_mes=" + nomeMes(dInicio.getMonthValue());
        url += "&numero_ano=" + dInicio.getYear();

        List<Solicitacao> solicitacoes = daoSolicitacao.getSolicitacaoAtiva(funcional.getId());
        if (!(solicitacoes.isEmpty())) {
            url += "&secretaria=" + solicitacoes.get(0).getLotacao().getNome().replaceAll(" ", "%20");
        }

        LotacaoSub lotacaoSub = daoLotacaoSub.getLotacaoSubPorId(idLotacaoSub);

        url += "&setor=" + lotacaoSub.getNome().replaceAll(" ", "%20");

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelFolhaPontoEmLoteDoServidor(String chave, String matricula, String idLotacaoSub, String mesAnoInicial, String mesAnoFinal, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("FolhaPonto", tipoRelatorio, chave);

        Funcional funcional = daoFuncional.getUmFuncionalPorMatricula(matricula);

        LocalDate dInicio = LocalDate.parse(mesAnoInicial + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate dFimAux = LocalDate.parse(mesAnoFinal + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate dFim = dFimAux.plusMonths(1).minusDays(1);

        url += "&cpf=" + funcional.getPessoa().getCpf();
        url += "&nome=" + funcional.getPessoa().getNome().replaceAll(" ", "%20");
        url += "&matricula=" + funcional.getMatricula();
        url += "&cargofuncao=" + funcional.getCargo().getNome().replaceAll(" ", "%20");
        if (!(funcional.getCargoCom() == null)) {
            url += "&funcaogratificada=" + funcional.getCargoCom().getNome().replaceAll(" ", "%20");
            //System.out.println(funcional.getCargoCom().getNome().replaceAll(" ", "%20"));
        }
        if ((funcional.getCargoCom().getNome()).equals("NAO")) {
            url += "&horasemanal=" + funcional.getCargo().getHoraSemanal();
        } else {
            url += "&horasemanal=" + funcional.getCargoCom().getHoraSemanal();
        }
        url += "&data_inicio=" + dInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&data_fim=" + dFim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&nome_mes=" + nomeMes(dInicio.getMonthValue());
        url += "&numero_ano=" + dInicio.getYear();

        List<Solicitacao> solicitacoes = daoSolicitacao.getSolicitacaoAtiva(funcional.getId());
        if (!(solicitacoes.isEmpty())) {
            url += "&secretaria=" + solicitacoes.get(0).getLotacao().getNome().replaceAll(" ", "%20");
        }

        LotacaoSub lotacaoSub = daoLotacaoSub.getLotacaoSubPorId(idLotacaoSub);

        url += "&setor=" + lotacaoSub.getNome().replaceAll(" ", "%20");

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelFolhaPontoEmLote(String chave, String idLotacaoSub, String mesAno, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("FolhaDePontoEmLote", tipoRelatorio, chave);
        LocalDate dInicio = LocalDate.parse(mesAno + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dFim = dInicio.plusMonths(1);
        url += "&idLotacaoSub=" + idLotacaoSub;
        url += "&data_inicio=" + dInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&data_fim=" + dFim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&nome_mes=" + nomeMes(dInicio.getMonthValue());
        url += "&numero_ano=" + dInicio.getYear();

        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelMarcacao(String chave, String mesAno, String matricula, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("Marcacao", tipoRelatorio, chave);
        Funcional funcional = daoFuncional.getUmFuncionalPorMatricula(matricula);
        LocalDate dInicio = LocalDate.parse(mesAno + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dFim = dInicio.plusMonths(1).minusDays(1);
        url += "&cpf=" + funcional.getPessoa().getCpf();
        url += "&nome=" + funcional.getPessoa().getNome().replaceAll(" ", "%20");
        url += "&matricula=" + funcional.getMatricula();
        url += "&cargofuncao=" + funcional.getCargo().getNome().replaceAll(" ", "%20");
        if (!(funcional.getCargoCom() == null)) {
            url += "&funcaogratificada=" + funcional.getCargoCom().getNome().replaceAll(" ", "%20");
        }
        if ((funcional.getCargoCom().getNome()).equals("NAO")) {
            url += "&horasemanal=" + funcional.getCargo().getHoraSemanal();
        } else {
            url += "&horasemanal=" + funcional.getCargoCom().getHoraSemanal();
        }
        url += "&data_inicio=" + dInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&data_fim=" + dFim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Solicitacao> solicitacoes = daoSolicitacao.getSolicitacaoAtiva(funcional.getId());
        if (!(solicitacoes.isEmpty())) {
            url += "&secretaria=" + solicitacoes.get(0).getLotacao().getNome().replaceAll(" ", "%20");
        }
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelMarcacaoServidorEmLote(String chave, String mesAnoInicial, String mesAnoFinal, String matricula, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("Marcacao", tipoRelatorio, chave);

        Funcional funcional = daoFuncional.getUmFuncionalPorMatricula(matricula);

        LocalDate dInicio = LocalDate.parse(mesAnoInicial + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate dFimAux = LocalDate.parse(mesAnoFinal + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate dFim = dFimAux.plusMonths(1).minusDays(1);

        url += "&cpf=" + funcional.getPessoa().getCpf();
        url += "&nome=" + funcional.getPessoa().getNome().replaceAll(" ", "%20");
        url += "&matricula=" + funcional.getMatricula();
        url += "&cargofuncao=" + funcional.getCargo().getNome().replaceAll(" ", "%20");
        if (!(funcional.getCargoCom() == null)) {
            url += "&funcaogratificada=" + funcional.getCargoCom().getNome().replaceAll(" ", "%20");
        }
        if ((funcional.getCargoCom().getNome()).equals("NAO")) {
            url += "&horasemanal=" + funcional.getCargo().getHoraSemanal();
        } else {
            url += "&horasemanal=" + funcional.getCargoCom().getHoraSemanal();
        }
        url += "&data_inicio=" + dInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&data_fim=" + dFim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Solicitacao> solicitacoes = daoSolicitacao.getSolicitacaoAtiva(funcional.getId());
        if (!(solicitacoes.isEmpty())) {
            url += "&secretaria=" + solicitacoes.get(0).getLotacao().getNome().replaceAll(" ", "%20");
        }
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }
    
    public String getRelMarcacaoServidorEmLotePorSetor(String chave, String mesAnoInicial, String mesAnoFinal, String idLotacaoSub, String tipoRelatorio) throws Exception {

        String url = getUrlBasica("MarcacaoEmLotePorSetor", tipoRelatorio, chave);

        LocalDate dInicio = LocalDate.parse(mesAnoInicial + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate dFimAux = LocalDate.parse(mesAnoFinal + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate dFim = dFimAux.plusMonths(1).minusDays(1);
        
        url += "&data_inicio=" + dInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&data_fim=" + dFim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        url += "&idLotacaoSub=" + idLotacaoSub;
        
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        
        return url;
    }

    public String getRelUsuariosAcesso(String chave, String idSecretaria, String orby, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("UsuariosAcesso", tipoRelatorio, chave);
        url += "&idSecretaria=" + idSecretaria;
        url += "&orby=" + orby;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelInfoSec(String chave, String idFuncional, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("InfoSec", tipoRelatorio, chave);
        url += "&idFuncional=" + idFuncional;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelInfoSecSetor(String chave, String idFuncional, String idSecretaria, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("InfoSecSetor", tipoRelatorio, chave);
        url += "&idFuncional=" + idFuncional;
        url += "&idSecretaria=" + idSecretaria;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelInfoEspecialidades(String chave, String idFuncional, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("InfoEspecialidades", tipoRelatorio, chave);
        url += "&idFuncional=" + idFuncional;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelInfoRegimeTrabalho(String chave, String idFuncional, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("InfoRegimeTrabalho", tipoRelatorio, chave);
        url += "&idFuncional=" + idFuncional;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelFolhaSintetica(String chave, String mesAnoInicial, String mesAnoFinal, String refNsds, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("FolhaSintetica", tipoRelatorio, chave);
        url += "&dataInicial=" + mesAnoInicial + "-" + "01";
        url += "&dataFinal=" + mesAnoFinal + "-" + "01";
        if(!refNsds.equals("%")){
           refNsds = refNsds.split("-")[1] + "%2F" + refNsds.split("-")[0]; 
        }else{
           refNsds = "%25";
        }
        url += "&refNsds=" + refNsds;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelPlanejamentoSetor(String chave, String idLoacaoSub, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("PlanejamentoSetor", tipoRelatorio, chave);
        url += "&setor=" + idLoacaoSub;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelPlanejamentoSecretaria(String chave, String idLoacao, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("PlanejamentoSecretaria", tipoRelatorio, chave);
        url += "&secretaria=" + idLoacao;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelExibitPortaria(String chave, String nomePortaria, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ExibirPortaria", tipoRelatorio, chave);
        url += "&nomePortaria=" + nomePortaria;
        url = Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return url;
    }

    public String getRelLogAcessos(String chave, String dataIni, String dataFim, String idUser, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("UserLog", tipoRelatorio, chave);
        url += "&dataInicial=" + dataIni;
        url += "&dataFinal=" + dataFim;
        url += "&login=" + idUser;
        return url;
    }

    public String getRelConsignadoCritica(String chave, String referenciaNsd, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ConsiguinadoCritica", tipoRelatorio, chave);

        String mes = referenciaNsd.split("-")[1];
        String ano = referenciaNsd.split("-")[0];

        url += "&referenciaNsd=" + mes + "%2F" + ano;
        return url;
    }

    public String getRelConsignadoLiquidacao(String chave, String referenciaNsd, String tipoRelatorio) throws Exception {
        String url = getUrlBasica("ConsignadoLiquidacao", tipoRelatorio, chave);
        url += "&referenciaNsd=" + referenciaNsd;
        return url;
    }

    public String getSrtHtml(String url) throws Exception {

        String resp;
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(url)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
            resp = "";
            String line;
            while ((line = reader.readLine()) != null) {
                resp += line;
            }
            reader.close();
            return resp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Arquivo não encontrado, permissão negada.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Ocorreu um erro ao obter o relatório.";
        }

    }

    public String nomeMes(Integer mes) {
        if (mes == 1) {
            return "JANEIRO";
        }
        if (mes == 2) {
            return "FEVEREIRO";
        }
        if (mes == 3) {
            return "MAR%C3%87O";
        }
        if (mes == 4) {
            return "ABRIL";
        }
        if (mes == 5) {
            return "MAIO";
        }
        if (mes == 6) {
            return "JUNHO";
        }
        if (mes == 7) {
            return "JULHO";
        }
        if (mes == 8) {
            return "AGOSTO";
        }
        if (mes == 9) {
            return "SETEMBRO";
        }
        if (mes == 10) {
            return "OUTUBRO";
        }
        if (mes == 11) {
            return "NOVEMBRO";
        }
        return "DEZEMBRO";
    }

}