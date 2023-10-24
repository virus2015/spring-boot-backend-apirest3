package com.mi_bandala.springboot.backend.apirest3.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tipoGasto")
public class tipoGasto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long	idtipoGasto;
    private String descripcion;

}
