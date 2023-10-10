package com.mi_bandala.springboot.backend.apirest3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mi_bandala.springboot.backend.apirest3.dao.ITiendaDao;
import com.mi_bandala.springboot.backend.apirest3.entity.Tiendas;

@Service
public class TiendaServiceImpl implements ITiendaService{

	
	@Autowired
	private ITiendaDao tiendasDao;
	
	
	
	@Override
	@Transactional(readOnly =true)
	public List<Tiendas> findAll() {
		
		return (List<Tiendas>) tiendasDao.findAll();
	}

	@Override
	@Transactional
	public Tiendas save(Tiendas tiendas) {
		
		return tiendasDao.save(tiendas);
	}

	@Override
	@Transactional(readOnly =true)
	public Tiendas findById(Long id) {
		
		return tiendasDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		tiendasDao.deleteById(id);
		
	}

}
