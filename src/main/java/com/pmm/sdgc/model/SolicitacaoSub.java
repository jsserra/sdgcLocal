/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author raphael
 */
@Entity
@Table(name = "solicitacao_sub")
public class SolicitacaoSub implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao_sub")
    private String id;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "datahora")
    private LocalDateTime data;

    @Column(name = "controle")
    private String controle;

//JOIN    
    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "id_lotacao_sub", referencedColumnName = "id_lotacao_sub")
    private LotacaoSub lotacaoSub;

    @ManyToOne
    @JoinColumn(name = "userlog", referencedColumnName = "id")
    private UserLogin userlogin;

//GET AND SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public LotacaoSub getLotacaoSub() {
        return lotacaoSub;
    }

    public void setLotacaoSub(LotacaoSub lotacaoSub) {
        this.lotacaoSub = lotacaoSub;
    }

    public UserLogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(UserLogin userlogin) {
        this.userlogin = userlogin;
    }

    //COMPARACAO
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SolicitacaoSub other = (SolicitacaoSub) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
