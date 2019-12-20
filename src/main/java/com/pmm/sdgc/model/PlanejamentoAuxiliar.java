package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.IntegerConverter;
import com.pmm.sdgc.converter.LocalTimeConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "planejamento_aux")
public class PlanejamentoAuxiliar implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entrada")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime entrada;

    @Column(name = "saida")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime saida;

    @Column(name = "dia_semana")
    private Integer diaSemana;

    @Column(name = "mes_ano")
    private String mesAno;

    @Column(name = "facultativo")
    private Boolean facultativo;

    @Column(name = "feriado")
    private Boolean feriado;

    @Column(name = "datahora")
    private LocalDateTime dataHora;

    //JOIN    
    @ManyToOne
    @JoinColumn(name = "nomeUser", referencedColumnName = "id")
    private UserLogin userLogin;

    @ManyToOne
    @JoinColumn(name = "id_planejamento", referencedColumnName = "id")
    private Planejamento planejamento;

    @ManyToOne
    @JoinColumn(name = "id_planejamento_desc", referencedColumnName = "id")
    private PlanejamentoDescricao planejamentoDescricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setSaida(LocalTime saida) {
        this.saida = saida;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public Boolean getFacultativo() {
        return facultativo;
    }

    public void setFacultativo(Boolean facultativo) {
        this.facultativo = facultativo;
    }

    public Boolean getFeriado() {
        return feriado;
    }

    public void setFeriado(Boolean feriado) {
        this.feriado = feriado;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public Planejamento getPlanejamento() {
        return planejamento;
    }

    public void setPlanejamento(Planejamento planejamento) {
        this.planejamento = planejamento;
    }

    public PlanejamentoDescricao getPlanejamentoDescricao() {
        return planejamentoDescricao;
    }

    public void setPlanejamentoDescricao(PlanejamentoDescricao planejamentoDescricao) {
        this.planejamentoDescricao = planejamentoDescricao;
    }

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
        final PlanejamentoAuxiliar other = (PlanejamentoAuxiliar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanejamentoAuxiliar{" + "id=" + id + ", entrada=" + entrada + ", saida=" + saida + ", diaSemana=" + diaSemana + ", mesAno=" + mesAno + ", facultativo=" + facultativo + ", feriado=" + feriado + ", dataHora=" + dataHora + ", userLogin=" + userLogin + ", planejamento=" + planejamento + ", planejamentoDescricao=" + planejamentoDescricao + '}';
    }
    
    
}
