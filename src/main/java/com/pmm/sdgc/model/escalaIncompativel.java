/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class escalaIncompativel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_escala_tipo_01", referencedColumnName = "id")
    private EspecialidadeTipo especialidadeTipo1;    
    
    @ManyToOne
    @JoinColumn(name = "id_escala_tipo_02", referencedColumnName = "id")
    private EspecialidadeTipo especialidadeTipo2;
    

  //GET_SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EspecialidadeTipo getEspecialidadeTipo1() {
        return especialidadeTipo1;
    }

    public void setEspecialidadeTipo1(EspecialidadeTipo especialidadeTipo1) {
        this.especialidadeTipo1 = especialidadeTipo1;
    }

    public EspecialidadeTipo getEspecialidadeTipo2() {
        return especialidadeTipo2;
    }

    public void setEspecialidadeTipo2(EspecialidadeTipo especialidadeTipo2) {
        this.especialidadeTipo2 = especialidadeTipo2;
    }




}
