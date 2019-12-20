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
@Table(name = "cargos_com")
public class CargoCom implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sec_com")
    private String horaSemanal;
    @Column(name = "controle")
    private String controle;
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

    public String getHoraSemanal() {
        return horaSemanal;
    }

    public void setHoraSemanal(String horaSemanal) {
        this.horaSemanal = horaSemanal;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public CargoGeral getCargoGeral() {
        return cargoGeral;
    }

    public void setCargoGeral(CargoGeral cargoGeral) {
        this.cargoGeral = cargoGeral;
    }
    
    @ManyToOne
    @JoinColumn(name = "id_cargo_geral", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private CargoGeral cargoGeral;
//É IGUAL
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
        final CargoCom other = (CargoCom) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
