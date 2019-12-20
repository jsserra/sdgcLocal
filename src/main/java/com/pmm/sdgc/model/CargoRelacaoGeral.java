package com.pmm.sdgc.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dreges
 */
@Entity
@Table(name="cargos_relacao_geral")
public class CargoRelacaoGeral implements Serializable {
//VAR    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "id_cargos_relacao", referencedColumnName = "id")
    private CargoRelacao cargoRelacao;
    
    @ManyToOne
    @JoinColumn(name = "id_cargos_geral", referencedColumnName = "id")
    private CargoGeral cargoGeral;
//GET SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CargoRelacao getCargoRelacao() {
        return cargoRelacao;
    }

    public void setCargoRelacao(CargoRelacao cargoRelacao) {
        this.cargoRelacao = cargoRelacao;
    }

    public CargoGeral getCargoGeral() {
        return cargoGeral;
    }

    public void setCargoGeral(CargoGeral cargoGeral) {
        this.cargoGeral = cargoGeral;
    }
    
}