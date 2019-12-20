/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.LocalDateConverter;
import com.pmm.sdgc.converter.LocalDateConverter1;
import com.pmm.sdgc.converter.LocalDateTimeAttributeConverter;
import com.pmm.sdgc.converter.LocalTimeConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Convert;
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
 * @author setinf
 */
@Entity
@Table(name = "marcacaoponto")
public class MarcacaoPonto implements Serializable {

    private static final long serialVersionUID = 1L;

    //VAR    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idinfopessoal", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Pessoa pessoa;

    @Column(name = "idponto")
    private Long idPonto;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "orgao")
    private int orgao;

    @Column(name = "dataponto")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate dataPonto;

    /*-------------------------------------------------*/
    @Column(name = "entrada1")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime entrada1;

    @Column(name = "justificativaentrada1")
    private String justificativaEntrada1;

    @Column(name = "saida1")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime saida1;

    @Column(name = "justificativasaida1")
    private String justificativaSaida1;

    /*-------------------------------------------------*/
    @Column(name = "entrada2")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime entrada2;

    @Column(name = "justificativaentrada2")
    private String justificativaEntrada2;

    @Column(name = "saida2")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime saida2;

    @Column(name = "justificativasaida2")
    private String justificativaSaida2;

    /*-------------------------------------------------*/
    @Column(name = "entrada3")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime entrada3;

    @Column(name = "justificativaentrada3")
    private String justificativaEntrada3;

    @Column(name = "saida3")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime saida3;

    @Column(name = "justificativasaida3")
    private String justificativaSaida3;

    /*-------------------------------------------------*/
    @Column(name = "entrada4")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime entrada4;

    @Column(name = "justificativaentrada4")
    private String justificativaEntrada4;

    @Column(name = "saida4")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime saida4;

    @Column(name = "justificativasaida4")
    private String justificativaSaida4;

    /*-------------------------------------------------*/
    @Column(name = "hstrabalhadas")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime hsTrabalhadas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(Long idPonto) {
        this.idPonto = idPonto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOrgao() {
        return orgao;
    }

    public void setOrgao(int orgao) {
        this.orgao = orgao;
    }

    public LocalDate getDataPonto() {
        return dataPonto;
    }

    public void setDataPonto(LocalDate dataPonto) {
        this.dataPonto = dataPonto;
    }

    public LocalTime getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(LocalTime entrada1) {
        this.entrada1 = entrada1;
    }

    public String getJustificativaEntrada1() {
        return justificativaEntrada1;
    }

    public void setJustificativaEntrada1(String justificativaEntrada1) {
        this.justificativaEntrada1 = justificativaEntrada1;
    }

    public LocalTime getSaida1() {
        return saida1;
    }

    public void setSaida1(LocalTime saida1) {
        this.saida1 = saida1;
    }

    public String getJustificativaSaida1() {
        return justificativaSaida1;
    }

    public void setJustificativaSaida1(String justificativaSaida1) {
        this.justificativaSaida1 = justificativaSaida1;
    }

    public LocalTime getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(LocalTime entrada2) {
        this.entrada2 = entrada2;
    }

    public String getJustificativaEntrada2() {
        return justificativaEntrada2;
    }

    public void setJustificativaEntrada2(String justificativaEntrada2) {
        this.justificativaEntrada2 = justificativaEntrada2;
    }

    public LocalTime getSaida2() {
        return saida2;
    }

    public void setSaida2(LocalTime saida2) {
        this.saida2 = saida2;
    }

    public String getJustificativaSaida2() {
        return justificativaSaida2;
    }

    public void setJustificativaSaida2(String justificativaSaida2) {
        this.justificativaSaida2 = justificativaSaida2;
    }

    public LocalTime getEntrada3() {
        return entrada3;
    }

    public void setEntrada3(LocalTime entrada3) {
        this.entrada3 = entrada3;
    }

    public String getJustificativaEntrada3() {
        return justificativaEntrada3;
    }

    public void setJustificativaEntrada3(String justificativaEntrada3) {
        this.justificativaEntrada3 = justificativaEntrada3;
    }

    public LocalTime getSaida3() {
        return saida3;
    }

    public void setSaida3(LocalTime saida3) {
        this.saida3 = saida3;
    }

    public String getJustificativaSaida3() {
        return justificativaSaida3;
    }

    public void setJustificativaSaida3(String justificativaSaida3) {
        this.justificativaSaida3 = justificativaSaida3;
    }

    public LocalTime getEntrada4() {
        return entrada4;
    }

    public void setEntrada4(LocalTime entrada4) {
        this.entrada4 = entrada4;
    }

    public String getJustificativaEntrada4() {
        return justificativaEntrada4;
    }

    public void setJustificativaEntrada4(String justificativaEntrada4) {
        this.justificativaEntrada4 = justificativaEntrada4;
    }

    public LocalTime getSaida4() {
        return saida4;
    }

    public void setSaida4(LocalTime saida4) {
        this.saida4 = saida4;
    }

    public String getJustificativaSaida4() {
        return justificativaSaida4;
    }

    public void setJustificativaSaida4(String justificativaSaida4) {
        this.justificativaSaida4 = justificativaSaida4;
    }

    public LocalDateTime getHsTrabalhadas() {
        return hsTrabalhadas;
    }

    public void setHsTrabalhadas(LocalDateTime hsTrabalhadas) {
        this.hsTrabalhadas = hsTrabalhadas;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
