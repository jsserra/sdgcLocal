package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.BooleanConverter;
import com.pmm.sdgc.converter.LocalDateConverter;
import com.pmm.sdgc.converter.LocalDateConverter1;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author dreges
 */
@Entity
@Table(name = "historico_funcional")
public class Funcional implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "data_admissao")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate dataAdmissao;

    @Column(name = "permutado")
    private String permutado;

    @Column(name = "acumula")
    @Convert(converter = BooleanConverter.class)
    private Boolean acumula;

    @Column(name = "acumula_qual")
    private String acumulaQual;

    @Column(name = "acumula_onde")
    private String acumulaOnde;

    @Column(name = "cessao")
    @Convert(converter = BooleanConverter.class)
    private Boolean cessao;

    @Column(name = "cessao_sentido")
    private String cessaoSentido;
    
    @Column(name = "data_confi")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dataConfi;
    
    @Column(name = "controle")
    private Character controle;
    
    @Transient
    private Boolean podeCriarUsuario;

    @ManyToOne
    @JoinColumn(name = "id_info", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Pessoa pessoa;


    @ManyToOne
    @JoinColumn(name = "id_vinculo", referencedColumnName = "id_vinculo")
    private Vinculo vinculo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_cargo_com", referencedColumnName = "id", nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private CargoCom cargoCom;

    @ManyToOne
    @JoinColumn(name = "id_regime", referencedColumnName = "id_regime")
    @NotFound(action = NotFoundAction.IGNORE)
    private Regime regime;

    @ManyToOne
    @JoinColumn(name = "id_cargo", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "id_situacao", referencedColumnName = "id_situacao")
    @NotFound(action = NotFoundAction.IGNORE)
    private Situacao situacao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getPermutado() {
        return permutado;
    }

    public void setPermutado(String permutado) {
        this.permutado = permutado;
    }

    public Boolean getAcumula() {
        return acumula;
    }

    public void setAcumula(Boolean acumula) {
        this.acumula = acumula;
    }

    public String getAcumulaQual() {
        return acumulaQual;
    }

    public void setAcumulaQual(String acumulaQual) {
        this.acumulaQual = acumulaQual;
    }

    public String getAcumulaOnde() {
        return acumulaOnde;
    }

    public void setAcumulaOnde(String acumulaOnde) {
        this.acumulaOnde = acumulaOnde;
    }

    public Boolean getCessao() {
        return cessao;
    }

    public void setCessao(Boolean cessao) {
        this.cessao = cessao;
    }

    public String getCessaoSentido() {
        return cessaoSentido;
    }

    public void setCessaoSentido(String cessaoSentido) {
        this.cessaoSentido = cessaoSentido;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public CargoCom getCargoCom() {
        return cargoCom;
    }

    public void setCargoCom(CargoCom cargoCom) {
        this.cargoCom = cargoCom;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getDataConfi() {
        return dataConfi;
    }

    public void setDataConfi(LocalDate dataConfi) {
        this.dataConfi = dataConfi;
    }

    public Character getControle() {
        return controle;
    }

    public void setControle(Character controle) {
        this.controle = controle;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Boolean getPodeCriarUsuario() {
        return podeCriarUsuario;
    }

    public void setPodeCriarUsuario(Boolean podeCriarUsuario) {
        this.podeCriarUsuario = podeCriarUsuario;
    }

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
        final Funcional other = (Funcional) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcional{" + "id=" + id + ", matricula=" + matricula + ", cargo=" + cargo + '}';
    }
    
    
    

}
