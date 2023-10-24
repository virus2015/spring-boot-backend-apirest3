package com.mi_bandala.springboot.backend.apirest3.services;


import com.mi_bandala.springboot.backend.apirest3.dao.ItipoGasto;
import com.mi_bandala.springboot.backend.apirest3.entity.TipoGasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TipoGastosServiceImpl implements ItipoGastosService{

    @Autowired
    ItipoGasto iTipoGastoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<TipoGasto> findAll() {
        return (List<TipoGasto>) iTipoGastoDAO.findAll();
    }

    @Override
    @Transactional
    public TipoGasto save(TipoGasto tiendas) {
        return iTipoGastoDAO.save(tiendas);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoGasto findById(Long id) {
        return iTipoGastoDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iTipoGastoDAO.deleteById(id);
    }
}
