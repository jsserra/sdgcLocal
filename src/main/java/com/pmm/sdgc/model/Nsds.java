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
@Table(name = "NSDS")
public class Nsds implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_nsds")
    private Long id;

    @Column(name = "cod_nsd_nsds")
    private String codigoNsd;
    @Column(name = "referencia_nsds")
    private String referencia;
    @Column(name = "codgan_nsds")
    private String codGan;
    @Column(name = "nomegan_nsds")
    private String nomeGan;
    @Column(name = "pop_nsds")
    private Integer pop;
    @Column(name = "valor_nsds")
    private Double valor;
    @Column(name = "item_nsds")
    private String itemNsds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoNsd() {
        return codigoNsd;
    }

    public void setCodigoNsd(String codigoNsd) {
        this.codigoNsd = codigoNsd;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodGan() {
        return codGan;
    }

    public void setCodGan(String codGan) {
        this.codGan = codGan;
    }

    public String getNomeGan() {
        return nomeGan;
    }

    public void setNomeGan(String nomeGan) {
        this.nomeGan = nomeGan;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getItemNsds() {
        return itemNsds;
    }

    public void setItemNsds(String itemNsds) {
        this.itemNsds = itemNsds;
    }


}
