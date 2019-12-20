package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.LocalDateConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @author acg
 */
@Entity
@Table(name = "portaria_relacao")
public class PortariaRelacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "dataHora")
    @Convert(converter = LocalDateConverter.class)
    private LocalDateTime dataHora;

    @Column(name = "controle")
    private String controle;

    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "id_portaria", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Portaria portaria;

    @ManyToOne
    @JoinColumn(name = "id_portaria_tpa", referencedColumnName = "id")
    private PortariaTpa portariaTpa;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_portaria_transmissao", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private PortariaTransmissao portariaTransmissao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public Portaria getPortaria() {
        return portaria;
    }

    public void setPortaria(Portaria portaria) {
        this.portaria = portaria;
    }

    public PortariaTpa getPortariaTpa() {
        return portariaTpa;
    }

    public void setPortariaTpa(PortariaTpa portariaTpa) {
        this.portariaTpa = portariaTpa;
    }

    public PortariaTransmissao getPortariaTransmissao() {
        return portariaTransmissao;
    }

    public void setPortariaTransmissao(PortariaTransmissao portariaTransmissao) {
        this.portariaTransmissao = portariaTransmissao;
    }

}
