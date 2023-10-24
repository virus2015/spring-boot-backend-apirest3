package com.mi_bandala.springboot.backend.apirest3.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tipo_Gasto")
public class TipoGasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long	idtipoGasto;
    private String descripcion;

    public TipoGasto(Long idtipoGasto, String descripcion) {
        this.idtipoGasto = idtipoGasto;
        this.descripcion = descripcion;
    }

    public TipoGasto() {
    }

    public Long getIdtipoGasto() {
        return idtipoGasto;
    }

    public void setIdtipoGasto(Long idtipoGasto) {
        this.idtipoGasto = idtipoGasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
