package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.LocalDateConverter;
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
 * @author acg
 */
@Entity
@Table(name = "portaria")
public class Portaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_publicacao")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate dataPublicacao;

    @Column(name = "data_inicio")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate dataFim;

    @Column(name = "arquivo_nome")
    private String arquivoNome;

    @Column(name = "ativo")
    private Integer ativo;

    @Column(name = "controle")
    private String controle;

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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getArquivoNome() {
        return arquivoNome;
    }

    public void setArquivoNome(String arquivoNome) {
        this.arquivoNome = arquivoNome;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }


    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }


}
