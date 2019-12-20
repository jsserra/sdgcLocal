/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @author mraphael
 */
@Entity
@Table(name = "escala")
public class Escala implements Serializable {

    //VAR
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "ativo")
    private Boolean ativo;
    
    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    //JOIN    
    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "userLogin", referencedColumnName = "id")
    private UserLogin userLogin;

    @ManyToOne
    @JoinColumn(name = "id_escala_tipo", referencedColumnName = "id")
    private EscalaTipo escalaTipo;

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


    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public EscalaTipo getEscalaTipo() {
        return escalaTipo;
    }

    public void setEscalaTipo(EscalaTipo escalaTipo) {
        this.escalaTipo = escalaTipo;
    }

    @Override
    public String toString() {
        return "Escala{" + "id=" + id + ", ativo=" + ativo + ", dataHora=" + dataHora + ", funcional=" + funcional + ", userLogin=" + userLogin + ", escalaTipo=" + escalaTipo + '}';
    }
    
    

   
}
