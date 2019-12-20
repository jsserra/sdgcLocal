/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

//import com.pmm.sdgc.model.Funcional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelFuncionalWs {

    private String id;
    private List<ModelEspecialidadeWs> especialidades = new ArrayList();
    private List<ModelRegimeWs> regimes = new ArrayList();
    private List<ModelLotacaoWs> lotacoes = new ArrayList();
    private List<ModelSetorWs> lotacoesSub = new ArrayList();
    private List<ModelSolicitacaoSubWs> solicitacoesSub = new ArrayList();
    private List<ModelOcorrenciaDescWs> ocorrenciaDesc = new ArrayList<>();
    private List<ModelSetorWs> setoresAtivos = new ArrayList();
    private List<ModelPortariaWs> portarias = new ArrayList();
    private List<ModelMensagemWs> mensagens = new ArrayList();
    private List<ModelUserTemplatePermissaoAcessoWs> permissoes = new ArrayList();
    private ModelBiometriaCadastradaWs biometria;

//    private ModelPermissaoAcessoWs permissaoEspecialidade;
//    private ModelPermissaoAcessoWs permissaoRegime;
//    private ModelPermissaoAcessoWs permissaoLotacao;
//    private ModelPermissaoAcessoWs permissaoLotacaoSub;
//    private ModelPermissaoAcessoWs permissaoSolicitacaoSub;
//    private ModelPermissaoAcessoWs permissaoOcorrencia;
//    
//    private ModelPermissaoAcessoWs permissaoSetor;
//    private ModelPermissaoAcessoWs permissaoPortaria;
//    private ModelPermissaoAcessoWs permissaoMensagem;
//    private ModelPermissaoAcessoWs permissaoContabil;
//    private ModelPermissaoAcessoWs permissaoPlanejamento;
//    private ModelPermissaoAcessoWs permissaoRep;
    
    private Boolean temUsuario;
    
    
    


