package init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.VueloDto;
import init.service.VueloService;

@CrossOrigin("*")
@RestController
public class VueloController {
	
	VueloService service;
	
	public VueloController(VueloService service) {
		this.service = service;
	}
	
	@GetMapping(value="vuelo/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VueloDto> buscarPorId(@PathVariable int id) {
		System.out.println("Desde vuelocontroller: "+id);
		VueloDto vuelo = service.getVuelo(id);
		if(vuelo != null) {
			return new ResponseEntity<>(vuelo,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.CONFLICT);
	}
	
	@GetMapping(value="vuelos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VueloDto>> buscarPorDestinoPlazas(@RequestParam String destino, @RequestParam int plazas) {
		List<VueloDto> vuelos = service.getVuelos(destino, plazas);
		if(vuelos.size() == 0) {
			return new ResponseEntity<>(null,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(vuelos,HttpStatus.OK);
	}
	
	/*
	 * Actualización de plazas a partir de identificador de vuelo y plazas reservadas 
	(securizado de maneara que solo usuarios de un determinado rol puedan utilizarlo) 
	 */
	@GetMapping(value="vuelosec")
	public ResponseEntity<Void> updatePlazas(@RequestParam int idVuelo, @RequestParam int plazas){
		System.out.println("Entra en vuelo Controller vuelosec");
		System.out.println("idVuelo: "+idVuelo);
		System.out.println("idplazas: "+plazas);
		
		if(service.updatePlazas(idVuelo, plazas)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	

	
}
