/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.ss
 */
package com.pmm.sdgc.model;
import java.io.Serializable;
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
@Table(name = "escala_incompativel")
public class EscalaIncompativel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_escala_tipo_01", referencedColumnName = "id")
    private EscalaTipo escalaTipo1;    
    
    @ManyToOne
    @JoinColumn(name = "id_escala_tipo_02", referencedColumnName = "id")
    private EscalaTipo escalaTipo2;
    

  //GET_SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EscalaTipo getEscalaTipo1() {
        return escalaTipo1;
    }

    public void setEscalaTipo1(EscalaTipo escalaTipo1) {
        this.escalaTipo1 = escalaTipo1;
    }

    public EscalaTipo getEscalaTipo2() {
        return escalaTipo2;
    }

    public void setEscalaTipo2(EscalaTipo escalaTipo2) {
        this.escalaTipo2 = escalaTipo2;
    }


}
