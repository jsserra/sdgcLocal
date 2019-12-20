package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.CargoRelacao;

/**
 *
 * @author jrdutra
 */
public class ModelCargoGeralWs {

    private String IdCargoGeral;
    private String nome;
    private CargoRelacao cargoRelacao;

    public String getIdCargoGeral() {
        return IdCargoGeral;
    }

    public void setIdCargoGeral(String IdCargoGeral) {
        this.IdCargoGeral = IdCargoGeral;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CargoRelacao getCargoRelacao() {
        return cargoRelacao;
    }

    public void setCargoRelacao(CargoRelacao cargoRelacao) {
        this.cargoRelacao = cargoRelacao;
    }
    
    
    
}
