package com.mi_bandala.springboot.backend.apirest3.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mi_bandala.springboot.backend.apirest3.entity.Tiendas;
import com.mi_bandala.springboot.backend.apirest3.services.ITiendaService;

//http://localhost:8090/api/tiendas
@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TiendaRestController {
	
	@Autowired
	private ITiendaService tiendaService;
	
	
	//Index metodo que lista todos los Tiendass
		@GetMapping("/tiendas")
		public List<Tiendas> index() {    		
		return	tiendaService.findAll();
		}
		
		//Metodo que busca por id
		@GetMapping("/tiendas/{id}")
		public ResponseEntity<?> show(@PathVariable Long id){
			
			Tiendas Tiendas = null;
		    
			Map<String,Object> response = new HashMap<>();
		    try {
				Tiendas = tiendaService.findById(id);
			} catch (DataAccessException e) {
				response.put("mensaje","Error al realizar la consulta en la base de datos");
				response.put("mensaje",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		    if(Tiendas ==null) {
		    	response.put("mensaje","El Tiendas id: ".concat(id.toString().concat("No existe el id en la base de datosx")));	
		    	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		    }
		    
		    return new ResponseEntity<Tiendas>(Tiendas,HttpStatus.OK);
		}
		
		////////////////////////////////////////////////////////////
		////////////////CREAR UN Tiendas///////////////////////////
		@PostMapping("/tiendas")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<?> create(@RequestBody Tiendas tienda) {
			Tiendas tiendaNew = null;
	        Map<String,Object> response = new HashMap<>();

	        try{
	        	tiendaNew = tiendaService.save(tienda);

	          }catch(DataAccessException e){
	           response.put("mensaje","Error al hacer la insercion a la base de datos");
	           response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	           return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

	       }
	       response.put("mensaje","La tienda ha sido creado con exito!");
	       response.put("tienda",tiendaNew);
	       return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}
		
		
		////////////////////////////////////////////////////////////
		////////////////ACTUALIZAR UN Tiendas//////////////////////
		
		@PutMapping("/tiendas/{id}")
		@ResponseStatus(HttpStatus.CREATED)	
		public ResponseEntity<?> update(@RequestBody Tiendas Tiendas,@PathVariable Long id){
			
		  Tiendas TiendasActual = 	tiendaService.findById(id);
		  Tiendas TiendasActualizado = null;
		  Map<String,Object> response = new HashMap<>();
		  
		  if(TiendasActual==null) {
			  response.put("mensaje","Error: no se pudo editar, el Tiendas ID: ".concat(id.toString().concat("No existe en la base de datos")));
			  return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			  
		  }
		  try {
			  TiendasActual.setNombre(Tiendas.getNombre());
			  TiendasActual.setCiudad(Tiendas.getCiudad());
			  TiendasActual.setColonia(Tiendas.getColonia());
			  TiendasActual.setCalle(Tiendas.getCalle());
			  TiendasActual.setTelefono(Tiendas.getTelefono());
			  TiendasActual.setWeb(Tiendas.getWeb());
			  
			  TiendasActualizado = tiendaService.save(TiendasActualizado);
			  
			
		} catch (DataAccessException e) {
			 response.put("mensaje","Error al actualizar el Tiendas en la base de datos");
			 response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		  response.put("mensaje","El Tiendas ha sido actualizado con exito");
		  response.put("tiendas", TiendasActualizado);
		  
		  return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		  
			
		}
		
		@DeleteMapping("/tiendas/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public ResponseEntity<?> delete(@PathVariable Long id){
			
			Map<String,Object> response = new HashMap<>();
			
			try {
				tiendaService.delete(id);
				
			} catch (DataAccessException e) {
				 response.put("mensaje","Error al eliminar el Tiendas en la base de datos");
				 response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje","El Tiendas ha sido eliminado con exito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			
		}

}
