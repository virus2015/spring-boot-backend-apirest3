package com.mi_bandala.springboot.backend.apirest3.dao;


import com.mi_bandala.springboot.backend.apirest3.entity.TipoGasto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItipoGasto extends CrudRepository<TipoGasto,Long> {


}


