package com.mi_bandala.springboot.backend.apirest3.dao;

import org.springframework.data.repository.CrudRepository;

import com.mi_bandala.springboot.backend.apirest3.entity.Tiendas;
import org.springframework.stereotype.Repository;


@Repository
public interface ITiendaDao extends CrudRepository<Tiendas,Long>{

}
