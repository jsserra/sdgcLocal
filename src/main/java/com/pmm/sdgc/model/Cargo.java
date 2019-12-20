package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.IntegerConverter;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "cargos")
public class Cargo implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "hora_semanal")
    @Convert(converter = IntegerConverter.class)
    private Integer horaSemanal;

    @Column(name = "controle", columnDefinition = "default 2")
    @Convert(converter = IntegerConverter.class)
    private Integer controle;

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

    public Integer getHoraSemanal() {
        return horaSemanal;
    }

    public void setHoraSemanal(Integer horaSemanal) {
        this.horaSemanal = horaSemanal;
    }

    public Integer getControle() {
        return controle;
    }

    public void setControle(Integer controle) {
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

//COMPARACAO
    @Override
    public int hashCode() {
        int hash = 5;
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
        final Cargo other = (Cargo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
