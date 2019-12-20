/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

//import com.pmm.sdgc.converter.LocalDateConverter;
import com.pmm.sdgc.converter.LocalDateConverter1;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ajuliano
 */

@Entity
@Table(name = "data_frequencia")
public class DataFrequencia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_data_frequencia")
    private Integer id;
    
  
    @Column(name = "data_frequencia")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate dataFrequencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataFrequencia() {
        return dataFrequencia;
    }

    public void setDataFrequencia(LocalDate dataFrequencia) {
        this.dataFrequencia = dataFrequencia;
    }

 
    //COMPARACAO
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
        final DataFrequencia other = (DataFrequencia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
