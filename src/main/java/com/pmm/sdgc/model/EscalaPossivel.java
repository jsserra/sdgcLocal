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
 * @author dreges
 */
@Entity
@Table(name = "escala_possivel")
public class EscalaPossivel implements Serializable {

    //VAR
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;
    
    @Column(name = "dataHora")
    private LocalDateTime dataHora;
    
    @Column(name = "controle")
    private Boolean controle;
    
    //JOIN    
    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    private Funcional funcional;


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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getControle() {
        return controle;
    }

    public void setControle(Boolean controle) {
        this.controle = controle;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public EscalaTipo getEscalaTipo() {
        return escalaTipo;
    }

    public void setEscalaTipo(EscalaTipo escalaTipo) {
        this.escalaTipo = escalaTipo;
    }
   
}
