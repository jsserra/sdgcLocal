package com.pmm.sdgc.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author dreges
 */
@Entity
@Table(name="cargos_geral")
public class CargoGeral implements Serializable {
//VAR    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    private String id;
    @Column(name = "nome")
    private String nome;
//GET SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CargoRelacao getCargoRelacao() {
        return cargoRelacao;
    }

    public void setCargoRelacao(CargoRelacao cargoRelacao) {
        this.cargoRelacao = cargoRelacao;
    }
    
    @ManyToOne
    @JoinColumn(name = "id_cargo_relacao", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private CargoRelacao cargoRelacao;
    
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
        final CargoGeral other = (CargoGeral) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CargoGeral{" + "id=" + id + ", nome=" + nome + ", cargoRelacao=" + cargoRelacao + '}';
    }
    
    
    
}
