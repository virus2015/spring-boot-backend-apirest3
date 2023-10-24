package com.mi_bandala.springboot.backend.apirest3.controllers;




import com.mi_bandala.springboot.backend.apirest3.entity.TipoGasto;
import com.mi_bandala.springboot.backend.apirest3.services.ItipoGastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TipoGastosController {

    @Autowired
    ItipoGastosService iTipoGastosService;

    @GetMapping("/tipoGasto")
    public List<TipoGasto> index() {
        return	iTipoGastosService.findAll();
    }

    @GetMapping("/tipoGasto/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        TipoGasto tipoGast = null;
        Map<String,Object> response = new HashMap<>();
        try {
            tipoGast = iTipoGastosService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("mensaje",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(tipoGast ==null) {
            response.put("mensaje","El  id: ".concat(id.toString().concat("No existe el id en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TipoGasto>(tipoGast,HttpStatus.OK);

    }
    @PostMapping("/tipoGasto")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody TipoGasto tipo) {
        TipoGasto tipoGastoNew = null;
        Map<String,Object> response = new HashMap<>();
        try{
            tipoGastoNew = iTipoGastosService.save(tipo);

        }catch(DataAccessException e){
            response.put("mensaje","Error al hacer la insercion a la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje","El registro ha sido creado con exito!");
        response.put("tipo",tipoGastoNew);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/tipoGasto/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody TipoGasto tipo, @PathVariable Long id){
        TipoGasto TipoActual = 	iTipoGastosService.findById(id);
        TipoGasto TipoActualizado = null;
        Map<String,Object> response = new HashMap<>();
        if(TipoActual==null) {
            response.put("mensaje","Error: no se pudo editar, el Tipo Gasto ID: ".concat(id.toString().concat("No existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {
            TipoActual.setDescripcion(tipo.getDescripcion());
            TipoActualizado = iTipoGastosService.save(TipoActual);
        } catch (DataAccessException e) {
            response.put("mensaje","Error al actualizar el Tiendas en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El Tiendas ha sido actualizado con exito");
        response.put("tipoGasto", TipoActualizado);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tipoGasto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            iTipoGastosService.delete(id);

        } catch (DataAccessException e) {
            response.put("mensaje","Error al eliminar el id TipoTienda en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El TipoTienda ha sido eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }



}
