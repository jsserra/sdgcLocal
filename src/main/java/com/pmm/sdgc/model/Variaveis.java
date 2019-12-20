/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import com.pmm.sdgc.enumeration.VariaveisStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author ajuliano
 */
@Entity
@Table (name = "variaveis")
public class Variaveis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "id_variaveis_desc", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private VariaveisDesc variaveisDesc;
    
    /* @author dreges */
    @ManyToOne
    @JoinColumn(name = "id_lotacao_sub", referencedColumnName = "id_lotacao_sub")
    @NotFound(action = NotFoundAction.IGNORE)
    private LotacaoSub lotacaoSub;
    /* ---- */

    @Column(name = "quantidade")
    private Double quantidade;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "periodoFolha")
    private LocalDate periodoFolha;

    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "userLogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin login;

    @Column(name = "fechado")
    private Boolean fechado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VariaveisStatus status;

    @Column(name = "ativo")
    private Boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public VariaveisDesc getVariaveisDesc() {
        return variaveisDesc;
    }

    public void setVariaveisDesc(VariaveisDesc variaveisDesc) {
        this.variaveisDesc = variaveisDesc;
    }

    public LotacaoSub getLotacaoSub() {
        return lotacaoSub;
    }

    public void setLotacaoSub(LotacaoSub lotacaoSub) {
        this.lotacaoSub = lotacaoSub;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getPeriodoFolha() {
        return periodoFolha;
    }

    public void setPeriodoFolha(LocalDate periodoFolha) {
        this.periodoFolha = periodoFolha;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public UserLogin getLogin() {
        return login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

    public Boolean getFechado() {
        return fechado;
    }

    public void setFechado(Boolean fechado) {
        this.fechado = fechado;
    }

    public VariaveisStatus getStatus() {
        return status;
    }

    public void setStatus(VariaveisStatus status) {
        this.status = status;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Variaveis other = (Variaveis) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
