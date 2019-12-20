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
@Table(name = "solicitacao")
public class Solicitacao implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "id_lotacao", referencedColumnName = "id_lotacao")
    @NotFound(action = NotFoundAction.IGNORE)
    private Lotacao lotacao;

    @ManyToOne
    @JoinColumn(name = "userlog", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin login;

    @Column(name = "pender")
    private Integer pender;

    @Column(name = "datahora")
    private LocalDateTime data;

    @Column(name = "controle")
    private String controle;

    @Column(name = "ativo")
    private Boolean ativo;

    //GET SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }

    public UserLogin getLogin() {
        return login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

    public Integer getPender() {
        return pender;
    }

    public void setPender(Integer pender) {
        this.pender = pender;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    //COMPARACAO
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Solicitacao other = (Solicitacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
