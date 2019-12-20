/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.DataFrequencia;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelDataFrequenciaWs {

    private LocalDate dataFrequencia;
    private String dataFrequenciaMesAno;
    private Integer id;

    public LocalDate getDataFrequencia() {
        return dataFrequencia;
    }

    public void setDataFrequencia(LocalDate dataFrequencia) {
        this.dataFrequencia = dataFrequencia;
    }

    public String getDataFrequenciaMesAno() {
        return dataFrequenciaMesAno;
    }

    public void setDataFrequenciaMesAno(String dataFrequenciaMesAno) {
        this.dataFrequenciaMesAno = dataFrequenciaMesAno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ModelDataFrequenciaWs> converter(List<DataFrequencia> datas) {
        List<ModelDataFrequenciaWs> datasWs = new ArrayList();

        for (DataFrequencia d : datas) {
            ModelDataFrequenciaWs md = new ModelDataFrequenciaWs();
            md.setDataFrequencia(d.getDataFrequencia());
            md.setDataFrequenciaMesAno(d.getDataFrequencia().format(DateTimeFormatter.ofPattern("MM-yyyy")));
            md.setId(d.getId());
            datasWs.add(md);
        }

        return datasWs;
    }

}
