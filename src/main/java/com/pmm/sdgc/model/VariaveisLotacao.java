/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "variaveisLotacao")
public class VariaveisLotacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_lotacao", referencedColumnName = "id_lotacao")
    @NotFound(action = NotFoundAction.IGNORE)
    private Lotacao lotacao;

    @ManyToOne
    @JoinColumn(name = "id_variaveis_desc", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private VariaveisDesc variaveisDesc;

    @Column(name = "periodoFolha")
    private LocalDate periodoFolha;

    @Column(name = "dataHora")
    private Integer dataHora;

    @Column(name = "fechado")
    private Boolean fechado;

    @ManyToOne
    @JoinColumn(name = "fechadoCodigo", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private VariaveisCodigoValidade fechamentoCodigo;

    @Column(name = "fechadoForcado")
    private Boolean fechadoForcado;

    @Column(name = "fechadoDataHora")
    private LocalDateTime fechadoDataHora;

    @ManyToOne
    @JoinColumn(name = "fechadoUserLogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin fechadoUserLogin;

    @ManyToOne
    @JoinColumn(name = "userLogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin login;

    @Column(name = "ativo")
    private Boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }

    public VariaveisDesc getVariaveisDesc() {
        return variaveisDesc;
    }

    public void setVariaveisDesc(VariaveisDesc variaveisDesc) {
        this.variaveisDesc = variaveisDesc;
    }

    public LocalDate getPeriodoFolha() {
        return periodoFolha;
    }

    public void setPeriodoFolha(LocalDate periodoFolha) {
        this.periodoFolha = periodoFolha;
    }

    public Integer getDataHora() {
        return dataHora;
    }

    public void setDataHora(Integer dataHora) {
        this.dataHora = dataHora;
    }



    public Boolean getFechado() {
        return fechado;
    }

    public void setFechado(Boolean fechado) {
        this.fechado = fechado;
    }

    public VariaveisCodigoValidade getFechamentoCodigo() {
        return fechamentoCodigo;
    }

    public void setFechamentoCodigo(VariaveisCodigoValidade fechamentoCodigo) {
        this.fechamentoCodigo = fechamentoCodigo;
    }

    public Boolean getFechadoForcado() {
        return fechadoForcado;
    }

    public void setFechadoForcado(Boolean fechadoForcado) {
        this.fechadoForcado = fechadoForcado;
    }

    public LocalDateTime getFechadoDataHora() {
        return fechadoDataHora;
    }

    public void setFechadoDataHora(LocalDateTime fechadoDataHora) {
        this.fechadoDataHora = fechadoDataHora;
    }

    public UserLogin getFechadoUserLogin() {
        return fechadoUserLogin;
    }

    public void setFechadoUserLogin(UserLogin fechadoUserLogin) {
        this.fechadoUserLogin = fechadoUserLogin;
    }

    public UserLogin getLogin() {
        return login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final VariaveisLotacao other = (VariaveisLotacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
