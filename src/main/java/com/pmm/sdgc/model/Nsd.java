package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.LocalDateConverter1;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author galan
 */
@Entity
@Table(name = "NSD")
public class Nsd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_nsd")
    private Long id;

    @Column(name = "cod_nsd")
    private String codigo;
    
    @Column(name = "tp_nsd")
    private Integer tp;
    
    @Column(name = "id_lotacao_nsd")
    private String idLotacao;
    
    @Column(name = "nr_nsd")
    private Integer nr;
    
    @Column(name = "referencia_nsd")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate referencia;
    
    @Column(name = "nomeorigem_nsd")
    private String nomeOrigem;
    
    @Column(name = "nomedestino_nsd")
    private String nomeDestino;
    
    @Column(name = "codorigem_nsd")
    private String codOrigem;
    
    @Column(name = "coddestino_nsd")
    private String codDestino;
    
    @Column(name = "item_nsd")
    private String item;
    
    @Column(name = "nomeitem_nsd")
    private String nomeItem;
    
    @Column(name = "valor_nsd")
    private Double valor;
    
    @Column(name = "ordenador_nsd")
    private String ordenador;
    
    @Column(name = "data_nsd")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate data;

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

    public Integer getTp() {
        return tp;
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }

    public String getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(String idLotacao) {
        this.idLotacao = idLotacao;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public LocalDate getReferencia() {
        return referencia;
    }

    public void setReferencia(LocalDate referencia) {
        this.referencia = referencia;
    }

    public String getNomeOrigem() {
        return nomeOrigem;
    }

    public void setNomeOrigem(String nomeOrigem) {
        this.nomeOrigem = nomeOrigem;
    }

    public String getNomeDestino() {
        return nomeDestino;
    }

    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }

    public String getCodOrigem() {
        return codOrigem;
    }

    public void setCodOrigem(String codOrigem) {
        this.codOrigem = codOrigem;
    }

    public String getCodDestino() {
        return codDestino;
    }

    public void setCodDestino(String codDestino) {
        this.codDestino = codDestino;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getOrdenador() {
        return ordenador;
    }

    public void setOrdenador(String ordenador) {
        this.ordenador = ordenador;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Nsd{" + "id=" + id + ", codigo=" + codigo + ", tp=" + tp + ", idLotacao=" + idLotacao + ", nr=" + nr + ", referencia=" + referencia + ", nomeOrigem=" + nomeOrigem + ", nomeDestino=" + nomeDestino + ", codOrigem=" + codOrigem + ", codDestino=" + codDestino + ", item=" + item + ", nomeItem=" + nomeItem + ", valor=" + valor + ", ordenador=" + ordenador + ", data=" + data + '}';
    }
    
}
