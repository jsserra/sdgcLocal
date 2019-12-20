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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author ajuliano
 */
@Entity
@Table(name = "requerimento")
public class Requerimento implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column
    private String protocolo;

    @ManyToOne
    @JoinColumn(name = "id_requerimento_status", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private RequerimentoStatus reqStatus;

    @ManyToOne
    @JoinColumn(name = "id_requerimento_solicitacao", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private RequerimentoSolicitacao reqSolicitacao;
    
    @ManyToOne
    @JoinColumn(name = "userLogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin login;

    @Column(name = "datahora")
    private LocalDateTime data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public RequerimentoStatus getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(RequerimentoStatus reqStatus) {
        this.reqStatus = reqStatus;
    }

    public RequerimentoSolicitacao getReqSolicitacao() {
        return reqSolicitacao;
    }

    public void setReqSolicitacao(RequerimentoSolicitacao reqSolicitacao) {
        this.reqSolicitacao = reqSolicitacao;
    }

    public UserLogin getLogin() {
        return login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Requerimento other = (Requerimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }




}
