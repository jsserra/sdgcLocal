package com.pmm.sdgc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dreges
 */
@Entity
@Table(name="usuarios_acessos")
public class Acessos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id_usuarios_acesso")
    private String id;
    @Column(name="CPF")
    private String cpf;
    @Column(name="nivel_acesso_recenseamento")
    private String naRecenseamento;
    @Column(name="nivel_acesso_gestao")
    private String naGestao;
    @Column(name="nivel_acesso_frequencia")
    private String naFrequencia;
    @Column(name="nivel_acesso_ocorrencia")
    private String naOcorrencia;
    @Column(name="nivel_acesso_gerenciar")
    private String naGerenciar;
    @Column(name="nivel_acesso_avaliacao")
    private String naAvaliacao;
    @Column(name="nivel_acesso_variaveis")
    private String naVariaveis;
    @Column(name="nivel_acesso_portaria")
    private String naPortaria;
    @Column(name="nivel_acesso_planejamento")
    private String naPlanejamento;
    @Column(name="nivel_acesso_nsd")
    private String naNSD;
    @Column(name="nivel_acesso_regime")
    private String naRegime;
    @Column(name="nivel_acesso_limitador")
    private String naLimitador;
    @Column(name="nivel_acesso_macprev")
    private String naMacprev;
    @Column(name="nivel_acesso_treinamento")
    private String naTreinamento;
    @Column(name="nivel_acesso_orcamentario")
    private String naOrcamentario;
    @Column(name="nivel_acesso_especialidade")
    private String naEspecialidade;
    @Column(name="nivel_acesso_fundeb")
    private String naFundeb;
    @Column(name="nivel_acesso_cnsg")
    private String naCnsg;
    @Column(name="nivel_acesso_folha_sintetica")
    private String naFolhaSintetica;
    @Column(name="nivel_acesso_rap")
    private String naRep;
    @Column(name="id_lotacao_usuario")
    private String lotacao;
    @Column(name="id_sub_lotacao_usuario")
    private String setor;
    @Column(name="usuario_admin_acesso")
    private String modificadoPor;
    @Column(name="usuario_acesso_data_hora")
    private String modificadoEm;
}
