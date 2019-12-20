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
 * @author setinf
 */
@Entity
@Table(name = "especialidade")
public class Especialidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "datahora")
    private LocalDateTime datahora;

    @Column(name = "controle")
    private String controle;

    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "id_especialidade_tipo", referencedColumnName = "id")
    private EspecialidadeTipo especialidadeTipo;

    @ManyToOne
    @JoinColumn(name = "userlog", referencedColumnName = "id")
    private UserLogin userlogin;

    //GET SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public EspecialidadeTipo getEspecialidadeTipo() {
        return especialidadeTipo;
    }

    public void setEspecialidadeTipo(EspecialidadeTipo especialidadeTipo) {
        this.especialidadeTipo = especialidadeTipo;
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
        final Especialidade other = (Especialidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

}
