/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Nsd;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author setinf
 */
public class ModelBuscaNSDWs {
    
    private String codigo;
    private LocalDate referencia;
    private String nomeOrigem;
    private String nomeDestino;
    private LocalDate data;
    

    
    public ModelBuscaNSDWs(String codigo, LocalDate referencia, String nomeOrigem, String nomeDestino, LocalDate data){
        this.codigo = codigo;
        this.referencia = referencia;
        this.nomeOrigem = nomeOrigem;
        this.nomeDestino = nomeDestino;
        this.data = data;
    }

    public static List<ModelBuscaNSDWs> toModelBuscaNSDWs(List<Nsd> nsds) {
        List<ModelBuscaNSDWs> mew = new ArrayList();
        
        for (Nsd n : nsds) {
            
            ModelBuscaNSDWs ew = new ModelBuscaNSDWs(
                    n.getCodigo(),
                    n.getReferencia(),
                    n.getNomeOrigem(),
                    n.getNomeDestino(),
                    n.getData()
            );
            
            mew.add(ew);
        }
        return mew;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

   
    
    
}
    
    
