package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.LocalDateConverter;
import com.pmm.sdgc.converter.LocalDateConverter1;
import java.io.Serializable;
import java.time.LocalDate;
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
 * @author dreges
 */
@Entity
@Table(name = "info_pessoal")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
//VAR    
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nascimento")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate nascimento;

    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "sexo")
    private String sexo;

    @Column(name = "endereco")
    private String endereco;
    
    @Column(name = "bairro")
    private String bairro;
            
    @Column(name = "municipio")
    private String municipio;
    
    @Column(name = "estado")
    private String estado;
            
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "celular")
    private String celular;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "deficiencia")
    private String deficiencia;
    
    @Column(name = "outros")
    private String cursoOutros;
    
    @Column(name = "controle")
    private String controle;
//JOIN    
    @ManyToOne
    @JoinColumn(name = "id_curso_grau_max", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private CursoGrauMax cursoMax;

   /* @ManyToOne
    @JoinColumn(name = "curso1_info", referencedColumnName = "id_curso")
    @NotFound(action = NotFoundAction.IGNORE)
    private Curso curso1;

    @ManyToOne
    @JoinColumn(name = "curso2_info", referencedColumnName = "id_curso")
    @NotFound(action = NotFoundAction.IGNORE)
    private Curso curso2;

    @ManyToOne
    @JoinColumn(name = "curso3_info", referencedColumnName = "id_curso")
    @NotFound(action = NotFoundAction.IGNORE)
    private Curso curso3;

    @ManyToOne
    @JoinColumn(name = "curso4_info", referencedColumnName = "id_curso")
    @NotFound(action = NotFoundAction.IGNORE)
    private Curso curso4;

    @ManyToOne
    @JoinColumn(name = "curso5_info", referencedColumnName = "id_curso")
    @NotFound(action = NotFoundAction.IGNORE)
    private Curso curso5;*/
    //GET SET

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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public String getCursoOutros() {
        return cursoOutros;
    }

    public void setCursoOutros(String cursoOutros) {
        this.cursoOutros = cursoOutros;
    }

    public CursoGrauMax getCursoMax() {
        return cursoMax;
    }

    public void setCursoMax(CursoGrauMax cursoMax) {
        this.cursoMax = cursoMax;
    }  

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    
}
