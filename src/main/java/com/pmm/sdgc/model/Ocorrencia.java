package com.pmm.sdgc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ocorrencia")
public class Ocorrencia implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "entrada")
    private LocalDateTime entrada;

    @Column(name = "saida")
    private LocalDateTime saida;

    @Column(name = "obs")
    private String obs;

    @Column(name = "mesAno")
    private String mesAno;

    @Column(name = "numeroDias")
    private Integer numeroDias;

    @Column(name = "datahora")
    private LocalDateTime dataHora;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "controle")
    private String controle;

    //JOIN    
    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "id_lotacao_sub", referencedColumnName = "id_lotacao_sub")
    @NotFound(action = NotFoundAction.IGNORE)
    private LotacaoSub lotacaoSub;

    @ManyToOne
    @JoinColumn(name = "id_oco_desc", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private OcorrenciaDesc ocorrenciaDesc;

    @ManyToOne
    @JoinColumn(name = "userlog", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin userLogin;

    //GET SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

    public LotacaoSub getLotacaoSub() {
        return lotacaoSub;
    }

    public void setLotacaoSub(LotacaoSub lotacaoSub) {
        this.lotacaoSub = lotacaoSub;
    }

    public OcorrenciaDesc getOcorrenciaDesc() {
        return ocorrenciaDesc;
    }

    public void setOcorrenciaDesc(OcorrenciaDesc ocorrenciaDesc) {
        this.ocorrenciaDesc = ocorrenciaDesc;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" + "id=" + id + ", entrada=" + entrada + ", saida=" + saida + ", obs=" + obs + ", mesAno=" + mesAno + ", numeroDias=" + numeroDias + ", dataHora=" + dataHora + ", ativo=" + ativo + ", controle=" + controle + ", funcional=" + funcional + ", lotacaoSub=" + lotacaoSub + ", ocorrenciaDesc=" + ocorrenciaDesc + ", userLogin=" + userLogin + '}';
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
        final Ocorrencia other = (Ocorrencia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