//    public ModelFuncionalWs() {
//        permissaoEspecialidade = new ModelPermissaoAcessoWs();
//        permissaoEspecialidade.setAlterar(Boolean.TRUE);
//        permissaoEspecialidade.setBuscar(Boolean.TRUE);
//        permissaoEspecialidade.setExcluir(Boolean.TRUE);
//        permissaoEspecialidade.setIncluir(Boolean.TRUE);
//        permissaoEspecialidade.setListar(Boolean.TRUE);
//
//        permissaoRegime = new ModelPermissaoAcessoWs();
//        permissaoRegime.setAlterar(Boolean.TRUE);
//        permissaoRegime.setBuscar(Boolean.TRUE);
//        permissaoRegime.setExcluir(Boolean.TRUE);
//        permissaoRegime.setIncluir(Boolean.TRUE);
//        permissaoRegime.setListar(Boolean.TRUE);
//
//        permissaoLotacao = new ModelPermissaoAcessoWs();
//        permissaoLotacao.setAlterar(Boolean.TRUE);
//        permissaoLotacao.setBuscar(Boolean.TRUE);
//        permissaoLotacao.setExcluir(Boolean.TRUE);
//        permissaoLotacao.setIncluir(Boolean.TRUE);
//        permissaoLotacao.setListar(Boolean.TRUE);
//
//        permissaoLotacaoSub = new ModelPermissaoAcessoWs();
//        permissaoLotacaoSub.setAlterar(Boolean.TRUE);
//        permissaoLotacaoSub.setBuscar(Boolean.TRUE);
//        permissaoLotacaoSub.setExcluir(Boolean.TRUE);
//        permissaoLotacaoSub.setIncluir(Boolean.TRUE);
//        permissaoLotacaoSub.setListar(Boolean.TRUE);
//
//        permissaoSolicitacaoSub = new ModelPermissaoAcessoWs();
//        permissaoSolicitacaoSub.setAlterar(Boolean.TRUE);
//        permissaoSolicitacaoSub.setBuscar(Boolean.TRUE);
//        permissaoSolicitacaoSub.setExcluir(Boolean.TRUE);
//        permissaoSolicitacaoSub.setIncluir(Boolean.TRUE);
//        permissaoSolicitacaoSub.setListar(Boolean.TRUE);
//
//        permissaoOcorrencia = new ModelPermissaoAcessoWs();
//        permissaoOcorrencia.setAlterar(Boolean.TRUE);
//        permissaoOcorrencia.setBuscar(Boolean.TRUE);
//        permissaoOcorrencia.setExcluir(Boolean.TRUE);
//        permissaoOcorrencia.setIncluir(Boolean.TRUE);
//        permissaoOcorrencia.setListar(Boolean.TRUE);
//
//        permissaoSetor = new ModelPermissaoAcessoWs();
//        permissaoSetor.setAlterar(Boolean.TRUE);
//        permissaoSetor.setBuscar(Boolean.TRUE);
//        permissaoSetor.setExcluir(Boolean.TRUE);
//        permissaoSetor.setIncluir(Boolean.TRUE);
//        permissaoSetor.setListar(Boolean.TRUE);
//
//        permissaoPortaria = new ModelPermissaoAcessoWs();
//        permissaoPortaria.setAlterar(Boolean.TRUE);
//        permissaoPortaria.setBuscar(Boolean.TRUE);
//        permissaoPortaria.setExcluir(Boolean.TRUE);
//        permissaoPortaria.setIncluir(Boolean.TRUE);
//        permissaoPortaria.setListar(Boolean.TRUE);
//
//        permissaoMensagem = new ModelPermissaoAcessoWs();
//        permissaoMensagem.setAlterar(Boolean.TRUE);
//        permissaoMensagem.setBuscar(Boolean.TRUE);
//        permissaoMensagem.setExcluir(Boolean.TRUE);
//        permissaoMensagem.setIncluir(Boolean.TRUE);
//        permissaoMensagem.setListar(Boolean.TRUE);
//
//        permissaoContabil = new ModelPermissaoAcessoWs();
//        permissaoContabil.setAlterar(Boolean.TRUE);
//        permissaoContabil.setBuscar(Boolean.TRUE);
//        permissaoContabil.setExcluir(Boolean.TRUE);
//        permissaoContabil.setIncluir(Boolean.TRUE);
//        permissaoContabil.setListar(Boolean.TRUE);
//
//        permissaoPlanejamento = new ModelPermissaoAcessoWs();
//        permissaoPlanejamento.setAlterar(Boolean.TRUE);
//        permissaoPlanejamento.setBuscar(Boolean.TRUE);
//        permissaoPlanejamento.setExcluir(Boolean.TRUE);
//        permissaoPlanejamento.setIncluir(Boolean.TRUE);
//        permissaoPlanejamento.setListar(Boolean.TRUE);
//
//        permissaoRep = new ModelPermissaoAcessoWs();
//        permissaoRep.setAlterar(Boolean.TRUE);
//        permissaoRep.setBuscar(Boolean.TRUE);
//        permissaoRep.setExcluir(Boolean.TRUE);
//        permissaoRep.setIncluir(Boolean.TRUE);
//        permissaoRep.setListar(Boolean.TRUE);
//
//        
//    }

    public ModelBiometriaCadastradaWs getBiometria() {
        return biometria;
    }

    public void setBiometria(ModelBiometriaCadastradaWs biometria) {
        this.biometria = biometria;
    }


    
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ModelEspecialidadeWs> getEspecialidades() {
        return especialidades;
    }

    public List<ModelSolicitacaoSubWs> getSolicitacoesSub() {
        return solicitacoesSub;
    }

    public void setSolicitacoesSub(List<ModelSolicitacaoSubWs> solicitacoesSub) {
        this.solicitacoesSub = solicitacoesSub;
    }

    public void setEspecialidades(List<ModelEspecialidadeWs> especialidades) {
        this.especialidades = especialidades;
    }

    public List<ModelRegimeWs> getRegimes() {
        return regimes;
    }

    public void setRegimes(List<ModelRegimeWs> regimes) {
        this.regimes = regimes;
    }

    public List<ModelLotacaoWs> getLotacoes() {
        return lotacoes;
    }

    public void setLotacoes(List<ModelLotacaoWs> lotacoes) {
        this.lotacoes = lotacoes;
    }

    public List<ModelSetorWs> getLotacoesSub() {
        return lotacoesSub;
    }

    public void setLotacoesSub(List<ModelSetorWs> lotacoesSub) {
        this.lotacoesSub = lotacoesSub;
    }

    public List<ModelOcorrenciaDescWs> getOcorrenciaDesc() {
        return ocorrenciaDesc;
    }

    public void setOcorrenciaDesc(List<ModelOcorrenciaDescWs> ocorrenciaDesc) {
        this.ocorrenciaDesc = ocorrenciaDesc;
    }

    public List<ModelSetorWs> getSetoresAtivos() {
        return setoresAtivos;
    }

    public void setSetoresAtivos(List<ModelSetorWs> setoresAtivos) {
        this.setoresAtivos = setoresAtivos;
    }

    public Boolean getTemUsuario() {
        return temUsuario;
    }

    public void setTemUsuario(Boolean temUsuario) {
        this.temUsuario = temUsuario;
    }

    public List<ModelPortariaWs> getPortarias() {
        return portarias;
    }

    public void setPortarias(List<ModelPortariaWs> portarias) {
        this.portarias = portarias;
    }

    public List<ModelMensagemWs> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<ModelMensagemWs> mensagens) {
        this.mensagens = mensagens;
    }

