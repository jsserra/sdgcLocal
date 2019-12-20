package com.pmm.sdgc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author galan
 */
@Entity
@Table(name = "NSDA")
public class Nsda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_nsda")
    private Long id;
    @Column(name = "cod_nsda")
    private String codigo;
    @Column(name = "referencia_nsda")
    private Integer referencia;
    @Column(name = "cpf_nsda")
    private String cpf;
    @Column(name = "vencimento_nsda")
    private Double vencimento;
    @Column(name = "id_cargo_nsda")
    private String idCargo;
    @Column(name = "nome_cargo_nsda")
    private String nomeCargo;
    @Column(name = "mat_nsda")
    private String mat;
    @Column(name = "nome_nsda")
    private String nome;
    @Column(name = "quant_nsda")
    private Double quant;
    @Column(name = "valor_nsda")
    private Double valor;
    @Column(name = "valorFundeb_nsda")
    private Double valorFundeb;
    @Column(name = "valorBruto_nsda")
    private Double valorBruto;
    @Column(name = "valorCnsg_nsda")
    private Double valorCnsg;
    @Column(name = "cargaHoraria_nsda")
    private String cargaHoraria;
    @Column(name = "nvesc_nsda")
    private String nvesc;
    @Column(name = "regime_nsda")
    private String regime;
    @Column(name = "fonte_nsda")
    private String fonte;
    @Column(name = "mac_massa_nsda")
    private Integer macMassa;
    @Column(name = "mac_ddo_nsda")
    private Double macDdo;
    @Column(name = "mac_emp_nsda")
    private Double macEmp;
    @Column(name = "item_nsda")
    private String item;
    @Column(name = "codgan_nsda")
    private String codGan;
    @Column(name = "vfgan_nsda")
    private String vfGan;
    @Column(name = "nomegan_nsda")
    private String nomeGan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getReferencia() {
        return referencia;
    }

    public void setReferencia(Integer referencia) {
        this.referencia = referencia;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getVencimento() {
        return vencimento;
    }

    public void setVencimento(Double vencimento) {
        this.vencimento = vencimento;
    }

    public String getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(String idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQuant() {
        return quant;
    }

    public void setQuant(Double quant) {
        this.quant = quant;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorFundeb() {
        return valorFundeb;
    }

    public void setValorFundeb(Double valorFundeb) {
        this.valorFundeb = valorFundeb;
    }

    public Double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(Double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public Double getValorCnsg() {
        return valorCnsg;
    }

    public void setValorCnsg(Double valorCnsg) {
        this.valorCnsg = valorCnsg;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getNvesc() {
        return nvesc;
    }

    public void setNvesc(String nvesc) {
        this.nvesc = nvesc;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Integer getMacMassa() {
        return macMassa;
    }

    public void setMacMassa(Integer macMassa) {
        this.macMassa = macMassa;
    }

    public Double getMacDdo() {
        return macDdo;
    }

    public void setMacDdo(Double macDdo) {
        this.macDdo = macDdo;
    }

    public Double getMacEmp() {
        return macEmp;
    }

    public void setMacEmp(Double macEmp) {
        this.macEmp = macEmp;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCodGan() {
        return codGan;
    }

    public void setCodGan(String codGan) {
        this.codGan = codGan;
    }

    public String getVfGan() {
        return vfGan;
    }

    public void setVfGan(String vfGan) {
        this.vfGan = vfGan;
    }

    public String getNomeGan() {
        return nomeGan;
    }

    public void setNomeGan(String nomeGan) {
        this.nomeGan = nomeGan;
    }
    
    
    
    
    
    
    
}
