package com.mi_bandala.springboot.backend.apirest3.services;

import java.util.List;


import com.mi_bandala.springboot.backend.apirest3.entity.Tiendas;

public interface ITiendaService {
	
	
	public List<Tiendas> findAll();	
	public Tiendas save(Tiendas tiendas);
	public Tiendas findById(Long id);		
	public void delete(Long id);	
		
  
	

}