//    public ModelPermissaoAcessoWs getPermissaoEspecialidade() {
//        return permissaoEspecialidade;
//    }
//
//    public void setPermissaoEspecialidade(ModelPermissaoAcessoWs permissaoEspecialidade) {
//        this.permissaoEspecialidade = permissaoEspecialidade;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoRegime() {
//        return permissaoRegime;
//    }
//
//    public void setPermissaoRegime(ModelPermissaoAcessoWs permissaoRegime) {
//        this.permissaoRegime = permissaoRegime;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoLotacao() {
//        return permissaoLotacao;
//    }
//
//    public void setPermissaoLotacao(ModelPermissaoAcessoWs permissaoLotacao) {
//        this.permissaoLotacao = permissaoLotacao;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoLotacaoSub() {
//        return permissaoLotacaoSub;
//    }
//
//    public void setPermissaoLotacaoSub(ModelPermissaoAcessoWs permissaoLotacaoSub) {
//        this.permissaoLotacaoSub = permissaoLotacaoSub;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoSolicitacaoSub() {
//        return permissaoSolicitacaoSub;
//    }
//
//    public void setPermissaoSolicitacaoSub(ModelPermissaoAcessoWs permissaoSolicitacaoSub) {
//        this.permissaoSolicitacaoSub = permissaoSolicitacaoSub;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoOcorrencia() {
//        return permissaoOcorrencia;
//    }
//
//    public void setPermissaoOcorrencia(ModelPermissaoAcessoWs permissaoOcorrencia) {
//        this.permissaoOcorrencia = permissaoOcorrencia;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoSetor() {
//        return permissaoSetor;
//    }
//
//    public void setPermissaoSetor(ModelPermissaoAcessoWs permissaoSetor) {
//        this.permissaoSetor = permissaoSetor;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoPortaria() {
//        return permissaoPortaria;
//    }
//
//    public void setPermissaoPortaria(ModelPermissaoAcessoWs permissaoPortaria) {
//        this.permissaoPortaria = permissaoPortaria;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoMensagem() {
//        return permissaoMensagem;
//    }
//
//    public void setPermissaoMensagem(ModelPermissaoAcessoWs permissaoMensagem) {
//        this.permissaoMensagem = permissaoMensagem;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoContabil() {
//        return permissaoContabil;
//    }
//
//    public void setPermissaoContabil(ModelPermissaoAcessoWs permissaoContabil) {
//        this.permissaoContabil = permissaoContabil;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoPlanejamento() {
//        return permissaoPlanejamento;
//    }
//
//    public void setPermissaoPlanejamento(ModelPermissaoAcessoWs permissaoPlanejamento) {
//        this.permissaoPlanejamento = permissaoPlanejamento;
//    }
//
//    public ModelPermissaoAcessoWs getPermissaoRep() {
//        return permissaoRep;
//    }
//
//    public void setPermissaoRep(ModelPermissaoAcessoWs permissaoRep) {
//        this.permissaoRep = permissaoRep;
//    }
//

    public List<ModelUserTemplatePermissaoAcessoWs> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<ModelUserTemplatePermissaoAcessoWs> permissoes) {
        this.permissoes = permissoes;
    }
   
    

}