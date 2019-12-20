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
 * @author setinf
 */
@Entity
@Table(name = "especialidade_possivel")
public class EspecialidadePossivel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "datahora")
    private LocalDateTime datahora;
    
    @Column(name = "ativo")
    private Boolean ativo;
    
    @Column(name = "controle")
    private String controle;

//    @ManyToOne
//    @JoinColumn(name = "id_cargo_relacao", referencedColumnName = "id")
//    private CargoRelacao cargoRelacao;

    @ManyToOne
    @JoinColumn(name = "id_especialidade_tipo", referencedColumnName = "id")
    private EspecialidadeTipo especialidadeTipo;
    
    @ManyToOne
    @JoinColumn(name = "id_cargo_relacao", referencedColumnName = "id")
    private CargoRelacao cargoRelacao;
    
    @ManyToOne
    @JoinColumn(name = "userlog", referencedColumnName = "id")
    private UserLogin userlogin;

  //GET_SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
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



}
