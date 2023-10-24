package com.mi_bandala.springboot.backend.apirest3.services;


import com.mi_bandala.springboot.backend.apirest3.entity.TipoGasto;

import java.util.List;

public interface ItipoGastosService {

    public List<TipoGasto> findAll();
    public TipoGasto save(TipoGasto tiendas);
    public TipoGasto findById(Long id);
    public void delete(Long id);


}
